package data.structure.tree;

import java.util.Comparator;
import java.util.List;

/**
 * 
 * NOTE: Duplicate keys are not supported. Adding support for duplicate keys is
 * very trivial. It will easily follow from the logic here.
 * 
 * 
 * @author neeraj
 *
 * @param <T>
 */
public class BST<T> {
	private TreeNode<T> root = null;
	private int nodes = 0;

	public BST() {
	}

	public BST(TreeNode<T> root) {
		this.root = root;
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	public void buildBST(List<T> dataItems,
			Comparator<TreeNode<T>> buildComparator) {
		for (T t : dataItems) {
			this.insertNode(t, buildComparator);
		}

	}

	public boolean insertNode(T data, Comparator<TreeNode<T>> comparator) {
		TreeNode<T> node = new TreeNode<T>(data);
		return this.insertNode(node, comparator);
	}

	public boolean insertNode(TreeNode<T> node,
			Comparator<TreeNode<T>> comparator) {
		TreeNode<T> helperNode = null;
		TreeNode<T> loopNode = this.root;

		while (null != loopNode) {
			helperNode = loopNode;
			if (comparator.compare(node, loopNode) <= 0) {
				loopNode = loopNode.getLeftChild();
			} else {
				loopNode = loopNode.getRightChild();
			}
		}

		if (null == helperNode) {
			this.root = node;
			this.nodes++;
			return true;
		} else {
			node.setParent(helperNode);
			if (comparator.compare(node, helperNode) <= 0) {
				helperNode.setLeftChild(node);
			} else {
				helperNode.setRightChild(node);
			}
			this.nodes++;
			return true;
		}
	}

	// search key in the BST

	public TreeNode<T> searchKey(T key, Comparator<T> comparator) {
		TreeNode<T> node = this.root;
		while (node != null && comparator.compare(key, node.getData()) != 0) {
			if (comparator.compare(key, node.getData()) < 0) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild();
			}
		}
		return node;
	}

	// recursive algorithm for in-order traversal.
	public String InOrderTraversal(TreeNode<T> root) {
		String inorder = "";
		if (null != root) {
			String leftString = InOrderTraversal(root.getLeftChild());
			inorder = leftString + " " + root.toString();
			String rightString = InOrderTraversal(root.getRightChild());
			inorder = inorder + " " + rightString;
		}
		return inorder;
	}

	public String preorderTraversal(TreeNode<T> root) {
		String preorder = "";
		if (null != root) {
			preorder = preorder + root.toString() + "  ";
			preorder = preorder + preorderTraversal(root.getLeftChild());
			preorder = preorder + preorderTraversal(root.getRightChild());
		}
		return preorder;
	}

	public String postOrderTraversal(TreeNode<T> root) {
		String postorder = "";
		if (null != root) {
			postorder = postorder + postOrderTraversal(root.getLeftChild());
			postorder = postorder + postOrderTraversal(root.getRightChild());
			postorder = postorder + root.toString() + "  ";
		}
		return postorder;
	}

	public TreeNode<T> getMinNode(TreeNode<T> n) {
		TreeNode<T> node = n;
		if (null == node) {
			return node;
		}
		while (null != node.getLeftChild()) {
			node = node.getLeftChild();
		}
		return node;

	}

	public TreeNode<T> getMaxNode(TreeNode<T> n) {
		TreeNode<T> node = n;
		if (null == node) {
			return node;
		}
		while (null != node.getRightChild()) {
			node = node.getRightChild();
		}
		return node;
	}

	public TreeNode<T> getPredecessor(T key, Comparator<T> comparator) {
		TreeNode<T> node = this.searchKey(key, comparator);
		if (null == node) {
			return null;
		} else {
			TreeNode<T> predecessor = null;
			predecessor = this.getMaxNode(node.getLeftChild());
			if (null != predecessor) {
				return predecessor;
			} else {
				// Find the highest ancestor, jyachya right sub tree madhe ha
				// node aahe
				predecessor = node.getParent();
				while (null != predecessor
						&& predecessor.getLeftChild() == node) {
					node = predecessor;
					predecessor = predecessor.getParent();
				}
				return predecessor;
			}
		}
	}

