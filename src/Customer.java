class Customer {
    String name;
    double balance;
    private final Cart cart;

    public Customer(String name, double balance, InventoryService inventoryService) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart(inventoryService);
    }

    public Cart getCart() {
        return cart;
    }
}