import java.time.LocalDate;

public class Main {

    public static void main(String... args) {
        InventoryService inventoryService = new InventoryService();
        populateInventory(inventoryService);

        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService, inventoryService);

        Customer customer = new Customer("Ahmed", 5000.0, inventoryService);
        Cart cart = customer.getCart();

        try {
            System.out.println("--- Ahmed's Shopping ---");
            cart.addProduct(new FoodProducts("Cheese", 100, 1, 200, LocalDate.now().plusDays(10)), 2);
            cart.addProduct(new Electronics("TV", 2000, 1), 1);
            cart.addProduct(new MobileScratchCard("Mobile Scratch Card", 15, 1), 1);
            checkoutService.processCheckout(customer, cart);
        } catch (IllegalStateException e) {
            System.out.println("failed: " + e.getMessage());
        }

        System.out.println("\n------------------------------------\n");

        Customer ahmed = new Customer("Ahmed", 100.0, inventoryService);
        Cart ahmedsCart = ahmed.getCart();

        try {
            System.out.println("--- checkout with a out-of-stock item ---");
            ahmedsCart.addProduct(new Electronics("TV", 2000, 1), 10);
        } catch (IllegalStateException e) {
            System.out.println("failed: " + e.getMessage());
        }

        try {
            System.out.println("\n--- checkout with an empty cart ---");
            checkoutService.processCheckout(ahmed, ahmedsCart);
        } catch (IllegalStateException e) {
            System.out.println("failed: " + e.getMessage());
        }

        System.out.println("\n------------------------------------\n");

        Customer youssef = new Customer("Youssef", 200.0, inventoryService);
        Cart youssefsCart = youssef.getCart();
        inventoryService.addProduct(new FoodProducts("Expired Cheese", 50, 1, 100, LocalDate.now().minusDays(1)));

        try {
            System.out.println("--- checkout with an expired item ---");
            youssefsCart.addProduct(new FoodProducts("Expired Cheese", 50, 1, 100, LocalDate.now().minusDays(1)), 1);
            checkoutService.processCheckout(youssef, youssefsCart);
        } catch (IllegalStateException e) {
            System.out.println("failed: " + e.getMessage());
        }

        System.out.println("\n------------------------------------\n");

        Customer lowBalanceCustomer = new Customer("Ali", 10.0, inventoryService);
        Cart lowBalanceCart = lowBalanceCustomer.getCart();

        try {
            System.out.println("--- checkout with insufficient funds ---");
            lowBalanceCart.addProduct(new Electronics("TV", 2000, 1), 1); // TV costs 2000, Ali has 10
            checkoutService.processCheckout(lowBalanceCustomer, lowBalanceCart);
        } catch (IllegalStateException e) {
            System.out.println("failed: " + e.getMessage());
        }

    }

    private static void populateInventory(InventoryService inventoryService) {
        for (int i = 0; i < 5; i++) {
            inventoryService.addProduct(new Electronics("TV", 2000, 1));
        }
        for (int i = 0; i < 10; i++) {
            inventoryService.addProduct(new Electronics("Mobile", 1000, 1));
        }
        for (int i = 0; i < 20; i++) {
            inventoryService.addProduct(new FoodProducts("Cheese", 100, 1, 200, LocalDate.now().plusDays(10)));
        }
        for (int i = 0; i < 15; i++) {
            inventoryService.addProduct(new FoodProducts("Biscuits", 150, 1, 350, LocalDate.now().plusDays(20)));
        }
        for (int i = 0; i < 50; i++) {
            inventoryService.addProduct(new MobileScratchCard("Mobile Scratch Card", 15, 1));
        }
    }
}