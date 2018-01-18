package com.bit.maipulation;

/**
 * 
 * @author neeraj
 * 
 *         Problem Statement: Given a number, give the smallest power of '2'
 *         greater than or equal to that number.
 *
 *
 */
public class SmallestPowerOfTwo {
	public static void main(String[] args) {
		System.out.println("5: " + simpleMethod(5));
		System.out.println("10: " + simpleMethod(10));
		System.out.println("8: " + simpleMethod(8));
		System.out.println("2048: " + simpleMethod(2048));
		System.out.println("300: " + simpleMethod(300));
		System.out.println("1: " + simpleMethod(1));
		System.out.println("0: " + simpleMethod(0));
	}

	public static int simpleMethod(int n) {

		if ((n & (n & (n - 1))) == 0) {
			return n;
		}

		int count = 0;

		while (n != 0) {
			n >>= 1;
			count += 1;
		}

		return 1 << count;
	}
}
