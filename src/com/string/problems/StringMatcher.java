package com.string.problems;

public class StringMatcher {

	public static void main(String[] args) {
		StringMatcher sm = new StringMatcher();
		sm.naiveStringMatcher("neerajdorleraj", "raj");
		sm.naiveStringMatcher("neerarajdorleraj", "raj");

		sm.distinctPatternCharacters("neerajdorleraj", "raj");
		sm.distinctPatternCharacters("neerarajdorleraj", "raj");

		System.out.println("");
		System.out.println("");
		System.out.println("Rabin-karp");

		sm.patternSearch("neeraj", "raj");
		sm.patternSearch("neerajdorleraj", "raj");
		sm.patternSearch("neerarajdorleraj", "raj");
	}

	// naive string matcher. It just checks for all the possible shifts.
	// the worst case running time for the algorithm is O(n^2)
	public void naiveStringMatcher(String text, String pattern) {
		int textLen = text.length();
		int patternLen = pattern.length();
		boolean found = true;
		for (int i = 0; i < textLen - patternLen + 1; i++) {
			for (int j = 0; j < patternLen; j++) {
				if (Character.toLowerCase(text.charAt(i + j)) != Character
						.toLowerCase(pattern.charAt(j))) {
					found = false;
					break;
				} else {
					found = true;
				}
			}
			if (found) {
				System.out.println("String found at: " + i + ", "
						+ text.substring(i, i + patternLen));
				found = false;

			}

		}
	}

	// If all the characters in the Pattern string are different, then we can
	// improve the running time of the code to O(n)
	public void distinctPatternCharacters(String text, String pattern) {
		int textLen = text.length();
		int patternLen = pattern.length();
		boolean found = true;
		for (int i = 0; i < textLen - patternLen + 1;) {
			for (int j = 0; j < patternLen; j++) {
				if (Character.toLowerCase(text.charAt(i + j)) != Character
						.toLowerCase(pattern.charAt(j))) {
					found = false;
					if (Character.toLowerCase(text.charAt(i + j)) == Character
							.toLowerCase(pattern.charAt(0))) {
						i = i + j;
					} else {
						i = i + j + 1;
					}
					break;
				} else {
					found = true;
				}
			}
			if (found) {
				System.out.println("String found at: " + i + ", "
						+ text.substring(i, i + patternLen));
				i = i + patternLen - 1;
				found = false;
			}
		}
	}

	/**
	 * 
	 * Rabin - karp algorithm
	 * 
	 */

	private int prime = 101;

	public void patternSearch(String text, String pattern) {

		int m = pattern.length();
		int n = text.length();
		long patternHash = createHash(pattern, m);
		long textHash = createHash(text, m);
		for (int i = 0; i <= n - m; i++) {
			if (patternHash == textHash && checkEqual(text, pattern, i)) {
				System.out.println("String match found at: " + i);
			}
			if (i < n - m) {
				textHash = recalculateHash(text, i, textHash, m);
			}
		}

	}

	private long recalculateHash(String text, int index, long oldHash, int m) {
		long newHash = oldHash - text.charAt(index);
		newHash = newHash / prime;
		newHash = (long) (newHash + text.charAt(index + m)
				* Math.pow(prime, m - 1));
		return newHash;
	}

	private boolean checkEqual(String text, String pattern, int i) {
		boolean areEqual = true;
		int counter = 0;
		for (int j = i; j < i + pattern.length(); j++) {
			if (Character.toLowerCase(text.charAt(j)) != Character
					.toLowerCase(pattern.charAt(counter++))) {
				areEqual = false;
				break;
			}
		}
		return areEqual;
	}

	private long createHash(String str, int m) {
		long hash = 0;
		for (int i = 0; i < m; i++) {
			hash = (long) (hash + (str.charAt(i) * Math.pow(prime, i)));
		}
		return hash;
	}

	/****************************************************************************************************************/

}
