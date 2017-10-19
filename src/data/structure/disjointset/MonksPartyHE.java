package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MonksPartyHE {
	private int[] Array;
	private int ARRAY_LENGTH;
	private int[] Size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int noOfVertices = Integer.parseInt(line);
		int noOfEdges = Integer.parseInt(br.readLine());
		MonksPartyHE dSC = new MonksPartyHE();
		dSC.initialize(noOfVertices);

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			int friend1 = Integer.parseInt(edge[0]);
			int friend2 = Integer.parseInt(edge[1]);
			dSC.union(friend1, friend2);

		}

		int noOfFoes = Integer.parseInt(br.readLine());
		for (int i = 0; i < noOfFoes; i++) {
			String input = br.readLine();
			String[] Foes = input.split(" ");
			int foe1 = Integer.parseInt(Foes[0]);
			int foe2 = Integer.parseInt(Foes[1]);
			updateWithFoes(dSC, foe1, foe2);
		}

		System.out.println(printSpecialGroup(dSC));
	}

	private static int printSpecialGroup(MonksPartyHE dSC) {

		int max = 0;
		for (int i = 1; i < dSC.Array.length; i++) {
			if (dSC.Array[i] == i) {
				if (dSC.Size[i] > 0) {
					if (max < dSC.Size[i]) {
						max = dSC.Size[i];
					}
				}
			}
		}
		return max;
	}

	private static void updateWithFoes(MonksPartyHE dSC, int foe1, int foe2) {
		int root_1 = dSC.root(foe1);
		int root_2 = dSC.root(foe2);

		if (root_1 == root_2) {
			dSC.Size[root_1] = -1;
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

}
