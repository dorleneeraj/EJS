package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WaveArray {

	public static void main(String[] args) {
		System.out.println(new WaveArray().wave(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 79, 29, 64, 79, 1, 25, 89, 58, 33, 0,
						89, 70, 17, 46, 38, 43, 38, 36, 21, 19, 96, 47, 88, 59,
						87, 35, 35, 12, 84, 89, 84, 34, 67, 19, 15, 45, 76, 61,
						25, 19, 31, 3, 98, 98 }))));

		System.out.println(new WaveArray().wave(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 6, 17, 15, 13 }))));
	}

	public ArrayList<Integer> wave(ArrayList<Integer> a) {
		Collections.sort(a);
		int i = 0;
		while (i < a.size() - 1) {
			int swapIndex = i + 1;
			while ((swapIndex < a.size()) && a.get(i) == a.get(swapIndex)) {
				swapIndex++;
			}
			swap(i, i + 1, a);
			i = i + 2;
		}

		return a;
	}

	private void swap(int i, int j, ArrayList<Integer> a) {
		int temp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
	}
}
