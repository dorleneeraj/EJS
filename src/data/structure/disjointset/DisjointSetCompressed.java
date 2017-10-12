package data.structure.disjointset;

import java.util.Arrays;

public class DisjointSetCompressed {
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
		boolean areConnected = false;
		if (root(A - 1) == root(B - 1)) {
			areConnected = true;
		}
		return areConnected;
	}

	public int[] getArray() {
		return Arrays.copyOf(this.Array, this.ARRAY_LENGTH + 1);
	}

	public int getARRAY_LENGTH() {
		return this.ARRAY_LENGTH;
	}

	public int[] getSize() {
		return Arrays.copyOf(this.Size, this.ARRAY_LENGTH + 1);
	}

}
