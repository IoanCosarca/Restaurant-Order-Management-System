package BusinessLayer;

import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<BaseProduct> products;

    public CompositeProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public double computePrice() {
        return 0;
    }

    public void add(MenuItem item) {}

    public void remove(MenuItem item) {}
}
