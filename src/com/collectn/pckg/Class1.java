package com.collectn.pckg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
 * 	Before moving further with the Collection Framework, it is important to
 *  understand some basic concepts and have knowledge about other things  that
 *  are foundations of the Collection framework and its functioning.
 *  Such is the concept of iterator and iterable interfaces. 
 *   
 */

/**
 * 
 * 
 * @author neeraj
 *
 */
public class Class1 {
	public static void main(String[] args) {

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		l1.add(6);

		for (int i = 0; i < l1.size(); i++) {
			System.out.println(l1.get(i));
			if (l1.get(i) == 3) {
				l1.remove(i);
			}
		}

		System.out.println(l1);

		for (Integer i : l1) {
			if (i == 2) {
				l1.remove(1);
				break;
			}
		}

		System.out.println(l1);

		Iterator<Integer> iterator = l1.iterator();
		l1.add(7); // throws concurrent modification exception.
		l1.add(8);
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		iterator.remove();
		System.out.println(l1);

		iterator.remove(); // throws illegal state exception

	}
}