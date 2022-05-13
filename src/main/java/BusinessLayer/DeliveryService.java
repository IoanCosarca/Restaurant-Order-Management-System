package BusinessLayer;

import DataAccessLayer.SerializeItem;
import DataAccessLayer.SerializeOrder;
import PresentationLayer.Observer;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private List<BaseProduct> baseMenuItems;
    private static List<MenuItem> menuItems;
    private List<Observer> observers;
    private HashMap<Order, Collection<MenuItem>> OrderInfo;
    private SerializeItem serializeItem = new SerializeItem();
    private SerializeOrder serializeOrder = new SerializeOrder();

    public DeliveryService()
    {
        baseMenuItems = new ArrayList<>();
        OrderInfo = new HashMap<>();
    }

    public static List<MenuItem> getList() {
        return menuItems;
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
    public List<MenuItem> importProducts()
    {
        try
        {
            File file = new File("products.csv");
            InputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            baseMenuItems = br.lines().skip(1).map(ExtractItem).filter(distinctByName(p -> p.getTitle())).collect(Collectors.toList());
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        List<MenuItem> list = serializeItem.getProducts();
        menuItems = new ArrayList<>();

        if (list.size() == 0) {
            menuItems.addAll(baseMenuItems);
        }
        else {
            menuItems.addAll(list);
        }
        serializeItem.addProducts(menuItems);

        return menuItems;
    }

    public static <T> Predicate<T> distinctByName(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private Function<String, BaseProduct> ExtractItem = (line) ->
    {
        String[] p = line.split(",");
        String Title    = p[0];
        double Rating   = Double.parseDouble(p[1]);
        double Calories = Double.parseDouble(p[2]);
        double Protein  = Double.parseDouble(p[3]);
        double Fat      = Double.parseDouble(p[4]);
        double Sodium   = Double.parseDouble(p[5]);
        double Price    = Double.parseDouble(p[6]);
        BaseProduct item = new BaseProduct(Title, Rating, Calories, Protein, Fat, Sodium, Price);

        return item;
    };

    @Override
    public boolean addProduct(BaseProduct product)
    {
        if (!findProduct(product, baseMenuItems))
        {
            baseMenuItems.add(product);
            menuItems.add(product);
            serializeItem.addProduct(product);
            return true;
        }
        return false;
    }

    private boolean findProduct(BaseProduct product, List<BaseProduct> baseMenuItems)
    {
        for (BaseProduct item : baseMenuItems) {
            if (item.equals(product)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteProduct(int index)
    {
        MenuItem item = menuItems.get(index);
        serializeItem.deleteProduct(item);
        baseMenuItems.remove(index);
        menuItems.remove(index);
    }

    @Override
    public void modifyProduct(int index, BaseProduct product)
    {
        baseMenuItems.set(index, product);
        menuItems.set(index, product);
        serializeItem.updateProduct(index, product);
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
