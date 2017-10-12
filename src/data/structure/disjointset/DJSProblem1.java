package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DJSProblem1 {

	public static void main(String[] args) throws Exception {
		// First get the input from the user.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] dimensions = line.split(" ");
		int noOfVertices = Integer.parseInt(dimensions[0]);
		int noOfEdges = Integer.parseInt(dimensions[1]);
		System.out.println("Vertices: " + noOfVertices);
		System.out.println("Edges: " + noOfEdges);

		int edges[][] = new int[noOfEdges][];

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			edges[i] = new int[] { Integer.parseInt(edge[0]),
					Integer.parseInt(edge[1]) };
		}
		// Input from the user is complete.

		DisjointSetBalanced dSB = new DisjointSetBalanced();
		dSB.initialize(noOfVertices);
		for (int i = 0; i < noOfEdges; i++) {
			dSB.union(edges[i][0], edges[i][1]);
			sortAndDisplay(dSB);
		}

	}

	private static void sortAndDisplay(DisjointSetBalanced dSB) {
		int[] Array = dSB.getArray();
		int[] Size = dSB.getSize();
		int ARRAY_LENGTH = dSB.getARRAY_LENGTH();
		int[] display = new int[ARRAY_LENGTH];
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			display[i] = -1;
		}

		int j = 0;
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (Array[i] == i + 1) {
				display[j] = Size[i];
				j++;
			}
		}
		sortAndDisplay(display, ARRAY_LENGTH);
	}

	private static void sortAndDisplay(int[] display, int ARRAY_LENGTH) {
		Arrays.sort(display);
		int i = 0;
		while (display[i] == -1) {
			i++;
		}

		while (i < ARRAY_LENGTH) {
			System.out.print(display[i]);
			System.out.print(" ");
			i++;
		}
		System.out.println("");
	}
}
