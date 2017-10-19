package data.structure.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author neeraj
 *
 * @param <T>
 *            This is the data that node holds.
 * @param <K>
 *            This is the key on which the heap property is maintained. This
 *            property has to be unique
 */
public class MaxHeap<T, K> {

	static class Node<T, K> {
		private T data;
		private K key; // The Heap property is maintained using this attribute

		public Node(T data, K key) {
			this.data = data;
			this.key = key;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", key=" + key + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}

	}

	static class IntegerComparator<T, K> implements Comparator<Node<T, K>> {

		/**
		 * Return 0 if i1 <= i2. Returns 1 if i1 > i2.
		 */
		@Override
		public int compare(Node<T, K> o1, Node<T, K> o2) {

			int returnVal = 1;
			if (null != o1 && null != o2) {
				Integer i1 = (Integer) o1.key;
				Integer i2 = (Integer) o2.key;
				if (i1 <= i2) {
					returnVal = 0;
				} else {
					returnVal = 1;
				}
			}
			return returnVal;

		}

	}

	private List<Node<T, K>> maxHeap;
	private Map<K, List<Integer>> keyToPositionMap; // Fast access of the
													// position of

	// the element in the List.
	// Else, we will have to search
	// the complete list every time
	// to get the index of the node

	public void init() {
		maxHeap = new ArrayList<Node<T, K>>();
		keyToPositionMap = new HashMap<K, List<Integer>>();
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
				if ((new IntegerComparator<T, K>().compare(leftChild,
						rightChild)) > 0) {
					maxNode = leftChild;
					nextIndex = leftChild(index);
				} else {
					maxNode = rightChild;
					nextIndex = rightChild(index);
				}
				if ((new IntegerComparator<T, K>()
						.compare(currentNode, maxNode)) <= 0) {
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
		List<Integer> list = new ArrayList<Integer>();
		list.add(maxHeap.size() - 1);
		keyToPositionMap.put(node.getKey(), list);
		if (maxHeap.size() > 1) {
			this.swapNodes(0, maxHeap.size() - 1);
			this.maxHeapify(0);
		}
	}

	public void addNode(K key, T data) {
		Node<T, K> node = new Node<T, K>(data, key);
		maxHeap.add(node);
		List<Integer> list = new ArrayList<Integer>();
		list.add(maxHeap.size() - 1);
		keyToPositionMap.put(node.getKey(), list);
		if (maxHeap.size() > 1) {
			this.swapNodes(0, maxHeap.size() - 1);
			this.maxHeapify(0);
		}
	}

	@Override
	public String toString() {
		return "MaxHeap [maxHeap=" + maxHeap + "]";
	}

	public static void main(String[] args) {
		MaxHeap<Integer, Integer> maxHeap = new MaxHeap<Integer, Integer>();
		maxHeap.init();
		maxHeap.addNode(16, 4);
		maxHeap.addNode(5, 10);
		System.out.println(maxHeap.toString());
	}
}
