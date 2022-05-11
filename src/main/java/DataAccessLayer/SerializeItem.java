package DataAccessLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeItem {
    private File FileMenuItems;

    public SerializeItem()
    {
        FileMenuItems = new File("ProductsLog.ser");
        if (!FileMenuItems.exists()) {
            try
            {
                FileOutputStream file = new FileOutputStream(FileMenuItems);
                file.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addProduct(BaseProduct product)
    {
        List<MenuItem> list;
        try
        {
            list = getProducts();
            if (list.size() == 0) {
                list = new ArrayList<>();
            }
            FileOutputStream file = new FileOutputStream(FileMenuItems);
            ObjectOutputStream out = new ObjectOutputStream(file);
            list.add(product);
            out.writeObject(list);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<MenuItem> getProducts()
    {
        List<MenuItem> ListProducts = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(FileMenuItems);
            ObjectInputStream in = new ObjectInputStream(file);
            ListProducts = (List<MenuItem>) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ListProducts;
    }

    public boolean addProduct(List<MenuItem> CompositeProduct)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(FileMenuItems);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(CompositeProduct);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
