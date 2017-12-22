package com.algorithms.sorting;

public class CountingSort {

	private static int[] countSort(int[] A, int k) {
		int[] C = new int[k + 1];
		int[] B = new int[A.length];
		// initialize the intermediate array to 0
		for (int i = 0; i < C.length; i++) {
			C[i] = 0;
		}
		// traverse the input array and store in C, the number of
		// occurrences of each index of array C in array A
		for (int i = 0; i < A.length; i++) {
			C[A[i]] = 1 + C[A[i]];
		}

		// For each index in C, store no. of elements less than or
		// equal to that index.
		for (int i = 1; i < C.length; i++) {
			C[i] = C[i] + C[i - 1];
		}

		// Output the final sorted array in B
		for (int i = (A.length - 1); i >= 0; i--) {
			B[C[A[i]] - 1] = A[i];
			C[A[i]] = C[A[i]] - 1;
		}
		return B;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 9, 1, 8, 2, 7, 3, 6, 4, 2, 5, 0, 1 };
		System.out.println("The input array is:");
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}
		arr = countSort(arr, 9);
		System.out.println();
		System.out.println("The sorted array is:");
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}
	}
}