package com.collectn.pckg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Class2 {
	public static void main(String[] args) {
		// is java iterator fail-fast or fail-safe iterator
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		l1.add(6);

		Iterator<Integer> itr = l1.iterator();
		l1.remove(2);
		l1.add(5);
		System.out.println(l1);

		itr.next(); // What does this tell? This tells that even if we have
					// created iterator, we can modify the list. But the first
					// use of itr.next() throws comodification exception.
	}
}
