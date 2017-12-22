package com.algorithms.sorting;

/*
 * It is one of the most basic sorting algorithms.
 * It takes an array [0....(n-1)] of length (n) as
 * an input and produces a sorted array as an 
 * output. It works in the same manner as most of 
 * the people sort a hand of cards. 
 * 
 * It is an in place sorting. At every iteration of
 * the loop, the key which is being checked, is 
 * "inserted" at the right position. The array to the
 * left of the key which is being compared is always
 * sorted.
 * 
 */

public class InsertionSort {
	public static void main(String[] args) {
		int[] array = new int[] { 5, 2, 4, 6, 3, 1 };
		// sortDescending(array);
		System.out.println("Array to be sorted....");
		printArray(array);
		// for-loop
		for (int i = 1; i <= array.length - 1; i++) {
			int key = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
				System.out.println();
				printArray(array);
			}
			array[j + 1] = key;
			System.out.println();
			printArray(array);
		}
		System.out.println();
		System.out.println("Sorted array....");
		printArray(array);
		/*
		 * At the beginning of each iteration of the outer for-loop, the
		 * sub-array A[0...i-1] is sorted. a[i] is the key being currently
		 * inserted. a[i+1....n-1] is the array which is yet to be sorted.
		 */
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(" ,");
			}
		}
	}

	public static void sortDescending(int[] array) {
		System.out.println("Descending order...");
		System.out.println("Array to be sorted...");
		printArray(array);
		System.out.println();
		for (int j = 1; j < array.length; j++) {
			int key = array[j];
			int i = j - 1;
			while (i >= 0 && array[i] < key) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
		System.out.println("Sorted array...");
		printArray(array);
	}
}
