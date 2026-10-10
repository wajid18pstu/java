/*
3. Create a class named Pizza that stores information about a single pizza. It should contain the
following:
∙ Private instance variables to store the size of the pizza (either small, medium, or large), the
number of cheese toppings, the number of pepperoni toppings, and the number of ham toppings
∙ Constructor(s) that set all of the instance variables.
∙ Public methods to get and set the instance variables.
∙ A public method named calcCost( ) that returns a double that is the cost of the pizza.
Pizza cost is determined by:
Small: $10 + $2 per topping
Medium: $12 + $2 per topping
Large: $14 + $2 per topping
∙ public method named getDescription( ) that returns a String containing the pizza size, quantity
of each topping.
Write test code to create several pizzas and output their descriptions. For example, a large pizza
with one cheese, one pepperoni and two ham toppings should cost a total of $22. Now Create a
PizzaOrder class that allows up to three pizzas to be saved in an order. Each pizza saved should be
a Pizza object. Create a method calcTotal() that returns the cost of order. In the runner order two
pizzas and return the total cost.
*/
class Pizza {
    private String size;          // "small", "medium" or "large"
    private int numCheese;
    private int numPepperoni;
    private int numHam;

    public Pizza(String size, int numCheese, int numPepperoni, int numHam) {
        setSize(size);
        this.numCheese = numCheese;
        this.numPepperoni = numPepperoni;
        this.numHam = numHam;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        String s = size.toLowerCase();
        if (!s.equals("small") && !s.equals("medium") && !s.equals("large")) {
            throw new IllegalArgumentException("Size must be small, medium, or large");
        }
        this.size = s;
    }

    public int getNumCheese() {
        return numCheese;
    }

    public void setNumCheese(int numCheese) {
        this.numCheese = numCheese;
    }

    public int getNumPepperoni() {
        return numPepperoni;
    }

    public void setNumPepperoni(int numPepperoni) {
        this.numPepperoni = numPepperoni;
    }

    public int getNumHam() {
        return numHam;
    }

    public void setNumHam(int numHam) {
        this.numHam = numHam;
    }

    public double calcCost() {
        double base;
        switch (size) {
            case "small":  base = 10; break;
            case "medium": base = 12; break;
            default:       base = 14; break;   // large
        }
        return base + 2.0 * (numCheese + numPepperoni + numHam);
    }

    public String getDescription() {
        return "Size: " + size
                + ", Cheese toppings: " + numCheese
                + ", Pepperoni toppings: " + numPepperoni
                + ", Ham toppings: " + numHam
                + ", Cost: $" + String.format("%.2f", calcCost());
    }
}

class PizzaOrder {
    private static final int MAX_PIZZAS = 3;
    private final Pizza[] pizzas = new Pizza[MAX_PIZZAS];
    private int count = 0;

    // Returns false if the order already holds three pizzas
    public boolean addPizza(Pizza pizza) {
        if (count >= MAX_PIZZAS) {
            return false;
        }
        pizzas[count++] = pizza;
        return true;
    }

    public double calcTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += pizzas[i].calcCost();
        }
        return total;
    }
}

public class Exercise3 {
    public static void main(String[] args) {
        // Test code: create several pizzas and output their descriptions
        Pizza p1 = new Pizza("large", 1, 1, 2);    // should cost $22
        Pizza p2 = new Pizza("small", 0, 2, 0);
        Pizza p3 = new Pizza("medium", 1, 0, 1);

        System.out.println(p1.getDescription());
        System.out.println(p2.getDescription());
        System.out.println(p3.getDescription());

        // Runner: order two pizzas and return the total cost
        PizzaOrder order = new PizzaOrder();
        order.addPizza(p1);
        order.addPizza(p2);

        System.out.println();
        System.out.println("Total cost of the order: $" + String.format("%.2f", order.calcTotal()));
    }
}
