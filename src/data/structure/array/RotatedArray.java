package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;

public class RotatedArray {
	public static void main(String[] args) {
		System.out.println(new RotatedArray().solve(new ArrayList<Integer>(
				Arrays.asList(new Integer[] { 5137, 5525, 9511, 13269, 16255,
						16700, 19870, 23034, 29247, 29934, 34583, 41585, 42598,
						44113, 46035, 50147, 50737, 57084, 65916, 76905, 84098,
						85912, 92081, 92257, 95449 }))));
	}

	public int solve(final ArrayList<Integer> a) {
		int low = 0;
		int high = a.size() - 1;
		return findMin(a, low, high);
	}

	private int findMin(ArrayList<Integer> a, int low, int high) {
		int returnVal = -1;
		if (null != a && !a.isEmpty()) {
			if (a.size() == 1) {
				returnVal = a.get(0);
			} else if (a.size() == 2) {
				if (a.get(0) > a.get(1)) {
					returnVal = a.get(1);
				}
			} else {
				while (high >= low) {
					int mid = (high + low) / 2;
					// now check on the conditions of the mid.

					if (high == low) {
						returnVal = a.get(high);
						break;
					}

					// if the element just before the mid is greater that the
					// element at the mid, then the element at the mid has to be
					// the
					// pivot element.
					if (a.get(mid) < a.get(mid - 1)) {
						returnVal = a.get(mid);
						break;
					}

					if (a.get(mid) > a.get(mid + 1)) {
						returnVal = a.get(mid + 1);
						break;
					}

					if (a.get(low) > a.get(mid)) {
						high = mid - 1;
					} else if (a.get(mid) > a.get(high)) {
						low = mid + 1;
					} else if (a.get(low) < a.get(mid)) {
						// the array is not rotated.
						returnVal = a.get(0);
						break;
					}
				}
			}
		}
		return returnVal;
	}
}
