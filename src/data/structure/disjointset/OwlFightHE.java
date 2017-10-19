package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OwlFightHE {
	private int[] Array;
	private int ARRAY_LENGTH;
	private int[] Size;
	private static int[] MAX_STRENGTH;
	private static int MAX_STRENGTH_LEN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] dimensions = line.split(" ");
		int noOfVertices = Integer.parseInt(dimensions[0]);
		int noOfEdges = Integer.parseInt(dimensions[1]);
		OwlFightHE dSC = new OwlFightHE();
		dSC.initialize(noOfVertices);
		MAX_STRENGTH_LEN = noOfVertices + 1;
		initializeMAXStrenghtArray();

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			int i1 = Integer.parseInt(edge[0]);
			int i2 = Integer.parseInt(edge[1]);
			if (i1 == 1202 || i1 == 32124 || i1 == 15831 || i1 == 9883
					|| i1 == 2016 || i2 == 2016 || i2 == 1202 || i2 == 32124
					|| i2 == 9883 || i2 == 15831) {
				System.out.println("check...");
			}
			// before this, calculate the respective max values for the
			// individual nodes before they are merged.

			int PREV_ROOT_1_MAX = MAX_STRENGTH[dSC.root(Integer
					.parseInt(edge[0]))];
			int PREV_ROOT_2_MAX = MAX_STRENGTH[dSC.root(Integer
					.parseInt(edge[1]))];

			dSC.union(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
			updateMaxStrengthArray(dSC, Integer.parseInt(edge[0]),
					PREV_ROOT_1_MAX, PREV_ROOT_2_MAX);
		}

		// System.out.println(dSC.root(32124));
		// System.out.println(dSC.root(2016));
		// System.out.println(dSC.Size[1202]);
		//
		// Stack<Integer> stack = new Stack<Integer>();
		// stack.push(1202);
		// List<Integer> l = new ArrayList<Integer>();
		// while (!stack.isEmpty()) {
		// int j = stack.pop();
		// l.add(j);
		// for (int i = 1; i < dSC.Array.length; i++) {
		// if ((i != j) && (dSC.Array[i] == j)) {
		// stack.push(i);
		// }
		// }
		// }
		//
		// System.out.println(l);
		// for (int i : l) {
		// System.out.println(i + ": ROOT is : " + dSC.root(i)
		// + " MAX Value: " + MAX_STRENGTH[dSC.root(i)]);
		// }
		int noOfContests = Integer.parseInt(br.readLine());
		System.out.println("");
		for (int i = 0; i < noOfContests; i++) {
			String input = br.readLine();
			String[] contest = input.split(" ");
			printWinner(dSC, Integer.parseInt(contest[0]),
					Integer.parseInt(contest[1]));
		}
	}

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

	private static void printWinner(OwlFightHE dSC, int c1, int c2) {

		int root_1 = dSC.root(c1);
		int root_2 = dSC.root(c2);
		if (root_1 == root_2) {
			System.out.println("TIE");
		} else {
			int max_1 = MAX_STRENGTH[root_1];
			int max_2 = MAX_STRENGTH[root_2];
			if (max_1 > max_2) {
				System.out.println(c1);
			} else {
				System.out.println(c2);
			}
		}
	}

	private static void updateMaxStrengthArray(OwlFightHE dSC, int v1,
			int PREV_ROOT_1_MAX, int PREV_ROOT_2_MAX) {
		int root_1 = dSC.root(v1); // get the root index. Since, both the
									// vertices
									// have same root, get root of v1 for
									// further calculations.
		int maxValue = 0;
		if (PREV_ROOT_1_MAX > PREV_ROOT_2_MAX) {
			maxValue = PREV_ROOT_1_MAX;
		} else {
			maxValue = PREV_ROOT_2_MAX;
		}

		if (MAX_STRENGTH[root_1] < maxValue) {
			MAX_STRENGTH[root_1] = maxValue;
		}

	}

	private static void initializeMAXStrenghtArray() {
		MAX_STRENGTH = new int[MAX_STRENGTH_LEN];
		for (int i = 0; i < MAX_STRENGTH_LEN; i++) {
			MAX_STRENGTH[i] = i;
		}
	}

}

/*
 * System.out.println(dSC.root(32124)); System.out.println(dSC.root(2016));
 * System.out.println(dSC.Size[1202]);
 * 
 * Stack<Integer> stack = new Stack<Integer>(); stack.push(1202); List<Integer>
 * l = new ArrayList<Integer>(); while (!stack.isEmpty()) { int j = stack.pop();
 * l.add(j); for (int i = 1; i < dSC.Array.length; i++) { if ((i != j) &&
 * (dSC.Array[i] == j)) { stack.push(i); } } }
 * 
 * System.out.println(l); for (int i : l) { System.out.println(i +
 * ": ROOT is : " + dSC.root(i) + " MAX Value: " + MAX_STRENGTH[dSC.root(i)]); }
 */
