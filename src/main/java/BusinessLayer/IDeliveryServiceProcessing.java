package BusinessLayer;

import java.util.Date;
import java.util.List;

public interface IDeliveryServiceProcessing {

    List<MenuItem> importProducts();

    /**
     * @pre product != null
     */
    boolean addProduct(BaseProduct product);

    /**
     * @pre index >= 0 && index < menuItems.size()
     */
    void deleteProduct(int index);

    /**
     * @pre index >= 0 && index < menuItems.size() && menuItem != null
     */
    void modifyProduct(int index, MenuItem menuItem);

    /**
     * @pre compositeProduct != null
     */
    boolean createProduct(CompositeProduct compositeProduct);

    /**
     * @pre startHour >= 0 && endHour <= 23 && startHour <= endHour
     */
    void generateReport(int startHour, int endHour);

    /**
     * @pre numberOfTimes > 0
     */
    void generateReport(int numberOfTimes);

    /**
     * @pre numberOfTimes > 0 && value > 0
     */
    void generateReport(int numberOfTimes, double value);

    /**
     * @pre date != null
     */
    void generateReport(Date date);

    /**
     * @pre user != null && orderComponents.size() > 0
     * @post @nochange
     */
    void createOrder(User UserID, List<MenuItem> orderComponents);

    List<MenuItem> searchProducts(String keyword, double rating, double calories, double protein, double fat, double sodium, double price);
}
