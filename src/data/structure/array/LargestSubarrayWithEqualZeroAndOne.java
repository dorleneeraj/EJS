package data.structure.array;

import java.util.HashMap;

public class LargestSubarrayWithEqualZeroAndOne {
	public static void main(String[] args) {
		int[] array = { 1, 0, 1, 1, 1, 0, 0 };
		int[] result = naive(array);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		array = new int[] { 1, 1, 1, 1 };
		result = naive(array);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		array = new int[] { 0, 0, 1, 1, 0 };
		result = naive(array);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

	}

	public static int[] naive(int[] array) {
		int[] indices = new int[] { -1, -1 };
		int length = array.length;
		int startingIndex = -1;
		int endIndex = -1;
		int maxLenSubArray = -1;
		int zerosFound = 0;
		int onesFound = 0;

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				for (int k = i; k <= j; k++) {
					if (array[k] == 0) {
						zerosFound++;
					} else {
						onesFound++;
					}
				}
				if (zerosFound == onesFound) {
					if (((j - i) + 1) > maxLenSubArray) {
						startingIndex = i;
						endIndex = j;
						maxLenSubArray = (j - i) + 1;
					}
				}
				zerosFound = 0;
				onesFound = 0;
			}
		}
		if (maxLenSubArray != -1) {
			indices[0] = startingIndex;
			indices[1] = endIndex;
		}

		return indices;
	}

	public void efficientSolution(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = 0;
		int max_len = 0;
		int startIndex = 0;
		int endIndex = -1;

		for (int i = 0; i < array.length; i++) {
			array[i] = (array[i] == 0) ? -1 : 1;
		}

		for (int i = 0; i < array.length; i++) {
			sum += array[i];
			if (sum == 0) {
				max_len = i + 1;
				endIndex = i;
			}

			if (map.containsKey(sum)) {
				if (max_len < (i - map.get(sum + array.length))) {
					max_len = i - map.get(sum + array.length);
					endIndex = i;
				}
			} else {
				map.put(sum + array.length, i);
			}

		}

		for (int i = 0; i < array.length; i++) {
			array[i] = (array[i] == -1) ? 0 : 1;
		}

		int end = endIndex - max_len + 1;
		System.out.println(end + " to " + endIndex);

	}

}
