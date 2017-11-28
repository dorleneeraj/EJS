package com.collectn.pckg;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Class3 {
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		l1.add(6);

		ListIterator<Integer> li = l1.listIterator();
		while (li.hasNext()) {
			System.out.println(li.next());
		}
		li.add(7);
		System.out.println(l1);
		while (li.hasPrevious()) {
			System.out.println(li.previous());
		}

		System.out.println("new list");

		List<Integer> l2 = new ArrayList<Integer>();
		ListIterator<Integer> li2 = l2.listIterator();
		li2.add(5);
		li2.add(8);
		System.out.println(li2.nextIndex());
		System.out.println(li2.previousIndex());
		while (li2.hasNext()) {
			System.out.println(li2.next());
		}
		while (li2.hasPrevious()) {
			System.out.println(li2.previous());
		}

		while (li2.hasNext()) {
			System.out.println(li2.next());
		}

		l2.add(10);
		li2.next();

	}
}
