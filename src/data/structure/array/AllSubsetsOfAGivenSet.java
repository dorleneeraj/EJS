package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSubsetsOfAGivenSet {

	public static void main(String[] args) {
//		ArrayList<ArrayList<Integer>> subsets = subsets2(new ArrayList<Integer>(
//				Arrays.asList(new Integer[] { 1, 2, 2 })));
//		for (List<Integer> l : subsets) {
//			System.out.println(l);
//		}
//		System.out.println(subsets.size());
		printSubsets(new char[] { 'a', 'b', 'c' });
	}

	static Map<List<Integer>, ArrayList<List<Integer>>> memo = new HashMap<List<Integer>, ArrayList<List<Integer>>>();

	public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
		Collections.sort(a);
		ArrayList<List<Integer>> subS = recursiveWays(a);
		// subS.add(0, new ArrayList<Integer>());
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		for (List<Integer> l : subS) {
			returnList.add((ArrayList<Integer>) l);
		}
		// Collections.sort(returnList, new
		// ListComparator<ArrayList<Integer>>());
		returnList.add(0, new ArrayList<Integer>());
		return returnList;
	}

	public static ArrayList<List<Integer>> recursiveWays(List<Integer> set) {
		ArrayList<List<Integer>> subsets = new ArrayList<List<Integer>>();
		if (memo.containsKey(set)) {
			return memo.get(set);
		}
		if (set.isEmpty()) {

		} else if (set.size() == 1) {
			subsets.add(set);
		} else {
			ArrayList<Integer> s = new ArrayList<Integer>();
			s.add(set.get(0));
			ArrayList<List<Integer>> set1 = recursiveWays(s);
			ArrayList<Integer> newSet = new ArrayList<Integer>();
			for (int i = 1; i < set.size(); i++) {
				newSet.add(set.get(i));
			}
			ArrayList<List<Integer>> set2 = recursiveWays(newSet);

			ArrayList<ArrayList<Integer>> set3 = new ArrayList<ArrayList<Integer>>();
			for (List<Integer> list : set2) {
				ArrayList<Integer> l = new ArrayList<Integer>();
				for (Integer j : list) {
					l.add(j);
				}
				set3.add(l);
			}
			subsets = mergeSets(set1, set2);
			subsets.addAll(set3);
		}
		memo.put(set, subsets);
		return subsets;
	}

	private static ArrayList<List<Integer>> mergeSets(
			ArrayList<List<Integer>> set1, ArrayList<List<Integer>> set2) {
		for (List<Integer> list : set2) {
			list.add(0, set1.get(0).get(0));
			Collections.sort(list);
		}
		set2.add(0, set1.get(0));

		return set2;
	}

	static class ListComparator<T> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			ArrayList<Integer> list1 = (ArrayList<Integer>) o1;
			ArrayList<Integer> list2 = (ArrayList<Integer>) o2;
			int counter = 0;
			boolean diffFound = false;
			while (counter < list1.size() && counter < list2.size()) {
				if (list1.get(counter) == list2.get(counter)) {
					counter++;
				} else {
					diffFound = true;
					break;
				}
			}
			if (!diffFound) {
				if (counter < list2.size()) {
					return -1;
				} else if (counter < list1.size()) {
					return 1;
				} else {
					return 0;
				}
			} else {
				if (list1.get(counter) < list2.get(counter)) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}

	public static ArrayList<ArrayList<Integer>> subsets2(ArrayList<Integer> a) {
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		output.add(new ArrayList<Integer>());
		if (a.size() == 0)
			return output;
		Collections.sort(a);
		generate(a, output, new ArrayList<Integer>(), 0);
		return output;
	}

	public static void generate(ArrayList<Integer> a,
			ArrayList<ArrayList<Integer>> output, ArrayList<Integer> temp,
			int index) {
		for (int i = index; i < a.size(); i++) {
			temp.add(a.get(i));
			output.add(new ArrayList<Integer>(temp));
			generate(a, output, temp, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	static void printSubsets(char set[]) {
		int n = set.length;

		// Run a loop for printing all 2^n
		// subsets one by obe
		for (int i = 0; i < (1 << n); i++) {
			System.out.print("{ ");

			// Print current subset
			for (int j = 0; j < n; j++)

				// (1<<j) is a number with jth bit 1
				// so when we 'and' them with the
				// subset number we get which numbers
				// are present in the subset and which
				// are not
				if ((i & (1 << j)) > 0)
					System.out.print(set[j] + " ");

			System.out.println("}");
		}
	}

}
