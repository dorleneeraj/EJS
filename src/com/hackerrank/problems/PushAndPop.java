package com.hackerrank.problems;

import java.util.ArrayList;
import java.util.List;

public class PushAndPop {

	static long[] minSegmentTree;
	static long[] maxSegmentTree;

	public static String OPERATION_PUSH = "push";
	public static String OPERATION_POP = "pop";

	public static void main(String[] args) {
		maxMin(new String[] { "push", "push", "push", "pop" }, new int[] { 1,
				2, 3, 1 });
	}

	static long[] maxMin(String[] operations, int[] x) {
		List<Long> returnValues = new ArrayList<Long>();
		List<Integer> list = new ArrayList<Integer>();
		int counter = 0;
		for (String operation : operations) {
			if (operation.equalsIgnoreCase(OPERATION_PUSH)) {
				list.add(x[counter]);
			} else if (operation.equalsIgnoreCase(OPERATION_POP)) {
				int index = 0;
				for (Integer i : list) {
					if (i == x[counter]) {
						break;
					}
					index++;
				}
				list.remove(index);
			}
			counter++;
			buildSegmentTree(list.toArray());
			long min = minSegmentTree[0];
			long max = maxSegmentTree[0];
			long ans = min * max;
			returnValues.add(ans);
		}

		long[] answer = new long[returnValues.size()];
		counter = 0;
		for (Long l : returnValues) {
			answer[counter++] = l;
		}
		return answer;
	}

	public static void buildSegmentTree(Object[] array) {
		int length = array.length;
		// The segment tree is a complete binary tree.
		// Meaning that, a node has either 0 or 2 child nodes.
		// Hence total number of nodes, given 'n' leaves in a complete binary
		// tree, total nodes is given by the formula, T = 2n - 1
		int height = (int) Math.ceil(Math.log(array.length) / Math.log(2));
		int segmentTreeLength = (int) (Math.pow(2, height + 1) - 1);
		minSegmentTree = new long[segmentTreeLength];
		maxSegmentTree = new long[segmentTreeLength];
		int[] newArray = new int[array.length];
		int counter = 0;
		for (Object o : array) {
			newArray[counter] = (int) o;
			counter++;
		}
		build(0, 0, length - 1, newArray);
	}

	private static void build(int treeIndex, int start, int end, int[] array) {
		if (start == end) {
			minSegmentTree[treeIndex] = array[start];
			maxSegmentTree[treeIndex] = array[start];
		} else {
			int mid = (start + end) / 2;
			build((treeIndex * 2) + 1, start, mid, array);
			build((treeIndex * 2) + 2, mid + 1, end, array);
			minSegmentTree[treeIndex] = Math.min(
					minSegmentTree[treeIndex * 2 + 1],
					minSegmentTree[treeIndex * 2 + 2]);

			maxSegmentTree[treeIndex] = Math.max(
					maxSegmentTree[treeIndex * 2 + 1],
					maxSegmentTree[treeIndex * 2 + 2]);
		}
	}
}
