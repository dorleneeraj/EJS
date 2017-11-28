package design.pattern.iteratorandcomposite;

import java.util.Iterator;

public class Waitress {
	MenuComponent allMenus;

	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}

	public void printMenu() {
		allMenus.print();
	}

	public void printVegetarianMenu() {
		Iterator<MenuComponent> itr = allMenus.getIterator();
		while (itr.hasNext()) {
			try {
				MenuComponent mC = itr.next();
				if (mC.isVegetarian()) {
					mC.print();
				}
			} catch (Exception e) {
				// do nothing
			}
		}
	}
}
