package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MaxSumSubArray {
	static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
	static int maxSum = 0;

	public static void main(String[] args) {
		System.out.println(new MaxSumSubArray()
				.bottomUpWay(new ArrayList<Integer>(
						Arrays.asList(new Integer[] { -2, 1, -3, 4, -1, 2, 1,
								-5, 4 }))));

		// ArrayList<Integer> A = new ArrayList<Integer>(
		// Arrays.asList(new Integer[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
		// byMemoization(A, 8);
		// System.out.println(maxSum);
		// ArrayList<Integer> maxSubArray = buildList(A);
		// Collections.reverse(maxSubArray);
		// System.out.println(maxSubArray);
		System.out.println(maxSubArray(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 4, -3, 3, 8 }))));
	}

	private static ArrayList<Integer> buildList(ArrayList<Integer> A) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int endIndex = -1;
		for (Entry<Integer, Integer> entry : memo.entrySet()) {
			if (entry.getValue() == maxSum) {
				endIndex = entry.getKey();
			}
		}
		int currentIndex = endIndex;
		list.add(A.get(currentIndex));
		while (true) {
			int prevIndex = currentIndex - 1;
			if (prevIndex >= 0) {
				int prevSum = memo.get(prevIndex);
				int currentNo = A.get(currentIndex);
				int currentSum = memo.get(currentIndex);
				if (currentSum == prevSum + currentNo) {
					list.add(A.get(currentIndex - 1));
					currentIndex = prevIndex;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return list;
	}

	public ArrayList<Integer> bottomUpWay(ArrayList<Integer> A) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int maxSum = A.get(0);
		int maxArrayStartingIndex = 0;
		int MaxArrayEndingIndex = 0;
		ArrayList<Integer> maxSums = new ArrayList<Integer>();
		maxSums.add(maxSum);
		for (int i = 1; i < A.size(); i++) {
			int currentNo = A.get(i);
			if (currentNo + maxSums.get(i - 1) >= currentNo) {
				// include current no. in the existing sub-array.
				maxSums.add(currentNo + maxSums.get(i - 1));
				if (maxSum < currentNo + maxSums.get(i - 1)) {
					MaxArrayEndingIndex = i;
					maxSum = currentNo + maxSums.get(i - 1);
				}
			} else {
				maxSums.add(currentNo);
				if (currentNo > maxSum) {
					maxArrayStartingIndex = i;
					MaxArrayEndingIndex = i;
					maxSum = currentNo;
				}
			}
		}
		for (int j = maxArrayStartingIndex; j <= MaxArrayEndingIndex; j++) {
			list.add(A.get(j));
		}
		return list;
	}

	public static int byMemoization(ArrayList<Integer> A, int index) {
		int max = 0;
		if (index == 0) {
			max = A.get(index);
			memo.put(index, max);
			maxSum = max;
		} else {
			if (memo.containsKey(index)) {
				max = memo.get(index);
			} else {
				int prevIndex = index - 1;
				int currentNo = A.get(index);
				int prevSum = byMemoization(A, prevIndex);
				if (currentNo > (currentNo + prevSum)) {
					memo.put(index, currentNo);
					max = currentNo;

				} else {
					memo.put(index, currentNo + prevSum);
					max = currentNo + prevSum;
				}
			}
			if (max > maxSum) {
				maxSum = max;
			}
		}
		return max;
	}

	static public int maxSubArray(final List<Integer> a) {

		int sum = Integer.MIN_VALUE;
		int currentSum = 0;
		for (int num : a) {
			currentSum += num;
			sum = Math.max(currentSum, sum);
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		return sum;
	}
}
