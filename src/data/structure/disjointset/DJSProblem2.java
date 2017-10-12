package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// This solution has an implementation of compressed DJS. 
// What does this mean? It means that, the parent of each node 
// is its Grand parent. How does that help? 
// It helps in reducing the search time for the "ROOT". 
// From N to Log(N)

public class DJSProblem2 {

	private int[] Array;
	private int ARRAY_LENGTH;
	private int[] Size;

	void initialize(int arrayLength) {
		this.ARRAY_LENGTH = arrayLength;
		Array = new int[this.ARRAY_LENGTH + 1];
		Size = new int[this.ARRAY_LENGTH + 1];

		for (int i = 0; i <= this.ARRAY_LENGTH; i++) {
			Array[i] = i;
			Size[i] = 1;
		}
	}

	int root(int A) {
		while (Array[A] != A) {
			Array[A] = Array[Array[A]]; // This is how we set the value to its
										// GrandParent.
			A = Array[A];
		}

		return A;
	}

	void union(int A, int B) {
		int root_A = root(A);
		int root_B = root(B);
		if (root_A != root_B) {
			if (Size[root_A] < Size[root_B]) {
				Array[root_A] = Array[root_B];
				Size[root_B] = Size[root_B] + Size[root_A];
			} else {
				Array[root_B] = Array[root_A];
				Size[root_A] = Size[root_B] + Size[root_A];
			}
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

	public static void main(String[] args) throws IOException {
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

		DJSProblem2 solver = new DJSProblem2();
		solver.initialize(noOfVertices);
		for (int i = 0; i < noOfEdges; i++) {
			solver.union(edges[i][0], edges[i][1]);
		}

		solver.printFriends();

	}

	private void printFriends() {
		for (int i = 1; i <= this.ARRAY_LENGTH; i++) {
			int root_I = root(i);
			int rootSize = Size[root_I];
			Size[i] = rootSize;
		}

		for (int i = 1; i <= this.ARRAY_LENGTH; i++) {
			if (Size[i] == 1) {
				System.out.print(0);
				System.out.print(" ");
			} else {
				System.out.print(Size[i] - 1); // Size array stores the total
												// no. of elements in the tree.
												// It includes itself. A person
												// cannot be friend of itself.
												// Hence removing self from the
												// friend-list
				System.out.print(" ");
			}
		}
		System.out.println("");
	}

}
