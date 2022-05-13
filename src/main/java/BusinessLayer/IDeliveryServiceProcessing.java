package BusinessLayer;

import java.util.List;

public interface IDeliveryServiceProcessing {

    public List<MenuItem> importProducts();

    public boolean addProduct(BaseProduct product);

    public void deleteProduct(int index);

    public void modifyProduct(int index, BaseProduct product);

    public void createProduct();

    public void generateReports();

    public void createOrder();

    public void searchProduct();
}
