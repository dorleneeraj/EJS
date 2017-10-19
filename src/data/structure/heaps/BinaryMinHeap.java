package data.structure.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryMinHeap<T> {

	private List<Node> allNodes = new ArrayList<Node>();
	private Map<T, Integer> nodePosition = new HashMap<T, Integer>();

	class Node {
		int weight;
		T key;
	}

	 public boolean containsData(T key){
	        return nodePosition.containsKey(key);
	    }
	
	public void add(int weight, T key) {
		Node node = new Node();
		node.weight = weight;
		node.key = key;

		allNodes.add(node);
		int size = allNodes.size();
		int currentIndex = size - 1;
		int parentIndex = (currentIndex - 1) / 2;
		nodePosition.put(node.key, currentIndex);

		while (parentIndex >= 0) {
			Node parentNode = allNodes.get(parentIndex);
			Node currentNode = allNodes.get(currentIndex);
			if (parentNode.weight > currentNode.weight) {
				// swap current node and parent node
				swap(currentNode, parentNode);
				updatePositionMap(parentNode.key, currentNode.key, parentIndex,
						currentIndex);
				currentIndex = parentIndex;
				parentIndex = (parentIndex - 1) / 2;
			} else {
				break;
			}
		}
	}

	public T min() {
		return allNodes.get(0).key;
	}

	public boolean empty() {
		return allNodes.size() == 0 ? true : false;
	}

	public Integer getWeight(T key) {
		Integer position = nodePosition.get(key);
		if (position == null) {
			return null;
		} else {
			return allNodes.get(position).weight;
		}
	}

	private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
		nodePosition.remove(data1);
		nodePosition.remove(data2);
		nodePosition.put(data1, pos1);
		nodePosition.put(data2, pos2);
	}

	public T extractMin() {
		Node node = extractMinNode();
		return node.key;
	}

	private void printPositionMap() {
		System.out.println(nodePosition);
	}

	public void printHeap() {
		for (Node n : allNodes) {
			System.out.println(n.weight + " " + n.key);
		}
	}

	private void swap(Node node1, Node node2) {
		int weight = node1.weight;
		T data = node1.key;

		node1.weight = node2.weight;
		node1.key = node2.key;

		node2.weight = weight;
		node2.key = data;
	}

	public Node extractMinNode() {
		int size = allNodes.size() - 1;
		Node minNode = new Node();
		minNode.key = allNodes.get(0).key;
		minNode.weight = allNodes.get(0).weight;

		int lastNodeWeight = allNodes.get(size).weight;
		T lastNodeKey = allNodes.get(size).key;
		allNodes.get(0).weight = lastNodeWeight;
		allNodes.get(0).key = lastNodeKey;
		nodePosition.remove(minNode.key);
		nodePosition.remove(allNodes.get(0));
		allNodes.remove(size);

		int currentIndex = 0;
		size--;
		while (true) {
			int left = 2 * currentIndex + 1;
			int right = 2 * currentIndex + 2;
			if (left > size) {
				break;
			}
			if (right > size) {
				right = left;
			}
			int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left
					: right;
			if (allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight) {
				swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
				updatePositionMap(allNodes.get(currentIndex).key,
						allNodes.get(smallerIndex).key, currentIndex,
						smallerIndex);
				currentIndex = smallerIndex;
			} else {
				break;
			}
		}
		return minNode;
	}
	
	 public void decrease(T data, int newWeight){
	        Integer position = nodePosition.get(data);
	        allNodes.get(position).weight = newWeight;
	        int parent = (position -1 )/2;
	        while(parent >= 0){
	            if(allNodes.get(parent).weight > allNodes.get(position).weight){
	                swap(allNodes.get(parent), allNodes.get(position));
	                updatePositionMap(allNodes.get(parent).key,allNodes.get(position).key,parent,position);
	                position = parent;
	                parent = (parent-1)/2;
	            }else{
	                break;
	            }
	        }
	    }
}
