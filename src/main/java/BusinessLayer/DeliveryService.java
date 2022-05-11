package BusinessLayer;

import DataAccessLayer.SerializeItem;
import DataAccessLayer.SerializeOrder;
import PresentationLayer.Observer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private Collection<BaseProduct> baseMenuItems;
    private List<BaseProduct> distinctItems;
    private List<Observer> observers;
    private HashMap<Order, Collection<MenuItem>> OrderInfo;
    private SerializeItem serializeItem = new SerializeItem();
    private SerializeOrder serializeOrder = new SerializeOrder();

    public DeliveryService()
    {
        baseMenuItems = new ArrayList<>();
        distinctItems = new ArrayList<>();
        OrderInfo = new HashMap<>();
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override
    void addObserver(Observer o) {

    }

    @Override
    void deleteObserver(Observer o) {

    }

    @Override
    void notifyObservers(Order order, List<MenuItem> list) {
        for (Observer o : observers) {
            o.update(order, list);
        }
    }

    @Override
    public List<BaseProduct> importProducts()
    {
        try
        {
            File file = new File("products.csv");
            InputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            baseMenuItems = br.lines().skip(1).map(ExtractItem).collect(Collectors.toList());
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        distinctItems = baseMenuItems.stream().distinct().collect(Collectors.toList());

        return distinctItems;
    }

    private Function<String, BaseProduct> ExtractItem = (line) ->
    {
        String[] p = line.split(",");
        String Title = p[0];
        double Rating = Double.parseDouble(p[1]);
        double Calories = Double.parseDouble(p[2]);
        double Protein = Double.parseDouble(p[3]);
        double Fat = Double.parseDouble(p[4]);
        double Sodium = Double.parseDouble(p[5]);
        double Price = Double.parseDouble(p[6]);
        BaseProduct item = new BaseProduct(Title, Rating, Calories, Protein, Fat, Sodium, Price);

        return item;
    };

    @Override
    public void addProduct(BaseProduct product)
    {
        List<MenuItem> menuList = serializeItem.getProducts();
        if (!findProduct(product, menuList))
        {
            baseMenuItems.add(product);
            serializeItem.addProduct(product);
        }
    }

    private boolean findProduct(BaseProduct product, List<MenuItem> baseMenuItems)
    {
        for (MenuItem menuItem : baseMenuItems) {
            if (menuItem.equals(product)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public void modifyProduct() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void generateReports() {

    }

    @Override
    public void createOrder() {

    }

    @Override
    public void searchProduct() {

    }

}
