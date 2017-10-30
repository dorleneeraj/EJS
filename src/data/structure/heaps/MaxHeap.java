package data.structure.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class MaxHeap<T, K> {

	private List<Node<T, K>> maxHeap;
	private Map<K, List<Integer>> keyToPositionMap; // Fast access of the
													// position of the element
													// in the List.Else, we will
													// have to search the
													// complete list every time
													// to get the index of the
													// node

	private int length = 0;
	private int heapSize = 0;

	public MaxHeap() {
		maxHeap = new ArrayList<Node<T, K>>();
		keyToPositionMap = new HashMap<K, List<Integer>>();
	}

	public MaxHeap(List<Node<T, K>> list) {
		this();
		this.maxHeap = list;
		buildMaxHeap();
		this.length = list.size();
	}

	public int getParentIndex(Node<T, K> node) {
		int parentNodeIndex = -1;
		int currentNodeIndex = -1;
		K key = node.getKey();
		List<Integer> pos = keyToPositionMap.get(key);
		if (null != pos && !pos.isEmpty()) {
			for (Integer i : pos) {
				Node<T, K> currentNode = maxHeap.get(i);
				if (currentNode.equals(node)) {
					currentNodeIndex = i;
					break;
				}
			}
			if (currentNodeIndex > 0) {
				parentNodeIndex = (currentNodeIndex - 1) / 2;
			}
		}

		return parentNodeIndex;
	}

	public int getParentIndex(int index) {
		int parentNodeIndex = -1;
		if (index >= 0 && index < this.maxHeap.size()) {
			parentNodeIndex = (index - 1) / 2;
		}
		return parentNodeIndex;
	}

	public void maxHeapify(int index) {
		if (index >= 0 && (index < maxHeap.size())) {
			try {
				Node<T, K> currentNode = maxHeap.get(index);
				Node<T, K> rightChild = null;
				Node<T, K> leftChild = null;
				int nextIndex = -1;

				int leftIndex = leftChild(index);
				if (leftIndex < maxHeap.size()) {
					leftChild = maxHeap.get(leftIndex);
				}
				int rightIndex = rightChild(index);
				if (rightIndex < maxHeap.size()) {
					rightChild = maxHeap.get(rightIndex);
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
					maxHeapify(nextIndex);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}

	public void swapNodes(int index, int nextIndex) {
		Node<T, K> node1 = maxHeap.get(index);
		Node<T, K> node2 = maxHeap.get(nextIndex);
		Node<T, K> tempNode = new Node<T, K>(node1.getData(), node1.getKey());

		List<Integer> list1 = keyToPositionMap.get(node1.getKey());
		if (null == list1) {
			list1 = new ArrayList<Integer>();
			list1.add(index);
			keyToPositionMap.put(node1.getKey(), list1);
		}
		int removePosition = 0;
		for (Integer i : list1) {
			if (node1.equals(maxHeap.get(i))) {
				break;
			}
			removePosition++;
		}
		list1.remove(removePosition);
		list1.add(nextIndex);
		maxHeap.get(index).setKey(node2.getKey());
		maxHeap.get(index).setData(node2.getData());

		List<Integer> list2 = keyToPositionMap.get(node2.getKey());
		if (null == list2) {
			list2 = new ArrayList<Integer>();
			list2.add(nextIndex);
			keyToPositionMap.put(node2.getKey(), list2);
		}
		removePosition = 0;
		for (Integer i : list2) {
			if (node2.equals(maxHeap.get(i))) {
				break;
			}
			removePosition++;
		}
		list2.remove(removePosition);
		list2.add(index);
		maxHeap.get(nextIndex).setKey(tempNode.getKey());
		maxHeap.get(nextIndex).setData(tempNode.getData());

	}

	public int leftChild(int index) {
		return (2 * index) + 1;
	}

	public int rightChild(int index) {
		return (2 * index) + 2;
	}

	public void addNode(Node<T, K> node) {
		maxHeap.add(node);
		List<Integer> list = keyToPositionMap.get(node.getKey());
		if (null == list) {
			list = new ArrayList<Integer>();
			keyToPositionMap.put(node.getKey(), list);
		}
		list.add(maxHeap.size() - 1);
		keyToPositionMap.put(node.getKey(), list);
		if (maxHeap.size() > 1) {
			// this.swapNodes(0, maxHeap.size() - 1);
			for (int k = (maxHeap.size() - 1) / 2; k >= 0; k--) {
				this.maxHeapify(k);
			}
		}
		this.length += 1;
		this.heapSize += 1;
	}

	public void addNode(K key, T data) {
		Node<T, K> node = new Node<T, K>(data, key);
		maxHeap.add(node);
		List<Integer> list = keyToPositionMap.get(key);
		if (null == list) {
			list = new ArrayList<Integer>();
			keyToPositionMap.put(key, list);
		}
		list.add(maxHeap.size() - 1);
		keyToPositionMap.put(node.getKey(), list);
		if (maxHeap.size() > 1) {
			// this.swapNodes(0, maxHeap.size() - 1);
			for (int k = getParentIndex(maxHeap.size() - 1); k >= 0; k--) {
				this.maxHeapify(k);
			}
		}
		this.length += 1;
		this.heapSize += 1;
	}

	public int length() {
		return this.length;
	}

	public int getHeapSize() {
		return this.heapSize;
	}

	public boolean isEmpty() {
		return this.heapSize == 0 ? true : false;
	}

	public List<Node<T, K>> sort() {
		// copy all the data and then perform all the operations on the old
		// copied data.
		// So that the original structure of the heap is not lost.

		Stack<Node<T, K>> stack = new Stack<Node<T, K>>();
		int oldLen = this.length;
		int oldHeapSize = this.heapSize;
		List<Node<T, K>> oldHeap = new ArrayList<Node<T, K>>();
		for (Node<T, K> node : maxHeap) {
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
				removeFromPositionMap(this.maxHeap.get(this.heapSize - 1),
						this.heapSize - 1);
				this.heapSize = heapSize - 1;
				stack.push(maxHeap.remove(maxHeap.size() - 1));
				this.maxHeapify(0);
				i--;
			}
		}

		// restore the old values
		this.length = oldLen;
		this.heapSize = oldHeapSize;
		this.maxHeap = oldHeap;
		this.keyToPositionMap = oldPositionMap;

		return stack;

	}

	// The above method for sorting is a traditional one. But as we have
	// provided
	// a Comparator function, we can simply use .sort on the maxHeap list.

	public List<Node<T, K>> sortHeap() {
		List<Node<T, K>> sortedHeap = new ArrayList<Node<T, K>>(this.maxHeap);
		Collections.sort(sortedHeap, new Common.ListComparator<T, K>());
		return sortedHeap;
	}

	public void increaseKey(int index, K Key, Comparator<K> comparator) {
		if (index >= 0 && index < this.heapSize) {
			Node<T, K> node = maxHeap.get(index);
			removeFromPositionMap(node, index);
			Common.IntegerComparator<T, Integer> iComparator = new Common.IntegerComparator<T, Integer>();

			K oldKey = node.getKey();
			if (comparator.compare(oldKey, Key) > 0) {
				node.setKey(Key);
				int parentIndex = getParentIndex(index);
				while (iComparator.compare((Node<T, Integer>) node,
						(Node<T, Integer>) maxHeap.get(parentIndex)) > 0) {
					swapNodes(index, parentIndex);
					node = maxHeap.get(parentIndex);
					index = parentIndex;
					parentIndex = getParentIndex(parentIndex);
				}
			}
		}
	}

	public Node<T, K> extractMAX() {
		Node<T, K> node = null;
		if (heapSize >= 0) {
			swapNodes(0, heapSize - 1);
			removeFromPositionMap(maxHeap.get(heapSize - 1), heapSize - 1);
			node = maxHeap.remove(heapSize - 1);
			maxHeapify(0);
			length = length - 1;
			heapSize = heapSize - 1;
		}
		return node;
	}

	private void removeFromPositionMap(Node<T, K> node, int index) {
		List<Integer> list = keyToPositionMap.get(node.getKey());
		if (null != list) {
			int counter = 0;
			for (Integer i : list) {
				if (maxHeap.get(i).equals(node)) {
					break;
				}
				counter++;
			}
			list.remove(counter);
		}
	}

	private void removeFromPositionMap(Node<T, K> node) {
	}

	public void buildMaxHeap() {
		int mid = (length - 1) / 2;
		while (mid >= 0) {
			maxHeapify(mid);
			mid--;
		}
		this.heapSize = this.length;
	}

	@Override
	public String toString() {
		return "MaxHeap [maxHeap=" + maxHeap + "]";
	}

	public static void main(String[] args) {
		MaxHeap<String, Integer> mHeap = new MaxHeap<String, Integer>();
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

		mHeap.increaseKey(12, 32, new Common.KIntegerComparator<Integer>());
		System.out.println(mHeap.toString());

		mHeap.addNode(35, "Great");
		mHeap.addNode(36, "Great");
		mHeap.addNode(29, "Great");
		System.out.println(mHeap);

		sortesList1 = mHeap.sort();
		System.out.println(sortesList1);
		System.out.println(mHeap.toString());

		mHeap.extractMAX();
		System.out.println(mHeap);

		sortesList2 = mHeap.sortHeap();
		System.out.println(sortesList2);
		System.out.println(mHeap.toString());

		mHeap.addNode(45, "Lord");

		while (mHeap.getHeapSize() >= 1) {
			System.out.println(mHeap.extractMAX());
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

