package PresentationLayer;

import BusinessLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements ActionListener {
    private final UserBLL userBLL;
    private final Login login;
    private final MessagePopUp message;
    private final DeliveryService deliveryService;
    private Register register;
    private Administrator administrator;
    private Employee employee;
    private Client client;
    private User CurrentUser;
    private List<MenuItem> menuItems;
    private List<MenuItem> results;
    private CompositeProduct compositeProduct;
    private List<MenuItem> OrderComponents;

    public Controller(UserBLL userBLL, Login login, MessagePopUp message, DeliveryService deliveryService)
    {
        this.userBLL            = userBLL;
        this.login              = login;
        this.message            = message;
        this.deliveryService    = deliveryService;
        register = new Register();
        administrator = new Administrator();
        employee = new Employee(deliveryService.getOrderHashMap());
        client = new Client(new ArrayList<>());
        CurrentUser = new User();
        menuItems = new ArrayList<>();
        compositeProduct = new CompositeProduct();
        OrderComponents = new ArrayList<>();
        deliveryService.registerObserver(employee);
        deliveryService.importOrderDetails();
        login.Login.addActionListener(this);
        login.CAccount.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        int iProductsAdmin = administrator.table.getSelectedRow();
        if (e.getSource() == login.CAccount)
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
            login.Refresh();
        }

        if (e.getSource() == login.Login) {
            CurrentUser = userBLL.getUser(login.UserName.getText(), login.Password.getText());
            if (CurrentUser == null) {
                message.showPopUp("Bad username/password");
            } else {
                if (CurrentUser.getType().equals("ADMINISTRATOR")) {
                    administrator = new Administrator();
                    administrator.setVisible(true);
                    administrator.ImportProducts.addActionListener(this);
                    administrator.AddProduct.addActionListener(this);
                    administrator.DeleteProduct.addActionListener(this);
                    administrator.ModifyProduct.addActionListener(this);
                    administrator.CreateProduct.addActionListener(this);
                    administrator.GenerateReports.addActionListener(this);
                    administrator.LogOut.addActionListener(this);
                    administrator.Add.addActionListener(this);
                    administrator.Remove.addActionListener(this);
                    administrator.SwitchButtons(false);
                    login.setVisible(false);
                }
                if (CurrentUser.getType().equals("EMPLOYEE")) {
                    employee = new Employee(deliveryService.getOrderHashMap());
                    employee.setVisible(true);
                }
                if (CurrentUser.getType().equals("CLIENT")) {
                    if (menuItems.size() != 0) {
                        results = menuItems;
                        client = new Client(deliveryService.importProducts());
                        client.setVisible(true);
                        client.Search.addActionListener(this);
                        client.CreateOrder.addActionListener(this);
                        client.AddProduct.addActionListener(this);
                        client.RemoveProduct.addActionListener(this);
                        client.LogOut.addActionListener(this);
                        login.setVisible(false);
                    }
                    else {
                        message.showPopUp("No import was made");
                    }
                }
            }
        }

        if (e.getSource() == administrator.ImportProducts)
        {
            administrator.SwitchButtons(true);
            if (administrator.model.getRowCount() == 0)
            {
                menuItems = deliveryService.importProducts();
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

        if (e.getSource() == administrator.DeleteProduct) {
            if (iProductsAdmin != -1)
            {
                deliveryService.deleteProduct(iProductsAdmin);
                administrator.removeRow(iProductsAdmin);
                administrator.Refresh();
            }
        }

        if (e.getSource() == administrator.ModifyProduct)
        {
            MenuItem menuItem = computeProduct();
            deliveryService.modifyProduct(iProductsAdmin, menuItem);
            administrator.updateRow(iProductsAdmin, menuItem);
        }

        if (e.getSource() == administrator.Add)
        {
            BaseProduct baseProduct = (BaseProduct) deliveryService.importProducts().get(iProductsAdmin);
            compositeProduct.add(baseProduct);
            administrator.addComponent(baseProduct);
        }

        if (e.getSource() == administrator.Remove)
        {
            int index = administrator.tableCompose.getSelectedRow();
            if (administrator.modelCompose.getRowCount() != 0)
            {
                BaseProduct baseProduct = compositeProduct.getValueAt(index);
                compositeProduct.remove(baseProduct);
                administrator.removeComponent(index);
            }
        }

        if (e.getSource() == administrator.CreateProduct)
        {
            if (!Objects.equals(administrator.TitleField.getText(),""))
            {
                compositeProduct.setTitle(administrator.TitleField.getText());
                if (deliveryService.createProduct(compositeProduct))
                {
                    administrator.addRow(compositeProduct);
                    compositeProduct.setTimesOrdered();
                    administrator.RefreshCompose();
                    compositeProduct = new CompositeProduct();
                }
                else {
                    message.showPopUp("Product already exists");
                }
            }
            else {
                message.showPopUp("Type a name for the new product");
            }
        }

        if (e.getSource() == administrator.GenerateReports)
        {
            try
            {
                int StartHour       = Integer.parseInt(administrator.StartField.getText());
                int EndHour         = Integer.parseInt(administrator.EndField.getText());
                int NumberOfTimes   = Integer.parseInt(administrator.NumberField.getText());
                double Value        = Double.parseDouble(administrator.ValueField.getText());
                SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = myFormat.parse(administrator.DateField.getText());
                deliveryService.generateReport(StartHour, EndHour);
                deliveryService.generateReport(NumberOfTimes);
                deliveryService.generateReport(NumberOfTimes, Value);
                deliveryService.generateReport(date);
            }
            catch (Exception ex) {
                message.showPopUp("Invalid Parameters to generate reports");
            }
        }

        if (e.getSource() == administrator.LogOut)
        {
            administrator.setVisible(false);
            login.Refresh();
        }

        if (e.getSource() == client.Search)
        {
            String keyword = client.SearchTitle.getText();
            double rating, calories, protein, fat, sodium, price;
            if (!Objects.equals(client.SearchRating.getText(),"")) {
                rating = Double.parseDouble(client.SearchRating.getText());
            }
            else rating = -1;
            if (!Objects.equals(client.SearchCalories.getText(),""))
                calories = Double.parseDouble(client.SearchCalories.getText());
            else calories = -1;
            if (!Objects.equals(client.SearchProtein.getText(),""))
                protein = Double.parseDouble(client.SearchProtein.getText());
            else protein = -1;
            if (!Objects.equals(client.SearchFat.getText(),""))
                fat = Double.parseDouble(client.SearchFat.getText());
            else fat = -1;
            if (!Objects.equals(client.SearchSodium.getText(),""))
                sodium = Double.parseDouble(client.SearchSodium.getText());
            else sodium = -1;
            if (!Objects.equals(client.SearchPrice.getText(),""))
                price = Double.parseDouble(client.SearchPrice.getText());
            else price = -1;
            results = deliveryService.searchProducts(keyword, rating, calories, protein, fat, sodium, price);
            client.filterTable(results);
        }

        if (e.getSource() == client.AddProduct)
        {
            int iClient = client.MenuTable.getSelectedRow();
            MenuItem menuItem = results.get(iClient);
            OrderComponents.add(menuItem);
            client.addToOrder(menuItem);
        }

        if (e.getSource() == client.RemoveProduct)
        {
            int iOrder = client.OrderTable.getSelectedRow();
            if (client.Order.getRowCount() != 0)
            {
                MenuItem menuItem = OrderComponents.get(iOrder);
                OrderComponents.remove(menuItem);
                client.removeFromOrder(iOrder);
            }
        }

        if (e.getSource() == client.CreateOrder)
        {
            deliveryService.createOrder(CurrentUser, OrderComponents);
            userBLL.updateUser(CurrentUser);
            message.showPopUp("A new order was placed");
            client.RefreshOrder();
            OrderComponents = new ArrayList<>();
        }

        if (e.getSource() == client.LogOut)
        {
            client.setVisible(false);
            login.Refresh();
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
