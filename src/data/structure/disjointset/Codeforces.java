package data.structure.disjointset;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Codeforces {
	public static InputStream inputStream = System.in;
	public static FastReader in = new FastReader(inputStream);
	public static PrintWriter out = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static final int mod = 1000000007;

	public static void main(String[] args) throws java.lang.Exception {
		new Codeforces().run();
		out.close();
	}

	void run() throws java.lang.Exception {
		int n = ni();
		int m = ni();
		DJSet ds = new DJSet(n);
		while (m-- > 0) {
			ds.union(ni() - 1, ni() - 1);
		}
		ds.comb();
		// ds.dislplaySizes();
	}

	public static class DJSet {
		public int[] upper;
		public int[] size;

		public DJSet(int n) {
			upper = new int[n];
			Arrays.fill(upper, -1);
			size = new int[n];
			Arrays.fill(size, 1);
		}

		public int root(int x) {
			return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
		}

		public boolean equiv(int x, int y) {
			return root(x) == root(y);
		}

		public boolean union(int x, int y) {
			x = root(x);
			y = root(y);
			if (x != y) {
				if (upper[y] < upper[x]) {
					int d = x;
					x = y;
					y = d;
				}
				int t1 = size[x];
				int t2 = size[y];
				size[x] += t2;
				size[y] += t1;
				upper[x] += upper[y];
				upper[y] = x;
			}
			return x == y;
		}

		public int count() {
			int ct = 0;
			for (int u : upper)
				if (u < 0)
					ct++;
			return ct;
		}

		public void comb() {
			long ret = 1;
			for (int i = 0; i < upper.length; i++) {
				if (upper[i] < 0) {
					ret = ret * size[i] % mod;
				}
			}
			out.println(ret);
		}

		public void dislplaySizes() {
			for (int i = 0; i < size.length; i++) {
				out.print(size[root(i)] + " ");
			}
		}
	}

	public static int[] enumLowestPrimeFactors(int n) {
		int tot = 0;
		int[] lpf = new int[n + 1];
		int u = n + 32;
		double lu = Math.log(u);
		int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
		for (int i = 2; i <= n; i++)
			lpf[i] = i;
		for (int p = 2; p <= n; p++) {
			if (lpf[p] == p)
				primes[tot++] = p;
			int tmp;
			for (int i = 0; i < tot && primes[i] <= lpf[p]
					&& (tmp = primes[i] * p) <= n; i++) {
				lpf[tmp] = primes[i];
			}
		}
		return lpf;
	}

	private static int ni() {
		return in.nextInt();
	}

	private static long nl() {
		return in.nextLong();
	}

	private static String ns() {
		return in.nextString();
	}

	private static char nc() {
		return in.nextCharacter();
	}

	private static double nd() {
		return in.nextDouble();
	}

	private static char[] ns(int n) {
		char[] a = new char[n];
		for (int i = 0; i < n; i++)
			a[i] = nc();
		return a;
	}

	private static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private static long[] nal(int n) {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = nl();
		return a;
	}

}

class FastReader {
	private boolean finished = false;

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public FastReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1) {
			throw new InputMismatchException();
		}
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0) {
				return -1;
			}
		}
		return buf[curChar++];
	}

	public int peek() {
		if (numChars == -1) {
			return -1;
		}
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				return -1;
			}
			if (numChars <= 0) {
				return -1;
			}
		}
		return buf[curChar];
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

	public long nextLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9') {
				throw new InputMismatchException();
			}
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String nextString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
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

	private String readLine0() {
		StringBuilder buf = new StringBuilder();
		int c = read();
		while (c != '\n' && c != -1) {
			if (c != '\r') {
				buf.appendCodePoint(c);
			}
			c = read();
		}
		return buf.toString();
	}

	public String nextLine() {
		String s = readLine0();
		while (s.trim().length() == 0)
			s = readLine0();
		return s;
	}

	public String nextLine(boolean ignoreEmptyLines) {
		if (ignoreEmptyLines) {
			return nextLine();
		} else {
			return readLine0();
		}
	}

	public BigInteger nextBigInteger() {
		try {
			return new BigInteger(nextString());
		} catch (NumberFormatException e) {
			throw new InputMismatchException();
		}
	}

	public char nextCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}

	public double nextDouble() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		double res = 0;
		while (!isSpaceChar(c) && c != '.') {
			if (c == 'e' || c == 'E') {
				return res * Math.pow(10, nextInt());
			}
			if (c < '0' || c > '9') {
				throw new InputMismatchException();
			}
			res *= 10;
			res += c - '0';
			c = read();
		}
		if (c == '.') {
			c = read();
			double m = 1;
			while (!isSpaceChar(c)) {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, nextInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				m /= 10;
				res += (c - '0') * m;
				c = read();
			}
		}
		return res * sgn;
	}

	public boolean isExhausted() {
		int value;
		while (isSpaceChar(value = peek()) && value != -1)
			read();
		return value == -1;
	}

	public SpaceCharFilter getFilter() {
		return filter;
	}

	public void setFilter(SpaceCharFilter filter) {
		this.filter = filter;
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}
