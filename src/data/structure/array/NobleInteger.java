package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// InterviewBit.

// This is not a valid solution. In case of duplicates this won't work. I
// mean all the duplicates will be included in the numbers greater.

// Work on distinct greater numbers.

public class NobleInteger {

	public static void main(String[] args) {
		System.out.println(solve(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 1, 1, 5, 6, 6, 6, 6, 6 }))));
	}

	public static int solve(ArrayList<Integer> A) {
		// n^2 running time
		boolean isNoble = false;
		for (int i = 0; i < A.size(); i++) {
			int count = 0;
			for (int j = 0; j < A.size(); j++) {
				if (A.get(j) > A.get(i)) {
					count++;
				}
			}
			if (count == A.get(i)) {
				isNoble = true;
				break;
			}
		}
		return isNoble ? 1 : -1;
	}

	public static int solve2(ArrayList<Integer> A) {
		boolean isNoble = false;
		Collections.sort(A);
		for (int i = 0; i < A.size() - 1; i++) {
			if (A.get(i) == A.get(i + 1)) {
				continue;
			} else {
				if (A.get(i) == A.size() - i - 1) {
					isNoble = true;
					break;
				}
			}
		}
		if (A.get(A.size() - 1) == 0) {
			isNoble = true;
		}
		return isNoble ? 1 : -1;
	}
}
