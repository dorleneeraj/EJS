package data.structure.disjointset;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		ScanReader in = new ScanReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Disjoint_Set solver = new Disjoint_Set();
		solver.solve(1, in, out);
		out.close();
	}

	static class Disjoint_Set {
		int total_node;
		int edges;
		int[] disjointArray;
		int[] size;

		public void solve(int testNumber, ScanReader in, PrintWriter out) {
			total_node = in.scanInt();
			edges = in.scanInt();
			inititalise();
			for (int i = 0; i < edges; i++) {
				union(in.scanInt(), in.scanInt());
				query(out);
			}

		}

		private void query(PrintWriter out) {
			int count[] = new int[1005];
			for (int i = 1; i <= total_node; i++) {
				if (disjointArray[i] == i)
					count[(size[i])]++;
			}
			for (int i = 1; i <= 1000; i++) {
				for (int j = 0; j < count[i]; j++) {
					out.print(i + " ");
				}
			}
			out.println();
		}

		private void inititalise() {
			disjointArray = new int[total_node + 1];
			size = new int[total_node + 1];
			Arrays.fill(size, 1);
			for (int i = 0; i <= total_node; i++)
				disjointArray[i] = i;
		}

		private int findRoot(int node) {
			while (disjointArray[node] != node) {
				disjointArray[node] = disjointArray[disjointArray[node]];
				node = disjointArray[node];
			}
			return node;
		}

		private void union(int a, int b) {
			int a_root = findRoot(a);
			int b_root = findRoot(b);
			if (a_root != b_root) {
				if (size[a_root] < size[b_root]) {
					disjointArray[a_root] = disjointArray[b_root];
					size[b_root] += size[a_root];
				} else {
					disjointArray[b_root] = disjointArray[a_root];
					size[a_root] += size[b_root];
				}
			}
		}

	}

	static class ScanReader {
		private byte[] buf = new byte[4 * 1024];
		private int index;
		private BufferedInputStream in;
		private int total;

		public ScanReader(InputStream inputStream) {
			in = new BufferedInputStream(inputStream);
		}

		private int scan() {
			if (index >= total) {
				index = 0;
				try {
					total = in.read(buf);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (total <= 0)
					return -1;
			}
			return buf[index++];
		}

		public int scanInt() {
			int integer = 0;
			int n = scan();
			while (isWhiteSpace(n))
				n = scan();
			int neg = 1;
			if (n == '-') {
				neg = -1;
				n = scan();
			}
			while (!isWhiteSpace(n)) {
				if (n >= '0' && n <= '9') {
					integer *= 10;
					integer += n - '0';
					n = scan();
				}
			}
			return neg * integer;
		}

		private boolean isWhiteSpace(int n) {
			if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
				return true;
			else
				return false;
		}

	}
}
