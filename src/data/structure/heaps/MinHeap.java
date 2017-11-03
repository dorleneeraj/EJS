package data.structure.heaps;

import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author neeraj
 *
 */
public class MinHeap<T, K> extends Heap<T, K> {

	public MinHeap() {
		super(HeapCommon.MIN_HEAP);
	}

	@Override
	protected void heapify(int index) {
		if (index >= 0 && index < heap.size()) {
			try {
				Node<T, K> currentNode = heap.get(index);
				Node<T, K> rightChild = null;
				Node<T, K> leftChild = null;

				int leftIndex = leftChild(index);
				int rightIndex = rightChild(index);
				if (leftIndex < heap.size()) {
					leftChild = heap.get(leftIndex);
				}
				if (rightIndex < heap.size()) {
					rightChild = heap.get(rightIndex);
				}

				int nextIndex = -1;
				Node<T, K> minNode = null;
				if ((new Common.IntegerComparator2<T, K>().compare(leftChild,
						rightChild)) <= 0) {
					minNode = leftChild;
					nextIndex = leftChild(index);
				} else {
					minNode = rightChild;
					nextIndex = rightChild(index);
				}
				if ((new Common.IntegerComparator2<T, K>().compare(currentNode,
						minNode)) > 0) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeKey(int index, K Key, Comparator<K> comparator) {
		return this.decreaseKey(index, Key, comparator);
	}

	private boolean decreaseKey(int index, K Key, Comparator<K> comparator) {

		boolean isSuccess = false;
		if (index > 0 && index < this.heapSize) {
			Node<T, K> node = heap.get(index);
			// removeFromPositionMap(node, index);
			Common.IntegerComparator2<T, Integer> iComparator = new Common.IntegerComparator2<T, Integer>();

			K oldKey = node.getKey();
			if (comparator.compare(oldKey, Key) <= 0) {
				node.setKey(Key);
				int parentIndex = getParentIndex(index);
				while ((parentIndex >= 0)
						&& (iComparator.compare((Node<T, Integer>) node,
								(Node<T, Integer>) heap.get(parentIndex)) <= 0)) {
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
		return "MinHeap [minHeap=" + heap + "]";
	}

	public static void main(String[] args) {
		Heap<String, Integer> mHeap = new MinHeap<String, Integer>();
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

		// List<Node<String, Integer>> sortesList1 = mHeap.sort();
		// System.out.println(sortesList1);
		// System.out.println(mHeap.toString());

		List<Node<String, Integer>> sortesList2 = mHeap.sortHeap();
		System.out.println(sortesList2);
		System.out.println(mHeap.toString());

		mHeap.changeKey(12, 7, new Common.KIntegerComparator<Integer>());
		System.out.println(mHeap.toString());

		mHeap.addNode(11, "Great");
		mHeap.addNode(6, "Great");
		mHeap.addNode(2, "Great");
		System.out.println(mHeap);

		// sortesList1 = mHeap.sort();
		// System.out.println(sortesList1);
		// System.out.println(mHeap.toString());

		mHeap.extract();
		System.out.println(mHeap);

		sortesList2 = mHeap.sortHeap();
		System.out.println(sortesList2);
		System.out.println(mHeap.toString());

		mHeap.addNode(0, "Lord");

		while (mHeap.getHeapSize() >= 1) {
			System.out.println(mHeap.extract());
		}

		System.out.println(mHeap.toString());
	}

}
