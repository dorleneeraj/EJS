package data.structure.disjointset;

import java.util.Arrays;

// This variation maintains a balanced tree.
public class DisjointSetBalanced {

	// The data is stored in an Array.
	private int[] Array;
	private int ARRAY_LENGTH;
	private int[] size;

	// Following are the basic operations performed.

	protected void initialize(int arraySize) {
		Array = new int[arraySize];
		ARRAY_LENGTH = arraySize;
		for (int i = 0; i < Array.length; i++) {
			Array[i] = i + 1;
		}

		size = new int[Array.length];
		for (int i = 0; i < Array.length; i++) {
			size[i] = 1;
		}
	}

	protected int root(int node) {
		// This operation finds its root node. The condition of a node being
		// root is
		// for eg: Array[A] = A
		while (Array[node] != (node + 1)) {
			node = root(Array[node] - 1);
			// node = Array[node];
		}
		return node;
	}

	protected void union(int A, int B) { // It means unite these two elements/
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

		int root_A = root(A - 1);
		int root_B = root(B - 1);
		if (size[root_A] < size[root_B]) {
			Array[root_A] = Array[root_B];
			size[root_B] += size[root_A];
		} else {
			Array[root_B] = Array[root_A];
			size[root_A] += size[root_B];
		}

	}

	protected boolean find(int A, int B) {
		// It finds if A and B are connected. How do we know this?
		// If they are connected, they will have the same parent.
		boolean areConnected = false;

		if (root(A - 1) == root(B - 1)) {
			areConnected = true;
		}

		return areConnected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ARRAY_LENGTH;
		result = prime * result + Arrays.hashCode(Array);
		result = prime * result + Arrays.hashCode(size);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DisjointSetBalanced other = (DisjointSetBalanced) obj;
		if (ARRAY_LENGTH != other.ARRAY_LENGTH)
			return false;
		if (!Arrays.equals(Array, other.Array))
			return false;
		if (!Arrays.equals(size, other.size))
			return false;
		return true;
	}

	public int[] getArray() {
		return Arrays.copyOf(Array, Array.length);
	}

	public int[] getSize() {
		return Arrays.copyOf(size, size.length);
	}

	public int getARRAY_LENGTH() {
		return ARRAY_LENGTH;
	}

	public static void main(String[] args) {

		DisjointSetBalanced dSB = new DisjointSetBalanced();
		dSB.initialize(5);
		System.out.println("Are nodes 1 and 2 connected?: " + dSB.find(1, 2));
		dSB.union(4, 5);
		System.out.println("Joining 4 & 5: " + Arrays.toString(dSB.Array));
		dSB.union(0, 1);
		System.out.println("Joining 0 & 1: " + Arrays.toString(dSB.Array));
		dSB.union(2, 3);
		System.out.println("Joining 2 & 3: " + Arrays.toString(dSB.Array));
		dSB.union(6, 7);
		System.out.println("Joining 6 & 7: " + Arrays.toString(dSB.Array));
		dSB.union(8, 9);
		System.out.println("Joining 8 & 9: " + Arrays.toString(dSB.Array));
		dSB.union(8, 0);
		System.out.println("Joining 8 & 0: " + Arrays.toString(dSB.Array));
		System.out.println("Are nodes 0 and 9 connected?: " + dSB.find(0, 9));
		dSB.union(1, 4);
		System.out.println("Joining 8 & 0: " + Arrays.toString(dSB.Array));

	}

}
