package BusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {
    public BaseProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price)
    {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public String computeTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double computeRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public double computeCalories() {
        return this.calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public double computeProtein() {
        return this.protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public double computeFat() {
        return this.fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    @Override
    public double computeSodium() {
        return this.sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    @Override
    public double computePrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
