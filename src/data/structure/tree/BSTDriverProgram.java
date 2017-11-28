package data.structure.tree;

import java.util.ArrayList;
import java.util.List;

public class BSTDriverProgram {
	public static void main(String[] args) {
		buildBST();
	}

	public static void buildBST() {
		List<Integer> list = new ArrayList<Integer>();
//		list.add(7);
//		list.add(15);
//		list.add(3);
//		list.add(4);
//		list.add(12);
//		list.add(8);
//		list.add(1);
//		list.add(17);
//		list.add(10);
//		BST<Integer> bst = new BST<Integer>();
//		bst.buildBST(list, new TreeCommon.IntegerNodeComparator());
//		System.out.println(bst.toString());
//
//		System.out.println("Searching for elements:");
//		System.out
//				.println("Searching for 3: "
//						+ bst.searchKey(3,
//								new TreeCommon.IntegerComparator<Integer>()));
//		System.out
//				.println("Searching for 2:"
//						+ bst.searchKey(2,
//								new TreeCommon.IntegerComparator<Integer>()));
//
//		System.out.println("*********Print MIN/MAX ************");
//		System.out.println("MAX: " + bst.getMaxNode(bst.getRoot()));
//		System.out.println("MIN: " + bst.getMinNode(bst.getRoot()));
//
//		System.out.println("******Successors: ******");
//		System.out.println("Successor for 1: "
//				+ bst.getSuccessor(1,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Successor for 7: "
//				+ bst.getSuccessor(7,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Successor for 17: "
//				+ bst.getSuccessor(17,
//						new TreeCommon.IntegerComparator<Integer>()));
//
//		System.out.println("*****Predecessors: ******");
//		System.out.println("Predecessor for 17: "
//				+ bst.getPredecessor(17,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Predecessor for 3: "
//				+ bst.getPredecessor(3,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Predecessor for 7: "
//				+ bst.getPredecessor(7,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Predecessor for 4: "
//				+ bst.getPredecessor(4,
//						new TreeCommon.IntegerComparator<Integer>()));
//
//		System.out.println("Predecessor for 1: "
//				+ bst.getPredecessor(1,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Predecessor for 8: "
//				+ bst.getPredecessor(8,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Predecessor for 10: "
//				+ bst.getPredecessor(10,
//						new TreeCommon.IntegerComparator<Integer>()));
//		System.out.println("Predecessor for 12: "
//				+ bst.getPredecessor(12,
//						new TreeCommon.IntegerComparator<Integer>()));

		System.out.println();
		System.out.println("*********************************************");
		System.out.println();
		System.out.println("Creating new BST for deletion operation");
		list.clear();
		list.add(15);
		list.add(5);
		list.add(3);
		list.add(12);
		list.add(13);
		list.add(10);
		list.add(6);
		list.add(7);
		list.add(16);
		list.add(20);
		list.add(18);
		list.add(23);

		BST<Integer> newBST = new BST<Integer>();
		newBST.buildBST(list, new TreeCommon.IntegerNodeComparator());
		System.out.println(newBST.toString());
		System.out.println("**************REMOVING 16");
		newBST.deleteNode(16, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 13");
		newBST.deleteNode(13, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 15");
		newBST.deleteNode(15, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 10");
		newBST.deleteNode(10, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 3");
		newBST.deleteNode(3, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 12");
		newBST.deleteNode(12, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 8");
		newBST.deleteNode(8, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 7");
		newBST.deleteNode(7, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

//		System.out.println("**************REMOVING 21");
//		newBST.deleteNode(21, new TreeCommon.IntegerComparator<Integer>());
//		System.out.println(newBST.toString());

		System.out.println("**************REMOVING 18");
		newBST.deleteNode(18, new TreeCommon.IntegerComparator<Integer>());
		System.out.println(newBST.toString());

//		System.out.println("**************REMOVING 23");
//		newBST.deleteNode(23, new TreeCommon.IntegerComparator<Integer>());
//		System.out.println(newBST.toString());
//
//		System.out.println("**************REMOVING 20");
//		newBST.deleteNode(20, new TreeCommon.IntegerComparator<Integer>());
//		System.out.println(newBST.toString());

	}
}
