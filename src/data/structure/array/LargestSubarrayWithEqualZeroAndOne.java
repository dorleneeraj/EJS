package data.structure.array;

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
}
