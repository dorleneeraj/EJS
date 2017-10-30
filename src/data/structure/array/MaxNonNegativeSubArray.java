package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxNonNegativeSubArray {
	public static void main(String[] args) {
		System.out.println(maxset(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 1967513926, 1540383426, -1303455736,
						-521595368 }))));
	}

	public static ArrayList<Integer> maxset(ArrayList<Integer> a) {
		int maxArrayIndex = -1;
		int maxArrayLength = -1;
		long maxArraySum = -1;
		int i = 0;
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		while (i < a.size()) {
			while (i < a.size() && a.get(i) < 0) {
				i++;
			}
			if (i < a.size()) {
				int index = i;
				long sum = 0;
				int len = 0;
				while (i < a.size() && a.get(i) >= 0) {
					sum = sum + a.get(i);
					len++;
					i++;
				}
				if (sum >= maxArraySum) {
					if (sum > maxArraySum) {
						maxArrayIndex = index;
						maxArrayLength = len;
						maxArraySum = sum;
					} else {
						if (len > maxArrayLength) {
							maxArrayIndex = index;
							maxArrayLength = len;
							maxArraySum = sum;

						} else if (len == maxArrayIndex) {
							if (index < maxArrayIndex) {
								maxArrayIndex = index;
								maxArrayLength = len;
								maxArraySum = sum;

							}
						}
					}
				}
			}
		}
		if (maxArrayIndex >= 0 && maxArrayIndex < a.size()) {
			for (int j = maxArrayIndex; j < maxArrayIndex + maxArrayLength; j++) {
				returnList.add(a.get(j));
			}
		}

		return returnList;
	}
}
