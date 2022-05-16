package PresentationLayer;

import BusinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends JFrame {
    private final ArrayList<String> columns;
    protected JTextField TitleField;
    protected JTextField RatingField;
    protected JTextField CaloriesField;
    protected JTextField ProteinField;
    protected JTextField FatField;
    protected JTextField SodiumField;
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
    protected JButton Add;
    protected JButton Remove;
    protected DefaultTableModel modelCompose;
    protected JTable tableCompose;
    private JScrollPane scroll;

    protected JTextField StartField;
    protected JTextField EndField;
    protected JTextField NumberField;
    protected JTextField ValueField;
    protected JTextField DateField;

    public Administrator()
    {
        getContentPane().setBounds(0,0,600,800);
        this.setSize(1400, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Administrator Operations");
        getContentPane().setLayout(null);

        columns = new ArrayList<>();
        for (Field f : MenuItem.class.getDeclaredFields()) {
            columns.add(f.getName());
        }
        columns.remove(columns.size() - 1);

        Font FontBtn        = new Font("", Font.PLAIN,18);
        JLabel title        = new JLabel("Title");
        TitleField          = new JTextField();
        JLabel rating       = new JLabel("Rating");
        RatingField         = new JTextField();
        JLabel calories     = new JLabel("Calories");
        CaloriesField       = new JTextField();
        JLabel protein      = new JLabel("Protein");
        ProteinField        = new JTextField();
        JLabel fat          = new JLabel("Fat");
        FatField            = new JTextField();
        JLabel sodium       = new JLabel("Sodium");
        SodiumField         = new JTextField();
        JLabel price        = new JLabel("Price");
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
        JLabel startHour    = new JLabel("Start Hour");
        StartField          = new JTextField();
        JLabel endHour      = new JLabel("End Hour");
        EndField            = new JTextField();
        JLabel number       = new JLabel("Number");
        NumberField         = new JTextField();
        JLabel value        = new JLabel("Value");
        ValueField          = new JTextField();
        JLabel date         = new JLabel("Date (dd.MM.yyyy)");
        DateField           = new JTextField();

        JPanel OrderInfo = new JPanel();
        getContentPane().add(OrderInfo);
        OrderInfo.setLayout(new GridLayout(7,2));
        OrderInfo.setBounds(25,25,400,200);
        title.setFont(FontBtn);
        OrderInfo.add(title);
        TitleField.setFont(FontBtn);
        OrderInfo.add(TitleField);
        rating.setFont(FontBtn);
        OrderInfo.add(rating);
        RatingField.setFont(FontBtn);
        OrderInfo.add(RatingField);
        calories.setFont(FontBtn);
        OrderInfo.add(calories);
        CaloriesField.setFont(FontBtn);
        OrderInfo.add(CaloriesField);
        protein.setFont(FontBtn);
        OrderInfo.add(protein);
        ProteinField.setFont(FontBtn);
        OrderInfo.add(ProteinField);
        fat.setFont(FontBtn);
        OrderInfo.add(fat);
        FatField.setFont(FontBtn);
        OrderInfo.add(FatField);
        sodium.setFont(FontBtn);
        OrderInfo.add(sodium);
        SodiumField.setFont(FontBtn);
        OrderInfo.add(SodiumField);
        price.setFont(FontBtn);
        OrderInfo.add(price);
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
        startHour.setFont(FontBtn);
        ReportParameters.add(startHour);
        StartField.setFont(FontBtn);
        ReportParameters.add(StartField);
        endHour.setFont(FontBtn);
        ReportParameters.add(endHour);
        EndField.setFont(FontBtn);
        ReportParameters.add(EndField);
        number.setFont(FontBtn);
        ReportParameters.add(number);
        NumberField.setFont(FontBtn);
        ReportParameters.add(NumberField);
        value.setFont(FontBtn);
        ReportParameters.add(value);
        ValueField.setFont(FontBtn);
        ReportParameters.add(ValueField);
        date.setFont(FontBtn);
        ReportParameters.add(date);
        DateField.setFont(FontBtn);
        ReportParameters.add(DateField);

        LogOut.setFont(FontBtn);
        LogOut.setBounds(50,690,150,50);
        getContentPane().add(LogOut);

        model               = new DefaultTableModel(columns.toArray(),0);
        table               = new JTable(model);
        JScrollPane slider  = new JScrollPane(table);
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

    public void addRows(List<MenuItem> products) {
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

    public void RefreshCompose()
    {
        getContentPane().remove(scroll);
        modelCompose = new DefaultTableModel(columns.toArray(),0);
        tableCompose = new JTable(modelCompose);
        scroll = new JScrollPane(tableCompose);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(500,450,800,200);
        getContentPane().add(scroll);
    }

    public void SwitchButtons(boolean mode)
    {
        AddProduct.setEnabled(mode);
        DeleteProduct.setEnabled(mode);
        ModifyProduct.setEnabled(mode);
        CreateProduct.setEnabled(mode);
        Add.setEnabled(mode);
        Remove.setEnabled(mode);
    }
}
