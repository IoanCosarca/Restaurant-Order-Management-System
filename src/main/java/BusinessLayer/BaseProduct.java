package BusinessLayer;

import java.io.Serializable;
import java.util.Objects;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public double computePrice() {
        return this.getPrice();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
