public class Electronics extends Product implements Shippable {

    double weight;
    public Electronics(String name, double price, int quantity) {
        super(name, price, quantity);
        switch (name){
            case "TV":
                this.weight = 1800;
                break;
            case "Mobile":
                this.weight=1000;
        }
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
