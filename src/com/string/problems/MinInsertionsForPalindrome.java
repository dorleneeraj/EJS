package com.string.problems;

public class MinInsertionsForPalindrome {
	public static void main(String[] args) {
		String s1 = "neeraj";
		int minInsertions = minInsertionsForPalindrome(s1);
		System.out
				.println("Min insertions for String: neeraj to make it palindrome are : "
						+ minInsertions);
		String s2 = "nitin";
		minInsertions = minInsertionsForPalindrome(s2);
		System.out
				.println("Min insertions for String: nitin to make it palindrome are : "
						+ minInsertions);

		String s3 = "esystem";
		minInsertions = minInsertionsForPalindrome(s3);
		System.out
				.println("Min insertions for String: esystem to make it palindrome are : "
						+ minInsertions);

		String s4 = "abc";
		System.out.println(findMinInsertionsDP(s4.toCharArray(), s4.length()));
		System.out.println(findMinInsertionsDP(s1.toCharArray(), s1.length()));
		System.out.println(findMinInsertionsDP(s2.toCharArray(), s2.length()));
		System.out.println(findMinInsertionsDP(s3.toCharArray(), s3.length()));

	}

	private static int minInsertionsForPalindrome(String s) {
		int insertionsNeeded = 0;
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s
					.charAt(j))) {
				// do nothing . just change the pointers
				i++;
				j--;
			} else {
				// insert character at 'j th index' before character at 'i th
				// index'
				s = s.substring(0, i) + s.charAt(j) + s.substring(i);
				i++;
				insertionsNeeded++;
			}
		}
		System.out.println(s);
		return insertionsNeeded;
	}

	private static int findMinInsertionsDP(char str[], int len) {
		int[][] table = new int[len][len];
		int l, h, gap;
		for (gap = 1; gap < len; gap++) {
			for (l = 0, h = gap; h < len; l++, h++) {
				table[l][h] = (str[l] == str[h]) ? table[l + 1][h - 1] : (Math
						.min(table[l][h - 1], table[l + 1][h]) + 1);
			}
		}
		System.out.println("For String: " + String.valueOf(str));
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println("");
		}
		return table[0][len - 1];
	}

}
