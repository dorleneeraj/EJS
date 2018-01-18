package ola.problems;

public class ConsecutiveStrings {
	public static void main(String[] args) {
		int[] array = minimalOperations(new String[] { "ab", "aab", "abb",
				"abab", "abaaba" });
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	static int[] minimalOperations(String[] words) {
		int[] array = new int[words.length];
		int counter = 0;
		for (String s : words) {
			int operations = 0;
			for (int i = 0; i < s.length() - 1; i++) {
				if (s.charAt(i) == s.charAt(i + 1)) {
					operations++;
					i = i + 2;
				}
			}
			array[counter] = operations;
			counter++;

		}

		return array;
	}
}
