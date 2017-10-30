package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;

public class MinStepsInInfiniteGrid {

	public static void main(String[] args) {

		ArrayList<Integer> X = new ArrayList<Integer>(
				Arrays.asList(new Integer[] { -1, -4, 13, 0, -7, 14, -6, 4,
						-12, -2, 2, 12, 8, -13, 0, -9, 8, -11, -5, 11, -15, 9,
						5, 0, 1, -7, 0, -12 }));
		// X.add(4);
		// X.add(8);
		// X.add(-7);
		// X.add(-5);
		// X.add(-13);
		// X.add(9);
		// X.add(-7);
		// X.add(8);

		ArrayList<Integer> Y = new ArrayList<Integer>(
				Arrays.asList(new Integer[] { 2, 11, -7, -9, 2, -14, -7, 9, -6,
						12, 8, -4, -5, 7, -2, 2, 6, -7, -6, -12, 12, -11, 3,
						-15, -11, 5, 10, 12 }));
		// Y.add(4);
		// Y.add(-15);
		// Y.add(-10);
		// Y.add(-3);
		// Y.add(-13);
		// Y.add(12);
		// Y.add(8);
		// Y.add(-8);

		System.out.println(coverPoints(X, Y));
		System.out.println(coverPoints2(X, Y));

	}

	public static int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
		int totalNumSteps = 0;
		for (int i = 0; i < X.size() - 1; i++) {
			int numSteps = 0;
			int x1 = X.get(i);
			int y1 = Y.get(i);
			int x2 = X.get(i + 1);
			int y2 = Y.get(i + 1);
			while (x1 != x2 && y1 != y2) {
				if (x2 < x1) {
					x1 = x1 - 1;
				} else {
					x1 = x1 + 1;
				}

				if (y2 < y1) {
					y1 = y1 - 1;
				} else {
					y1 = y1 + 1;
				}
				numSteps++;
			}

			if (x1 != x2) {
				if ((x2 < 0 && x1 < 0) || (x1 >= 0 && x2 >= 0)) {
					numSteps = numSteps + Math.abs(Math.abs(x2) - Math.abs(x1));
				} else {
					numSteps = numSteps + Math.abs(x2) + Math.abs(x1);
				}
			}

			if (y1 != y2) {
				if ((y2 < 0 && y1 < 0) || (y1 >= 0 && y2 >= 0)) {
					numSteps = numSteps + Math.abs(Math.abs(y2) - Math.abs(y1));
				} else {
					numSteps = numSteps + Math.abs(y2) + Math.abs(y1);
				}
			}
			totalNumSteps = totalNumSteps + numSteps;
		}
		return totalNumSteps;
	}

	public static int coverPoints2(ArrayList<Integer> X, ArrayList<Integer> Y) {
		int numSteps = 0;
		for (int i = 1; i < X.size(); i++) {
			numSteps += Math.max(Math.abs(X.get(i) - X.get(i - 1)),
					Math.abs(Y.get(i) - Y.get(i - 1)));
		}
		return numSteps;
	}
}

// int count = 0;
// while (count < X.size()) {
// int x1 = X.get(0);
// int y1 = Y.get(0);
//
//
// count++;
// }