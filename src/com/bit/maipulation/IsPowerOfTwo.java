package com.bit.maipulation;

public class IsPowerOfTwo {
	public static void main(String[] args) {
		System.out.println("2 : " + simpleMethod(2));
		System.out.println("4 : " + simpleMethod(4));
		System.out.println("8 : " + simpleMethod(8));
		System.out.println("10 : " + simpleMethod(10));
		System.out.println("12 : " + simpleMethod(12));
		System.out.println("14 : " + simpleMethod(14));

		System.out.println("******************************");
		System.out.println("Bit-Wise");

		System.out.println("2 : " + bitwiseSolution(2));
		System.out.println("4 : " + bitwiseSolution(4));
		System.out.println("8 : " + bitwiseSolution(8));
		System.out.println("10 : " + bitwiseSolution(10));
		System.out.println("12 : " + bitwiseSolution(12));
		System.out.println("14 : " + bitwiseSolution(14));
		System.out.println("0 : " + bitwiseSolution(0));
	}

	public static boolean simpleMethod(int num) {
		boolean isPower = false;

		if (Math.ceil(Math.log(num) / Math.log(2)) == Math.floor(Math.log(num)
				/ Math.log(2))) {
			isPower = true;
		}

		return isPower;
	}

	public static boolean bitwiseSolution(int num) {
		boolean isPower = false;

		if ((num != 0 && ((num & (num - 1)) == 0))) {
			isPower = true;
		}

		return isPower;
	}

}
