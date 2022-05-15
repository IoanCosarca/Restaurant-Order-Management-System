package BusinessLayer;

import DataAccessLayer.CreateReports;
import DataAccessLayer.SerializeItem;
import DataAccessLayer.SerializeOrder;
import PresentationLayer.Observer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private List<BaseProduct> baseMenuItems;
    private List<MenuItem> menuItems;
    private List<Observer> observers;
    private HashMap<Order, List<MenuItem>> OrderHashMap;
    private SerializeItem serializeItem = new SerializeItem();
    private SerializeOrder serializeOrder = new SerializeOrder();
    private CreateReports createReports = new CreateReports();

    public DeliveryService()
    {
        baseMenuItems = new ArrayList<>();
        observers = new ArrayList<>();
        OrderHashMap = new HashMap<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Order order, List<MenuItem> list) {
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
            baseMenuItems = br.lines().skip(1).map(ExtractItem).filter(distinctByName(p -> p.computeTitle())).collect(Collectors.toList());
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

    public static <T> Predicate<T> distinctByName(Function<? super T, Object> nameExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(nameExtractor.apply(t), Boolean.TRUE) == null;
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
        return new BaseProduct(Title, Rating, Calories, Protein, Fat, Sodium, Price);
    };

    @Override
    public boolean addProduct(BaseProduct product)
    {
        if (newProduct(product, menuItems))
        {
            menuItems.add(product);
            serializeItem.addProduct(product);
            return true;
        }
        return false;
    }

    /**
     * Checks if the product to be inserted is new or if it already exists in the list
     * @param item
     * @param menuItems
     * @return
     */
    private boolean newProduct(MenuItem item, List<MenuItem> menuItems)
    {
        for (MenuItem i : menuItems) {
            if (i.equals(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void deleteProduct(int index)
    {
        MenuItem item = menuItems.get(index);
        serializeItem.deleteProduct(item);
        menuItems.remove(index);
    }

    @Override
    public void modifyProduct(int index, MenuItem menuItem)
    {
        menuItems.set(index, menuItem);
        serializeItem.updateProduct(index, menuItem);
    }

    @Override
    public boolean createProduct(CompositeProduct compositeProduct)
    {
        if (newProduct(compositeProduct, menuItems))
        {
            menuItems.add(compositeProduct);
            serializeItem.addProduct(compositeProduct);
            return true;
        }
        return false;
    }

    @Override
    public void generateReport(int startHour, int endHour)
    {
        List<Order> IntervalOrders = OrderHashMap.keySet().stream().filter(o -> o.getHour() >= startHour)
                                                                    .filter(o -> o.getHour() <= endHour).collect(Collectors.toList());
        createReports.Report1(IntervalOrders);
    }

    @Override
    public void generateReport(int numberOfTimes)
    {
        List<MenuItem> PopularProducts = menuItems.stream().filter(p -> p.computeTimesOrdered() > numberOfTimes).collect(Collectors.toList());
        createReports.Report2(PopularProducts);
    }

    @Override
    public void generateReport(int numberOfTimes, double value)
    {
        List<Order> SpecificOrders = OrderHashMap.keySet().stream().filter(o -> o.getClient().getOrdersPlaced() > numberOfTimes)
                                                                    .filter(o -> calculateOrderValue(OrderHashMap.get(o)) > value).toList();
        createReports.Report3(numberOfTimes, value, SpecificOrders);
    }

    @Override
    public void generateReport(Date date)
    {
        List<Order> OrdersInDay = OrderHashMap.keySet().stream().filter(o -> o.getDay() == date.getDay())
                                                                    .filter(o -> o.getMonth() == date.getMonth())
                                                                    .filter(o -> o.getYear() == date.getYear()).toList();
        List<MenuItem> OrderedProducts = OrdersInDay.stream().map(i -> OrderHashMap.get(i)).flatMap(List::stream).toList();
        createReports.Report4(date, OrderedProducts);
    }

    public void importOrderDetails() {
        OrderHashMap = serializeOrder.getOrders();
    }

    public HashMap<Order, List<MenuItem>> getOrderHashMap() {
        return OrderHashMap;
    }

    private double calculateOrderValue(List<MenuItem> orderComponents)
    {
        double totalPrice = 0;
        for (MenuItem menuItem : orderComponents) {
            totalPrice += menuItem.computePrice();
        }
        return totalPrice;
    }

    @Override
    public void createOrder(User user, List<MenuItem> orderComponents)
    {
        Order order = new Order(OrderHashMap.size() + 1, user, LocalDateTime.now());
        OrderHashMap.put(order, orderComponents);
        serializeOrder.addOrders(OrderHashMap);
        createBill(order, orderComponents);
        for (MenuItem item : orderComponents) {
            item.setTimesOrdered();
            System.out.println(item.computeTimesOrdered());
        }
        user.setOrdersPlaced();
        notifyObservers(order, orderComponents);
    }

    private void createBill(Order order, List<MenuItem> orderComponents)
    {
        try
        {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Bill" + order.getOrderID() + ".pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 17, BaseColor.BLACK);
            Paragraph paragraph1 = new Paragraph("Order number " + order.getOrderID(), font);
            document.add(paragraph1);
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            Paragraph paragraph2 = new Paragraph(LocalDate.now() + " " + LocalTime.now().format(myFormat), font);
            document.add(paragraph2);
            Paragraph paragraph3 = new Paragraph("Client: " + order.getClient().getUsername(), font);
            document.add(paragraph3);
            for (MenuItem menuItem : orderComponents)
            {
                Paragraph paragraph = new Paragraph(menuItem.computeTitle(), font);
                document.add(paragraph);
            }
            double totalPrice = calculateOrderValue(orderComponents);
            Paragraph paragraph4 = new Paragraph("Total price: " + totalPrice, font);
            document.add(paragraph4);
            document.close();
        }
        catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> searchProducts(String keyword, double rating, double calories, double protein, double fat, double sodium, double price) {
        return menuItems.stream().filter(p -> p.FilterTitle(keyword) && p.FilterRating(rating) && p.FilterCalories(calories) && p.FilterProtein(protein) && p.FilterFat(fat) && p.FilterSodium(sodium) && p.FilterPrice(price)).collect(Collectors.toList());
    }
}
