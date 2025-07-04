import java.util.Map;

public class CheckoutService {

    private final ShippingService shippingService;
    private final InventoryService inventoryService;

    public CheckoutService(ShippingService shippingService, InventoryService inventoryService) {
        this.shippingService = shippingService;
        this.inventoryService = inventoryService;
    }

    public void processCheckout(Customer customer, Cart cart) {
        validateCartIsNotEmpty(cart);
        validateItemsAreInStockAndNotExpired(cart);

        double subtotal = calculateSubtotal(cart);
        double shippingFee = calculateShippingFee(cart);
        double totalAmount = subtotal + shippingFee;

        validateCustomerBalance(customer, totalAmount);
        processPayment(customer, totalAmount);

        shippingService.processShipment(cart);
        updateInventory(cart);

        printReceipt(customer, cart, subtotal, shippingFee, totalAmount);
    }

    private void validateItemsAreInStockAndNotExpired(Cart cart) {
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (!inventoryService.isStockAvailable(product.getName(), quantity)) {
                throw new IllegalStateException("Product out of stock or expired: " + product.getName());
            }
        }
    }

    private double calculateSubtotal(Cart cart) {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            subtotal += entry.getKey().price * entry.getValue();
        }
        return subtotal;
    }

    private void validateCustomerBalance(Customer customer, double amount) {
        if (customer.balance < amount) {
            throw new IllegalStateException("Insufficient funds.");
        }
    }

    private void printReceipt(Customer customer, Cart cart, double subtotal, double shippingFee, double totalAmount) {
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName() + "       " + (product.price * quantity) + " EGP");
        }
        System.out.println("Subtotal        " + subtotal + " EGP");
        System.out.println("Shipping        " + shippingFee + " EGP");
        System.out.println("Amount          " + totalAmount + " EGP");
        System.out.println("Customer current balance: " + customer.balance + " EGP");
    }

    private void validateCartIsNotEmpty(Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }
    }

    private void processPayment(Customer customer, double totalAmount) {
        customer.balance -= totalAmount;
    }

    private void updateInventory(Cart cart) {
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            inventoryService.fulfillOrder(entry.getKey().getName(), entry.getValue());
        }
    }

    private double calculateShippingFee(Cart cart) {
        return shippingService.calculateShippingFee(cart);
    }

}
