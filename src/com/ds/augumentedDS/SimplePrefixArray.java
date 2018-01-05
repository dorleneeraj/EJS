package com.ds.augumentedDS;

public class SimplePrefixArray {
	static private int[] a = { 2, 1, 4, 6, -1, 5, -32, 0, 1 };
	static private int[] prefixSumArray;

	static private void buildPrefixSum() {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];
			prefixSumArray[i] = sum;
		}
	}

	private static void printArray() {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

		System.out.println();

	}

	static private void printPrefixArray() {
		for (int i = 0; i < prefixSumArray.length; i++) {
			System.out.print(prefixSumArray[i] + " ");
		}

		System.out.println();
	}

	static private void updateIndex(int index, int newVal) {
		int prevVal = a[index];
		a[index] = newVal;
		
		int diff = newVal - prevVal;
		for (int i = index ; i < prefixSumArray.length; i++) {
			prefixSumArray[i] += diff;
		}
	}

	public static void main(String[] args) {
		prefixSumArray = new int[a.length];
		buildPrefixSum();
		printArray();
		printPrefixArray();
		updateIndex(0, 12);
		printPrefixArray();
	}

}
