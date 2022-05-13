package PresentationLayer;

import BusinessLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener {
    private UserBLL userBLL;
    private Login login;
    private MessagePopUp message;
    private DeliveryService deliveryService;
    private Register register;
    private Administrator administrator;
    private Employee employee;
    private Client client;

    private ArrayList<String> columns;
    private List<MenuItem> menuItems;

    public Controller(UserBLL userBLL, Login login, MessagePopUp message, DeliveryService deliveryService)
    {
        this.userBLL = userBLL;
        this.login = login;
        this.message = message;
        this.deliveryService = deliveryService;

        columns = new ArrayList<>();
        for (Field f : MenuItem.class.getDeclaredFields()) {
            columns.add(f.getName());
        }
        menuItems = new ArrayList<>();
        register = new Register();
        administrator = new Administrator(columns);
        employee = new Employee();
        client = new Client(columns, menuItems);

        login.Login.addActionListener(this);
        login.Register.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login.Register)
        {
            login.setVisible(false);
            register = new Register();
            register.setVisible(true);
            register.Register.addActionListener(this);
            register.GoBack.addActionListener(this);
        }

        if (e.getSource() == register.Register)
        {
            User newUser = new User(register.UserName.getText(), register.Password.getText(), register.UserTypes.getSelectedItem().toString());
            if (!userBLL.addUser(newUser)) {
                message.showPopUp("User already exists!");
            }
            register.UserName.setText(null);
            register.Password.setText(null);
        }

        if (e.getSource() == register.GoBack)
        {
            register.setVisible(false);
            login.setVisible(true);
            login.UserName.setText(null);
            login.Password.setText(null);
        }

        if (e.getSource() == login.Login) {
            User user = new User(login.UserName.getText(), login.Password.getText());
            user.setType(userBLL.verifyAndFindType(user));
            if (user.getType() == null) {
                message.showPopUp("Bad username/password");
            } else {
                if (user.getType().equals("ADMINISTRATOR")) {
                    administrator = new Administrator(columns);
                    administrator.setVisible(true);
                    administrator.ImportProducts.addActionListener(this);
                    administrator.AddProduct.addActionListener(this);
                    administrator.AddProduct.setEnabled(false);
                    administrator.DeleteProduct.addActionListener(this);
                    administrator.DeleteProduct.setEnabled(false);
                    administrator.ModifyProduct.addActionListener(this);
                    administrator.ModifyProduct.setEnabled(false);
                    administrator.CreateProduct.addActionListener(this);
                    administrator.CreateProduct.setEnabled(false);
                    administrator.GenerateReports.addActionListener(this);
                    administrator.GenerateReports.setEnabled(false);
                    administrator.LogOut.addActionListener(this);
                    administrator.Add.addActionListener(this);
                    administrator.Remove.addActionListener(this);
                }
                if (user.getType().equals("EMPLOYEE")) {
                    employee = new Employee();
                }
                if (user.getType().equals("CLIENT")) {
                    if (menuItems.size() != 0) {
                        client = new Client(columns, menuItems);
                        client.setVisible(true);
                        client.Search.addActionListener(this);
                        client.CreateOrder.addActionListener(this);
                        client.AddProduct.addActionListener(this);
                        client.RemoveProduct.addActionListener(this);
                        client.LogOut.addActionListener(this);
                    }
                    else {
                        message.showPopUp("No import was made");
                    }
                }
                login.setVisible(false);
            }
        }

        if (e.getSource() == administrator.ImportProducts)
        {
            administrator.AddProduct.setEnabled(true);
            administrator.DeleteProduct.setEnabled(true);
            administrator.ModifyProduct.setEnabled(true);
            administrator.CreateProduct.setEnabled(true);
            administrator.GenerateReports.setEnabled(true);
            menuItems = deliveryService.importProducts();
            if (administrator.model.getRowCount() == 0) {
                administrator.addRows(menuItems);
            }
        }

        if (e.getSource() == administrator.AddProduct)
        {
            try
            {
                BaseProduct baseProduct = computeProduct();
                if (deliveryService.addProduct(baseProduct)) {
                    administrator.addRow(baseProduct);
                }
                else {
                    message.showPopUp("Product already exists");
                }
                administrator.Refresh();
            }
            catch (NumberFormatException ex) {
                message.showPopUp("Invalid Product Details!");
            }
        }

        if (e.getSource() == administrator.DeleteProduct)
        {
            int index = administrator.table.getSelectedRow();
            deliveryService.deleteProduct(index);
            administrator.removeRow(index);
        }

        if (e.getSource() == administrator.ModifyProduct)
        {
            int index = administrator.table.getSelectedRow();
            BaseProduct baseProduct = computeProduct();
            deliveryService.modifyProduct(index, baseProduct);
            administrator.updateRow(index, baseProduct);
        }

        if (e.getSource() == administrator.CreateProduct) {
            deliveryService.createProduct();
        }

        if (e.getSource() == administrator.GenerateReports) {
            deliveryService.generateReports();
        }

        if (e.getSource() == administrator.LogOut)
        {
            administrator.setVisible(false);
            login.setVisible(true);
            login.UserName.setText(null);
            login.Password.setText(null);
        }

        if (e.getSource() == client.Search) {
            deliveryService.searchProduct();
        }

        if (e.getSource() == client.CreateOrder) {
            deliveryService.createOrder();
        }

        if (e.getSource() == client.LogOut)
        {
            client.setVisible(false);
            login.setVisible(true);
            login.UserName.setText(null);
            login.Password.setText(null);
        }
    }

    private BaseProduct computeProduct()
    {
        String Title    = administrator.TitleField.getText();
        double Rating   = Double.parseDouble(administrator.RatingField.getText());
        double Calories = Double.parseDouble(administrator.CaloriesField.getText());
        double Protein  = Double.parseDouble(administrator.ProteinField.getText());
        double Fat      = Double.parseDouble(administrator.FatField.getText());
        double Sodium   = Double.parseDouble(administrator.SodiumField.getText());
        double Price    = Double.parseDouble(administrator.PriceField.getText());
        return new BaseProduct(Title, Rating, Calories, Protein, Fat, Sodium, Price);
    }
}
