package data.structure.tree;

public class MirrorTree {

	public static void main(String[] args) {
		MirrorTree mT = new MirrorTree();
		mT.buildTreeFromGivenString("1 2 R 1 3 L");
		mT.buildTreeFromGivenString("10 20 L 10 30 R 20 40 L 20 60 R");

	}

	public Node buildTreeFromGivenString(String treeString) {
		return null;
	}

	public void generateMirrorTree(Node root) {
		// This function generates the mirror of the given tree.
	}

	public void getTheMirrorNode(Node node) {

	}

}

class Node {
	int data;
	Node left, right;

	Node(int item) {
		data = item;
		left = right = null;
	}
}