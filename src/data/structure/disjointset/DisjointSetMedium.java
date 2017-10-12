package data.structure.disjointset;

import java.util.Arrays;

// Difference between this and the DisjointSetSimple is that, it maintains the parent-child hierarchy
public class DisjointSetMedium {

	// The data is stored in an Array.
	private int[] Array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private final int ARRAY_LENGTH = 10;

	// Following are the basic operations performed.

	private int root(int node) {
		// This operation finds its root node. The condition of a node being
		// root is
		// for eg: Array[A] = A
		while (Array[node] != node) {
			node = Array[node];
		}
		return node;
	}

	private void union(int A, int B) { // It means unite these two elements/
										// indexes.
		// So just change the value at the index at A, to the value of the index
		// at B.
		// This operation basically changes the value of arr[A] to arr[B]
		// It changes all values of all the indices, that have value of arr[A].
		// What does this mean?
		// This means: Let us say, there are few elements that are connected to
		// element at index 1.
		// Now, command comes, to unite 1 and some other index 'x'
		// Now we change all the values that have value of 1, to the value at x.

		int root_A = root(A);
		int root_B = root(B);
		Array[root_A] = root_B;

	}

	private boolean find(int A, int B) {
		// It finds if A and B are connected. How do we know this?
		// If they are connected, they will have the same parent.
		boolean areConnected = false;

		if (root(A) == root(B)) {
			areConnected = true;
		}

		return areConnected;
	}

	public static void main(String[] args) {
		DisjointSetMedium dSM = new DisjointSetMedium();
		System.out.println("Are nodes 1 and 2 connected?: " + dSM.find(1, 2));
		dSM.union(4, 5);
		System.out.println("Joining 4 & 5: " + Arrays.toString(dSM.Array));
		dSM.union(0, 1);
		System.out.println("Joining 0 & 1: " + Arrays.toString(dSM.Array));
		dSM.union(2, 3);
		System.out.println("Joining 2 & 3: " + Arrays.toString(dSM.Array));
		dSM.union(6, 7);
		System.out.println("Joining 6 & 7: " + Arrays.toString(dSM.Array));
		dSM.union(8, 9);
		System.out.println("Joining 8 & 9: " + Arrays.toString(dSM.Array));
		dSM.union(8, 0);
		System.out.println("Joining 8 & 0: " + Arrays.toString(dSM.Array));
		System.out.println("Are nodes 0 and 9 connected?: " + dSM.find(0, 9));
		dSM.union(1, 4);
		System.out.println("Joining 8 & 0: " + Arrays.toString(dSM.Array));

	}

}
