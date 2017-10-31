package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxUnsortedArray {
	public static void main(String[] args) {
		System.out
				.println(getMaxUnsortedArrayBruteForce(new ArrayList<Integer>(
						Arrays.asList(new Integer[] { 1, 3, 45, 55, 7, 8, 36,
								46, 56, 32, 2, 60, 70, 80 }))));
		System.out
				.println(getMaxUnsortedArrayBruteForce(new ArrayList<Integer>(
						Arrays.asList(new Integer[] { 1, 3, 2, 4, 5 }))));
		System.out
				.println(getMaxUnsortedArrayBruteForce(new ArrayList<Integer>(
						Arrays.asList(new Integer[] { 1, 1 }))));

		System.out
				.println(getMaxUnsortedArrayBruteForce(new ArrayList<Integer>(
						Arrays.asList(new Integer[] { 3, 3, 4, 5, 5, 9, 11, 13,
								14, 15, 15, 16, 15, 20, 16 }))));
		System.out.println(subUnsort(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 1, 3, 45, 55, 7, 8, 36, 46, 56, 32, 2,
						60, 70, 80 }))));
	}

	public static ArrayList<Integer> getMaxUnsortedArrayBruteForce(
			ArrayList<Integer> A) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int currentMaxValue = A.get(0);
		int currentMaxIndex = 0;
		int finalIndex = 0;
		int currentLowestIndex = -1;
		int currentLowestValue = -1;
		int count = 0;
		boolean found = false;
		if (null != A && A.size() > 1) {
			list.add(-1);
			for (int i = 1; i < A.size(); i++) {
				if (A.get(i) < currentMaxValue) {
					finalIndex = i;
					if (count == 0) {
						list.remove(0);
						currentLowestValue = A.get(i);
						found = true;
					} else if (currentLowestValue > A.get(i)) {
						currentLowestValue = A.get(i);
					}
					int j = i - 1;
					while ((j >= 0)
							&& (A.get(i) < A.get(j)
									|| A.get(i) < currentLowestValue || j >= currentMaxIndex)) {
						j = j - 1;
					}
					if (count == 0) {
						currentLowestIndex = j + 1;
						count++;
					} else if ((j + 1) < currentLowestIndex) {
						currentLowestIndex = j + 1;
					}

				} else {

					currentMaxValue = A.get(i);
					currentMaxIndex = i;
				}
			}
		} else {
			list.add(-1);
		}
		if (found) {
			list.add(currentLowestIndex);
			list.add(finalIndex);
		}
		return list;
	}

	public static ArrayList<Integer> subUnsort(ArrayList<Integer> A) {

		int start = 0, end = 0, max = 0, i, min = A.get(0);
		boolean once = true;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (i = 0; i < A.size() - 1; i++) {
			if (A.get(i) < max)
				end = i;
			if (A.get(i) <= A.get(i + 1))
				continue;
			else {
				if (once) {
					start = i;
					once = false;
					min = A.get(i + 1);
				}
				if (A.get(i) > max)
					max = A.get(i);
				if (!once) {
					if (A.get(i + 1) < min)
						min = A.get(i + 1);
				}
			}
		}
		if (A.get(i) < max)
			end = i;
		for (i = 0; i <= start; i++) {
			if (A.get(i) <= min)
				continue;
			else {
				start = i;
				break;
			}
		}
		if (start >= 0 && end > 0) {
			arr.add(start);
			arr.add(end);
			return arr;
		} else {
			arr.add(-1);
			return arr;
		}
	}
}
