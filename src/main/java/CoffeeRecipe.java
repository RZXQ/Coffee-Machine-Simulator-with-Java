enum CoffeeRecipe {

	ESPRESSO_RECIPE(250, 0, 16, 4), LATTE_RECIPE(350, 75, 20, 7), CAPPUCCINO_RECIPE(200, 100, 12, 6);

	private final int waterNeeded;

	private final int milkNeeded;

	private final int coffeeBeansNeeded;

	private final int costNeeded;

	CoffeeRecipe(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int price) {
		this.waterNeeded = waterNeeded;
		this.milkNeeded = milkNeeded;
		this.coffeeBeansNeeded = coffeeBeansNeeded;
		this.costNeeded = price;
	}

	public int getWaterNeeded() {
		return this.waterNeeded;
	}

	public int getMilkNeeded() {
		return this.milkNeeded;
	}

	public int getCoffeeBeansNeeded() {
		return this.coffeeBeansNeeded;
	}

	public int getCostNeeded() {
		return this.costNeeded;
	}

}
