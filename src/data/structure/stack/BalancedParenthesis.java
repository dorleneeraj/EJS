package data.structure.stack;

import java.util.ArrayList;
import java.util.List;

public class BalancedParenthesis {

	// Simple stack implementation
	static class Stack {
		int top = -1;
		List<Character> items = new ArrayList<Character>();

		void push(char x) {
			items.add(x);
			top++;
		}

		Character pop() {
			Character c = null;
			c = items.get(top);
			top--;
			return c;
		}

		boolean isEmpty() {
			return top == -1 ? true : false;
		}
	}

	public static void main(String[] args) {
		String parenthesisString = "({([])})";
		if (areParenthesisBalanced(parenthesisString)) {
			System.out.println("Balanced...");
		} else {
			System.out.println("Not Balanced...");
		}
	}

	private static boolean areParenthesisBalanced(String parenthesisString) {
		boolean isBalanced = true;
		Stack stack = new Stack();
		for (int i = 0; i < parenthesisString.length(); i++) {
			if (isOpeningParenthesis(parenthesisString.charAt(i))) {
				stack.push(parenthesisString.charAt(i));
			} else if (isClosingParenthesis(parenthesisString.charAt(i))) {
				if (stack.isEmpty()) {
					isBalanced = false;
					break;
				} else if (!isMatchingPair(stack.pop(),
						parenthesisString.charAt(i))) {
					isBalanced = false;
					break;
				}
			}
		}
		if (!stack.isEmpty()) {
			isBalanced = false;
		}

		return isBalanced;
	}

	private static boolean isMatchingPair(Character c1, Character c2) {
		boolean isMatching = false;
		if (c1 == '{' && c2 == '}') {
			isMatching = true;
		} else if (c1 == '(' && c2 == ')') {
			isMatching = true;
		} else if (c1 == '[' && c2 == ']') {
			isMatching = true;
		}
		return isMatching;
	}

	private static boolean isClosingParenthesis(char c) {
		return (c == '}' || c == ']' || c == ')') ? true : false;
	}

	private static boolean isOpeningParenthesis(char c) {
		return (c == '{' || c == '[' || c == '(') ? true : false;
	}

}
