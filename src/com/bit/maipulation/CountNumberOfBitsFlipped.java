package com.bit.maipulation;

/**
 * 
 * @author neeraj
 *
 *
 *         Problem Description: Given two numbers 'a' and 'b' , find out the
 *         number of bits to be flipped to convert a to b and vice-a-versa.
 * 
 *         Solution: Get the a XOR b. This will set all the bits that are
 *         unequal in a and b. Get the number of bits set.
 */

public class CountNumberOfBitsFlipped {

	public static void main(String[] args) {
		System.out.println("Convert 10 to 20: " + solution(10, 20));
		System.out.println("Convert 10 to 7: " + solution(10, 7));
	}

	public static int solution(int a, int b) {
		int count = 0;

		int xor = a ^ b;
		count = CountSetBitsInAnInteger.brianAndKernighansAlgorithm(xor);

		return count;
	}

}
