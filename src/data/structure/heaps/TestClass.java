package data.structure.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestClass {
	public static void main(String[] args) {
		List<Integer> mList = new ArrayList<Integer>();
		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(8);
		mList.add(9);
		mList.add(10);
		mList.add(5);
		mList.add(7);
		mList.add(6);

		System.out.println(mList);
		Collections.sort(mList, new IntegerComparator());
		System.out.println(mList);
		Collections.sort(mList);
		System.out.println(mList);

	}

	static class IntegerComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if (o1 < o2) {
				return -1;
			} else {
				return 1;
			}
		}

	}
}
