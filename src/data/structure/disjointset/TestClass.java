package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class TestClass {

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

	public int[] getArray() {
		return Arrays.copyOf(Array, Array.length);
	}

	public int[] getSize() {
		return Arrays.copyOf(size, size.length);
	}

	public int getARRAY_LENGTH() {
		return ARRAY_LENGTH;
	}

	private static void sortAndDisplay(TestClass dSB) {
		int[] Array = dSB.getArray();
		int[] Size = dSB.getSize();
		int ARRAY_LENGTH = dSB.getARRAY_LENGTH();
		int[] display = new int[1005];
		// for (int i = 0; i < ARRAY_LENGTH; i++) {
		// display[i] = -1;
		// }

		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (Array[i] == i + 1) {
				display[(Size[i])]++;
			}
		}

		for (int i = 1; i < 1000; i++) {
			for (int j = 0; j < display[i]; j++) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	public static void main(String args[]) throws Exception {

		// First get the input from the user.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] dimensions = line.split(" ");
		int noOfVertices = Integer.parseInt(dimensions[0]);
		int noOfEdges = Integer.parseInt(dimensions[1]);
		System.out.println("Vertices: " + noOfVertices);
		System.out.println("Edges: " + noOfEdges);

		int edges[][] = new int[noOfEdges][];

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			edges[i] = new int[] { Integer.parseInt(edge[0]),
					Integer.parseInt(edge[1]) };
		}
		// Input from the user is complete.

		TestClass dSB = new TestClass();
		dSB.initialize(noOfVertices);
		for (int i = 0; i < noOfEdges; i++) {
			dSB.union(edges[i][0], edges[i][1]);
			sortAndDisplay(dSB);
		}

	}
}
