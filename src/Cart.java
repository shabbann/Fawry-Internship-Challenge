import java.util.HashMap;
import java.util.Map;

class Cart {

    private final Map<Product, Integer> items = new HashMap<>();
    private final InventoryService inventoryService;

    public Cart(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void addProduct(Product product, int quantity) {
        if (!inventoryService.isStockAvailable(product.getName(), quantity)) {
            throw new IllegalStateException("Product out of stock or expired: " + product.getName());
        }
        this.items.put(product, this.items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}