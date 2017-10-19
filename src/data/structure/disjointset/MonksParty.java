package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MonksParty {

	private static int[] SIZE_COPY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int noOfVertices = Integer.parseInt(line);
		int noOfEdges = Integer.parseInt(br.readLine());
		DisjointSetCompressed dSC = new DisjointSetCompressed();
		dSC.initialize(noOfVertices);

		for (int i = 0; i < noOfEdges; i++) {
			String input = br.readLine();
			String[] edge = input.split(" ");
			int friend1 = Integer.parseInt(edge[0]);
			int friend2 = Integer.parseInt(edge[1]);
			dSC.union(friend1, friend2);

		}
		SIZE_COPY = dSC.getSize();
		int noOfFoes = Integer.parseInt(br.readLine());
		for (int i = 0; i < noOfFoes; i++) {
			String input = br.readLine();
			String[] Foes = input.split(" ");
			int foe1 = Integer.parseInt(Foes[0]);
			int foe2 = Integer.parseInt(Foes[1]);
			updateWithFoes(dSC, foe1, foe2);
		}

		System.out.println(printSpecialGroup(dSC));
	}

	private static int printSpecialGroup(DisjointSetCompressed dSC) {
		int[] Array = dSC.getArray();
		int max = 0;
		for (int i = 1; i < Array.length; i++) {
			if (Array[i] == i) {
				if (SIZE_COPY[i] > 0) {
					if (max < SIZE_COPY[i]) {
						max = SIZE_COPY[i];
					}
				}
			}
		}
		return max;
	}

	private static void updateWithFoes(DisjointSetCompressed dSC, int foe1,
			int foe2) {
		int root_1 = dSC.root(foe1);
		int root_2 = dSC.root(foe2);

		if (root_1 == root_2) {
			SIZE_COPY[root_1] = -1;
		}
	}
}
