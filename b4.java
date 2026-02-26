import java.util.ArrayList;
import java.util.List;

// 1. Lớp Product
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

// 2. Interface ProductProcessor
interface ProductProcessor {

    // abstract method
    double calculateTotalValue(List<Product> products);

    // static method
    static void printProductList(List<Product> products) {
        System.out.println("Danh sách sản phẩm:");
        products.forEach(System.out::println);
    }

    // default method
    default boolean hasExpensiveProduct(List<Product> products) {
        return products.stream().anyMatch(p -> p.getPrice() > 100);
    }
}

// 3. Lớp ProductProcessorImpl
class ProductProcessorImpl implements ProductProcessor {

    @Override
    public double calculateTotalValue(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}

// 4. Chương trình chính
public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1200));
        products.add(new Product("Mouse", 25));
        products.add(new Product("Keyboard", 75));
        products.add(new Product("Monitor", 200));

        ProductProcessor processor = new ProductProcessorImpl();

        // In danh sách sản phẩm
        ProductProcessor.printProductList(products);

        // Kiểm tra sản phẩm > 100
        if (processor.hasExpensiveProduct(products)) {
            System.out.println("Có sản phẩm đắt tiền (>100)");
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }

        // Tính tổng giá trị
        double total = processor.calculateTotalValue(products);
        System.out.println("Tổng giá trị sản phẩm: " + total);
    }
}