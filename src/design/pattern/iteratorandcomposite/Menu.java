package design.pattern.iteratorandcomposite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Menu extends MenuComponent {
	List<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
	String name;
	String description;

	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}

	@Override
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}

	@Override
	public MenuComponent getChild(int i) {
		return menuComponents.get(i);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void print() {
		System.out.println(" " + this.name);
		System.out.println(" " + this.description);
		System.out.println("----------------------");
		Iterator<MenuComponent> itr = menuComponents.iterator();
		while (itr.hasNext()) {
			MenuComponent mC = itr.next();
			mC.print();
		}
	}

	@Override
	public Iterator<MenuComponent> getIterator() {
		return new CompositeIterator();
	}

	private class CompositeIterator implements Iterator<MenuComponent> {
		Stack<MenuComponent> stack = new Stack<MenuComponent>();

		public CompositeIterator() {
			this.stack.push(menuComponents.get(0));
		}

		@Override
		public boolean hasNext() {
			if (stack.isEmpty()) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public MenuComponent next() {
			MenuComponent mC = stack.pop();
			if (mC instanceof Menu) {
				Iterator<MenuComponent> iterator = ((Menu) mC).menuComponents
						.iterator();
				while (iterator.hasNext()) {
					this.stack.push(iterator.next());
				}
			}
			return mC;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
