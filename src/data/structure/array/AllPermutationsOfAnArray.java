package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// given an array, print all of its permutations

public class AllPermutationsOfAnArray {

	public static void main(String[] args) {
		// List<List<Integer>> list = findPermutations(Arrays
		// .asList(new Integer[] { 1, 2, 3 }));
		//
		//
		// System.out.println(list.size());
		//
		// permuteHelper(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 0);

		List<List<Integer>> list = permute(new int[] { 1, 2, 3, 4, 5, 6, 7, 8,
				9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 });
		// for (List<Integer> l : list) {
		// System.out.println(l);
		// }

		System.out.println(list.size());

	}

	public static List<List<Integer>> findPermutations(List<Integer> array) {
		List<List<Integer>> arrayPermutations = new ArrayList<List<Integer>>();

		if (array.isEmpty()) {
			return Collections.emptyList();
		}

		if (array.size() == 1) {
			List<Integer> l = new ArrayList<Integer>();
			l.add(array.get(0));
			arrayPermutations.add(l);
		} else {
			for (int i = 0; i < array.size(); i++) {
				List<Integer> helperArray = new ArrayList<Integer>();
				for (Integer num : array) {
					helperArray.add(num);
				}
				int constantInt = helperArray.remove(i);
				List<List<Integer>> intermediatePermutations = findPermutations(helperArray);
				for (List<Integer> list : intermediatePermutations) {
					list.add(0, constantInt);
				}

				arrayPermutations.addAll(intermediatePermutations);
			}

		}

		return arrayPermutations;
	}

	private static void permuteHelper(int[] arr, int index) {
		if (index >= arr.length - 1) { // If we are at the last element -
										// nothing left to permute
			// System.out.println(Arrays.toString(arr));
			// Print the array
			System.out.print("[");
			for (int i = 0; i < arr.length - 1; i++) {
				System.out.print(arr[i] + ", ");
			}
			if (arr.length > 0)
				System.out.print(arr[arr.length - 1]);
			System.out.println("]");
			return;
		}

		for (int i = index; i < arr.length; i++) { // For each index in the sub
													// array arr[index...end]

			// Swap the elements at indices index and i
			int t = arr[index];
			arr[index] = arr[i];
			arr[i] = t;

			// Recurse on the sub array arr[index+1...end]
			permuteHelper(arr, index + 1);

			// Swap the elements back
			t = arr[index];
			arr[index] = arr[i];
			arr[i] = t;
		}
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return results;
		}
		List<Integer> result = new ArrayList<>();
		dfs(nums, results, result);
		return results;
	}

	public static void dfs(int[] nums, List<List<Integer>> results,
			List<Integer> result) {
		if (nums.length == result.size()) {
			List<Integer> temp = new ArrayList<>(result);
			results.add(temp);
		}
		for (int i = 0; i < nums.length; i++) {
			if (!result.contains(nums[i])) {
				result.add(nums[i]);
				dfs(nums, results, result);
				result.remove(result.size() - 1);
			}
		}
	}
}
