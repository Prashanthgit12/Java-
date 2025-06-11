import java.util.Scanner;
class Pizza {
    protected double basePrice;
    protected String[] toppings;
    protected boolean takeaway;

    public Pizza(double basePrice, String[] toppings, boolean takeaway) {
        this.basePrice = basePrice;
        this.toppings = toppings;
        this.takeaway = takeaway;
    }

    public double calculatePrice() {
        double price = basePrice;
        if (takeaway) {
            price += 10;
        }
        return price;
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder("Pizza with base price: $" + basePrice + "\n");
        description.append("Toppings: ");
        for (String topping : toppings) {
            description.append(topping).append(" ");
        }
        if (takeaway) {
            description.append("\nTakeaway: Yes");
        } else {
            description.append("\nTakeaway: No");
        }
        return description.toString();
    }
}

class VegPizza extends Pizza {
    public VegPizza(double basePrice, String[] toppings, boolean takeaway) {
        super(basePrice, toppings, takeaway);
    }

    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        return price; // No extra charge for Veg pizza
    }
}

class NonVegPizza extends Pizza {
    public NonVegPizza(double basePrice, String[] toppings, boolean takeaway) {
        super(basePrice, toppings, takeaway);
    }

    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        return price + 20; //extra charge for NonVegPizza
    }
}

class DeluxeVegPizza extends VegPizza {
    public DeluxeVegPizza(double basePrice, String[] toppings, boolean takeaway) {
        super(basePrice, toppings, takeaway);
    }

    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        return price + 15; //extra charge for DeluxeVegPizza
    }
}
class DeluxeNonVegPizza extends NonVegPizza {
    public DeluxeNonVegPizza(double basePrice, String[] toppings, boolean takeaway) {
        super(basePrice, toppings, takeaway);
    }

    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        return price + 25; //extra charge for DeluxeNonVegPizza
    }
}

public class PizzaBillGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter pizza type (Veg, NonVeg, DeluxeVeg, DeluxeNonVeg): ");
        String pizzaType = scanner.nextLine();

        System.out.println("Enter base price: ");
        double basePrice = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Enter number of toppings: ");
        int numToppings = scanner.nextInt();
        scanner.nextLine(); 
        String[] toppings = new String[numToppings];
        for (int i = 0; i < numToppings; i++) {
            System.out.println("Enter topping " + (i + 1) + ": ");
            toppings[i] = scanner.nextLine();
        }

        System.out.println("Takeaway (yes or no): ");
        boolean takeaway = scanner.nextLine().equalsIgnoreCase("yes");

        Pizza pizza = null;
        switch (pizzaType.toLowerCase()) {
            case "veg":
                pizza = new VegPizza(basePrice, toppings, takeaway);
                break;
            case "nonveg":
                pizza = new NonVegPizza(basePrice, toppings, takeaway);
                break;
            case "deluxeveg":
                pizza = new DeluxeVegPizza(basePrice, toppings, takeaway);
                break;
            case "deluxenonveg":
                pizza = new DeluxeNonVegPizza(basePrice, toppings, takeaway);
                break;
            default:
                System.out.println("Invalid pizza type.");
                System.exit(0);
        }

        System.out.println("\nPizza Bill:");
        System.out.println(pizza.getDescription());
        System.out.println("Total Price: $" + pizza.calculatePrice());
    }
}
