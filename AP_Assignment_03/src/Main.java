import basic.Admin;
import basic.Customer;
import basic.MenuItem;
import basic.Order;
import basic.Review;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();

        admin.addItem("Burger", 5.0, "Meal", true);
        admin.addItem("Fries", 2.0, "Snack", true);
        admin.addItem("Pizza", 8.0, "Meal", true);

        while (true) {
            System.out.println("Select Role: \n1. Admin \n2. Customer \n3. Exit");
            int role = sc.nextInt();
            sc.nextLine();

            if (role == 1) {
                adminInterface(admin, sc);
            } else if (role == 2) {
                System.out.print("Enter Customer Name: ");
                String name = sc.nextLine();
                System.out.print("VIP Customer (true/false): ");
                boolean isVIP = sc.nextBoolean();
                sc.nextLine();
                Customer customer = new Customer(name, isVIP, admin);
                customerInterface(customer, sc);
            } else if (role == 3) {
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        sc.close();
    }

    public static void adminInterface(Admin admin, Scanner sc) {
        while (true) {
            System.out.println("Admin Menu: \n1. View Menu \n2. Add Item \n3. Update Item \n4. Remove Item \n5. View Orders \n6. Update Order \n7. Generate Report \n8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                admin.menu.forEach(System.out::println);
            } else if (choice == 2) {
                System.out.print("Enter name, price, category, available (true/false): ");
                String name = sc.nextLine();
                double price = sc.nextDouble();
                String category = sc.next();
                boolean available = sc.nextBoolean();
                admin.addItem(name, price, category, available);
            } else if (choice == 3) {
                System.out.print("Enter name, new price, availability (true/false): ");
                String name = sc.nextLine();
                double price = sc.nextDouble();
                boolean available = sc.nextBoolean();
                admin.updateItem(name, price, available);
            } else if (choice == 4) {
                System.out.print("Enter item name to remove: ");
                String name = sc.nextLine();
                admin.removeItem(name);
            } else if (choice == 5) {
                admin.viewOrders();
            } else if (choice == 6) {
                System.out.print("Enter order ID and status: ");
                int id = sc.nextInt();
                String status = sc.next();
                admin.updateOrderStatus(id, status);
            } else if (choice == 7) {
                admin.generateReport();
            } else if (choice == 8) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    /*public static void customerInterface(Customer customer, Scanner sc) {
        while (true) {
            System.out.println("Customer Menu: \n1. Browse Menu \n2. Add to Cart \n3. Remove from Cart \n4. Modify Cart Quantity \n5. Checkout \n6. View Cart Total \n7. View Order History \n8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    browseMenuOptions(customer, sc);
                    break;

                case 2:
                    System.out.print("Enter item name to add to cart: ");
                    String addItemName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int addQuantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    customer.addToCart(addItemName, addQuantity);
                    break;

                case 3:
                    System.out.print("Enter item name to remove from cart: ");
                    String removeItemName = sc.nextLine();
                    customer.removeFromCart(removeItemName);
                    break;

                case 4:
                    System.out.print("Enter item name to modify quantity: ");
                    String modifyItemName = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    customer.modifyCartQuantity(modifyItemName, newQuantity);
                    break;

                case 5:
                    System.out.print("Enter any special request for checkout: ");
                    String specialRequest = sc.nextLine();
                    customer.checkout(specialRequest);
                    break;

                case 6:
                    customer.viewCartTotal();
                    break;

                case 7:
                    customer.viewOrderHistory();
                    break;

                case 8:
                    System.out.println("Exiting Customer Menu.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }*/

    public static void customerInterface(Customer customer, Scanner sc) {
        while (true) {
            System.out.println("Customer Menu: \n1. Browse Menu \n2. Add to Cart \n3. Remove from Cart \n4. Modify Cart Quantity \n5. Checkout \n6. View Cart Total \n7. View Order History \n8. View Order Status \n9. Cancel Order \n10. Provide Review \n11. View Reviews \n12. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    browseMenuOptions(customer, sc);
                    break;

                case 2:
                    System.out.print("Enter item name to add to cart: ");
                    String addItemName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int addQuantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    customer.addToCart(addItemName, addQuantity);
                    break;

                case 3:
                    System.out.print("Enter item name to remove from cart: ");
                    String removeItemName = sc.nextLine();
                    customer.removeFromCart(removeItemName);
                    break;

                case 4:
                    System.out.print("Enter item name to modify quantity: ");
                    String modifyItemName = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    customer.modifyCartQuantity(modifyItemName, newQuantity);
                    break;

                case 5:
                    System.out.print("Enter any special request for checkout: ");
                    String specialRequest = sc.nextLine();
                    customer.checkout(specialRequest);
                    break;

                case 6:
                    customer.viewCartTotal();
                    break;

                case 7:
                    customer.viewOrderHistory();
                    break;

                case 8:
                    System.out.print("Enter Order ID to view status: ");
                    int orderIdStatus = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    customer.viewOrderStatus(orderIdStatus);
                    break;

                case 9:
                    System.out.print("Enter Order ID to cancel: ");
                    int orderIdCancel = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    customer.cancelOrder(orderIdCancel);
                    break;

                case 10:
                    System.out.print("Enter item name to review: ");
                    String reviewItemName = sc.nextLine();
                    System.out.print("Enter your review: ");
                    String reviewText = sc.nextLine();
                    customer.provideReview(reviewItemName, reviewText);
                    break;

                case 11:
                    System.out.print("Enter item name to view reviews: ");
                    String viewReviewsItemName = sc.nextLine();
                    customer.viewReviews(viewReviewsItemName);
                    break;

                case 12:
                    System.out.println("Exiting Customer Menu.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void browseMenuOptions(Customer customer, Scanner sc) {
        while (true) {
            System.out.println("\nBrowse Menu Options: \n1. View All Items \n2. Search by Name \n3. Filter by Category \n4. Sort by Price \n5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int browseChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (browseChoice) {
                case 1:
                    customer.browseMenu();
                    break;

                case 2:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    customer.searchMenu(keyword);
                    break;

                case 3:
                    System.out.print("Enter category to filter by: ");
                    String category = sc.nextLine();
                    customer.filterMenu(category);
                    break;

                case 4:
                    System.out.print("Sort by price (1 for ascending, 2 for descending): ");
                    int sortChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    boolean ascending = (sortChoice == 1);
                    customer.sortMenuByPrice(ascending);
                    break;

                case 5:
                    System.out.println("Returning to Main Menu.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
