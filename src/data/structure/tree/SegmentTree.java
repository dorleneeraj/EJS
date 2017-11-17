package data.structure.tree;

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
		build(0, 0, length - 1);
		for (Integer i : segmentTree) {
			System.out.print(i + " ");
		}
	}

	public void build(int treeIndex, int start, int end) {
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

	public void updateValue(int index, int value) {
		int diff = value - array[index];
		array[index] = value;
		update(index, diff, 0, 0, array.length - 1);
	}

	private void update(int index, int diff, int pos, int low, int high) {
		if (index < low || index > high) {
			// out of bounds. No changes propogate here.
			// do nothing
		} else if (low >= high) {
			segmentTree[pos] = segmentTree[pos] + diff;
		} else {
			segmentTree[pos] = segmentTree[pos] + diff; // change the value and
														// propagate it to the
														// lower nodes
			int mid = (low + high) / 2;
			update(index, diff, 2 * pos + 1, low, mid);
			update(index, diff, 2 * pos + 2, mid + 1, high);
		}
	}

	public void updateRange(int node, int start, int end, int l, int r, int val) {
		// out of range
		if (start > end || start > r || end < l) {
			return; // do nothing. Not a valid range.
		} else if (start == end) {
			segmentTree[node] += val;
		} else {
			int mid = (start + end) / 2;
			updateRange(node * 2 + 1, start, mid, l, r, val);
			updateRange(node * 2 + 2, mid + 1, end, l, r, val);
			segmentTree[node] = segmentTree[node * 2 + 1]
					+ segmentTree[node * 2 + 2];
		}

	}

}
