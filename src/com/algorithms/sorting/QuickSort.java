package com.algorithms.sorting;

public class QuickSort {

	public static void quickSort(int[] array, int startIndex, int endIndex) {
		if (endIndex > startIndex) {
			int partitionIndex = partition(array, startIndex, endIndex);
			quickSort(array, startIndex, partitionIndex - 1);
			quickSort(array, partitionIndex + 1, endIndex);
		}
	}

	private static int partition(int[] array, int startIndex, int endIndex) {
		int pivot = array[endIndex];
		int i = startIndex - 1;
		for (int k = startIndex; k < endIndex; k++) {
			if (array[k] < pivot) {
				i++;
				int temp = array[i];
				array[i] = array[k];
				array[k] = temp;
			}
		}
		int pivotIndex = i + 1;
		int temp = array[pivotIndex];
		array[pivotIndex] = pivot;
		array[endIndex] = temp;
		return pivotIndex;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 8, 2, 4, 5, 0, 7, 1, 3, 8, 2, 6, 9 };
		System.out.println("The input array is as follows:");
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}
		quickSort(arr, 0, arr.length - 1);
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
