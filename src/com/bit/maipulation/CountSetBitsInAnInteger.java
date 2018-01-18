package com.bit.maipulation;

public class CountSetBitsInAnInteger {

	static short[] lookup_t = new short[256];

	public static void main(String[] args) {
		System.out.println("Using simple method: " + simpleMethod(198));
		System.out.println("Using Brian-Kernighan's method: "
				+ brianAndKernighansAlgorithm(198));
		buildTheLookupTable();
		System.out.println("Using Lookup table method: "
				+ usingLookupTable(198));

	}

	public static void buildTheLookupTable() {
		for (int i = 0; i < 256; i++) {
			lookup_t[i] = (short) ((i & 1) + lookup_t[i / 2]);
		}
	}

	public static int simpleMethod(int number) {
		int count = 0;

		while (number > 0) {
			count += number & 1;
			number >>= 1;
		}

		return count;
	}

	public static int brianAndKernighansAlgorithm(int number) {
		int count = 0;

		// The simple method runs till the number = 0 and sequentially checks
		// all the bits one by one. So in the worst case it runs for O(n) time.

		// Brian-kernighan's algorithm runs for O(log(n)) time: Means it runs as
		// many times as there are set bits in the number. For any number, the
		// number of set bits = log(n), hence the complexity of: O(log(n))

		// How does it work?

		// Take the number. subtract 1 from it. What will happen by doing this?
		// It will toggle all the bits from right to left till the rightmost '1'
		// or the rightmost set bit is encountered(including the rightmost set
		// bit).
		// If we perform an '&' operation, it will un-set the rightmost bit.
		// If we run this in a loop(till number is > 0), we will get the count
		// depending upon how many times the loop is executed.

		while (number > 0) {
			number = (number) & (number - 1);
			count++;
		}

		return count;
	}

	public static int usingLookupTable(int number) {
		int count = 0;

		for (int i = 0; i < (Integer.SIZE / Byte.SIZE) && (number > 0); i++) {
			count += lookup_t[number & 255];
			number >>= 8;
		}

		return count;
	}

}
