package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {
    private JLabel SearchLabel;
    private JLabel SearchByKeyword;
    private JTextField SearchKeyword;
    private JLabel SearchByRating;
    private JTextField SearchRating;
    private JLabel SearchByNumber;
    private JTextField SearchNumber;
    protected JButton Search;
    protected JButton CreateOrder;
    protected JButton AddProduct;
    protected JButton RemoveProduct;
    protected JButton LogOut;

    public Client()
    {
        getContentPane().setBounds(0,0,600,600);
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Client Operations");
        getContentPane().setLayout(null);

        SearchLabel     = new JLabel("Search Criteria");
        SearchByKeyword = new JLabel("Search By Keyword");
        SearchKeyword   = new JTextField();
        SearchByRating  = new JLabel("Rating");
        SearchRating    = new JTextField();
        SearchByNumber  = new JLabel("Search By Number");
        SearchNumber    = new JTextField();
        Search          = new JButton("SEARCH");
        CreateOrder     = new JButton("Create Order");
        AddProduct      = new JButton("ADD PRODUCT");
        RemoveProduct   = new JButton("REMOVE PRODUCT");
        LogOut          = new JButton("LOGOUT");

        SearchLabel.setBounds(200,50,100,50);
        getContentPane().add(SearchLabel);

        JPanel SearchPanel = new JPanel();
        getContentPane().add(SearchPanel);
        SearchPanel.setLayout(new GridLayout(3,2));
        SearchPanel.setBounds(50,100,300,150);
        SearchPanel.add(SearchByKeyword);
        SearchPanel.add(SearchKeyword);
        SearchPanel.add(SearchByRating);
        SearchPanel.add(SearchRating);
        SearchPanel.add(SearchByNumber);
        SearchPanel.add(SearchNumber);

        Search.setBounds(50,300,150,50);
        getContentPane().add(Search);

        CreateOrder.setBounds(50,375,150,50);
        getContentPane().add(CreateOrder);

        LogOut.setBounds(50,450,150,50);
        getContentPane().add(LogOut);

        this.setVisible(false);
    }
}
