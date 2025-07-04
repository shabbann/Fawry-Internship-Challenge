````## My Approach and Assumptions

*   **Inventory Management:** I used a `PriorityQueue` for expirable products to simulate better warehouse system.
*   **Checkout Validation:** The `CheckoutService` this kinda wraps all business and logical operation.
* 
## Test it
you can compile and run the `Main` class that show all test cases:

## Final Output of `Main`

```
--- Ahmed's Shopping Spree ---
** Shipment notice **
1x TV       1800.0g
2x Cheese       400.0g
Total package weight 2.2kg

** Checkout receipt **
1x TV       2000.0 EGP
2x Cheese       200.0 EGP
1x Mobile Scratch Card       15.0 EGP
Subtotal        2215.0 EGP
Shipping        110.0 EGP
Amount          2325.0 EGP
Customer current balance: 2675.0 EGP

------------------------------------

--- Ahmed tries to buy too many TVs ---
Action failed: Product out of stock or expired: TV

--- Ahmed tries to checkout with an empty cart ---
Action failed: Cart is empty.

------------------------------------

--- Youssef tries to buy an expired item ---
Action failed: Product out of stock or expired: Expired Cheese
```
````