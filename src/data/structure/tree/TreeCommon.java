package data.structure.tree;

import java.util.Comparator;

public class TreeCommon {

	static class IntegerComparator<T> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			Integer i1 = (Integer) o1;
			Integer i2 = (Integer) o2;
			if (i1 < i2) {
				return -1;
			} else if (i1 == i2) {
				return 0;
			} else {
				return 1;
			}

		}

	}

	static class IntegerNodeComparator implements Comparator<TreeNode<Integer>> {
		@Override
		public int compare(TreeNode<Integer> o1, TreeNode<Integer> o2) {
			Integer i1 = o1.getData();
			Integer i2 = o2.getData();
			return new IntegerComparator<Integer>().compare(i1, i2);
		}
	}
}
