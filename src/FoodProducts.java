import java.time.LocalDate;

public class FoodProducts extends Product implements Shippable, Expirable {

    private final double weight;
    private final LocalDate expiryDate;

    public FoodProducts(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(this.expiryDate);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

}
