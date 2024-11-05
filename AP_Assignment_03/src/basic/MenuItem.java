package basic;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    String name;
    double price;
    String category;
    boolean available;
    List<Review> reviews;

    public MenuItem(String name, double price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
        this.reviews = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (" + category + ") " + (available ? "Available" : "Unavailable");
    }

    public double getPrice() {
        return price;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

}