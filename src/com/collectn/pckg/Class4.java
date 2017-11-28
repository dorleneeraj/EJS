package com.collectn.pckg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Class4 {
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

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		l1.add(7);
		l1.add(8);
		l1.add(9);
		l1.add(10);

		Iterator<Integer> itr2 = l1.iterator();

		while (itr2.hasNext()) {
			System.out.println(itr2.next());
		}

	}

}

class Class5 implements Iterator<Integer> {

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
