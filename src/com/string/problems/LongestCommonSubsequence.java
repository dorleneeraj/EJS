package com.string.problems;

import java.util.HashSet;
import java.util.Set;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		LongestCommonSubsequence LCS = new LongestCommonSubsequence();
		Set<String> LCSSet = LCS.bruteForceMethod("neeraj", "viraaj");
		for (String s : LCSSet) {
			System.out.println(s);
		}
		LCSSet = LCS.bruteForceMethod("ABNCDBTJALWZEFOY", "CNATBWKRJDGZLEFOY");
		for (String s : LCSSet) {
			System.out.println(s);
		}

		int maxLenLCS = LCS.recursiveLCS("ABNCDBTJALWZEFOY",
				"CNATBWKRJDGZLEFOY", 15, 16);
		System.out.println(maxLenLCS);

		LCS.init(16, 17);
		maxLenLCS = LCS.withMemoization("ABNCDBTJALWZEFOY",
				"CNATBWKRJDGZLEFOY", 15, 16);
		System.out.println(maxLenLCS);

		LCS.bottomUpApproach("ABNCDBTJALWZEFOY", "CNATBWKRJDGZLEFOY");
		LCS.bottomUpApproach("neeraj", "viraaj");
	}

	// Brute force method: What brute force method states is that:
	// Let us consider two strings X(of length M) and Y(of length N).
	// For all the subsequences of string X, check if it is also a
	// subsequence of Y.

	// Total sequences of X(with length M) = 2^m
	// Time to check if it is a subsequence of Y(with length N) = N
	// Therefore total time using brute force = O((2^m)*n)

	public Set<String> bruteForceMethod(String X, String Y) {
		int longestLCSLen = 0;
		Set<String> longestLCS = new HashSet<String>();

		Set<String> subsequencesOfX = PrintAllSubSequence.topToBottom(X);
		for (String str : subsequencesOfX) {
			if (checkIfSubsequenceOfY(str, Y)) {
				if (str.length() > longestLCSLen) {
					longestLCS.clear();
					longestLCS.add(str);
					longestLCSLen = str.length();
				} else if (str.length() == longestLCSLen) {
					if (!longestLCS.contains(str)) {
						longestLCS.add(str);
					}
				}
			}
		}
		return longestLCS;
	}

	private boolean checkIfSubsequenceOfY(String str, String y) {
		boolean isSubsequence = false;
		int strLen = str.length();
		int yLen = y.length();
		int strIndex = 0;
		int yIndex = 0;

		while (yIndex < yLen && strIndex < strLen) {
			if (Character.toLowerCase(y.charAt(yIndex)) == Character
					.toLowerCase(str.charAt(strIndex))) {
				strIndex++;
			}
			yIndex++;
		}
		if (strIndex < strLen) {
			isSubsequence = false;
		} else {
			isSubsequence = true;
		}

		return isSubsequence;
	}

	public int recursiveLCS(String X, String Y, int i, int j) {

		if (i == 0 || j == 0) {
			return 0;
		}

		if (Character.toLowerCase(X.charAt(i)) == Character.toLowerCase(Y
				.charAt(j))) {
			return 1 + recursiveLCS(X, Y, i - 1, j - 1);
		} else {
			return Math.max(recursiveLCS(X, Y, i - 1, j),
					recursiveLCS(X, Y, i, j - 1));
		}
	}

	int[][] L;

	public void init(int i, int j) {
		L = new int[i][j];
		for (int k = 0; k < i; k++) {
			for (int l = 0; l < j; l++) {
				L[k][l] = 0;
			}
		}
	}

	public int withMemoization(String X, String Y, int i, int j) {
		int maxSize = 0;
		if (i == 0 || j == 0) {
			return 0;
		} else if (L[i][j] >= 0) {
			return L[i][j];
		} else if (Character.toLowerCase(X.charAt(i)) == Character
				.toLowerCase(Y.charAt(j))) {
			maxSize = 1 + withMemoization(X, Y, i - 1, j - 1);
		} else {
			maxSize = Math.max(withMemoization(X, Y, i - 1, j),
					withMemoization(X, Y, i, j - 1));
		}
		L[i][j] = maxSize;
		return maxSize;
	}

	public void bottomUpApproach(String X, String Y) {
		init(X.length(), Y.length());
		for (int i = 0; i < X.length(); i++) {
			for (int j = 0; j < Y.length(); j++) {
				if (Character.toLowerCase(X.charAt(i)) == Character
						.toLowerCase(Y.charAt(j))) {
					int prevVal = 0;
					if (i > 0 && j > 0) {
						prevVal = L[i - 1][j - 1];
					}
					L[i][j] = prevVal + 1;
				} else {
					int maxVal = 0;
					int iVal = 0;
					int jVal = 0;
					if (i > 0) {
						iVal = L[i - 1][j];
					}
					if (j > 0) {
						jVal = L[i][j - 1];
					}
					maxVal = Math.max(iVal, jVal);
					L[i][j] = maxVal;
				}
			}
		}
		printL(X.length(), Y.length());
		// Set<String> lcsStrings = lcs(X, Y, X.length() - 1, Y.length() - 1);
		// for (String str : lcsStrings) {
		// System.out.println(str);
		// }

		Set<String> set = new HashSet<String>();
		allLCS(L, X, Y, X.length(), Y.length(), set, "");
		System.out.println(set);
	}

	private Set<String> buildLCS(String X, String Y, int i, int j) {
		Set<String> strings = new HashSet<String>();
		Set<String> previousStrings;
		if (i > 0 && j > 0) {
			if ((L[i - 1][j] == L[i][j - 1]) && (L[i][j] == L[i - 1][j] + 1)) {
				previousStrings = buildLCS(X, Y, i - 1, j - 1);
				for (String str : previousStrings) {
					strings.add(X.charAt(i) + str);
				}
			} else {

				if (L[i][j - 1] == L[i][j] && L[i][j - 1] > L[i - 1][j]) {
					j = j - 1;
					return buildLCS(X, Y, i, j - 1);
				} else if (L[i - 1][j] == L[i][j] && L[i - 1][j] > L[i][j - 1]) {
					return buildLCS(X, Y, i - 1, j);
				} else {
					Set<String> s1 = buildLCS(X, Y, i - 1, j);
					Set<String> s2 = buildLCS(X, Y, i, j - 1);
					for (String str : s1) {
						strings.add(X.charAt(i) + str);
					}
					for (String str : s2) {
						strings.add(Y.charAt(j) + str);
					}
				}
			}
		} else {
			strings.add("");
		}
		return strings;
	}

	private void printL(int i, int j) {

		for (int k = 0; k < i; k++) {
			for (int l = 0; l < j; l++) {
				System.out.print(L[k][l] + " ");
			}
			System.out.println();
		}

	}

	Set<String> lcs(String s1, String s2, int len1, int len2) {
		if (len1 == 0 || len2 == 0) {
			Set<String> set = new HashSet<String>();
			set.add("");
			return set;
		}
		if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
			Set<String> set = lcs(s1, s2, len1 - 1, len2 - 1);
			Set<String> set1 = new HashSet<>();
			for (String temp : set) {
				temp = temp + s1.charAt(len1 - 1);
				set1.add(temp);
			}
			return set1;
		} else {
			Set<String> set = new HashSet<>();
			Set<String> set1 = new HashSet<>();
			if (L[len1 - 1][len2] >= L[len1][len2 - 1]) {
				set = lcs(s1, s2, len1 - 1, len2);
			}
			if (L[len1][len2 - 1] >= L[len1 - 1][len2]) {
				set1 = lcs(s1, s2, len1, len2 - 1);
			}
			for (String temp : set) {
				set1.add(temp);
			}
			// System.out.println("In lcs" + set1);
			return set1;

		}
	}

	public static void allLCS(int[][] c, String X, String Y, int i, int j,
			Set<String> setLCS, String s) {
		// return when either of the string length is 0
		if (i == 0 || j == 0) {
			setLCS.add(s);
			return;
		}

		// if last characters are equal, they belong in lcs
		if (X.charAt(i - 1) == Y.charAt(j - 1)) {
			// prepend the char to lcs since, we are going backwards
			s = X.charAt(i - 1) + s;
			// continue finding lcs in substrings X.substring(0,i-1) and
			// Y.substring(0,j-1)
			allLCS(c, X, Y, i - 1, j - 1, setLCS, s);
		} // if there is a tie in matrix cells, we backtrack in both ways,
			// else one way, which ever is greater
		else if (c[i - 1][j] == c[i][j - 1]) {
			// continue finding lcs in substring X.substring(0,i-1)
			allLCS(c, X, Y, i - 1, j, setLCS, s);
			// continue finding lcs in substring Y.substring(0,j-1)
			allLCS(c, X, Y, i, j - 1, setLCS, s);
		} else if (c[i - 1][j] > c[i][j - 1]) {
			allLCS(c, X, Y, i - 1, j, setLCS, s);
		} else {
			allLCS(c, X, Y, i, j - 1, setLCS, s);
		}

	}

}
