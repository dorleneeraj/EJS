package data.structure.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author neeraj
 *
 */
public class MinHeap<T, K> {
	private List<Node<T, K>> minHeap;
	private Map<K, List<Integer>> keyToPositionMap;
	private int length;
	private int heapSize;

	public MinHeap() {
		minHeap = new ArrayList<Node<T, K>>();
		keyToPositionMap = new HashMap<K, List<Integer>>();
		this.length = 0;
		this.heapSize = 0;
	}
	
	
	

}
