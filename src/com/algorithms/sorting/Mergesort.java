package com.algorithms.sorting;

public class Mergesort {

	public static void sort(Integer[] arr, int startIndex, int endIndex) {
		if (endIndex <= startIndex) {
			return;
		} else {
			int len = endIndex - startIndex + 1;
			int mid = len / 2;
			sort(arr, startIndex, (startIndex + mid) - 1);
			sort(arr, startIndex + mid, endIndex);
			merge(arr, startIndex, (startIndex + mid) - 1, endIndex);
		}
	}

	private static void merge(Integer[] arr, int startIndex, int mid,
			int endIndex) {
		int counter1 = 0;
		int counter2 = 0;
		int[] leftArray = new int[mid - startIndex + 1];
		int[] rightArray = new int[endIndex - mid];
		int c = 0;
		for (int i = startIndex; i <= mid; i++) {
			leftArray[c++] = arr[i];
		}
		c = 0;
		for (int i = (mid + 1); i <= endIndex; i++) {
			rightArray[c++] = arr[i];
		}

		int i = 0;
		for (i = startIndex; i <= endIndex; i++) {
			if (counter1 >= leftArray.length) {
				break;
			}
			if (counter2 >= rightArray.length) {
				break;
			}
			if (leftArray[counter1] < rightArray[counter2]) {
				arr[i] = leftArray[counter1];
				counter1++;
			} else {
				arr[i] = rightArray[counter2];
				counter2++;
			}
		}
		if (counter1 < leftArray.length) {
			while (counter1 < leftArray.length) {
				arr[i++] = leftArray[counter1++];
			}
		} else if (counter2 < rightArray.length) {
			while (counter2 < rightArray.length) {
				arr[i++] = rightArray[counter2++];
			}
		}
	}

	public static void main(String[] args) {
		// Integer[] arr = new Integer[] { 8, 5, 6, 1, 10, 50, 29, 31, 4, 0,
		// 100,
		// 0, 0, 0, 1, 1, 4, 8 };
		Integer[] arr = new Integer[] { 1, 2, 3, 4 };
		System.out.println("The input array is as follows:");
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}
		sort(arr, 0, (arr.length - 1));
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
