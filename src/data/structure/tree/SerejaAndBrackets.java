package data.structure.tree;

/**
 * 
 * @author neeraj
 *
 *
 *         Sereja has a bracket sequence s1, s2, ..., sn, or, in other words, a
 *         string s of length n, consisting of characters "(" and ")".
 * 
 *         Sereja needs to answer m queries, each of them is described by two
 *         integers li, ri (1 ≤ li ≤ ri ≤ n). The answer to the i-th query is
 *         the length of the maximum correct bracket subsequence of sequence
 *         sli, sli + 1, ..., sri. Help Sereja answer all queries.
 * 
 *         You can find the definitions for a subsequence and a correct bracket
 *         sequence in the notes. Input
 * 
 *         The first line contains a sequence of characters s1, s2, ..., sn
 *         (1 ≤ n ≤ 106) without any spaces. Each character is either a "(" or a
 *         ")". The second line contains integer m (1 ≤ m ≤ 105) — the number of
 *         queries. Each of the next m lines contains a pair of integers. The
 *         i-th line contains integers li, ri (1 ≤ li ≤ ri ≤ n) — the
 *         description of the i-th query.
 * 
 * 
 *         Output:
 * 
 *         Print the answer to each question on a single line. Print the answers
 *         in the order they go in the input.
 *
 *
 */
public class SerejaAndBrackets {

	String sequence = "())(())(())(";
	// String sequence = "()";
	// String sequence = ")()(";
	// int sequenceLength = 4;
	int[] t; // storing the value at that interval.
	int[] o; // storing the value of '(': opening brackets
	int[] c; // storing the value of ')': opening brackets

	public static void main(String[] args) {
		SerejaAndBrackets obj = new SerejaAndBrackets();
		obj.init();
		obj.buildTree(0, 0, 11);
		Node node = obj.segment(0, 0, 0, 0, 11);
		System.out.println(node);
		System.out.println(node.completeBrackets * 2);

	}

	public void init() {
		int sequenceLength = this.sequence.length();
		int segmentTreeLen = getSegmentTreeLength(sequenceLength);
		t = new int[segmentTreeLen];
		o = new int[segmentTreeLen];
		c = new int[segmentTreeLen];
	}

	public int getSegmentTreeLength(int sequenceLen) {
		int height = (int) Math.ceil(Math.log(sequenceLen) / Math.log(2));
		int segmentTreeLength = (int) (Math.pow(2, height + 1) - 1);
		return segmentTreeLength;
	}

	public void buildTree(int id, int l, int r) {

		// base case
		if (l == r) {
			if (sequence.charAt(l) == '(') {
				o[id] = 1;
				c[id] = 0;
			} else {
				c[id] = 1;
				o[id] = 0;
			}
		} else {

			int mid = (l + r) / 2;
			int leftNodeIndex = (2 * id) + 1;
			int rightNodeIndex = (2 * id) + 2;
			buildTree(leftNodeIndex, l, mid);
			buildTree(rightNodeIndex, mid + 1, r);
			// calculate the value for the current id, once its child ids have
			// been
			// computed

			int tmp = Math.min(o[leftNodeIndex], c[rightNodeIndex]);
			t[id] = t[2 * id + 1] + t[2 * id + 2] + tmp;
			o[id] = o[2 * id + 1] + o[2 * id + 2] - tmp;
			c[id] = c[2 * id + 1] + c[2 * id + 2] - tmp;
		}
	}

	public Node segment(int x, int y, int id, int l, int r) {
		if (l >= y || x >= r) {
			return new Node(0, 0, 0);
		} else if (x <= l && r <= y) {
			return new Node(t[id], o[id], c[id]);
		} else {
			int mid = (l + r) / 2;
			Node a = segment(x, y, id * 2 + 1, l, mid);
			Node b = segment(x, y, id * 2 + 2, mid, r);
			int T = 0, temp = 0, O = 0, C = 0;
			temp = Math.min(a.pair.lUnsatisfied, b.pair.rUnsatisfied);
			T = a.completeBrackets + b.completeBrackets + temp;
			O = a.pair.lUnsatisfied + b.pair.lUnsatisfied - temp;
			C = a.pair.rUnsatisfied + b.pair.rUnsatisfied - temp;
			return new Node(T, O, C);
		}
	}

	class Pair {
		private int lUnsatisfied = 0;
		private int rUnsatisfied = 0;

		public Pair(int i1, int i2) {
			this.lUnsatisfied = i1;
			this.rUnsatisfied = i2;
		}

		public int getlUnsatisfied() {
			return lUnsatisfied;
		}

		public void setlUnsatisfied(int lUnsatisfied) {
			this.lUnsatisfied = lUnsatisfied;
		}

		public int getrUnsatisfied() {
			return rUnsatisfied;
		}

		public void setrUnsatisfied(int rUnsatisfied) {
			this.rUnsatisfied = rUnsatisfied;
		}

		@Override
		public String toString() {
			return "Pair [lUnsatisfied=" + lUnsatisfied + ", rUnsatisfied="
					+ rUnsatisfied + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + lUnsatisfied;
			result = prime * result + rUnsatisfied;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (lUnsatisfied != other.lUnsatisfied)
				return false;
			if (rUnsatisfied != other.rUnsatisfied)
				return false;
			return true;
		}

		private SerejaAndBrackets getOuterType() {
			return SerejaAndBrackets.this;
		}

	}

	class Node {
		private int completeBrackets = 0;
		private Pair pair;

		public Node(int i1, Pair p) {
			this.completeBrackets = i1;
			this.pair = p;
		}

		public Node(int i1, int i2, int i3) {
			Pair p = new Pair(i2, i3);
			this.pair = p;
			this.completeBrackets = i1;
		}

		public int getCompleteBrackets() {
			return completeBrackets;
		}

		public void setCompleteBrackets(int completeBrackets) {
			this.completeBrackets = completeBrackets;
		}

		public Pair getPair() {
			return pair;
		}

		public void setPair(Pair pair) {
			this.pair = pair;
		}

		@Override
		public String toString() {
			return "Node [completeBrackets=" + completeBrackets + ", pair="
					+ pair + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + completeBrackets;
			result = prime * result + ((pair == null) ? 0 : pair.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (completeBrackets != other.completeBrackets)
				return false;
			if (pair == null) {
				if (other.pair != null)
					return false;
			} else if (!pair.equals(other.pair))
				return false;
			return true;
		}

		private SerejaAndBrackets getOuterType() {
			return SerejaAndBrackets.this;
		}
	}
}
