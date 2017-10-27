package data.structure.graphs;

import java.io.*;
import java.util.*;

class MrPresident2 {

	private static int find(int n) {

		return (union[n] == n) ? n : (union[n] = find(union[n]));
	}

	private static boolean connected(int v, int w) {

		return find(v) == find(w);
	}

	public static class Pair implements Comparable {
		int v, w;
		long wei;

		Pair(int v, int w, long wei) {
			this.v = v;
			this.w = w;
			this.wei = wei;
		}

		public int compareTo(Object o) {
			Pair pp = (Pair) o;
			if (pp.wei == wei)
				return 0;
			else if (wei > pp.wei)
				return 1;
			else
				return -1;
		}
	}

	public static int union[];

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = in.nextInt();
		int m = in.nextInt();
		long k = in.nextLong();
		ArrayList<Pair> list = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			long w = in.nextLong();
			list.add(new Pair(x, y, w));
		}
		union = new int[n + 1];
		for (int i = 0; i <= n; i++)
			union[i] = i;
		if (k < n - 1 || m < n - 1) {
			out.println("-1");
			out.flush();
			out.close();
			return;
		}
		Collections.sort(list);
		boolean flag = false;
		int x = 0;
		int ans = 0;
		for (int t = 0; t < m; t++) {
			Pair pp = list.get(t);
			int v = pp.v;
			int w = pp.w;
			long wt = pp.wei;
			if (connected(v, w))
				continue;

			x++;

			if (k - wt < (n - 1 - x)) {
				ans = Math.max(ans, n - x);
				flag = true;
			}
			k -= wt;
			union[find(v)] = find(w);
			if (x == n - 1)
				break;

		}
		if (x != n - 1) {
			out.println("-1");
		} else
			out.println(ans);
		out.flush();
		out.close();
	}

	public static class Reader {
		public BufferedReader reader;
		public StringTokenizer st;

		public Reader(InputStreamReader stream) {
			reader = new BufferedReader(stream);
			st = null;
		}

		public String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public String nextLine() throws IOException {
			return reader.readLine();
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

	}
}