	public TreeNode<T> getSuccessor(T key, Comparator<T> comparator) {
		TreeNode<T> node = this.searchKey(key, comparator);
		if (null == node) {
			// node not found
			return null;
		} else {
			TreeNode<T> successor = null;
			if (null != node.getRightChild()) {
				successor = this.getMinNode(node.getRightChild());
			} else {
				successor = node.getParent();
				TreeNode<T> helperNode = node;
				while (null != successor
						&& successor.getRightChild() == helperNode) {
					helperNode = successor;
					successor = successor.getParent();
				}
			}
			return successor;
		}
	}

	public TreeNode<T> deleteNode(T key, Comparator<T> comparator) {
		TreeNode<T> node = this.searchKey(key, comparator);
		if (null == node) {
			return null;
		}
		if (null == node.getLeftChild() && null == node.getRightChild()) {
			TreeNode<T> parent = node.getParent();
			if (null == parent) {
				this.root = null;
				return node;
			} else {
				if (parent.getLeftChild() == node) {
					parent.setLeftChild(null);
				} else {
					parent.setRightChild(null);
				}
				return node;
			}
		} else {
			if (null != node.getLeftChild() && null == node.getRightChild()) {
				TreeNode<T> parent = node.getParent();
				if (null == parent) {
					TreeNode<T> left = node.getLeftChild();
					left.setParent(null);
					this.root = left;
					return node;
				} else {
					TreeNode<T> n = null;
					if (parent.getLeftChild() == node) {
						n = node.getLeftChild();
						parent.setLeftChild(n);
						n.setParent(parent);
					} else {
						n = node.getLeftChild();
						parent.setRightChild(n);
						n.setParent(parent);
					}
					return node;
				}
			} else if (null != node.getRightChild()
					&& null == node.getLeftChild()) {

				TreeNode<T> parent = node.getParent();
				if (null == parent) {
					TreeNode<T> right = node.getRightChild();
					right.setParent(null);
					this.root = right;
					root.setParent(null);
					return node;
				} else {
					TreeNode<T> n = null;
					if (parent.getRightChild() == node) {
						n = node.getRightChild();
						parent.setRightChild(n);
						n.setParent(parent);
					} else {
						n = node.getRightChild();
						parent.setLeftChild(n);
						n.setParent(parent);
					}
					return node;
				}
			} else {
				TreeNode<T> successor = this.getSuccessor(key, comparator);
				TreeNode<T> successorRight = successor.getRightChild();
				if (null != successorRight) {
					TreeNode<T> p = successor.getParent();
					if ((p.getLeftChild() == successor)) {
						p.setLeftChild(successorRight);
					} else {
						p.setRightChild(successorRight);
					}
					successorRight.setParent(p);
					successor.setParent(null);
				} else {
					if (successor.getParent().getLeftChild() == successor) {
						successor.getParent().setLeftChild(null);
					} else {
						successor.getParent().setRightChild(null);
					}
					successor.setParent(null);
				}
				TreeNode<T> parent = node.getParent();
				TreeNode<T> right = node.getRightChild();
				TreeNode<T> left = node.getLeftChild();
				if (null != parent) {
					if (parent.getLeftChild() == node) {
						parent.setLeftChild(successor);
					}
					if (parent.getRightChild() == node) {
						parent.setRightChild(successor);
					}
					successor.setParent(parent);
				} else {
					this.root = successor;
					successor.setParent(null);
				}
				successor.setLeftChild(left);
				successor.setRightChild(right);
				return node;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder op = new StringBuilder();
		op.append("Printing the Inorder traversal \n");
		op.append(InOrderTraversal(root));
		op.append("\n\n");

		op.append("Printing the pre-order traversal\n");
		op.append(preorderTraversal(root));
		op.append("\n\n");

		op.append("Printing the post-order traversal\n");
		op.append(postOrderTraversal(root));

		return op.toString();
	}
}
