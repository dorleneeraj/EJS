package interviews.grab;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceFromCapital {
	public static void main(String[] args) {
		DistanceFromCapital dfc = new DistanceFromCapital();
		dfc.solution(new int[] { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 });
	}

	public int[] solution(int[] T) {
		int[] parentDistanceArray = new int[T.length];
		int[] distanceArray = new int[T.length - 1];
		Queue<Integer> q = new LinkedList<Integer>();
		int capital = -1;
		for (int i = 0; i < T.length; i++) {
			if (T[i] == i) {
				capital = i;
				break;
			}
		}

		for (int i = 0; i < T.length; i++) {
			if (T[i] != i && T[i] == capital) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int element = q.remove();
			int parent = T[element];
			int distance = parentDistanceArray[parent] + 1;
			parentDistanceArray[element] = distance;
			for (int i = 0; i < T.length; i++) {
				if (T[i] == element) {
					q.add(i);
				}
			}
		}

		for (int i = 0; i < T.length; i++) {
			if (parentDistanceArray[i] != 0) {
				distanceArray[parentDistanceArray[i] - 1]++;
			}
		}

		for (int i = 0; i < distanceArray.length; i++) {

			System.out.print(distanceArray[i] + " ");
		}

		return distanceArray;
	}

}

/*
 * 
 * 
 * int[] distancesArray = new int[T.length]; int[] parentDistanceArray = new
 * int[T.length]; for (int i = 0; i < T.length; i++) { if (T[i] != i) {
 * parentDistanceArray[i] = parentDistanceArray[T[i]] + 1; } else {
 * parentDistanceArray[i] = 0; } }
 * 
 * System.out.println("Pass1"); for (int i = 0; i < parentDistanceArray.length;
 * i++) { System.out.print(parentDistanceArray[i] + " "); }
 * 
 * System.out.println("");
 * 
 * for (int i = 0; i < T.length; i++) { if (T[i] != i) { parentDistanceArray[i]
 * = parentDistanceArray[T[i]] + 1; } else { parentDistanceArray[i] = 0; } }
 * 
 * System.out.println("Pass2"); for (int i = 0; i < parentDistanceArray.length;
 * i++) { System.out.print(parentDistanceArray[i] + " "); }
 * 
 * System.out.println("");
 * 
 * for (int i = 0; i < parentDistanceArray.length; i++) {
 * distancesArray[parentDistanceArray[i]]++; }
 * 
 * System.out.println("distance array"); for (int i = 0; i <
 * parentDistanceArray.length; i++) { System.out.print(distancesArray[i] + " ");
 * }
 * 
 * System.out.println();
 * 
 * return distancesArray;
 */
