package com.string.problems;

public class StringMatcherFA {

	private static final int NO_OF_CHARACTERS = 256;
	private static int[][] TF;

	public static void main(String[] args) {
		StringMatcherFA sm = new StringMatcherFA();
		sm.search("abcd", "ababcdefabcd");
	}

	public void search(String pattern, String text) {
		int M = pattern.length();
		int N = text.length();
		TF = new int[M + 1][NO_OF_CHARACTERS];
		computeTF(pattern, M);
		printTF(M);

		int state = 0;
		for (int i = 0; i < N; i++) {
			state = TF[state][text.charAt(i)];
			if (state == M) {
				System.out.println("Pattern found at index: " + (i - M + 1));
			}
		}
	}

	private void printTF(int M) {
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j < NO_OF_CHARACTERS; j++) {
				System.out.print(TF[i][j] + " ");
			}
			System.out.println();
		}

	}

	private void computeTF(String pattern, int M) {
		int state = 0;
		int x = 0;
		for (state = 0; state <= M; ++state) {
			for (x = 0; x < NO_OF_CHARACTERS; ++x) {
				TF[state][x] = getNextState(pattern, M, state, x);
			}
		}
	}

	private int getNextState(String pattern, int M, int state, int x) {
		int newState = 0;
		int i;

		if (state < M && (x == pattern.charAt(state))) {
			newState = state + 1;
		} else {
			for (newState = state; newState > 0; newState--) {
				if (pattern.charAt(newState - 1) == x) {
					for (i = 0; i < newState - 1; i++) {
						if (pattern.charAt(i) != pattern.charAt(state
								- newState + i)) {
							break;
						}
					}
					if (i == newState - 1) {
						break;
					}
				}
			}
		}
		return newState;
	}
}
