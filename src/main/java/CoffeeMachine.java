import java.util.Scanner;

public class CoffeeMachine {

	private static final String ML_OF_WATER = " ml of water";

	private static final String ML_OF_MILK = " ml of milk";

	private static final String G_OF_COFFEE_BEANS = " g of coffee beans";

	private static final String UNIT_OF_DISPOSABLE_CUPS = " disposable cups";

	private static final String UNIT_OF_MONEY = " of money";

	private final Scanner scanner = new Scanner(System.in);

	private int waterStorage;

	private int milkStorage;

	private int coffeeBeanStorage;

	private int disposableCups;

	private int totalEarnedMoney;

	public CoffeeMachine(int waterStorage, int milkStorage, int coffeeBeanStorage, int disposableCups, int money) {
		this.waterStorage = waterStorage;
		this.milkStorage = milkStorage;
		this.coffeeBeanStorage = coffeeBeanStorage;
		this.disposableCups = disposableCups;
		this.totalEarnedMoney = money;
	}

	public static void main(String[] args) {
		CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
		while (true) {
			coffeeMachine.chooseMode();
		}
	}

	private void printCoffeeMachineInfo() {
		System.out.println("\nThe coffee machine has:");
		System.out.println(this.waterStorage + ML_OF_WATER);
		System.out.println(this.milkStorage + ML_OF_MILK);
		System.out.println(this.coffeeBeanStorage + G_OF_COFFEE_BEANS);
		System.out.println(this.disposableCups + UNIT_OF_DISPOSABLE_CUPS);
		System.out.println("$" + this.totalEarnedMoney + UNIT_OF_MONEY);
	}

	private void chooseMode() {
		System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
		System.out.print("> ");

		CoffeMachineMode option = CoffeMachineMode.valueOf(this.scanner.next().toUpperCase());
		switch (option) {
			case BUY -> buyCoffee();
			case FILL -> fillResource();
			case TAKE -> takeMoney();
			case REMAINING -> printCoffeeMachineInfo();
			case EXIT -> exitProgram();
		}
	}

	private void buyCoffee() {
		System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
		System.out.println("> ");

		if (scanner.hasNextInt()) {
			int choice = scanner.nextInt();
			createCoffee(choice);
		}
		else if (scanner.hasNext()) {
			String str = scanner.next();
			if ("back".equals(str)) {
				return;
			}
			throw new IllegalArgumentException("Wrong input");
		}
	}

	private void createCoffee(int choice) {
		Coffee coffee = null;
		CoffeeRecipe coffeeRecipe = mapChoiceToCoffeeRecipe(choice);
		switch (coffeeRecipe) {
			case ESPRESSO_RECIPE -> {
				if (checkResource(CoffeeRecipe.ESPRESSO_RECIPE)) {
					coffee = new Espresso();
				}
			}
			case LATTE_RECIPE -> {
				if (checkResource(CoffeeRecipe.LATTE_RECIPE)) {
					coffee = new Latte();
				}
			}
			case CAPPUCCINO_RECIPE -> {
				if (checkResource(CoffeeRecipe.CAPPUCCINO_RECIPE)) {
					coffee = new Cappuccino();
				}
			}
		}

		if (coffee != null) {
			serveCoffee(coffeeRecipe);
		}
	}

	private CoffeeRecipe mapChoiceToCoffeeRecipe(int choice) {
		switch (choice) {
			case 1:
				return CoffeeRecipe.ESPRESSO_RECIPE;
			case 2:
				return CoffeeRecipe.LATTE_RECIPE;
			case 3:
				return CoffeeRecipe.CAPPUCCINO_RECIPE;
			default:
				throw new IllegalArgumentException("Invalid choice!");
		}
	}

	private void serveCoffee(CoffeeRecipe coffeeRecipe) {
		this.waterStorage -= coffeeRecipe.getWaterNeeded();
		this.milkStorage -= coffeeRecipe.getMilkNeeded();
		this.coffeeBeanStorage -= coffeeRecipe.getCoffeeBeansNeeded();
		this.totalEarnedMoney += coffeeRecipe.getCostNeeded();
		this.disposableCups--;
	}

	private boolean checkResource(CoffeeRecipe coffeeRecipe) {
		boolean result = false;
		if (this.waterStorage >= coffeeRecipe.getWaterNeeded() && this.milkStorage >= coffeeRecipe.getMilkNeeded()
				&& this.coffeeBeanStorage >= coffeeRecipe.getCoffeeBeansNeeded()) {
			System.out.println("I have enough resources, making you a coffee!");
			result = true;
		}
		if (this.waterStorage < coffeeRecipe.getWaterNeeded()) {
			System.out.println("Sorry, not enough water!");
		}
		if (this.milkStorage < coffeeRecipe.getMilkNeeded()) {
			System.out.println("Sorry, not enough milk!");
		}
		if (this.coffeeBeanStorage < coffeeRecipe.getCoffeeBeansNeeded()) {
			System.out.println("Sorry, not enough coffee beans!");
		}
		return result;
	}

	private void fillResource() {
		fillWater();
		fillMilk();
		fillCoffeBeans();
		fillDisposableCups();
	}

	private void fillDisposableCups() {
		System.out.println("Write how many disposable cups you want to add:");
		this.disposableCups += this.scanner.nextInt();
	}

	private void fillWater() {
		System.out.println("Write how many ml of water you want to add:");
		this.waterStorage += this.scanner.nextInt();
	}

	private void fillMilk() {
		System.out.println("Write how many ml of milk you want to add:");
		this.milkStorage += this.scanner.nextInt();
	}

	private void fillCoffeBeans() {
		System.out.println("Write how many grams of coffee beans you want to add:");
		this.coffeeBeanStorage += this.scanner.nextInt();
	}

	private void takeMoney() {
		System.out.println("I gave you $" + this.totalEarnedMoney);
		this.totalEarnedMoney = 0;
	}

	private void exitProgram() {
		System.exit(0);
	}

}
