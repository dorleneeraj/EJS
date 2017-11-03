package data.structure.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains the common functionality implementation for both the
 * Heaps.
 * 
 * 
 * @author neeraj
 *
 * @param <T>
 * @param <K>
 */
public abstract class Heap<T, K> {
	// define the class members.

	protected List<Node<T, K>> heap;
	protected Map<K, List<Integer>> keyToPositionMap; // Fast access of the
														// position of the
														// element
														// in the List.Else, we
														// will
														// have to search the
														// complete list every
														// time
														// to get the index of
														// the
														// node

	protected int length = 0;
	protected int heapSize = 0;
	protected final int type;

	// define the constructors

	public Heap(int type) {
		heap = new ArrayList<Node<T, K>>();
		keyToPositionMap = new HashMap<K, List<Integer>>();
		this.type = type;
	}

	public Heap(List<Node<T, K>> list, int type) {
		this.type = type;
		this.heap = list;
		this.length = list.size();
		buildHeap();
	}

	// define the abstract methods

	protected abstract void heapify(int index);

	// public abstract Node<T, K> extract();

	public abstract List<Node<T, K>> sort();

	public abstract boolean changeKey(int index, K Key, Comparator<K> comparator);

	// define getters and setters and other utility methods.
	public int length() {
		return this.length;
	}

	public int getHeapSize() {
		return this.heapSize;
	}

	public boolean isEmpty() {
		return this.heapSize == 0 ? true : false;
	}

	public int leftChild(int index) {
		return (2 * index) + 1;
	}

	public int rightChild(int index) {
		return (2 * index) + 2;
	}

	public int getType() {
		return type;
	}

	protected void buildHeap() {
		int mid = (length - 1) / 2;
		while (mid >= 0) {
			this.heapify(mid);
			mid--;
		}
		this.heapSize = this.length;
	}

	public int getParentIndex(Node<T, K> node) {
		int parentNodeIndex = -1;
		int currentNodeIndex = -1;
		K key = node.getKey();
		List<Integer> pos = keyToPositionMap.get(key);
		if (null != pos && !pos.isEmpty()) {
			for (Integer i : pos) {
				Node<T, K> currentNode = heap.get(i);
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
		if (index > 0 && index < this.heap.size()) {
			parentNodeIndex = (index - 1) / 2;
		}
		return parentNodeIndex;
	}

	public boolean addNode(Node<T, K> node) {
		boolean isSuccess = false;
		heap.add(node);
		List<Integer> list = keyToPositionMap.get(node.getKey());
		if (null == list) {
			list = new ArrayList<Integer>();
			keyToPositionMap.put(node.getKey(), list);
			isSuccess = true;
		}
		list.add(heap.size() - 1);
		// keyToPositionMap.put(node.getKey(), list);
		if (heap.size() > 1) {
			// this.swapNodes(0, maxHeap.size() - 1);
			int index = getParentIndex(heap.size() - 1);
			while (index >= 0) {
				heapify(index);
				index = getParentIndex(index);
			}
			isSuccess = true;
		}
		this.length += 1;
		this.heapSize += 1;
		return isSuccess;
	}

	public boolean addNode(K key, T data) {
		Node<T, K> node = new Node<T, K>(data, key);
		return this.addNode(node);
	}

	public void swapNodes(int index, int nextIndex) {
		Node<T, K> node1 = heap.get(index);
		Node<T, K> node2 = heap.get(nextIndex);
		Node<T, K> tempNode = new Node<T, K>(node1.getData(), node1.getKey());

		List<Integer> list1 = keyToPositionMap.get(node1.getKey());
		if (null == list1) {
			list1 = new ArrayList<Integer>();
			list1.add(index);
			keyToPositionMap.put(node1.getKey(), list1);
		}
		int removePosition = 0;
		for (Integer i : list1) {
			if (node1.equals(heap.get(i))) {
				break;
			}
			removePosition++;
		}
		list1.remove(removePosition);
		list1.add(nextIndex);
		heap.get(index).setKey(node2.getKey());
		heap.get(index).setData(node2.getData());

		List<Integer> list2 = keyToPositionMap.get(node2.getKey());
		if (null == list2) {
			list2 = new ArrayList<Integer>();
			list2.add(nextIndex);
			keyToPositionMap.put(node2.getKey(), list2);
		}
		removePosition = 0;
		for (Integer i : list2) {
			if (node2.equals(heap.get(i))) {
				break;
			}
			removePosition++;
		}
		list2.remove(removePosition);
		list2.add(index);
		heap.get(nextIndex).setKey(tempNode.getKey());
		heap.get(nextIndex).setData(tempNode.getData());

	}

	public Node<T, K> extract() {
		Node<T, K> node = null;
		if (null != this.heap && !isEmpty()) {
			if (heapSize == 1) {
				removeFromPositionMap(heap.get(0));
				node = heap.remove(0);
			} else if (heapSize > 1) {
				swapNodes(0, heapSize - 1);
				removeFromPositionMap(heap.get(heapSize - 1), heapSize - 1);
				node = heap.remove(heapSize - 1);
				heapify(0);
			}
			length = length - 1;
			heapSize = heapSize - 1;
		}
		return node;
	}

	protected void removeFromPositionMap(Node<T, K> node, int index) {
		List<Integer> list = keyToPositionMap.get(node.getKey());
		if (null != list) {
			int counter = 0;
			for (Integer i : list) {
				if (index == i) {
					break;
				}
				counter++;
			}
			list.remove(counter);
		}
	}

	protected void removeFromPositionMap(Node<T, K> node) {
		List<Integer> list = keyToPositionMap.get(node.getKey());
		if (null != list) {
			int counter = 0;
			for (Integer i : list) {
				if (heap.get(i).equals(node)) {
					break;
				}
				counter++;
			}
			list.remove(counter);
		}
	}

	public List<Node<T, K>> sortHeap() {
		List<Node<T, K>> sortedHeap = new ArrayList<Node<T, K>>(this.heap);
		Collections.sort(sortedHeap, new Common.ListComparator<T, K>());
		return sortedHeap;
	}

}
