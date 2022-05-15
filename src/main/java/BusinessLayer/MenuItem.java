package BusinessLayer;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
    String title;
    double rating;
    double calories;
    double protein;
    double fat;
    double sodium;
    double price;
    int timesOrdered;

    public abstract String computeTitle();

    public abstract double computeRating();

    public abstract double computeCalories();

    public abstract double computeProtein();

    public abstract double computeFat();

    public abstract double computeSodium();

    public abstract double computePrice();

    public int computeTimesOrdered() {
        return this.timesOrdered;
    }

    public void setTimesOrdered() {
        timesOrdered = 0;
    }

    public void incTimesOrdered() {
        timesOrdered++;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MenuItem item = (MenuItem) obj;
        return Objects.equals(title, item.title);
    }

    @Override
    public String toString() {
        return title;
    }

    public boolean FilterTitle(String keyword) {
        return title.contains(keyword);
    }

    public boolean FilterRating(double rating)
    {
        if (Double.compare(rating, -1) == 0) {
            return true;
        }
        return Double.compare(rating, this.rating) == 0;
    }

    public boolean FilterCalories(double calories)
    {
        if (Double.compare(calories, -1) == 0) {
            return true;
        }
        return Double.compare(calories, this.calories) == 0;
    }

    public boolean FilterProtein(double protein)
    {
        if (Double.compare(protein, -1) == 0) {
            return true;
        }
        return Double.compare(protein, this.protein) == 0;
    }

    public boolean FilterFat(double fat)
    {
        if (Double.compare(fat, -1) == 0) {
            return true;
        }
        return Double.compare(fat, this.fat) == 0;
    }

    public boolean FilterSodium(double sodium)
    {
        if (Double.compare(sodium, -1) == 0) {
            return true;
        }
        return Double.compare(sodium, this.sodium) == 0;
    }

    public boolean FilterPrice(double price)
    {
        if (Double.compare(price, -1) == 0) {
            return true;
        }
        return Double.compare(price, this.price) == 0;
    }
}
