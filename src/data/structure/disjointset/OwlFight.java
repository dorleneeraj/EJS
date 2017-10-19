package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OwlFight {

	private static int[] MAX_STRENGTH;
	private static int MAX_STRENGTH_LEN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] dimensions = line.split(" ");
		int noOfVertices = Integer.parseInt(dimensions[0]);
		int noOfEdges = Integer.parseInt(dimensions[1]);
		DisjointSetCompressed dSC = new DisjointSetCompressed();
		dSC.initialize(noOfVertices);
		MAX_STRENGTH_LEN = noOfVertices + 1;
		initializeMAXStrenghtArray();

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			dSC.union(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
			updateMaxStrengthArray(dSC, Integer.parseInt(edge[0]),
					Integer.parseInt(edge[1]));
		}
		int noOfContests = Integer.parseInt(br.readLine());
		System.out.println("");
		for (int i = 0; i < noOfContests; i++) {
			String input = br.readLine();
			String[] contest = input.split(" ");
			printWinner(dSC, Integer.parseInt(contest[0]),
					Integer.parseInt(contest[1]));
		}
	}

	private static void printWinner(DisjointSetCompressed dSC, int c1, int c2) {

		int root_1 = dSC.root(c1);
		int root_2 = dSC.root(c2);
		if (root_1 == root_2) {
			System.out.println("TIE");
		} else {
			int max_1 = MAX_STRENGTH[root_1];
			int max_2 = MAX_STRENGTH[root_2];
			if (max_1 > max_2) {
				System.out.println(c1);
			} else {
				System.out.println(c2);
			}
		}
	}

	private static void updateMaxStrengthArray(DisjointSetCompressed dSC,
			int v1, int v2) {
		int root = dSC.root(v1); // get the root index. Since, both the vertices
									// have same root, get root of v1 for
									// further calculations.
		int maxValue = 0;
		if (MAX_STRENGTH[v1] > MAX_STRENGTH[v2]) {
			maxValue = MAX_STRENGTH[v1];
		} else {
			maxValue = MAX_STRENGTH[v2];
		}

		if (MAX_STRENGTH[root] < maxValue) {
			MAX_STRENGTH[root] = maxValue;
		}

	}

	private static void initializeMAXStrenghtArray() {
		MAX_STRENGTH = new int[MAX_STRENGTH_LEN];
		for (int i = 0; i < MAX_STRENGTH_LEN; i++) {
			MAX_STRENGTH[i] = i;
		}
	}
}
