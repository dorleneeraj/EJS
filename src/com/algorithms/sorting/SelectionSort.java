package com.algorithms.sorting;

/*
 Selection sort:
 For every outer loop iteration, selection sort 
 selects the appropriate element from the remaining
 unsorted array. Hence the name 'selection sort'. 
 */

public class SelectionSort {
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
			int smallIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[smallIndex]) {
					smallIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[smallIndex];
			arr[smallIndex] = temp;
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
