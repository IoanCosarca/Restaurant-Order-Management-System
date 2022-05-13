package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends JFrame {
    private JLabel Title;
    protected JTextField TitleField;
    private JLabel Rating;
    protected JTextField RatingField;
    private JLabel Calories;
    protected JTextField CaloriesField;
    private JLabel Protein;
    protected JTextField ProteinField;
    private JLabel Fat;
    protected JTextField FatField;
    private JLabel Sodium;
    protected JTextField SodiumField;
    private JLabel Price;
    protected JTextField PriceField;

    protected JButton ImportProducts;
    protected JButton AddProduct;
    protected JButton DeleteProduct;
    protected JButton ModifyProduct;
    protected JButton CreateProduct;
    protected JButton GenerateReports;

    protected JButton LogOut;

    protected DefaultTableModel model;
    protected JTable table;
    private JScrollPane slider;

    protected JButton Add;
    protected JButton Remove;
    protected DefaultTableModel modelCompose;
    protected JTable tableCompose;
    private JScrollPane scroll;

    public Administrator(ArrayList<String> columns)
    {
        getContentPane().setBounds(0,0,600,800);
        this.setSize(1400, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Administrator Operations");
        getContentPane().setLayout(null);

        Font FontBtn        = new Font("", Font.PLAIN,18);

        Title               = new JLabel("Title");
        TitleField          = new JTextField();
        Rating              = new JLabel("Rating");
        RatingField         = new JTextField();
        Calories            = new JLabel("Calories");
        CaloriesField       = new JTextField();
        Protein             = new JLabel("Protein");
        ProteinField        = new JTextField();
        Fat                 = new JLabel("Fat");
        FatField            = new JTextField();
        Sodium              = new JLabel("Sodium");
        SodiumField         = new JTextField();
        Price               = new JLabel("Price");
        PriceField          = new JTextField();
        ImportProducts      = new JButton("Import Products");
        AddProduct          = new JButton("Add Product");
        DeleteProduct       = new JButton("Delete Product");
        ModifyProduct       = new JButton("Modify Product");
        CreateProduct       = new JButton("Create Product");
        GenerateReports     = new JButton("Generate Reports");
        LogOut              = new JButton("LOGOUT");
        Add                 = new JButton("ADD");
        Remove              = new JButton("REMOVE");

        JPanel OrderInfo = new JPanel();
        getContentPane().add(OrderInfo);
        OrderInfo.setLayout(new GridLayout(7,2));
        OrderInfo.setBounds(50,25,400,250);
        Title.setFont(FontBtn);
        OrderInfo.add(Title);
        TitleField.setFont(FontBtn);
        OrderInfo.add(TitleField);
        Rating.setFont(FontBtn);
        OrderInfo.add(Rating);
        RatingField.setFont(FontBtn);
        OrderInfo.add(RatingField);
        Calories.setFont(FontBtn);
        OrderInfo.add(Calories);
        CaloriesField.setFont(FontBtn);
        OrderInfo.add(CaloriesField);
        Protein.setFont(FontBtn);
        OrderInfo.add(Protein);
        ProteinField.setFont(FontBtn);
        OrderInfo.add(ProteinField);
        Fat.setFont(FontBtn);
        OrderInfo.add(Fat);
        FatField.setFont(FontBtn);
        OrderInfo.add(FatField);
        Sodium.setFont(FontBtn);
        OrderInfo.add(Sodium);
        SodiumField.setFont(FontBtn);
        OrderInfo.add(SodiumField);
        Price.setFont(FontBtn);
        OrderInfo.add(Price);
        PriceField.setFont(FontBtn);
        OrderInfo.add(PriceField);

        ImportProducts.setFont(FontBtn);
        ImportProducts.setBounds(150,290,200,50);
        getContentPane().add(ImportProducts);

        JPanel ProductManagement = new JPanel();
        getContentPane().add(ProductManagement);
        ProductManagement.setLayout(new GridLayout(2,2));
        ProductManagement.setBounds(50,350,400,100);
        AddProduct.setFont(FontBtn);
        ProductManagement.add(AddProduct);
        DeleteProduct.setFont(FontBtn);
        ProductManagement.add(DeleteProduct);
        ModifyProduct.setFont(FontBtn);
        ProductManagement.add(ModifyProduct);
        CreateProduct.setFont(FontBtn);
        ProductManagement.add(CreateProduct);

        GenerateReports.setFont(FontBtn);
        GenerateReports.setBounds(150,460,200,50);
        getContentPane().add(GenerateReports);

        LogOut.setFont(FontBtn);
        LogOut.setBounds(50,575,150,50);
        getContentPane().add(LogOut);

        model   = new DefaultTableModel(columns.toArray(),0);
        table   = new JTable(model);
        slider  = new JScrollPane(table);

        slider.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        slider.setBounds(500,25,800,350);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = table.getSelectedRow();
                TitleField.setText((String) model.getValueAt(i,0));
                RatingField.setText(String.valueOf(model.getValueAt(i,1)));
                CaloriesField.setText(String.valueOf(model.getValueAt(i,2)));
                ProteinField.setText(String.valueOf(model.getValueAt(i,3)));
                FatField.setText(String.valueOf(model.getValueAt(i,4)));
                SodiumField.setText(String.valueOf(model.getValueAt(i,5)));
                PriceField.setText(String.valueOf(model.getValueAt(i,6)));
            }
        });
        getContentPane().add(slider);

        Add.setFont(FontBtn);
        Add.setBounds(875,380,100,50);
        getContentPane().add(Add);

        modelCompose    = new DefaultTableModel(columns.toArray(),0);
        tableCompose    = new JTable(modelCompose);
        scroll          = new JScrollPane(tableCompose);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(500,450,800,200);
        getContentPane().add(scroll);

        Remove.setFont(FontBtn);
        Remove.setBounds(850,675,150,50);
        getContentPane().add(Remove);

        this.setVisible(false);
    }

    public void addRows(List<MenuItem> products)
    {
        for (MenuItem item : products)
        {
            Object[] o = new Object[]{item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(), item.getFat(), item.getSodium(), item.getPrice()};
            model.addRow(o);
        }
    }

    public void addRow(BaseProduct product)
    {
        Object[] o = new Object[]{product.getTitle(), product.getRating(), product.getCalories(), product.getProtein(), product.getFat(), product.getSodium(), product.getPrice()};
        model.addRow(o);
    }

    public void removeRow(int index) {
        model.removeRow(index);
    }

    public void updateRow(int index, BaseProduct product)
    {
        Object[] o = new Object[]{product.getTitle(), product.getRating(), product.getCalories(), product.getProtein(), product.getFat(), product.getSodium(), product.getPrice()};
        model.insertRow(index, o);
    }

    public void Refresh()
    {
        TitleField.setText("");
        RatingField.setText("");
        CaloriesField.setText("");
        ProteinField.setText("");
        FatField.setText("");
        SodiumField.setText("");
        PriceField.setText("");
    }
}
