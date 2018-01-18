package com.bit.maipulation;

public class CheckIfTwoNumbersDifferAtSingleBit {
	public static void main(String[] args) {
		
	}

	static boolean solution(int a, int b) {
		boolean differBySinglePosition = false;

		int num = a ^ b;
		differBySinglePosition = IsPowerOfTwo.bitwiseSolution(num);

		return differBySinglePosition;
	}
}
