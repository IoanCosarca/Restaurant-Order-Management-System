package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import javax.swing.*;
import java.util.List;

public class Employee extends JFrame implements Observer {
    @Override
    public void update(Order order, List<MenuItem> products)
    {
        this.setVisible(false);
        //JOptionPane.showMessageDialog(this,"Order with the ID "+order.getOrderID()+", made by client "+order.getClientID()+" placed at "+order.getOrderDate());
    }
}
