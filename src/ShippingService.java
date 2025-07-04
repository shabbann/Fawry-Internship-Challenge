import java.util.Map;

public class ShippingService {

    private static final double SHIPPING_RATE_PER_GRAM = 0.05;

    public double calculateShippingFee(Cart cart) {
        double totalWeight = 0;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product instanceof Shippable) {
                totalWeight += ((Shippable) product).getWeight() * quantity;
            }
        }
        return totalWeight * SHIPPING_RATE_PER_GRAM;
    }

    public void processShipment(Cart cart) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product instanceof Shippable) {
                double itemWeight = ((Shippable) product).getWeight() * quantity;
                totalWeight += itemWeight;
                System.out.println(quantity + "x " + product.getName() + "       " + itemWeight + "g");
            }
        }
        System.out.println("Total package weight " + totalWeight / 1000 + "kg");
        System.out.println();
    }
}