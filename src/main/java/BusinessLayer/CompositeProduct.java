package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private final List<BaseProduct> ProductComposition = new ArrayList<>();

    @Override
    public String computeTitle() {
        return this.title;
    }

    public void setTitle(String title)
    {
        String s = title + "[";
        for (BaseProduct product : ProductComposition)
        {
            s = s + product.toString();
            if (ProductComposition.indexOf(product) != ProductComposition.size() - 1) {
                s = s + ", ";
            }
        }
        this.title = s + "]";
    }

    @Override
    public double computeRating()
    {
        int k = ProductComposition.size();
        double rating = 0;
        for (BaseProduct baseProduct : ProductComposition) {
            rating += baseProduct.computeRating();
        }
        return rating / k;
    }

    @Override
    public double computeCalories()
    {
        double calories = 0;
        for (BaseProduct baseProduct : ProductComposition) {
            calories += baseProduct.computeCalories();
        }
        return calories;
    }

    @Override
    public double computeProtein()
    {
        double protein = 0;
        for (BaseProduct baseProduct : ProductComposition) {
            protein += baseProduct.computeProtein();
        }
        return protein;
    }

    @Override
    public double computeFat()
    {
        double fat = 0;
        for (BaseProduct baseProduct : ProductComposition) {
            fat += baseProduct.computeFat();
        }
        return fat;
    }

    @Override
    public double computeSodium()
    {
        double sodium = 0;
        for (BaseProduct baseProduct : ProductComposition) {
            sodium += baseProduct.computeSodium();
        }
        return sodium;
    }

    @Override
    public double computePrice()
    {
        double price = 0;
        for (BaseProduct baseProduct : ProductComposition) {
            price += baseProduct.computePrice();
        }
        return price;
    }

    public void add(BaseProduct product) {
        ProductComposition.add(product);
    }

    public void remove(BaseProduct product) {
        ProductComposition.remove(product);
    }

    public BaseProduct getValueAt(int index) {
        return ProductComposition.get(index);
    }
}
