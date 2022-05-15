package BusinessLayer;

import java.util.Date;
import java.util.List;

public interface IDeliveryServiceProcessing {

    List<MenuItem> importProducts();

    boolean addProduct(BaseProduct product);

    void deleteProduct(int index);

    void modifyProduct(int index, MenuItem menuItem);

    boolean createProduct(CompositeProduct compositeProduct);

    void generateReport(int startHour, int endHour);

    void generateReport(int numberOfTimes);

    void generateReport(int numberOfTimes, double value);

    void generateReport(Date date);

    void createOrder(User UserID, List<MenuItem> orderComponents);

    List<MenuItem> searchProducts(String keyword, double rating, double calories, double protein, double fat, double sodium, double price);
}
