package design.pattern.iteratorandcomposite;

import java.util.Iterator;

public class MenuItem extends MenuComponent {
	String name;
	String description;
	boolean isVegetarian;
	double price;

	public MenuItem(String name, String description, boolean isVegetarian,
			double price) {
		this.description = description;
		this.name = name;
		this.price = price;
		this.isVegetarian = isVegetarian;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public boolean isVegetarian() {
		return this.isVegetarian;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void print() {
		System.out.println(" " + this.name);
		if (isVegetarian) {
			System.out.println(" (v)");
		}
		System.out.println(" " + this.description);
		System.out.println(" " + this.price);
	}

	@Override
	public Iterator<MenuComponent> getIterator() {
		return new NullIterator();
	}

	private class NullIterator implements Iterator<MenuComponent> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public MenuComponent next() {
			return null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
