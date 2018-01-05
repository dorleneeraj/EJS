package com.ds.augumentedDS;

public class BinaryIndexedTree {
	public static void main(String[] args) {
		int n = 12;
		int[] a = { 0, 4, 2, 7, 5, 1, 3, 6, 4, 6, 63, 3 };
		int[] BIT = new int[n + 1];

		// build the BIT
		for (int i = 1; i <= n; i++) {
			int j = i;
			System.out.println("a[" + i + "] = " + a[i]);
			while (j <= n) {
				BIT[j] += a[i];
				j = j + (j & (-j));
			}

			for (j = 1; j <= n; j++) {
				System.out.print(BIT[j] + " ");
			}
			System.out.println();
		}
	}
}
