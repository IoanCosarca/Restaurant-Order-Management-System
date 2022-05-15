package DataAccessLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.*;
import java.util.HashMap;
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

    public void addOrders(HashMap<Order, List<MenuItem>> orders)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(FileOrders);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(orders);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Order, List<MenuItem>> getOrders()
    {
        HashMap<Order, List<MenuItem>> listOrders = new HashMap<>();
        try
        {
            FileInputStream file = new FileInputStream(FileOrders);
            ObjectInputStream in = new ObjectInputStream(file);
            listOrders = (HashMap) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listOrders;
    }
}
