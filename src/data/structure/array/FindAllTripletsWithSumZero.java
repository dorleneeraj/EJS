package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllTripletsWithSumZero {

	public static void main(String[] args) {
		FindAllTripletsWithSumZero fatwsz = new FindAllTripletsWithSumZero();

		System.out.println("*******Using Naive Method********");
		fatwsz.naiveMethod(new int[] { 0, -1, 2, -3, 1 });
		fatwsz.naiveMethod(new int[] { 1, -2, 1, 0, 5 });
		System.out.println("********Using Sort and then Loop*******");
		fatwsz.sortAndLoop(new int[] { 0, -1, 2, -3, 1 }, 0);
		fatwsz.sortAndLoop(new int[] { 1, -2, 1, 0, 5 }, 0);
		System.out.println("*******For sum = 20 *******");
		fatwsz.sortAndLoop(new int[] { 1, 2, 4, 5, 6, 7, 9, 10, 11, 13 }, 20);
		System.out.println("*********findTriplets***********");
		fatwsz.findTriplets(new int[] { 0, -1, 2, -3, 1 }, 5, 0);

	}

	/**
	 * This is the naive method. It generates all the triplets and checks if
	 * their sum is zero
	 * 
	 * Time Complexity is: O(n^3)
	 * 
	 * @param array
	 */
	void naiveMethod(int[] array) {
		List<Triplet> triplets = new ArrayList<Triplet>();
		int len = array.length - 1;
		for (int i = 0; i <= len - 2; i++) {
			for (int j = i + 1; j <= len - 1; j++) {
				for (int k = j + 1; k <= len; k++) {
					if ((array[i] + array[j] + array[k]) == 0) {
						triplets.add(new Triplet(i, j, k));
					}
				}
			}
		}
		if (!triplets.isEmpty()) {
			for (Triplet t : triplets) {
				System.out.println(array[t.getX()] + " " + array[t.getY()]
						+ " " + array[t.getZ()]);
			}
		}

	}

	/**
	 * First sort the complete array and then check the sum against the given
	 * val.
	 * 
	 * The logic is very straight forward.
	 * 
	 * @param array
	 * @param val
	 */
	void sortAndLoop(int[] array, int val) {
		List<Triplet> triplets = new ArrayList<Triplet>();
		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {
			int l = i + 1;
			int r = array.length - 1;
			int x = array[i];
			while (l < r) {
				int sum = x + array[l] + array[r];
				if (sum == val) {
					triplets.add(new Triplet(i, l, r));
					l++;
					r--;
				} else if (sum < val) {
					l++;
				} else {
					if (sum > val) {
						r--;
					}
				}
			}
		}
		if (!triplets.isEmpty()) {
			for (Triplet t : triplets) {
				System.out.println(array[t.getX()] + " " + array[t.getY()]
						+ " " + array[t.getZ()]);
			}
		}

	}

	/**
	 * From GeekForGeeks Replacing Set with Hash, will improve the performance
	 * further...
	 */

	void findTriplets(int arr[], int n, int sum) {
		boolean found = false;

		for (int i = 0; i < n - 1; i++) {
			Set<Integer> s = new HashSet<Integer>();
			for (int j = i + 1; j < n; j++) {
				int x = sum - (arr[i] + arr[j]);
				if (s.contains(x)) {
					System.out.println(x + ", " + arr[i] + ", " + arr[j]);
					found = true;
				} else
					s.add(arr[j]);
			}
		}

		if (found == false)
			System.out.println(" No Triplet Found");
	}
}

class Triplet {
	int x, y, z;

	public Triplet(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "triplets [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
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
		Triplet other = (Triplet) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

}
