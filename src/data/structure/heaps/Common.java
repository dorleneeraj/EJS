package data.structure.heaps;

import java.util.Comparator;

/**
 * 
 * @author neeraj
 *
 */

public class Common {
	static class IntegerComparator<T, K> implements Comparator<Node<T, K>> {

		/**
		 * Return 0 if i1 <= i2. Returns 1 if i1 > i2.
		 */
		@Override
		public int compare(Node<T, K> o1, Node<T, K> o2) {

			int returnVal = 1;
			if (null != o1 && null != o2) {
				Integer i1 = (Integer) o1.getKey();
				Integer i2 = (Integer) o2.getKey();
				if (i1 <= i2) {
					returnVal = 0;
				} else {
					returnVal = 1;
				}
			}
			return returnVal;

		}

	}

	static class ListComparator<T, K> implements Comparator<Node<T, K>> {

		@Override
		public int compare(Node<T, K> o1, Node<T, K> o2) {
			int returnVal = 1;
			if (null != o1 && null != o2) {
				Integer i1 = (Integer) o1.getKey();
				Integer i2 = (Integer) o2.getKey();
				if (i1 < i2) {
					returnVal = -1;
				} else {
					returnVal = 1;
				}
			}
			return returnVal;
		}

	}

	/**
	 * Returns 1 if o2 is greater than o1
	 * 
	 * @author neeraj
	 *
	 * @param <K>
	 */
	static class KIntegerComparator<K> implements Comparator<K> {

		@Override
		public int compare(K o1, K o2) {
			Integer i1 = (Integer) o1;
			Integer i2 = (Integer) o2;
			if (i2 > i1) {
				return 1;
			} else {
				return 0;
			}
		}

	}

}
