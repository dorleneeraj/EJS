package com.string.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Print all the sub-sequences of the string.
 * 
 * Ex: abc: Solution: a,b,c,ab,bc,ac, abc
 * 
 * 
 * @author neeraj
 *
 */
public class PrintAllSubSequence {

	public static void main(String[] args) {
		String s = "abc";
		naiveSolution(s);
		for (String str : subS) {
			System.out.println(str);
		}

		subS.clear();
		s = "neeraj";
		naiveSolution(s);
		for (String str : subS) {
			System.out.println(str);
		}

		s = "abc";
		topToBottom(s);
		for (String str : subSequenceSet) {
			System.out.println(str);
		}
		subSequenceSet.clear();
		subsequenceMap.clear();

		s = "neeraj";
		topToBottom(s);
		for (String str : subSequenceSet) {
			System.out.println(str);
		}

		boolean flag = true;
		for (String str : subS) {
			if (!subSequenceSet.contains(str)) {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
	}

	static Set<String> subS = new HashSet<String>();

	public static void naiveSolution(String s) {

		for (int i = 0; i < s.length(); i++) {
			for (int j = s.length(); j > i; j--) {
				String subStr = s.substring(i, j);
				if (!subS.contains(subStr)) {
					subS.add(subStr);
				}
				for (int k = 1; k < subStr.length() - 1; k++) {
					StringBuffer sb = new StringBuffer(subStr);
					sb.deleteCharAt(k);
					naiveSolution(sb.toString());
				}
			}
		}
	}

	static Map<Integer, Set<String>> subsequenceMap = new HashMap<Integer, Set<String>>();
	static Set<String> subSequenceSet = new HashSet<String>();

	public static Set<String> topToBottom(String s) {
		for (int i = s.length() - 1; i >= 0; i--) {
			String charAtI = s.charAt(i) + "";
			if (!subSequenceSet.contains(charAtI)) {
				subSequenceSet.add(charAtI);
			}
			Set<String> subsequencesAtI = new HashSet<String>();
			subsequencesAtI.add(charAtI);
			for (int j = i + 1; j < s.length(); j++) {
				Set<String> previousSubS = subsequenceMap.get(j);
				if (null != previousSubS && !previousSubS.isEmpty()) {
					for (String pss : previousSubS) {
						String sub = charAtI + pss;
						subsequencesAtI.add(sub);
						if (!subSequenceSet.contains(sub)) {
							subSequenceSet.add(sub);
						}
					}
				}
			}
			subsequenceMap.put(i, subsequencesAtI);
		}
		return subSequenceSet;
	}
}
