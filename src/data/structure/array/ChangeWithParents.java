package data.structure.array;

/**
 * You are given coins with certain denominations. Your goal is to make a change
 * for a given amount in minimum number of coins. One of the denominations is 1,
 * so a solution is always possible.
 * 
 * Clearly, a BFS is possible. You start with a 0 vertex which has edges to
 * vertices representing the denominations that fan out to additional vertices
 * and so on. BFS gives us shortest path from 0 to that amount. But, is a dp
 * solution better? It appears so, because we are solving lots and lots of
 * common subproblems. If we want the minimum number of coins, C(i) that reach
 * an amount i, then Ci must be 1 + min (C(i-d1), C(i-d2), ..., C(i-dn)), where
 * d1..dn are the denominations available. Thus, if we can store the solutions
 * of smaller subproblems, we can build the solution in O(d.i)!
 */

class ChangeWithParents {
	public static void main(String[] args) {
		// int a = Integer.valueOf(args[0]);
		// int[] d = s2i(args, 1);
		int x = solve(5, new int[] { 1, 2 });
		if (x == -1)
			System.out.println("Can't get the amount in given denominations");
	}

	static int solve(int a, int[] d) {
		// assume d is sorted
		int[] minc = new int[a + 1];
		int[] parents = new int[a + 1];

		for (int i = 0; i < minc.length; i++) {
			minc[i] = Integer.MAX_VALUE;
		}
		minc[0] = 0;

		for (int i = 0; i < d.length; i++) {
			if (d[i] <= a) {
				minc[d[i]] = 1;
			}
		}

		for (int i = 0; i < parents.length; i++) {
			parents[i] = -1;
		}

		for (int i = 1; i < minc.length; i++) {
			for (int j = 0; j < d.length; j++) {
				int pi = i - d[j];
				if (pi < 0) {
					break;
				}
				// now pi is indexed in minc
				if (minc[i] > minc[pi] + 1) {
					minc[i] = minc[pi] + 1;
					parents[i] = pi;
				}
			}
		}
		if (minc[a] == Integer.MAX_VALUE)
			return -1;
		else {
			System.out.println("min number of coins to get: " + a + " is: "
					+ minc[a]);
			int i = a;
			while (true) {
				if (parents[i] == -1) {
					System.out.println("coin denomination: " + i);
					return 1;
				}
				System.out.println("coin denomination: " + (i - parents[i]));
				i = parents[i];
			}
		}
	}

	static int[] s2i(String[] s, int beg) {
		int[] a = new int[s.length - beg];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.valueOf(s[i + beg]);
		return a;
	}
}

// int min = minc[i]; initialize the min num of coins to 'get a
// change' for i