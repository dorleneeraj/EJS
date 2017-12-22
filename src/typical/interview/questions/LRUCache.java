package typical.interview.questions;

import java.util.HashMap;
import java.util.Map;

import data.structure.linkedlist.DNode;
import data.structure.linkedlist.DoublyLinkedList;
import data.structure.linkedlist.INode;

public class LRUCache {
	private int HEAD = 0;
	private int TAIL = 0;
	int capacity;
	DoublyLinkedList<Integer> queue = new DoublyLinkedList<Integer>();
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public LRUCache(int size) {
		this.capacity = size;
	}

	public boolean isPresent(int page) {
		return map.containsKey(page);
	}

	public int getPageIndex(int page) {
		return map.get(page);
	}

	public boolean referencePage(int page) {
		if (isPresent(page)) {
			int index = getPageIndex(page);
			INode<Integer> node = queue.removeNode(index);
			queue.addNode(node, HEAD);
		} else {
			// the page is not present.
			INode<Integer> node = null;
			if (TAIL == (capacity - 1)) {
				node = queue.removeNode(TAIL);
			}
			node = new DNode<Integer>(page);
			queue.addNode(node, 0);
		}
		updateMapEntries();
		return true;
	}

	private void updateMapEntries() {
		map.clear();
		int count = 0;
		for (Integer i : queue) {
			map.put(i, count++);
		}
	}

	public static void main(String[] args) {

	}
}
