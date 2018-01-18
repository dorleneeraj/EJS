package com.bit.maipulation;

public class PowerFunctions {

	public static void main(String[] args) {
		System.out.println(power3(2, -3));
	}

	static int power(int x, int y) {
		if (y == 0) {
			return 1;
		} else if (y % 2 == 0) {
			return power(x, y / 2) * power(x, y / 2);
		} else {
			return x * power(x, y / 2) * power(x, y / 2);
		}
	}

	static int power2(int x, int y) {

		int temp;
		if (y == 0) {
			return 1;
		}
		temp = power2(x, y / 2);
		if (y % 2 == 0) {
			return temp * temp;
		} else {
			return x * temp * temp;
		}

	}

	static float power3(float x, int y) {
		float temp = 0.0f;

		if (y == 0) {
			return 1;
		}
		temp = power3(x, y / 2);
		if (y % 2 == 0) {
			return temp * temp;
		} else {
			if (y > 0) {
				return x * temp * temp;
			} else {
				return temp * temp * (1 / x);
			}
		}
	}

	int iterativePower(int x, int y) {
		int res = 1;
		while (y > 0) {
			if ((y & 1) > 0) {
				res = res * x;
			}
			y = y >> 1;
			x = x * x;

		}

		return res;
	}

}
