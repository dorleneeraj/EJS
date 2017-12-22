package misc.problems;

public class PascalsTriangle {

	public static void main(String[] args) {
		PascalsTriangle pt = new PascalsTriangle();
		pt.printPascal(5);
		pt.printPascal(6);
	}

	void printPascal(int n) {
		for (int line = 0; line < n; line++) {
			for (int i = 0; i <= line; i++) {
				// print the binomial quotient.
				System.out.print(" " + binomialCoefficient(line, i));
			}
			System.out.println();
		}
	}

	int binomialCoefficient(int n, int k) {

		int res = 1;
		if (k > (n - k)) {
			k = n - k;
		}

		for (int i = 0; i < k; ++i) {
			res *= (n - i);
			res /= (i + 1);
		}
		return res;
	}

}
