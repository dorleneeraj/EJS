package com.string.problems;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CamelCasePatternMatching {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCases = Integer.parseInt(scanner.nextLine().trim());
		for (int i = 0; i < testCases; i++) {

			// for every testCase
			int noOfStrings = Integer.parseInt(scanner.nextLine().trim());
			List<String> strings = Arrays.asList(scanner.nextLine().split(" "));
			String pattern = scanner.nextLine();
			Pattern root = buildPattern(pattern);
			boolean matchFound = false;
			System.out.println();
			for (String str : strings) {
				if (process(str, root)) {
					System.out.println(str);
					matchFound = true;
				}
			}

			if (!matchFound) {
				System.out.println("No match found");
			}
		}
		scanner.close();

	}

	private static boolean process(String str, Pattern root) {
		int strLen = str.length();
		boolean isValid = false;
		if (Character.isUpperCase(str.charAt(0))
				&& (str.charAt(0) == root.getC())) {
			int i = 1;
			root = root.getNext();
			while (i < strLen) {
				if (Character.isUpperCase(str.charAt(i))) {
					if (str.charAt(i) == root.getC()) {
						if (null == root.getNext()) {
							isValid = true;
							break;
						}
						root = root.getNext();
					} else {
						isValid = false;
						break;
					}
				}
				i++;
			}
		}

		return isValid;
	}

	private static Pattern buildPattern(String pattern) {
		int patternLen = pattern.length();
		Pattern root = null;
		Pattern iterator = null;
		for (int i = 0; i < patternLen; i++) {
			Pattern p = new Pattern(pattern.charAt(i));
			if (null == root) {
				root = p;
				iterator = p;
			} else {
				iterator.next = p;
				iterator = iterator.next;
			}
		}
		return root;
	}
}

class Pattern {
	Character c;
	Pattern next = null;

	public Pattern(Character ch) {
		this.c = ch;
	}

	public Character getC() {
		return c;
	}

	public Pattern getNext() {
		return next;
	}

	public void setNext(Pattern next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Pattern [c=" + c + ", next=" + next + "]";
	}

}
