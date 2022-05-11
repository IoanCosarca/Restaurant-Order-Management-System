package PresentationLayer;

import BusinessLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Controller implements ActionListener {
    private UserBLL userBLL;
    private Login login;
    private DeliveryService deliveryService;
    private Register register;
    private Administrator administrator;
    private Employee employee;
    private Client client;

    private ArrayList<String> columns;

    public Controller(UserBLL userBLL, Login login, DeliveryService deliveryService)
    {
        this.userBLL = userBLL;
        this.login = login;
        this.deliveryService = deliveryService;

        columns = new ArrayList<>();
        for (Field f : MenuItem.class.getDeclaredFields()) {
            columns.add(f.getName());
        }
        register = new Register();
        administrator = new Administrator(columns);
        employee = new Employee();
        client = new Client();

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
                register.showPopUp("User already exists!");
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
                login.showPopUp("Bad username/password");
            } else {
                if (user.getType().equals("ADMINISTRATOR")) {
                    administrator = new Administrator(columns);
                    administrator.setVisible(true);
                    administrator.ImportProducts.addActionListener(this);
                    administrator.AddProduct.addActionListener(this);
                    administrator.DeleteProduct.addActionListener(this);
                    administrator.ModifyProduct.addActionListener(this);
                    administrator.CreateProduct.addActionListener(this);
                    administrator.GenerateReports.addActionListener(this);
                    administrator.LogOut.addActionListener(this);
                }
                if (user.getType().equals("EMPLOYEE")) {
                    employee = new Employee();
                }
                if (user.getType().equals("CLIENT")) {
                    client = new Client();
                    client.setVisible(true);
                    client.Search.addActionListener(this);
                    client.CreateOrder.addActionListener(this);
                    client.AddProduct.addActionListener(this);
                    client.RemoveProduct.addActionListener(this);
                    client.LogOut.addActionListener(this);
                }
                login.setVisible(false);
            }
        }

        if (e.getSource() == administrator.ImportProducts) {
            administrator.addRows(deliveryService.importProducts());
        }

        if (e.getSource() == administrator.AddProduct)
        {
            String Title = administrator.TitleField.getText();
            double Rating = Double.parseDouble(administrator.RatingField.getText());
            double Calories = Double.parseDouble(administrator.CaloriesField.getText());
            double Protein = Double.parseDouble(administrator.ProteinField.getText());
            double Fat = Double.parseDouble(administrator.FatField.getText());
            double Sodium = Double.parseDouble(administrator.SodiumField.getText());
            double Price = Double.parseDouble(administrator.PriceField.getText());
            BaseProduct baseProduct = new BaseProduct(Title, Rating, Calories, Protein, Fat, Sodium,Price);
            deliveryService.addProduct(baseProduct);
        }

        if (e.getSource() == administrator.DeleteProduct) {
            deliveryService.deleteProduct();
        }

        if (e.getSource() == administrator.ModifyProduct) {
            deliveryService.modifyProduct();
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
}
