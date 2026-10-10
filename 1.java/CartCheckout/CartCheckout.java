import java.util.ArrayList;
import java.util.List;

/*
Create a Cart class that can hold items with price and quantity. Implement a checkout() method
that:
i.     Throws EmptyCartException if no items are present.
ii.    Throws InsufficientBalanceException if total cost exceeds the available user balance.
*/

// Thrown when checkout is attempted on a cart with no items
class EmptyCartException extends Exception {
    public EmptyCartException(String message) {
        super(message);
    }
}

// Thrown when the total cost is more than the user's available balance
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// An item that can be placed in the cart
class Item {
    private final String name;
    private final double price;
    private final int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }
}

class Cart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(String name, double price, int quantity) {
        items.add(new Item(name, price, quantity));
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    // Takes the user's available balance and returns the balance left after payment
    public double checkout(double userBalance) throws EmptyCartException, InsufficientBalanceException {
        if (items.isEmpty()) {
            throw new EmptyCartException("Cannot check out: the cart is empty.");
        }

        double total = getTotal();
        if (total > userBalance) {
            throw new InsufficientBalanceException(String.format(
                    "Insufficient balance: total cost is %.2f but available balance is %.2f.",
                    total, userBalance));
        }

        items.clear();                      // order placed, so the cart is emptied
        return userBalance - total;
    }
}

public class CartCheckout {
    public static void main(String[] args) {

        // Case 1: empty cart -> EmptyCartException
        System.out.println("--- Case 1: empty cart ---");
        Cart emptyCart = new Cart();
        try {
            emptyCart.checkout(500);
        } catch (EmptyCartException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Case 2: total exceeds balance -> InsufficientBalanceException
        System.out.println("\n--- Case 2: insufficient balance ---");
        Cart cart2 = new Cart();
        cart2.addItem("Keyboard", 1200.00, 1);
        cart2.addItem("Mouse", 450.50, 2);          // total = 2101.00
        try {
            cart2.checkout(1500);
        } catch (EmptyCartException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Case 3: enough balance -> successful checkout
        System.out.println("\n--- Case 3: successful checkout ---");
        Cart cart3 = new Cart();
        cart3.addItem("Notebook", 80.00, 3);        // 240.00
        cart3.addItem("Pen", 10.00, 5);             // 50.00 -> total = 290.00
        try {
            double balanceLeft = cart3.checkout(1000);
            System.out.println("Checkout successful!");
            System.out.printf("Remaining balance: %.2f%n", balanceLeft);
        } catch (EmptyCartException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
