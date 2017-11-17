package data.structure.array;

import java.util.Stack;

public class MaxRectangularAreaUnderHistogram {

	int[] histogram;
	int len;
	int[] minSegTree;

	public static void main(String[] args) {
		// System.out
		// .println(maxRectangularArea(new int[] { 6, 2, 5, 4, 5, 1, 6 }));
		// System.out.println(new
		// MaxRectangularAreaUnderHistogram().getMaxArea());
		System.out.println(getMaxAreaLinear(new int[] { 6, 2, 5, 4, 5, 1, 6 },
				7));
		System.out.println(getMaxAreaLinear(new int[] { 2, 3, 4, 5, 6, 8, 10 },
				7));
		System.out.println(getMaxAreaLinear(new int[] { 2, 3, 4, 5, 1 }, 5));
		System.out.println(getMaxAreaLinear(new int[] { 5, 5, 4, 5, 5 }, 5));

	}

	/*
	 * This is a simple solution to the problem.
	 */
	public static int maxRectangularArea(int[] histogramArray) {
		int len = histogramArray.length;
		int maxArea = 0;
		for (int i = 0; i < len; i++) {
			int smallest = histogramArray[i];
			int counter = 1;
			int area = histogramArray[i];
			int intermediateArea = histogramArray[i];
			for (int j = i - 1; j >= 0; j--) {
				counter++;
				if (histogramArray[j] < smallest) {
					smallest = histogramArray[j];
				}
				intermediateArea = smallest * counter;
				if (intermediateArea > area) {
					area = intermediateArea;
				}
			}
			if (area > maxArea) {
				maxArea = area;
			}
		}
		return maxArea;
	}

	/*
	 * This is a "divide and conquer" solution using segment trees. How will
	 * this work?
	 * 
	 * 1. Find a minimum bar in the given range.
	 * 
	 * 2. Max area = Max(Max area to left of minimum bar, Max area to right of
	 * minimum bar, Max area using the minimum bar)
	 * 
	 * 3. The areas in the left and right of the minimum bar can be found
	 * recursively.
	 */

	public int getMaxArea() {
		// initialize.
		// histogram = new int[] { 6, 2, 5, 4, 5, 1, 6 };
		histogram = new int[] { 1, 100, 12000, 500, 6, 900, 6900, 5490, 222, 2,
				3, 4, 5, 6700, 5403, 2100, 1902, 6723, 1, 2, 54, 11, 22, 33,
				44, 55, 66, 77, 88, 99, 90, 90, 90, 100, 16500, 1200, 1300, 1,
				1, 1, 10, 0, 0, 0, 0, 0, 5, 900000, 0, 2, 10000000, 1, 2, 3,
				670000, 500000, 60 };
		len = histogram.length;

		int segTreeLen = getSetTreeLen(len);
		minSegTree = new int[segTreeLen];

		buildMinSegmentTree(0, len - 1, 0);

		int counter = 1;
		for (int i = 0; i < minSegTree.length;) {
			for (int j = 0; j < counter; j++) {
				System.out.print(minSegTree[i] + " ");
				i++;
			}
			System.out.println("");
			counter = counter * 2;
		}

		// Now the min segment tree has been built in O(N) time.
		// Now recursively find the max area.
		int maxArea = getMaxAreaUTIL(0, len - 1, len, 0, len - 1);
		return maxArea;
	}

	private int getMaxAreaUTIL(int start, int end, int len, int l, int r) {

		// get the index of the element which the minimum over the range of l &
		// r
		int minIndex = rangeQuery(start, end, len);
		// once you get the minIndex, find the max Area in the left subtree and
		// the right subtree of that index
		int leftTreeLen = ((minIndex - 1) - start) + 1;
		int leftMaxArea = 0;
		if (leftTreeLen > 0) {

			leftMaxArea = getMaxAreaUTIL(start, minIndex - 1, leftTreeLen, l, r);
		}
		int rightTreeLen = (end - (minIndex + 1)) + 1;
		int rightMaxArea = 0;
		if (rightTreeLen > 0) {
			rightMaxArea = getMaxAreaUTIL(minIndex + 1, end, rightTreeLen, l, r);
		}
		int maxAreaMidInclusive = histogram[minIndex] * len;
		int maxArea = getMax(leftMaxArea, rightMaxArea, maxAreaMidInclusive);
		return maxArea;

	}

