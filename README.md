# BYTE_ME

# Restaurant Management System

This project is a simple console-based restaurant management system written in Java, which includes an `Admin` and `Customer` interface. It allows `Admin` users to manage menu items, view and update orders, and generate reports, while `Customer` users can browse the menu, add items to their cart, checkout, view order history, and leave reviews.

## Features

### Admin Interface
The Admin role allows for the following actions:
1. **View Menu**: Lists all available menu items.
2. **Add Item**: Adds a new item to the menu with name, price, category, and availability status.
3. **Update Item**: Updates price and availability of an existing item.
4. **Remove Item**: Removes an item from the menu.
5. **View Orders**: Displays all orders placed by customers.
6. **Update Order Status**: Changes the status of a specified order.
7. **Generate Report**: Generates a report summarizing orders and inventory.

### Customer Interface
The Customer role includes the following functionalities:
1. **Browse Menu**:
   - View all items.
   - Search items by name.
   - Filter items by category.
   - Sort items by price (ascending or descending).
2. **Add to Cart**: Adds selected items to the customer’s cart with specified quantities.
3. **Remove from Cart**: Removes specific items from the cart.
4. **Modify Cart Quantity**: Changes the quantity of a specific item in the cart.
5. **Checkout**: Completes the order with an optional special request.
6. **View Cart Total**: Displays the total price of items in the cart.
7. **View Order History**: Shows previous orders.
8. **View Order Status**: Checks the status of a specific order.
9. **Cancel Order**: Cancels an order by its ID.
10. **Provide Review**: Submits a review for a specific menu item.
11. **View Reviews**: Views all reviews for a specific menu item.

## How to Use

1. **Setup**: Make sure all classes (`Admin`, `Customer`, `MenuItem`, `Order`, `Review`) are in the `basic` package.
2. **Run the Program**: Execute the `Main` class to start the application.
3. **Select Role**: Choose between `Admin` or `Customer` to proceed with the respective interface.

## Example Usage

1. **Admin Login**:
   - Select `Admin` from the main menu.
   - Add or update items, view orders, or generate reports as needed.

2. **Customer Login**:
   - Enter a customer name and specify if they are a VIP.
   - Browse the menu, add items to cart, and proceed to checkout.
   - After checkout, view order history, check order status, or leave a review.

## Project Structure
- **Main.java**: The entry point of the application containing the main menu and navigation logic.
- **Admin.java**: Manages menu items, orders, and report generation.
- **Customer.java**: Manages the customer’s interaction with the menu and order process.
- **MenuItem.java, Order.java, Review.java**: Represent the core entities for menu items, orders, and reviews respectively.

## Future Improvements
- **Database Integration**: Store menu items and orders in a database.
- **User Authentication**: Add authentication for Admin and Customer roles.
- **Enhanced Order Tracking**: Implement more advanced tracking and notification system for orders.

## Requirements
- Java Development Kit (JDK) 8 or above.
