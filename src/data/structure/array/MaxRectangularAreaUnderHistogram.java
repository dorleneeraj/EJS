package data.structure.array;

public class MaxRectangularAreaUnderHistogram {

	public static void main(String[] args) {
		System.out
				.println(maxRectangularArea(new int[] { 6, 2, 5, 4, 5, 1, 6 }));
	}

	public static int maxRectangularArea(int[] histogramArray) {
		int len = histogramArray.length;
		int maxArea = 0;
		for (int i = 0; i < len; i++) {
			int smallest = histogramArray[i];
			int counter = 1;
			int area = histogramArray[i];
			int intermediateArea = histogramArray[i];
			for (int j = i - 1; j >= 0; j--) {
				counter++;
				if (histogramArray[j] < smallest) {
					smallest = histogramArray[j];
				}
				intermediateArea = smallest * counter;
				if (intermediateArea > area) {
					area = intermediateArea;
				}
			}
			if (area > maxArea) {
				maxArea = area;
			}
		}
		return maxArea;
	}
}
