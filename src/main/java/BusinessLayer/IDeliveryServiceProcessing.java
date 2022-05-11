package BusinessLayer;

import java.util.List;

public interface IDeliveryServiceProcessing {

    public List<BaseProduct> importProducts();

    public void addProduct(BaseProduct product);

    public void deleteProduct();

    public void modifyProduct();

    public void createProduct();

    public void generateReports();

    public void createOrder();

    public void searchProduct();
}
