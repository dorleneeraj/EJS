package data.structure.disjointset;

import java.util.Arrays;

public class DisjointSetSimple {

	// The data is stored in an Array.
	private int[] Array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private int ARRAY_LENGTH;

	// Following are the basic operations performed.

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

		int temp = Array[A];

		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (Array[i] == temp) {
				Array[i] = Array[B];
			}
		}

	}

	private boolean find(int A, int B) {
		// It finds if A and B are connected. How do we know this?
		// If they are connected, they will have the same parent.
		boolean areConnected = false;

		if (Array[A] == Array[B]) {
			areConnected = true;
		}

		return areConnected;
	}

	public static void main(String[] args) {
		DisjointSetSimple dSS = new DisjointSetSimple();
		System.out.println("Are nodes 1 and 2 connected?: " + dSS.find(1, 2));
		dSS.union(4, 5);
		System.out.println("Joining 4 & 5: " + Arrays.toString(dSS.Array));
		dSS.union(0, 1);
		System.out.println("Joining 0 & 1: " + Arrays.toString(dSS.Array));
		dSS.union(2, 3);
		System.out.println("Joining 2 & 3: " + Arrays.toString(dSS.Array));
		dSS.union(6, 7);
		System.out.println("Joining 6 & 7: " + Arrays.toString(dSS.Array));
		dSS.union(8, 9);
		System.out.println("Joining 8 & 9: " + Arrays.toString(dSS.Array));
		dSS.union(8, 0);
		System.out.println("Joining 8 & 0: " + Arrays.toString(dSS.Array));
		System.out.println("Are nodes 0 and 9 connected?: " + dSS.find(0, 9));

	}
}
