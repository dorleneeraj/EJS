package com.algorithms.sorting;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 8, 2, 4, 5, 0, 7, 1, 3, 8, 2, 6, 9 };
		System.out.println("The input array is as follows:");
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		System.out.println("");
		System.out.println("The sorted array is:");
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}
	}
}