	private int query(int start, int end, int l, int r) {
		if (start > r || end < l || end < start) {
			return -1; // invalid query. Out of range
		} else if (start == end) {
			return 0;
		} else if (l >= start && r <= end) {
			return minSegTree[start];
		} else {
			int mid = (l + r) / 2;
			int lSmallestIndex = query(start, end, l, mid);
			int rSmallestIndex = query(start, end, mid + 1, r);
			if (lSmallestIndex >= 0 && rSmallestIndex >= 0) {
				if (histogram[lSmallestIndex] <= histogram[rSmallestIndex]) {
					return lSmallestIndex;
				} else {
					return rSmallestIndex;
				}
			} else {
				if (lSmallestIndex == -1) {
					return rSmallestIndex;
				} else {
					return lSmallestIndex;
				}
			}
		}
	}

	private int getMax(int leftMaxArea, int rightMaxArea,
			int maxAreaMidInclusive) {
		int max = 0;
		if (leftMaxArea >= rightMaxArea) {
			max = leftMaxArea;
		} else {
			max = rightMaxArea;
		}
		if (maxAreaMidInclusive > max) {
			max = maxAreaMidInclusive;
		}
		return max;
	}

	private int getSetTreeLen(int len) {
		int height = (int) Math.ceil(Math.log(len) / Math.log(2));
		int segmentTreeLength = (int) (Math.pow(2, height + 1) - 1);
		return segmentTreeLength;
	}

	private void buildMinSegmentTree(int start, int end, int pos) {

		if (start == end) {
			minSegTree[pos] = start;
			return;
		}
		int mid = (start + end) / 2;
		// build the left segment tree
		buildMinSegmentTree(start, mid, pos * 2 + 1);
		// build the right segment tree
		buildMinSegmentTree(mid + 1, end, pos * 2 + 2);
		int minIndex = -1;
		if (histogram[minSegTree[2 * pos + 1]] <= histogram[minSegTree[2 * pos + 2]]) {
			minIndex = minSegTree[2 * pos + 1];
		} else {
			minIndex = minSegTree[2 * pos + 2];
		}
		minSegTree[pos] = minIndex;
	}

	public int rangeQuery(int qlow, int qhigh, int len) {
		return rangeQuery(0, len - 1, qlow, qhigh, 0);
	}

	private int rangeQuery(int low, int high, int qlow, int qhigh, int pos) {
		if (qlow <= low && qhigh >= high) {
			return minSegTree[pos];
		}
		if (qlow > high || qhigh < low) {
			return -1;
		}
		int mid = (low + high) / 2;
		int leftMin = rangeQuery(low, mid, qlow, qhigh, 2 * pos + 1);
		int rightMin = rangeQuery(mid + 1, high, qlow, qhigh, 2 * pos + 2);
		if (leftMin >= 0 && rightMin >= 0) {
			if (histogram[leftMin] <= histogram[rightMin]) {
				return leftMin;
			} else {
				return rightMin;
			}
		} else {
			if (leftMin < 0) {
				return rightMin;
			} else {
				return leftMin;
			}
		}

	}

	static int getMaxAreaLinear(int hist[], int n) {
		// Create an empty stack. The stack holds indexes of hist[] array
		// The bars stored in stack are always in increasing order of their
		// heights.
		Stack<Integer> s = new Stack<>();

		int max_area = 0; // Initialize max area
		int tp; // To store top of stack
		int area_with_top; // To store area with top bar as the smallest bar

		// Run through all bars of given histogram
		int i = 0;
		while (i < n) {
			// If this bar is higher than the bar on top stack, push it to stack
			if (s.empty() || hist[s.peek()] <= hist[i])
				s.push(i++);

			// If this bar is lower than top of stack, then calculate area of
			// rectangle
			// with stack top as the smallest (or minimum height) bar. 'i' is
			// 'right index' for the top and element before top in stack is
			// 'left index'
			else {
				tp = s.peek(); // store the top index
				s.pop(); // pop the top

				// Calculate the area with hist[tp] stack as smallest bar
				area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

				// update max area, if needed
				if (max_area < area_with_top)
					max_area = area_with_top;
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (s.empty() == false) {
			tp = s.peek();
			s.pop();
			area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

			if (max_area < area_with_top)
				max_area = area_with_top;
		}

		return max_area;

	}

}
