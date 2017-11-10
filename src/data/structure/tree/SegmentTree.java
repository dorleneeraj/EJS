package data.structure.tree;

import java.util.Arrays;

public class SegmentTree {

	int[] segmentTree;
	int[] array = new int[] { 1, 3, 5, 7, 9, 11 };

	public static void main(String[] args) {
		SegmentTree sT = new SegmentTree();
		sT.buildSegmentTree(sT.array);
	}

	public void buildSegmentTree(int[] array) {
		int length = array.length;
		// The segment tree is a complete binary tree.
		// Meaning that, a node has either 0 or 2 child nodes.
		// Hence total number of nodes, given 'n' leaves in a complete binary
		// tree, total nodes is given by the formula, T = 2n - 1
		int height = (int) Math.ceil(Math.log(array.length) / Math.log(2));
		int segmentTreeLength = (int) (Math.pow(2, height + 1) - 1);
		segmentTree = new int[segmentTreeLength];
		Arrays.fill(segmentTree, -1);
		build(0, 0, length - 1);
		for (Integer i : segmentTree) {
			System.out.print(i + " ");
		}
	}

	private void build(int treeIndex, int start, int end) {
		if (start == end) {
			segmentTree[treeIndex] = array[start];
		} else {
			int mid = (start + end) / 2;
			build((treeIndex * 2) + 1, start, mid);
			build((treeIndex * 2) + 2, mid + 1, end);
			segmentTree[treeIndex] = segmentTree[treeIndex * 2 + 1]
					+ segmentTree[treeIndex * 2 + 2];
		}

	}
}
