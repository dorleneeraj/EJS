package scjp.chapter3.literals;

public class Array {
	public static void main(String[] args) {
		int[] intArray = { 1, 2, 3 };
		int[] intArray2 = new int[5] { 1, 2, 3 }; // - Cannot define dimension
													// expressions when an array
													// initializer is
													// provided
		int[] intArray3;
		intArray3 = new int[] { 5, 6, 7 };

	}
}
