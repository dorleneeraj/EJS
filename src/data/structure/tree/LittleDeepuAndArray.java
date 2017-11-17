package data.structure.tree;

import java.util.Scanner;

public class LittleDeepuAndArray {

	public static void main(String[] args) {
		naiveSolution();
	}

	public static void naiveSolution() {
		Scanner scan = new Scanner(System.in);

		int arraySize = scan.nextInt();
		int[] array = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			array[i] = scan.nextInt();
		}
		int queries = scan.nextInt();
		for (int i = 0; i < queries; i++) {
			int val = scan.nextInt();
			for (int j = 0; j < arraySize; j++) {
				if (array[j] > val) {
					array[j] -= 1;
				}
			}
		}
		scan.close();
		for (int i = 0; i < arraySize; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
