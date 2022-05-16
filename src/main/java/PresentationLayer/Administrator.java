package PresentationLayer;

import BusinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends JFrame {
    private final JLabel Title;
    protected JTextField TitleField;
    private final JLabel Rating;
    protected JTextField RatingField;
    private final JLabel Calories;
    protected JTextField CaloriesField;
    private final JLabel Protein;
    protected JTextField ProteinField;
    private final JLabel Fat;
    protected JTextField FatField;
    private final JLabel Sodium;
    protected JTextField SodiumField;
    private final JLabel Price;
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
    private final JScrollPane slider;

    protected JButton Add;
    protected JButton Remove;
    protected DefaultTableModel modelCompose;
    protected JTable tableCompose;
    private JScrollPane scroll;

    private final JLabel StartHour;
    protected JTextField StartField;
    private final JLabel EndHour;
    protected JTextField EndField;
    private final JLabel Number;
    protected JTextField NumberField;
    private final JLabel Value;
    protected JTextField ValueField;
    private final JLabel Date;
    protected JTextField DateField;

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
        LogOut              = new JButton("LOG OUT");
        Add                 = new JButton("ADD");
        Remove              = new JButton("REMOVE");
        StartHour           = new JLabel("Start Hour");
        StartField          = new JTextField();
        EndHour             = new JLabel("End Hour");
        EndField            = new JTextField();
        Number              = new JLabel("Number");
        NumberField         = new JTextField();
        Value               = new JLabel("Value");
        ValueField          = new JTextField();
        Date                = new JLabel("Date (dd.MM.yyyy)");
        DateField           = new JTextField();

        JPanel OrderInfo = new JPanel();
        getContentPane().add(OrderInfo);
        OrderInfo.setLayout(new GridLayout(7,2));
        OrderInfo.setBounds(25,25,400,200);
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
        ImportProducts.setBounds(150,240,200,50);
        getContentPane().add(ImportProducts);

        JPanel ProductManagement = new JPanel();
        getContentPane().add(ProductManagement);
        ProductManagement.setLayout(new GridLayout(2,2));
        ProductManagement.setBounds(50,300,400,100);
        AddProduct.setFont(FontBtn);
        ProductManagement.add(AddProduct);
        DeleteProduct.setFont(FontBtn);
        ProductManagement.add(DeleteProduct);
        ModifyProduct.setFont(FontBtn);
        ProductManagement.add(ModifyProduct);
        CreateProduct.setFont(FontBtn);
        ProductManagement.add(CreateProduct);

        GenerateReports.setFont(FontBtn);
        GenerateReports.setBounds(150,410,200,50);
        getContentPane().add(GenerateReports);

        JPanel ReportParameters = new JPanel();
        getContentPane().add(ReportParameters);
        ReportParameters.setLayout(new GridLayout(5,2));
        ReportParameters.setBounds(25,470,400,200);
        StartHour.setFont(FontBtn);
        ReportParameters.add(StartHour);
        StartField.setFont(FontBtn);
        ReportParameters.add(StartField);
        EndHour.setFont(FontBtn);
        ReportParameters.add(EndHour);
        EndField.setFont(FontBtn);
        ReportParameters.add(EndField);
        Number.setFont(FontBtn);
        ReportParameters.add(Number);
        NumberField.setFont(FontBtn);
        ReportParameters.add(NumberField);
        Value.setFont(FontBtn);
        ReportParameters.add(Value);
        ValueField.setFont(FontBtn);
        ReportParameters.add(ValueField);
        Date.setFont(FontBtn);
        ReportParameters.add(Date);
        DateField.setFont(FontBtn);
        ReportParameters.add(DateField);

        LogOut.setFont(FontBtn);
        LogOut.setBounds(50,690,150,50);
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
                if (i != -1)
                {
                    TitleField.setText((String) model.getValueAt(i, 0));
                    RatingField.setText(String.valueOf(model.getValueAt(i, 1)));
                    CaloriesField.setText(String.valueOf(model.getValueAt(i, 2)));
                    ProteinField.setText(String.valueOf(model.getValueAt(i, 3)));
                    FatField.setText(String.valueOf(model.getValueAt(i, 4)));
                    SodiumField.setText(String.valueOf(model.getValueAt(i, 5)));
                    PriceField.setText(String.valueOf(model.getValueAt(i, 6)));
                }
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
            Object[] o = new Object[]{item.computeTitle(), item.computeRating(), item.computeCalories(), item.computeProtein(), item.computeFat(), item.computeSodium(), item.computePrice()};
            model.addRow(o);
        }
    }

    public void addRow(MenuItem item)
    {
        Object[] o = new Object[]{item.computeTitle(), item.computeRating(), item.computeCalories(), item.computeProtein(), item.computeFat(), item.computeSodium(), item.computePrice()};
        model.addRow(o);
    }

    public void removeRow(int index) {
        model.removeRow(index);
    }

    public void updateRow(int index, MenuItem product)
    {
        Object[] o = new Object[]{product.computeTitle(), product.computeRating(), product.computeCalories(), product.computeProtein(), product.computeFat(), product.computeSodium(), product.computePrice()};
        model.setValueAt(o[0], index,0);
        model.setValueAt(o[1], index,1);
        model.setValueAt(o[2], index,2);
        model.setValueAt(o[3], index,3);
        model.setValueAt(o[4], index,4);
        model.setValueAt(o[5], index,5);
        model.setValueAt(o[6], index,6);
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

    public void addComponent(MenuItem item)
    {
        Object[] o = new Object[]{item.computeTitle(), item.computeRating(), item.computeCalories(), item.computeProtein(), item.computeFat(), item.computeSodium(), item.computePrice()};
        modelCompose.addRow(o);
    }

    public void removeComponent(int index) {
        modelCompose.removeRow(index);
    }

    public void RefreshCompose(ArrayList<String> columns)
    {
        getContentPane().remove(scroll);
        modelCompose = new DefaultTableModel(columns.toArray(),0);
        tableCompose = new JTable(modelCompose);
        scroll = new JScrollPane(tableCompose);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(500,450,800,200);
        getContentPane().add(scroll);
    }
}
