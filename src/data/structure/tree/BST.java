package data.structure.tree;

public class BST<T> {
	private TreeNode<T> root = null;

	public BST(TreeNode<T> root) {
		this.root = root;
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	
	
	// recursive algorithm for in-order traversal.
	public void InOrderTraversal(TreeNode<T> root) {
		if (null != root) {
			InOrderTraversal(root.getLeftChild());
			System.out.println(root);
			InOrderTraversal(root.getRightChild());
		}
	}

	public void InOrderTraversalIterative() {

	}
}
