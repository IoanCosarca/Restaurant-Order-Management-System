package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Employee extends JFrame implements Observer {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;

    public Employee(HashMap<Order, List<MenuItem>> orderHashMap)
    {
        getContentPane().setBounds(0,0,600,700);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Employee Window");
        getContentPane().setLayout(null);

        ArrayList<String> columns = new ArrayList<>();
        for (Field f : Order.class.getDeclaredFields()) {
            columns.add(f.getName());
        }
        columns.add("Products");

        model   = new DefaultTableModel(columns.toArray(),0);
        table   = new JTable(model);
        scroll  = new JScrollPane(table);
        if (!orderHashMap.isEmpty()) {
            for (Order order : orderHashMap.keySet())
            {
                Object[] o = new Object[]{order.getOrderID(), order.getClient().getUsername(), order.getOrderDate(), orderHashMap.get(order).toString()};
                model.addRow(o);
            }
        }
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(50,50,900,500);
        getContentPane().add(scroll);

        this.setVisible(false);
    }

    @Override
    public void update(Order order, List<MenuItem> products)
    {
        Object[] o = new Object[]{order.getOrderID(), order.getClient().getUsername(), order.getOrderDate(), products.toString()};
        model.addRow(o);
        this.setVisible(true);
    }
}
