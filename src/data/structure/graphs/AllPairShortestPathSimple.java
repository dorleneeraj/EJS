package data.structure.graphs;

import java.util.Scanner;

public class AllPairShortestPathSimple {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int vertices = s.nextInt();
		int[][] A = new int[vertices][vertices];
		int rows = vertices;
		int columns = vertices;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				A[i][j] = s.nextInt();
			}
		}

		s.close();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(A[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}

		// 1 longest path sathi... Longest possible path(simple) is no.
		// vertices - 1
		for (int v = 1; v < vertices; v++) {
			// pratyeek node sathi
			for (int i = 0; i < vertices; i++) {
				// pratyeek dusrya node sathi
				for (int j = 0; j < vertices; j++) {
					// check the route using pratyeek madhlya node mule...
					for (int k = 0; k < vertices; k++) {
						if (A[i][k] != Integer.MAX_VALUE
								&& A[k][j] != Integer.MAX_VALUE)
							if (A[i][j] > A[i][k] + A[k][j]) { // Relax the
																// edge...
								A[i][j] = A[i][k] + A[k][j];
							}
					}
				}
			}
		}

		System.out.println("");
		System.out.println("");

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(A[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}

	}
}
