package data.structure.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author neeraj
 *
 *         Given an array of non-negative integers, you are initially positioned
 *         at the first index of the array.
 * 
 *         Each element in the array represents your maximum jump length at that
 *         position.
 * 
 *         Your goal is to reach the last index in the minimum number of jumps.
 * 
 *         Example : Given array A = [2,3,1,1,4]
 * 
 *         The minimum number of jumps to reach the last index is 2. (Jump 1
 *         step from index 0 to 1, then 3 steps to the last index.)
 * 
 *         If it is not possible to reach the end index, return -1.
 *
 *
 */

public class MinJumpsArray {
	public static void main(String[] args) {
		jump(new int[] { 1, 0, 1, 1, 4 });
		System.out.println(minJumpsBottomUp(new int[] { 1, 0, 1, 1, 4 }, 5));
		// System.out.println(minJumps(new int[] { 2, 3, 1, 1, 4 }, 0, 5));
	}

	public static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

	static public int jump(int[] A) {
		int jumps = 0;
		System.out.println(minJumpsRecursive(A, 0));
		return jumps;
	}

	static public int minJumpsRecursive(int[] A, int index) {
		int minJumps = Integer.MAX_VALUE - 1;
		int val = A[index];
		int i = index;
		if (memo.containsKey(index)) {
			minJumps = memo.get(index);
		} else if (index == A.length - 1) {
			minJumps = 0;
			memo.put(index, minJumps);
		} else if (val > 0) {
			i = i + 1;
			while ((i < A.length) && (i <= index + val)) {
				int jumps = minJumpsRecursive(A, i);
				if ((jumps + 1) < minJumps) {
					minJumps = jumps + 1;
					memo.put(index, minJumps);
				}
				i++;
			}
			if (!memo.containsKey(index)) {
				memo.put(index, Integer.MAX_VALUE - 1);
			}
		} else if (val == 0) {
			memo.put(index, Integer.MAX_VALUE - 1);
			minJumps = Integer.MAX_VALUE - 1;
		}
		return minJumps;
	}

	static public int minJumpsBottomUp(int[] arr, int n) {

		int jumps[] = new int[n]; // jumps[n-1] will hold the
		// result
		int i, j;

		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE; // if first element is 0,
		// end cannot be reached

		jumps[0] = 0;

		// Find the minimum number of jumps to reach arr[i]
		// from arr[0], and assign this value to jumps[i]
		for (i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (j = 0; j < i; j++) {
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					break;
				}
			}
		}
		return jumps[n - 1];
	}

	static int minJumps(int arr[], int l, int h) {
		// Base case: when source and destination are same
		if (h == l)
			return 0;

		// When nothing is reachable from the given source
		if (arr[l] == 0)
			return Integer.MAX_VALUE;

		// Traverse through all the points reachable from arr[l]. Recursively
		// get the minimum number of jumps needed to reach arr[h] from these
		// reachable points.
		int min = Integer.MAX_VALUE;
		for (int i = l + 1; i <= h && i <= l + arr[l]; i++) {
			int jumps = minJumps(arr, i, h);
			if (jumps != Integer.MAX_VALUE && jumps + 1 < min)
				min = jumps + 1;
		}

		return min;
	}

	static int minJumps2(int[] arr, int n) {
		int jumps[] = new int[n]; // jumps[n-1] will hold the
									// result
		int i, j;

		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE; // if first element is 0,
										// end cannot be reached

		jumps[0] = 0;

		// Find the minimum number of jumps to reach arr[i]
		// from arr[0], and assign this value to jumps[i]
		for (i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (j = 0; j < i; j++) {
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					break;
				}
			}
		}
		return jumps[n - 1];
	}
}
