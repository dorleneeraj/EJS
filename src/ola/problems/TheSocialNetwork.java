package ola.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TheSocialNetwork {
	public static void main(String[] args) {
		brute_force(new int[] { 3, 3, 3, 3, 3, 1, 3 });
		improvedSolution(new int[] { 3, 3, 3, 3, 3, 1, 3 });
	}

	public static void brute_force(int[] groupSizes) {
		Set<Integer> usedIndices = new HashSet<Integer>();

		// For int i = 0 ..n - 1, for value of groupSizes[i]
		// find all the indices in that group and add those indices
		// to usedIndices Set.

		for (int i = 0; i < groupSizes.length; i++) {
			if (!usedIndices.contains(i)) {
				usedIndices.add(i);
				int groupSizeOfIndex = groupSizes[i];
				int membersRemaining = groupSizeOfIndex - 1;
				System.out.print(i + " ");
				for (int j = i + 1; j < groupSizes.length
						&& membersRemaining > 0; j++) {
					if (groupSizes[j] == groupSizeOfIndex) {
						System.out.print(j + " ");
						membersRemaining--;
						usedIndices.add(j);
					}
				}

				System.out.println("");

			}
		}
	}

	public static void improvedSolution(int[] groupSizes) {
		Map<Integer, Integer> keyToIndexMap = new HashMap<Integer, Integer>();
		Integer[] chainedArray = new Integer[groupSizes.length];
		for (int i = 0; i < groupSizes.length; i++) {
			int index = i;
			int groupSizeOfIndex = groupSizes[index];
			Integer latestChild = keyToIndexMap.get(groupSizeOfIndex);
			if (null != latestChild) {
				chainedArray[latestChild] = i;
			}
			keyToIndexMap.put(groupSizes[i], i);
			chainedArray[i] = i;
		}

		// Every iteration of loop will print a row.
		for (int i = 0; i < chainedArray.length; i++) {
			if (null == chainedArray[i]) {
				continue;
			} else {
				int sizeAtIndex = groupSizes[i];
				System.out.print(i + " ");
				sizeAtIndex--;
				int chainedIndex = chainedArray[i];
				chainedArray[i] = null;
				while (sizeAtIndex > 0) {
					System.out.print(chainedIndex + " ");
					int tmp = chainedArray[chainedIndex];
					chainedArray[chainedIndex] = null;
					chainedIndex = tmp;
					sizeAtIndex--;
				}
				System.out.println("");
			}
		}

	}
}
