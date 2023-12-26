abstract class Coffee {

	private final CoffeeRecipe coffeeRecipe;

	protected Coffee(CoffeeRecipe coffeeRecipe) {
		this.coffeeRecipe = coffeeRecipe;
	}

	public CoffeeRecipe getCoffeeRecipe() {
		return this.coffeeRecipe;
	}

}
