package BusinessLayer;

import java.util.Date;
import java.util.List;

public interface IDeliveryServiceProcessing {

    /**
     * @pre true
     * @post @result.size() > 0
     */
    List<MenuItem> importProducts();

    /**
     * @pre !Objects.equals(product.computeTitle()."") && product.computeRating() >= 0 && product.computeCalories() >= 0 && product.computeProtein() >= 0 && product.computeFat() >= 0 && product.computeSodium() >= 0 && product.computePrice() >= 0
     * @post menuItems.size() = menuItems.size()@pre + 1
     */
    boolean addProduct(BaseProduct product);

    /**
     * @pre index >= 0 && index < menuItems.size()
     * @post menuItems.size() = menuItems.size()@pre - 1
     */
    void deleteProduct(int index);

    /**
     * @pre index >= 0 && index < menuItems.size() && menuItem != null
     * @pre !Objects.equals(menuItem.computeTitle(),"") && menuItem.computeRating() >= 0 && menuItem.computeCalories() >= 0 && menuItem.computeProtein() >= 0 && menuItem.computeFat() >= 0 && menuItem.computeSodium() >= 0 && menuItem.computePrice() >= 0
     * @post menuItems.get(index) == menuItem
     */
    void modifyProduct(int index, MenuItem menuItem);

    /**
     * @pre compositeProduct != null
     * @post menuItems.size() = menuItems.size()@pre + 1
     */
    boolean createProduct(CompositeProduct compositeProduct);

    /**
     * @pre startHour >= 0 && endHour <= 23 && startHour <= endHour
     * @post @nochange
     */
    void generateReport(int startHour, int endHour);

    /**
     * @pre numberOfTimes > 0
     * @post @nochange
     */
    void generateReport(int numberOfTimes);

    /**
     * @pre numberOfTimes > 0 && value > 0
     * @post @nochange
     */
    void generateReport(int numberOfTimes, double value);

    /**
     * @pre date != null
     * @post @nochange
     */
    void generateReport(Date date);

    /**
     * @pre user != null && orderComponents.size() > 0
     * @post OrderHashMap.size() = OrderHashMap.size()@pre + 1
     */
    void createOrder(User UserID, List<MenuItem> orderComponents);

    /**
     * @pre true
     * @post @nochange
     */
    List<MenuItem> searchProducts(String keyword, double rating, double calories, double protein, double fat, double sodium, double price);
}
