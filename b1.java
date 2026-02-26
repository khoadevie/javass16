import java.util.*;
import java.util.stream.Collectors;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        if (price < 0) throw new IllegalArgumentException("Price must >= 0");
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + price;
    }
}

public class Main {

    static Map<Integer, Product> products = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Product Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Display Products");
            System.out.println("5. Filter Products (Price > 100)");
            System.out.println("6. Total Value of Products");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> editProduct();
                    case 3 -> deleteProduct();
                    case 4 -> displayProducts();
                    case 5 -> filterProducts();
                    case 6 -> totalValue();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (products.containsKey(id)) {
            System.out.println("ID already exists!");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(sc.nextLine());

        products.put(id, new Product(id, name, price));
        System.out.println("Product added successfully.");
    }

    static void editProduct() {
        System.out.print("Enter ID to edit: ");
        int id = Integer.parseInt(sc.nextLine());

        Product p = products.get(id);
        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Enter new name: ");
        p.setName(sc.nextLine());

        System.out.print("Enter new price: ");
        p.setPrice(Double.parseDouble(sc.nextLine()));

        System.out.println("Product updated.");
    }

    static void deleteProduct() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        if (products.remove(id) != null)
            System.out.println("Deleted successfully.");
        else
            System.out.println("Product not found!");
    }

    static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products.");
            return;
        }
        products.values().forEach(System.out::println);
    }

    static void filterProducts() {
        List<Product> list = products.values()
                .stream()
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());

        if (list.isEmpty()) System.out.println("No products > 100");
        else list.forEach(System.out::println);
    }

    static void totalValue() {
        double total = products.values()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Total value = " + total);
    }
}
