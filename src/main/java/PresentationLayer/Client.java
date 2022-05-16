package PresentationLayer;

import BusinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Client extends JFrame {
    private final ArrayList<String> columns;
    private final JLabel SearchLabel;
    private final JLabel SearchByTitle;
    protected JTextField SearchTitle;
    private final JLabel SearchByRating;
    protected JTextField SearchRating;
    private final JLabel SearchByCalories;
    protected JTextField SearchCalories;
    private final JLabel SearchByProtein;
    protected JTextField SearchProtein;
    private final JLabel SearchByFat;
    protected JTextField SearchFat;
    private final JLabel SearchBySodium;
    protected JTextField SearchSodium;
    private final JLabel SearchByPrice;
    protected JTextField SearchPrice;
    protected JButton Search;
    protected JButton CreateOrder;
    protected JButton LogOut;

    protected DefaultTableModel Menu;
    protected JTable MenuTable;
    private JScrollPane scrollMenu;
    protected JButton AddProduct;
    protected JButton RemoveProduct;
    protected DefaultTableModel Order;
    protected JTable OrderTable;
    private JScrollPane scrollOrder;

    public Client(List<MenuItem> menuItems)
    {
        getContentPane().setBounds(0,0,600,800);
        this.setSize(1400, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Client Operations");
        getContentPane().setLayout(null);

        columns = new ArrayList<>();
        for (Field f : MenuItem.class.getDeclaredFields()) {
            columns.add(f.getName());
        }
        columns.remove(columns.size() - 1);

        SearchLabel         = new JLabel("Search Criteria");
        SearchByTitle       = new JLabel("Title");
        SearchTitle         = new JTextField();
        SearchByRating      = new JLabel("Rating");
        SearchRating        = new JTextField();
        SearchByCalories    = new JLabel("Calories");
        SearchCalories      = new JTextField();
        SearchByProtein     = new JLabel("Protein");
        SearchProtein       = new JTextField();
        SearchByFat         = new JLabel("Fat");
        SearchFat           = new JTextField();
        SearchBySodium      = new JLabel("Sodium");
        SearchSodium        = new JTextField();
        SearchByPrice       = new JLabel("Price");
        SearchPrice         = new JTextField();
        Search              = new JButton("SEARCH");
        CreateOrder         = new JButton("Create Order");
        LogOut              = new JButton("LOG OUT");
        AddProduct          = new JButton("ADD PRODUCT");
        RemoveProduct       = new JButton("REMOVE PRODUCT");

        SearchLabel.setBounds(200,25,100,50);
        getContentPane().add(SearchLabel);

        JPanel SearchPanel = new JPanel();
        getContentPane().add(SearchPanel);
        SearchPanel.setLayout(new GridLayout(7,2));
        SearchPanel.setBounds(50,75,300,200);
        SearchPanel.add(SearchByTitle);
        SearchPanel.add(SearchTitle);
        SearchPanel.add(SearchByRating);
        SearchPanel.add(SearchRating);
        SearchPanel.add(SearchByCalories);
        SearchPanel.add(SearchCalories);
        SearchPanel.add(SearchByProtein);
        SearchPanel.add(SearchProtein);
        SearchPanel.add(SearchByFat);
        SearchPanel.add(SearchFat);
        SearchPanel.add(SearchBySodium);
        SearchPanel.add(SearchSodium);
        SearchPanel.add(SearchByPrice);
        SearchPanel.add(SearchPrice);

        Search.setBounds(50,300,150,50);
        getContentPane().add(Search);

        CreateOrder.setBounds(50,375,150,50);
        getContentPane().add(CreateOrder);

        LogOut.setBounds(50,450,150,50);
        getContentPane().add(LogOut);

        Menu        = new DefaultTableModel(columns.toArray(),0);
        MenuTable   = new JTable(Menu);
        scrollMenu  = new JScrollPane(MenuTable);
        for (MenuItem item : menuItems)
        {
            Object[] o = new Object[]{item.computeTitle(), item.computeRating(), item.computeCalories(), item.computeProtein(), item.computeFat(), item.computeSodium(), item.computePrice()};
            Menu.addRow(o);
        }
        scrollMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMenu.setBounds(450,25,850,400);
        getContentPane().add(scrollMenu);

        AddProduct.setBounds(800,430,150,50);
        getContentPane().add(AddProduct);

        Order       = new DefaultTableModel(columns.toArray(),0);
        OrderTable  = new JTable(Order);
        scrollOrder = new JScrollPane(OrderTable);
        scrollOrder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOrder.setBounds(450,490,850,200);
        getContentPane().add(scrollOrder);

        RemoveProduct.setBounds(800,700,150,50);
        getContentPane().add(RemoveProduct);

        this.setVisible(false);
    }

    public void RefreshOrder()
    {
        getContentPane().remove(scrollOrder);
        Order       = new DefaultTableModel(columns.toArray(),0);
        OrderTable  = new JTable(Order);
        scrollOrder = new JScrollPane(OrderTable);
        scrollOrder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOrder.setBounds(450,490,850,200);
        getContentPane().add(scrollOrder);
    }

    public void filterTable(List<MenuItem> results)
    {
        getContentPane().remove(scrollMenu);
        Menu = new DefaultTableModel(columns.toArray(),0);
        MenuTable = new JTable(Menu);
        scrollMenu = new JScrollPane(MenuTable);
        for (MenuItem item : results)
        {
            Object[] o = new Object[]{item.computeTitle(), item.computeRating(), item.computeCalories(), item.computeProtein(), item.computeFat(), item.computeSodium(), item.computePrice()};
            Menu.addRow(o);
        }
        scrollMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMenu.setBounds(450,25,850,400);
        getContentPane().add(scrollMenu);
    }

    public void addToOrder(MenuItem item)
    {
        Object[] o = new Object[]{item.computeTitle(), item.computeRating(), item.computeCalories(), item.computeProtein(), item.computeFat(), item.computeSodium(), item.computePrice()};
        Order.addRow(o);
    }

    public void removeFromOrder(int iOrder) {
        Order.removeRow(iOrder);
    }
}
