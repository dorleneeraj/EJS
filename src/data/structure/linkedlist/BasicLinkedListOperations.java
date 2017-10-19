package data.structure.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class BasicLinkedListOperations {
	public static void main(String[] args) {
		ILinkedList<Integer> myLinkedList = new SinglyLinkedList<Integer>();
		System.out.println(myLinkedList.isEmpty());
		INode<Integer> i1 = new SNode<Integer>(5);
		myLinkedList.addNode(i1);
		System.out.println(myLinkedList);
		List<INode<Integer>> myList = new ArrayList<INode<Integer>>();
		myList.add(new SNode<Integer>(6));
		myList.add(new SNode<Integer>(7));
		myList.add(new SNode<Integer>(8));
		myList.add(new SNode<Integer>(9));
		myList.add(new SNode<Integer>(10));
		myLinkedList.addNodes(myList);
		System.out.println(myLinkedList);
		myLinkedList = ((SinglyLinkedList<Integer>) myLinkedList).reverseList2();
		System.out.println(myLinkedList);

		INode<Integer> r1 = myLinkedList.removeNode(new SNode<Integer>(5));
		System.out.println(r1.toString());
		System.out.println(myLinkedList.getHead());
		System.out.println(myLinkedList);

	}
}
