package data.structure.array;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MinimumNoOfCoins {

	private static Map<Integer, Integer> minCoinsMap = new TreeMap<Integer, Integer>();

	public static int getMinCoinsMemoization(int value, int[] denominations) {
		int minCoins = 0;
		if (minCoinsMap.containsKey(value)) {
			minCoins = minCoinsMap.get(value);
		} else {
			int minVal = Integer.MAX_VALUE;
			for (int i = 0; i < denominations.length; i++) {
				if (value == denominations[i]) {
					minCoins = 1;
					break;
				} else if (value > denominations[i]) {
					int val = getMinCoinsMemoization(value - denominations[i],
							denominations) + 1;
					if (val < minVal) {
						minVal = val;
						minCoins = minVal;
					}
				}
			}
		}
		minCoinsMap.put(value, minCoins);
		System.out.println(minCoinsMap);
		return minCoins;
	}

	public static Integer getMinCoins(int amount, Integer[] denominations) {
		int coins = 0;
		// Arrays.sort(denominations, (v1, v2) -> Integer.compare(v2, v1));
		for (Integer denomination : denominations) { // reverse sorted iteration
														// order
			if (denomination > amount)
				continue; // if a denomination > amount, we can skip it
			int cofi = amount / denomination; // # coins of denominations[i]
			amount -= cofi * denomination; // reduce the amount proportionately
			coins += cofi;
		}
		return coins;
	}

	public static int getMinCoins2(int value, int[] denominations) {
		// This is a very interesting solution.
		// Create two arrays, of length = value + 1.
		// That is each value between 1 to 'value' has an index in each of the
		// arrays.
		// Therefore arrays will be of length value + 1. (indexes for 0 to n)

		int[] valueArray = new int[value + 1];
		// Initialize this array with Infinity, i.e with Integer.Max value.
		Arrays.fill(valueArray, Integer.MAX_VALUE - 1);
		valueArray[0] = 0;
		int[] resultArray = new int[value + 1];
		Arrays.fill(resultArray, -1);

		// Thus, the arrays are now initialized. This is a bottom up solution.
		// Why?
		// Because the solution is built in the bottom up fashion, meaning
		// the min no. of coins are calculated for all the values between 1 to
		// n.

		// The algorithm goes like this,
		// For every coin denomination, check, if using this coin will make the
		// number of coins minimum for the total under consideration.

		// The loop is run for all the available coin denominations.
		for (int i = 0; i < denominations.length; i++) {
			// Check if this coin helps in building minimum coins for the
			// total.
			for (int j = 1; j <= value; j++) {
				// check the ith coin can be useful to form the value of j.
				if (j >= denominations[i]) {
					if (valueArray[j - denominations[i]] + 1 < valueArray[j]) {
						valueArray[j] = 1 + valueArray[j - denominations[i]];
						resultArray[j] = i;
					}
				}
			}
		}

		return valueArray[value];
	}

	public static void main(String[] args) {
		// System.out.println(getMinCoins(18, new Integer[] { 1, 9, 10 }));
		System.out.println(getMinCoins2(5, new int[] { 1, 2, 3 }));
		// System.out.println(getMinCoinsMemoization(13, new int[] { 1, 2, 5
		// }));
		// System.out.println(minCoinsMap);
	}
}