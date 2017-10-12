package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DJSProblem3 {

	private int[] Array;
	private int ARRAY_LENGTH;
	private int[] Size;
	private int MODULO_OPERATOR = 1000000007;

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
		// System.out.println("Vertices: " + noOfVertices);
		// System.out.println("Edges: " + noOfEdges);

		int edges[][] = new int[noOfEdges][];

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			edges[i] = new int[] { Integer.parseInt(edge[0]),
					Integer.parseInt(edge[1]) };
		}
		// Input from the user is complete.

		DJSProblem3 solver = new DJSProblem3();
		solver.initialize(noOfVertices);
		for (int i = 0; i < noOfEdges; i++) {
			solver.union(edges[i][0], edges[i][1]);
		}

		solver.printWaysToSelectLeader(solver);

	}

	void printWaysToSelectLeader(DJSProblem3 solver) {
		int arraySize = ARRAY_LENGTH;
		int rootASize = -1;
		int rootBSize = -1;
		int[] someArray = new int[arraySize + 1];
		int j = 1;
		for (int i = 1; i <= arraySize; i++) {
			if (Array[i] == i) {
				someArray[j] = Size[i];
				j++;
			}
		}

		int result = 1;
		int counter = 1;
		while (someArray[counter] != 0) {
			result = (result * someArray[counter]) % 1000000007;
			counter++;
		}
		// result = result % 1000000007;
		System.out.println(result);
	}
}
