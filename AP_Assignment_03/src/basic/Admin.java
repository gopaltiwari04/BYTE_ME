package basic;

import java.util.*;

public class Admin {
    public List<MenuItem> menu = new ArrayList<>();
    PriorityQueue<Order> orders = new PriorityQueue<>();
    Map<String, List<Order>> orderHistory = new HashMap<>();
    static int orderCounter = 0;

    public void addItem(String name, double price, String category, boolean available) {
        menu.add(new MenuItem(name, price, category, available));
    }

    public void updateItem(String name, double price, boolean available) {
        for (MenuItem item : menu) {
            if (item.name.equalsIgnoreCase(name)) {
                item.price = price;
                item.available = available;
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void removeItem(String name) {
        menu.removeIf(item -> item.name.equalsIgnoreCase(name));
        for (Order order : orders) {
            if (order.items.stream().anyMatch(item -> item.name.equalsIgnoreCase(name))) {
                order.status = "Denied";
            }
        }
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders to process.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.orderId == orderId) {
                order.status = status;
                if (status.equals("Completed")) {
                    orders.remove(order);
                    orderHistory.computeIfAbsent(order.toString(), k -> new ArrayList<>()).add(order);
                }
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public void generateReport() {
        System.out.println("Sales Report:");
        double totalSales = 0;
        for (List<Order> orderList : orderHistory.values()) {
            for (Order order : orderList) {
                totalSales += order.items.stream().mapToDouble(item -> item.price).sum();
            }
        }
        System.out.println("Total Sales: $" + totalSales);
    }
}
