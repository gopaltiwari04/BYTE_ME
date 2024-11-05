package basic;

import java.util.*;

public class Customer {
    String name;
    boolean isVIP;
    List<MenuItem> cart = new ArrayList<>();
    Map<MenuItem, Integer> cartQuantities = new HashMap<>();
    Admin admin;

    public Customer(String name, boolean isVIP, Admin admin) {
        this.name = name;
        this.isVIP = isVIP;
        this.admin = admin;
    }

    // View all items
    public void browseMenu() {
        for (MenuItem item : admin.menu) {
            System.out.println(item);
        }
    }

    // Search for items by name
    public void searchMenu(String keyword) {
        for (MenuItem item : admin.menu) {
            if (item.name.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item);
            }
        }
    }

    // Filter items by category
    public void filterMenu(String category) {
        for (MenuItem item : admin.menu) {
            if (item.category.equalsIgnoreCase(category)) {
                System.out.println(item);
            }
        }
    }

    // Sort items by price
    public void sortMenuByPrice(boolean ascending) {
        admin.menu.sort(Comparator.comparingDouble(MenuItem::getPrice));
        if (!ascending) {
            Collections.reverse(admin.menu);
        }
        browseMenu();
    }

    // Add items to cart with quantity
    public void addToCart(String itemName, int quantity) {
        for (MenuItem item : admin.menu) {
            if (item.name.equalsIgnoreCase(itemName) && item.available) {
                cart.add(item);
                cartQuantities.put(item, cartQuantities.getOrDefault(item, 0) + quantity);
                System.out.println(quantity + "x " + itemName + " added to cart.");
                return;
            }
        }
        System.out.println("Item not found or unavailable.");
    }

    // Modify quantity in cart
    public void modifyCartQuantity(String itemName, int newQuantity) {
        for (MenuItem item : cart) {
            if (item.name.equalsIgnoreCase(itemName)) {
                if (newQuantity > 0) {
                    cartQuantities.put(item, newQuantity);
                    System.out.println("Quantity of " + itemName + " updated to " + newQuantity);
                } else {
                    removeFromCart(itemName);
                }
                return;
            }
        }
        System.out.println("Item not found in cart.");
    }

    // Remove items from cart
    public void removeFromCart(String itemName) {
        cart.removeIf(item -> item.name.equalsIgnoreCase(itemName));
        cartQuantities.entrySet().removeIf(entry -> entry.getKey().name.equalsIgnoreCase(itemName));
        System.out.println(itemName + " removed from cart.");
    }


    // View total price of items in the cart
    public void viewCartTotal() {
        double total = 0;
        for (MenuItem item : cart) {
            total += item.price * cartQuantities.get(item);
        }
        System.out.println("Total: $" + total);
    }

    // Checkout process
    public void checkout(String specialRequest) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        // Create a new order with cart items, VIP status, and special request
        Order order = new Order(new ArrayList<>(cart), isVIP, specialRequest);
        admin.orders.add(order); // Adds to admin's queue
        System.out.println("Order placed: " + order);
        cart.clear(); // Clears cart after checkout
        cartQuantities.clear();
    }

    // Track order status
    public void viewOrderStatus(int orderId) {
        for (Order order : admin.orders) {
            if (order.orderId == orderId) {
                System.out.println("Order #" + orderId + " - Status: " + order.status);
                return;
            }
        }
        System.out.println("Order not found.");
    }

    // Cancel an order before it is processed
    public void cancelOrder(int orderId) {
        Iterator<Order> iterator = admin.orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.orderId == orderId && order.status.equals("Received")) {
                iterator.remove();
                System.out.println("Order #" + orderId + " has been canceled.");
                return;
            }
        }
        System.out.println("Order not found or cannot be canceled.");
    }

    // View order history
    public void viewOrderHistory() {
        if (admin.orderHistory.containsKey(name)) {
            for (Order order : admin.orderHistory.get(name)) {
                System.out.println(order);
            }
        } else {
            System.out.println("No order history found.");
        }
    }

    // Re-order a previous order
    public void reorder(int orderId) {
        if (admin.orderHistory.containsKey(name)) {
            for (Order order : admin.orderHistory.get(name)) {
                if (order.orderId == orderId) {
                    cart.addAll(order.items);
                    System.out.println("Order #" + orderId + " has been re-added to cart.");
                    return;
                }
            }
        }
        System.out.println("Order not found in history.");
    }

    // Provide a review for a specific item
    public void provideReview(String itemName, String reviewText) {
        for (MenuItem item : admin.menu) {
            if (item.name.equalsIgnoreCase(itemName)) {
                item.addReview(new Review(name, reviewText));
                System.out.println("Review added for " + itemName + ".");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    // View reviews of a specific item
    public void viewReviews(String itemName) {
        for (MenuItem item : admin.menu) {
            if (item.name.equalsIgnoreCase(itemName)) {
                System.out.println("Reviews for " + itemName + ":");
                for (Review review : item.reviews) {
                    System.out.println(review);
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }
}
