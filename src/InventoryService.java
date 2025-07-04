import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class InventoryService {

    private final Map<String, Queue<Product>> inventory = new HashMap<>();
    //to simulate the expire system I used a pq to sort things by its expire date
    public void addProduct(Product product) {
        Queue<Product> productQueue = inventory.get(product.getName());

        if (productQueue == null) {
            if (product instanceof Expirable) {
                productQueue = new PriorityQueue<>(Comparator.comparing(p -> ((Expirable) p).getExpiryDate()));
            } else {
                productQueue = new LinkedList<>();
            }
            inventory.put(product.getName(), productQueue);
        }

        productQueue.add(product);
    }

    public boolean isStockAvailable(String productName, int quantity) {
        Queue<Product> productQueue = inventory.get(productName);

        if (productQueue == null || productQueue.isEmpty()) {
            return false;
        }

        if (productQueue.peek() instanceof Expirable) {
            long availableCount = productQueue.stream()
                    .filter(p -> !((Expirable) p).isExpired())
                    .count();
            return availableCount >= quantity;
        } else {
            return productQueue.size() >= quantity;
        }
    }

    public void fulfillOrder(String productName, int quantity) {
        Queue<Product> productQueue = inventory.get(productName);

        if (productQueue == null) {
            throw new IllegalStateException("Product not found");
        }

        for (int i = 0; i < quantity; i++) {
            if (productQueue.isEmpty()) {
                throw new IllegalStateException("Out of stock product: " + productName);
            }
            productQueue.poll();
        }
    }
}
