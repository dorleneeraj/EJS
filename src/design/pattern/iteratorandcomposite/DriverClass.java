package design.pattern.iteratorandcomposite;

public class DriverClass {
	public static void main(String[] args) {
		MenuComponent allMenus = new Menu("ALL", "Menu Card");

		MenuComponent vegetarianMenu = new Menu("ALL Vegetarian",
				"Vegetarian Menu Card");
		vegetarianMenu.add(new MenuItem("Poha",
				"traditional maharashtrian dish", true, 10));
		vegetarianMenu.add(new MenuItem("Sheera", "Sweet Maharashtrian dish. ",
				true, 20));
		vegetarianMenu.add(new MenuItem("Khichadi",
				"Best snacks for morning. ", true, 30));

		allMenus.add(vegetarianMenu);

		MenuComponent eggDishes = new Menu("Egg dishes", "Rojj khao aande!");
		eggDishes.add(new MenuItem("egg bhurji", "healthy!", false, 10));
		eggDishes.add(new MenuItem("Cheese Omlette", "Mouth watery omlette",
				false, 30));
		allMenus.add(eggDishes);

		MenuComponent chickenDishes = new Menu("Chicken Dishes", "Yummy!");
		chickenDishes
				.add(new MenuItem("Chicken masala", "Awesome", false, 200));
		allMenus.add(chickenDishes);

		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();

		System.out.println("--------------------------");
		waitress.printVegetarianMenu();

	}
}
