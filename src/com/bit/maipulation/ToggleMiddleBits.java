package com.bit.maipulation;

public class ToggleMiddleBits {

	public static void main(String[] args) {
		int n = 9;

		System.out.println(n + " after toggling middle bits: "
				+ toggleMiddleBits(n));
	}

	static int setMiddleBits(int num) {

		num = num | num >> 1;
		num = num | num >> 2;
		num = num | num >> 4;
		num = num | num >> 8;
		num = num | num >> 16;

		num = (num >> 1) ^ 1;

		return num;

	}

	static int toggleMiddleBits(int n) {

		if (n == 1) {
			return n;
		} else {
			return n ^ setMiddleBits(n);
		}

	}

}
