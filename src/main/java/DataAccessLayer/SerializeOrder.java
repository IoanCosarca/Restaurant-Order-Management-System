package DataAccessLayer;

import BusinessLayer.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeOrder {
    private File FileOrders;

    public SerializeOrder()
    {
        FileOrders = new File("OrdersLog.ser");
        if (!FileOrders.exists()) {
            try
            {
                FileOutputStream file = new FileOutputStream(FileOrders);
                file.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addOrder(Order order)
    {
        List<Order> list;
        try
        {
            list = getOrder();
            if (list.size() == 0) {
                list = new ArrayList<>();
            }
            FileOutputStream file = new FileOutputStream(FileOrders);
            ObjectOutputStream out = new ObjectOutputStream(file);
            list.add(order);
            out.writeObject(list);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<Order> getOrder()
    {
        List<Order> ListOrders = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(FileOrders);
            ObjectInputStream in = new ObjectInputStream(file);
            ListOrders = (List<Order>) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ListOrders;
    }
}
