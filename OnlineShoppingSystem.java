import java.util.*;

class Product {
    int id;
    String name;
    double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - ₹" + price;
    }
}

class ShoppingCart {
    Map<Product, Integer> cartItems = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + "x " + product.name + " added to cart.");
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\nYour Shopping Cart:");
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            System.out.println(p.name + " x" + qty + " = ₹" + (p.price * qty));
            total += p.price * qty;
        }
        System.out.println("Total: ₹" + total);
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checkout.");
            return;
        }
        displayCart();
        System.out.println("Checkout successful! Thank you for shopping.");
        cartItems.clear();
    }
}

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 50000));
        products.add(new Product(2, "Smartphone", 20000));
        products.add(new Product(3, "Headphones", 1500));
        products.add(new Product(4, "Smartwatch", 3000));

        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n=== Online Shopping System ===");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Products:");
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Enter product ID to add: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    if (productId > 0 && productId <= products.size()) {
                        cart.addToCart(products.get(productId - 1), quantity);
                    } else {
                        System.out.println("Invalid Product ID.");
                    }
                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:
                    cart.checkout();
                    break;

                case 5:
                    System.out.println("Exiting... Thank you for shopping!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
