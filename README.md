## My Approach and Assumptions

*   **Inventory Management:** I used a `PriorityQueue` for expirable products to simulate better warehouse system.
*   **Checkout Validation:** The `CheckoutService` this kinda wraps all business and logical operation.

## Test it
you can compile and run the `Main` class that show all test cases:

## Final Output of `Main`

```
--- Ahmed's Shopping ---
** Shipment notice **
1x TV       1800.0g
2x Cheese       400.0g
Total package weight 2.2kg

** Checkout receipt **
1x TV       2000.0 EGP
1x Mobile Scratch Card       15.0 EGP
2x Cheese       200.0 EGP
Subtotal        2215.0 EGP
Shipping        110.0 EGP
Amount          2325.0 EGP
Customer current balance: 2675.0 EGP

------------------------------------

--- checkout with a out-of-stock item ---
failed: Product out of stock or expired: TV

--- checkout with an empty cart ---
failed: Cart is empty.

------------------------------------

--- checkout with an expired item ---
failed: Product out of stock or expired: Expired Cheese

------------------------------------

--- checkout with insufficient funds ---
failed: Insufficient funds.
```