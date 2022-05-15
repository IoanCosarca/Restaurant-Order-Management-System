package DataAccessLayer;

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

    public void addProduct(MenuItem product)
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
    }

    public List<MenuItem> getProducts()
    {
        List<MenuItem> ListProducts = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(FileMenuItems);
            ObjectInputStream in = new ObjectInputStream(file);
            ListProducts = (List) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ListProducts;
    }

    public void addProducts(List<MenuItem> products)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(FileMenuItems);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(products);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(MenuItem menuItem)
    {
        List<MenuItem> list;
        try
        {
            list = getProducts();
            list.remove(menuItem);
            if (list.size() == 0) {
                list = new ArrayList<>();
            }
            new SerializeItem();
            FileOutputStream file = new FileOutputStream(FileMenuItems);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(list);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int index, MenuItem item)
    {
        List<MenuItem> list;
        try
        {
            list = getProducts();
            list.set(index, item);
            FileOutputStream file = new FileOutputStream(FileMenuItems);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(list);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
