package BusinessLayer;

import DataAccessLayer.CreateReports;
import DataAccessLayer.SerializeItem;
import DataAccessLayer.SerializeOrder;
import PresentationLayer.Observer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @invariant isWellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private List<BaseProduct> baseMenuItems;
    private List<MenuItem> menuItems;
    private final List<Observer> observers;
    private HashMap<Order, List<MenuItem>> OrderHashMap;
    private final SerializeItem serializeItem = new SerializeItem();
    private final SerializeOrder serializeOrder = new SerializeOrder();
    private final CreateReports createReports = new CreateReports();

    public DeliveryService()
    {
        baseMenuItems   = new ArrayList<>();
        observers       = new ArrayList<>();
        OrderHashMap    = new HashMap<>();
    }

    protected boolean isWellFormed()
    {
        int n = 0;
        for (MenuItem item : menuItems)
        {
            n++;
            if (Objects.equals(item.computeTitle(),""))
                return false;
            if (item.computeRating() < 0)
                return false;
            if (item.computeCalories() < 0)
                return false;
            if (item.computeProtein() < 0)
                return false;
            if (item.computeFat() < 0)
                return false;
            if (item.computeSodium() < 0)
                return false;
            if (item.computePrice() < 0)
                return false;
        }
        return n == menuItems.size();
    }

    @Override
    public void registerObserver(Observer o) {observers.add(o);}

    @Override
    public void unregisterObserver(Observer o) {observers.remove(o);}

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
        assert menuItems.size() > 0;
        assert isWellFormed();
        return menuItems;
    }

    public static <T> Predicate<T> distinctByName(Function<? super T, Object> nameExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(nameExtractor.apply(t), Boolean.TRUE) == null;
    }

    private final Function<String, BaseProduct> ExtractItem = (line) ->
    {
        String[] p = line.split(",");
        String Title = p[0];
        double Rating = Double.parseDouble(p[1]);
        double Calories = Double.parseDouble(p[2]);
        double Protein = Double.parseDouble(p[3]);
        double Fat = Double.parseDouble(p[4]);
        double Sodium = Double.parseDouble(p[5]);
        double Price = Double.parseDouble(p[6]);
        return new BaseProduct(Title, Rating, Calories, Protein, Fat, Sodium, Price);
    };

    @Override
    public boolean addProduct(BaseProduct product)
    {
        assert !Objects.equals(product.computeTitle(),"") && product.computeRating() >= 0 && product.computeCalories() >= 0 && product.computeProtein() >= 0 && product.computeFat() >= 0 && product.computeSodium() >= 0 && product.computePrice() >= 0;
        int sizePre = menuItems.size();
        if (newProduct(product, menuItems))
        {
            menuItems.add(product);
            int sizePost = menuItems.size();
            assert sizePost == sizePre + 1;
            assert isWellFormed();
            serializeItem.addProduct(product);
            return true;
        }
        return false;
    }

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
        assert index >= 0 && index < menuItems.size();
        int sizePre = menuItems.size();
        MenuItem item = menuItems.get(index);
        serializeItem.deleteProduct(item);
        menuItems.remove(index);
        int sizePost = menuItems.size();
        assert sizePost == sizePre - 1;
        assert isWellFormed();
    }

    @Override
    public void modifyProduct(int index, MenuItem menuItem)
    {
        assert index >= 0 && index < menuItems.size();
        assert !Objects.equals(menuItem.computeTitle(),"") && menuItem.computeRating() >= 0 && menuItem.computeCalories() >= 0 && menuItem.computeProtein() >= 0 && menuItem.computeFat() >= 0 && menuItem.computeSodium() >= 0 && menuItem.computePrice() >= 0;
        menuItems.set(index, menuItem);
        assert menuItems.get(index) == menuItem;
        assert isWellFormed();
        serializeItem.updateProduct(index, menuItem);
    }

    @Override
    public boolean createProduct(CompositeProduct compositeProduct)
    {
        assert compositeProduct != null;
        int sizePre = menuItems.size();
        if (newProduct(compositeProduct, menuItems)) {
            menuItems.add(compositeProduct);
            int sizePost = menuItems.size();
            assert sizePost == sizePre + 1;
            assert isWellFormed();
            serializeItem.addProduct(compositeProduct);
            return true;
        }
        return false;
    }

    @Override
    public void generateReport(int startHour, int endHour)
    {
        assert startHour >= 0 && endHour <= 23 && startHour <= endHour;
        List<Order> IntervalOrders = OrderHashMap.keySet().stream().filter(o -> o.getHour() >= startHour).filter(o -> o.getHour() <= endHour).collect(Collectors.toList());
        createReports.Report1(IntervalOrders);
    }

    @Override
    public void generateReport(int numberOfTimes)
    {
        assert numberOfTimes > 0;
        List<MenuItem> PopularProducts = importProducts().stream().filter(p -> p.computeTimesOrdered() >= numberOfTimes).collect(Collectors.toList());
        createReports.Report2(PopularProducts);
    }

    @Override
    public void generateReport(int numberOfTimes, double value)
    {
        assert numberOfTimes > 0 && value > 0;
        List<Order> SpecificOrders = OrderHashMap.keySet().stream().filter(o -> o.getClient().getOrdersPlaced() >= numberOfTimes)
                .filter(o -> calculateOrderValue(OrderHashMap.get(o)) >= value).filter(distinctByName(o -> o.getClient().getUsername())).toList();
        createReports.Report3(numberOfTimes, value, SpecificOrders);
    }

    @Override
    public void generateReport(Date date)
    {
        assert date != null;
        List<Order> OrdersInDay = OrderHashMap.keySet().stream().filter(o -> o.getDay() == date.getDate())
                .filter(o -> o.getMonth() == date.getMonth() + 1).filter(o -> o.getYear() == date.getYear() + 1900).toList();
        List<MenuItem> OrderedProducts = OrdersInDay.stream().map(i -> OrderHashMap.get(i)).flatMap(List::stream).filter(distinctByName(p -> p.computeTitle())).toList();
        createReports.Report4(date, OrderedProducts);
    }

    public void importOrderDetails() {OrderHashMap = serializeOrder.getOrders();}

    public HashMap<Order, List<MenuItem>> getOrderHashMap() {return OrderHashMap;}

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
        assert user != null && orderComponents.size() > 0;
        int sizePre = OrderHashMap.size();
        Order order = new Order(OrderHashMap.size() + 1, user, LocalDateTime.now());
        OrderHashMap.put(order, orderComponents);
        int sizePost = OrderHashMap.size();
        assert sizePost == sizePre + 1;
        serializeOrder.addOrders(OrderHashMap);
        createBill(order, orderComponents);
        user.incOrdersPlaced();
        for (MenuItem item : orderComponents) {
            for (MenuItem menuItem : menuItems) {
                if (item.equals(menuItem)) {
                    menuItem.incTimesOrdered();
                    item.incTimesOrdered();
                }
            }
        }
        serializeItem.addProducts(menuItems);
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
            Paragraph paragraph3 = new Paragraph("Client: " + order.getClient().getUsername() + "\n=========================", font);
            document.add(paragraph3);
            for (MenuItem menuItem : orderComponents)
            {
                Paragraph paragraph = new Paragraph(menuItem.computeTitle(), font);
                document.add(paragraph);
            }
            double totalPrice = calculateOrderValue(orderComponents);
            Paragraph paragraph4 = new Paragraph("=========================\nTotal price: " + totalPrice, font);
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