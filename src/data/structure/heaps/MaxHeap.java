package data.structure.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author neeraj
 *
 * @param <T>
 *            This is the data that node holds.
 * @param <K>
 *            This is the key on which the heap property is maintained. This
 *            property has to be unique.
 * 
 *            Well, that was what I thought. But the key can be duplicate.
 * 
 */
public class MaxHeap<T, K> extends Heap<T, K> {

	public MaxHeap() {
		super(HeapCommon.MAX_HEAP);
	}

	@Override
	protected void heapify(int index) {

		if (index >= 0 && (index < heap.size())) {
			try {
				Node<T, K> currentNode = heap.get(index);
				Node<T, K> rightChild = null;
				Node<T, K> leftChild = null;
				int nextIndex = -1;

				int leftIndex = leftChild(index);
				if (leftIndex < heap.size()) {
					leftChild = heap.get(leftIndex);
				}
				int rightIndex = rightChild(index);
				if (rightIndex < heap.size()) {
					rightChild = heap.get(rightIndex);
				}
				Node<T, K> maxNode = null;
				if ((new Common.IntegerComparator<T, K>().compare(leftChild,
						rightChild)) > 0) {
					maxNode = leftChild;
					nextIndex = leftChild(index);
				} else {
					maxNode = rightChild;
					nextIndex = rightChild(index);
				}
				if ((new Common.IntegerComparator<T, K>().compare(currentNode,
						maxNode)) <= 0) {
					swapNodes(index, nextIndex);
					this.heapify(nextIndex);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Node<T, K>> sort() {
		// copy all the data and then perform all the operations on the old
		// copied data.
		// So that the original structure of the heap is not lost.

		Stack<Node<T, K>> stack = new Stack<Node<T, K>>();
		int oldLen = this.length;
		int oldHeapSize = this.heapSize;
		List<Node<T, K>> oldHeap = new ArrayList<Node<T, K>>();
		for (Node<T, K> node : heap) {
			Node<T, K> n = new Node<T, K>(node.getData(), node.getKey());
			oldHeap.add(n);
		}

		HashMap<K, List<Integer>> oldPositionMap = new HashMap<K, List<Integer>>();
		for (K key : keyToPositionMap.keySet()) {
			List<Integer> l = new ArrayList<Integer>(keyToPositionMap.get(key));
			oldPositionMap.put(key, l);
		}
		if (this.length > 1 && this.heapSize > 1) {
			int i = this.heapSize;
			while (i >= 1) {
				swapNodes(this.heapSize - 1, 0);
				// remove the node from keyToPositionMap
				removeFromPositionMap(this.heap.get(this.heapSize - 1),
						this.heapSize - 1);
				this.heapSize = heapSize - 1;
				stack.push(heap.remove(heap.size() - 1));
				this.heapify(0);
				i--;
			}
		}

		// restore the old values
		this.length = oldLen;
		this.heapSize = oldHeapSize;
		this.heap = oldHeap;
		this.keyToPositionMap = oldPositionMap;

		return stack;
	}

	// The above method for sorting is a traditional one. But as we have
	// provided
	// a Comparator function, we can simply use .sort on the maxHeap list.

	@Override
	public boolean changeKey(int index, K Key, Comparator<K> comparator) {
		return this.increaseKey(index, Key, comparator);
	}

	public boolean increaseKey(int index, K Key, Comparator<K> comparator) {
		boolean isSuccess = false;
		if (index > 0 && index < this.heapSize) {
			Node<T, K> node = heap.get(index);
			// removeFromPositionMap(node, index);
			Common.IntegerComparator<T, Integer> iComparator = new Common.IntegerComparator<T, Integer>();

			K oldKey = node.getKey();
			if (comparator.compare(oldKey, Key) > 0) {
				node.setKey(Key);
				int parentIndex = getParentIndex(index);
				while ((parentIndex >= 0)
						&& (iComparator.compare((Node<T, Integer>) node,
								(Node<T, Integer>) heap.get(parentIndex)) > 0)) {
					swapNodes(index, parentIndex);
					node = heap.get(parentIndex);
					index = parentIndex;
					parentIndex = getParentIndex(parentIndex);
				}
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	@Override
	public String toString() {
		return "MaxHeap [maxHeap=" + heap + "]";
	}

	public static void main(String[] args) {
		Heap<String, Integer> mHeap = new MaxHeap<String, Integer>();
		mHeap.addNode(16, "neeraj");
		mHeap.addNode(21, "Ketki");
		mHeap.addNode(12, "Viraaj");
		mHeap.addNode(10, "Rohan");
		mHeap.addNode(13, "Deven");
		mHeap.addNode(10, "Pavtya");
		mHeap.addNode(8, "Shaunak");
		mHeap.addNode(3, "Tungya");
		mHeap.addNode(8, "Manyu");
		mHeap.addNode(1, "Ashok");
		mHeap.addNode(9, "Sujay");
		mHeap.addNode(22, "Dorle");
		mHeap.addNode(18, "Goodluck");
		mHeap.addNode(14, "Gang");
		mHeap.addNode(25, "Great");
		System.out.println(mHeap.toString());

		List<Node<String, Integer>> sortesList1 = mHeap.sort();
		System.out.println(sortesList1);
		System.out.println(mHeap.toString());

		List<Node<String, Integer>> sortesList2 = mHeap.sortHeap();
		System.out.println(sortesList2);
		System.out.println(mHeap.toString());

		mHeap.changeKey(12, 32, new Common.KIntegerComparator<Integer>());
		System.out.println(mHeap.toString());

		mHeap.addNode(35, "Great");
		mHeap.addNode(36, "Great");
		mHeap.addNode(29, "Great");
		System.out.println(mHeap);

		sortesList1 = mHeap.sort();
		System.out.println(sortesList1);
		System.out.println(mHeap.toString());

		mHeap.extract();
		System.out.println(mHeap);

		sortesList2 = mHeap.sortHeap();
		System.out.println(sortesList2);
		System.out.println(mHeap.toString());

		mHeap.addNode(45, "Lord");

		while (mHeap.getHeapSize() >= 1) {
			System.out.println(mHeap.extract());
		}

		System.out.println(mHeap.toString());
	}

}

// List<Integer> list = keyToPositionMap.get(maxHeap
// .get(heapSize - 1));
// int index = 0;
// for (Integer j : list) {
// if (maxHeap.get(heapSize - 1).equals(maxHeap.get(j))) {
// break;
// } else {
// index++;
// }
// }
// if (index >= 0) {
// list.remove(index);
// }
// if (list.isEmpty()) {
// keyToPositionMap.remove(maxHeap.get(heapSize - 1).getKey());
// }

