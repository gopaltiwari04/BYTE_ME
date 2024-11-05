package basic;

import java.time.LocalDate;
import java.util.List;

public class Order implements Comparable<Order> {
    static int orderCounter = 0;
    int orderId;
    List<MenuItem> items;
    String status;
    boolean isVIP;
    String specialRequest;
    LocalDate date;

    public Order(List<MenuItem> items, boolean isVIP, String specialRequest) {
        this.orderId = ++orderCounter;
        this.items = items;
        this.status = "Received";
        this.isVIP = isVIP;
        this.specialRequest = specialRequest;
        this.date = LocalDate.now();
    }

    @Override
    public int compareTo(Order other) {
        return Boolean.compare(other.isVIP, this.isVIP); // VIP orders are prioritized
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " - Status: " + status + " - VIP: " + isVIP;
    }
}
