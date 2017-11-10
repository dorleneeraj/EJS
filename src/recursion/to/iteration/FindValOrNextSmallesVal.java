package recursion.to.iteration;

import data.structure.tree.BST;
import data.structure.tree.TreeNode;

public class FindValOrNextSmallesVal {

	BST<Integer> bst = new BST<Integer>(new TreeNode<Integer>(15));

	public static void main(String[] args) {
		FindValOrNextSmallesVal fvons = new FindValOrNextSmallesVal();
		fvons.temporaryBSTBuildFunction();
		fvons.bst.InOrderTraversal(fvons.bst.getRoot());
		System.out.println("fvons for 14: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 14));
		System.out.println("fvons for 11: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 11));
		System.out.println("fvons for 32: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 32));
		System.out.println("fvons for 18: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 18));
		System.out.println("fvons for 19: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 19));
		System.out.println("fvons for 13: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 13));
		System.out.println("fvons for 35: "
				+ fvons.fvonsRecursive(fvons.bst.getRoot(), 35));

		System.out.println("*************************************************");
		System.out.println("fvons for 14: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 14, fvons.bst
						.getRoot().getData()));
		System.out.println("fvons for 11: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 11, fvons.bst
						.getRoot().getData()));
		System.out.println("fvons for 32: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 32, fvons.bst
						.getRoot().getData()));
		System.out.println("fvons for 18: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 18, fvons.bst
						.getRoot().getData()));
		System.out.println("fvons for 19: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 19, fvons.bst
						.getRoot().getData()));
		System.out.println("fvons for 13: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 13, fvons.bst
						.getRoot().getData()));
		System.out.println("fvons for 35: "
				+ fvons.fvonsTailRecursion(fvons.bst.getRoot(), 35, fvons.bst
						.getRoot().getData()));

	}

	public void temporaryBSTBuildFunction() {
		bst.getRoot().setLeftChild(new TreeNode<Integer>(10));
		bst.getRoot().setRightChild(new TreeNode<Integer>(25));
		TreeNode<Integer> lChild = bst.getRoot().getLeftChild();
		lChild.setLeftChild(new TreeNode<Integer>(7));
		lChild.setRightChild(new TreeNode<Integer>(12));
		TreeNode<Integer> rChild = lChild.getRightChild();
		rChild.setRightChild(new TreeNode<Integer>(14));
		rChild = bst.getRoot().getRightChild();
		rChild.setLeftChild(new TreeNode<Integer>(17));
		rChild.setRightChild(new TreeNode<Integer>(32));
		;
		lChild = rChild.getLeftChild();
		lChild.setRightChild(new TreeNode<Integer>(18));
		;
	}

	public Integer fvonsRecursive(TreeNode<Integer> node, Integer val) {

		if (null == node) {
			return null;
		} else if (node.getData() == val) {
			return val;
		} else if (node.getData() > val) {
			// search for the left subtree.
			return fvonsRecursive(node.getLeftChild(), val);
		} else {
			Integer right_val = fvonsRecursive(node.getRightChild(), val);
			if (null == right_val) {
				return node.getData();
			} else {
				return right_val;
			}
		}

	}

	public Integer fvonsTailRecursion(TreeNode<Integer> node, Integer val,
			Integer nextSmallValue) {

		if (node.getData() == val) {
			return val;
		} else if (node.getLeftChild() == null && node.getRightChild() == null) {
			return nextSmallValue;
		} else if (node.getData() > val) {
			node = node.getLeftChild();
			if (null != node) {
				nextSmallValue = node.getData();
				return fvonsTailRecursion(node, val, nextSmallValue);
			} else {
				return nextSmallValue;
			}
		} else {
			nextSmallValue = node.getData();
			TreeNode<Integer> nextNode = node.getRightChild();
			if (null != nextNode) {
				if (nextNode.getData() < val) {
					nextSmallValue = nextNode.getData();
				}
			}
			return fvonsTailRecursion(nextNode, val, nextSmallValue);
		}
	}

}
