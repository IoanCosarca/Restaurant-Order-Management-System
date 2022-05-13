package PresentationLayer;

import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Client extends JFrame {
    private JLabel SearchLabel;
    private JLabel SearchByTitle;
    private JTextField SearchTitle;
    private JLabel SearchByRating;
    private JTextField SearchRating;
    private JLabel SearchByCalories;
    private JTextField SearchCalories;
    private JLabel SearchByProtein;
    private JTextField SearchProtein;
    private JLabel SearchByFat;
    private JTextField SearchFat;
    private JLabel SearchBySodium;
    private JTextField SearchSodium;
    private JLabel SearchByPrice;
    private JTextField SearchPrice;
    protected JButton Search;
    protected JButton CreateOrder;
    protected JButton AddProduct;
    protected JButton RemoveProduct;
    protected JButton LogOut;

    public Client(ArrayList<String> columns, List<MenuItem> importProducts)
    {
        getContentPane().setBounds(0,0,600,600);
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Client Operations");
        getContentPane().setLayout(null);

        SearchLabel     = new JLabel("Search Criteria");
        SearchByTitle = new JLabel("Title");
        SearchTitle   = new JTextField();
        SearchByRating  = new JLabel("Rating");
        SearchRating    = new JTextField();
        SearchByCalories  = new JLabel("Calories");
        SearchCalories    = new JTextField();
        SearchByProtein = new JLabel("Protein");
        SearchProtein = new JTextField();
        SearchByFat = new JLabel("Fat");
        SearchFat = new JTextField();
        SearchBySodium = new JLabel("Sodium");
        SearchSodium = new JTextField();
        SearchByPrice = new JLabel("Price");
        SearchPrice = new JTextField();
        Search          = new JButton("SEARCH");
        CreateOrder     = new JButton("Create Order");
        AddProduct      = new JButton("ADD PRODUCT");
        RemoveProduct   = new JButton("REMOVE PRODUCT");
        LogOut          = new JButton("LOGOUT");

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

        this.setVisible(false);
    }
}
