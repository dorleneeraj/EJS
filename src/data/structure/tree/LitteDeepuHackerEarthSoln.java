package data.structure.tree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class LitteDeepuHackerEarthSoln {
	public static void main(String[] args) {
		// InputStream inputStream = System.in;
		// OutputStream outputStream = System.out;
		// FastReader in = new FastReader(inputStream);
		// PrintWriter out = new PrintWriter(outputStream);
		LittleDeepuAndArray solver = new LittleDeepuAndArray();
		solver.solve();
		// out.close();
	}

	// parameters for the solve method that i have removed.
	// 1, in, out
	// int testNumber, FastReader in, PrintWriter out

	static class LittleDeepuAndArray {
		public int[] segMin;
		public int[] segMax;
		public int[] lazy;
		public int[] a;
		int N;
		int size;

		public void solve() {
			// N = in.nextInt();
			N = 5;
			size = 4 * N;
			segMin = new int[size];
			segMax = new int[size];
			lazy = new int[size];
			// a = in.nextIntArray(N);
			a = new int[] { 7, 8, 3, 2, 10 };
			construct(0, N - 1, 0);
			// int m = in.nextInt();
			// int m = 4;

			// while (m-- > 0) {
			// int val = in.nextInt();
			// update(0, N - 1, 0, val);
			// }

			int[] updates = new int[] { 1, 3, 5, 7 };
			for (int val : updates) {
				update(0, N - 1, 0, val);
			}

			query(0, N - 1, 0);
			for (int i = 0; i < N; i++) {
				if (i > 0)
					System.out.print(" ");
				System.out.print(a[i]);
			}
			System.out.println();
		}

		public void construct(int start, int end, int position) {
			if (start == end) {
				segMax[position] = segMin[position] = a[start];
				return;
			}
			int m = (start + end) >>> 1;
			construct(start, m, 2 * position + 1);
			construct(m + 1, end, 2 * position + 2);
			segMax[position] = Math.max(segMax[2 * position + 1],
					segMax[2 * position + 2]);
			segMin[position] = Math.min(segMin[2 * position + 1],
					segMin[2 * position + 2]);
		}

		public void update(int start, int end, int position, int val) {
			if (lazy[position] != 0) {
				if (start != end) {
					lazy[2 * position + 1] += lazy[position];
					lazy[2 * position + 2] += lazy[position];
				}
				segMax[position] -= lazy[position];
				segMin[position] -= lazy[position];
				lazy[position] = 0;
			}
			if (segMin[position] > val) {
				lazy[position] += 1;
				return;
			} else if (segMax[position] <= val) {
				return;
			} else {
				int m = (start + end) >>> 1;
				update(start, m, 2 * position + 1, val);
				update(m + 1, end, 2 * position + 2, val);
				segMax[position] = Math.max(segMax[2 * position + 1],
						segMax[2 * position + 2]);
				segMin[position] = Math.min(segMin[2 * position + 1],
						segMin[2 * position + 2]);
			}
		}

		public void query(int start, int end, int c) {
			if (lazy[c] != 0) {
				if (start != end) {
					lazy[2 * c + 1] += lazy[c];
					lazy[2 * c + 2] += lazy[c];
				}
				segMax[c] -= lazy[c];
				segMin[c] -= lazy[c];
				lazy[c] = 0;
			}
			if (start == end) {
				a[start] = segMin[c];
				return;
			}
			int m = (start + end) >>> 1;
			query(start, m, 2 * c + 1);
			query(m + 1, end, 2 * c + 2);
		}

	}

	static class FastReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int pnumChars;
		private FastReader.SpaceCharFilter filter;

		public FastReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (pnumChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= pnumChars) {
				curChar = 0;
				try {
					pnumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (pnumChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int nextInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c == ',') {
					c = read();
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int[] array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = nextInt();
			}
			return array;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return isWhitespace(c);
		}

		public static boolean isWhitespace(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);

		}

	}

}
