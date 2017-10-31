package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestNumber {
	public static void main(String[] args) {
		System.out.println(largestNumber(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 1 }))));
	}

	public static String largestNumber(final List<Integer> a) {
		ArrayList<String> list = new ArrayList<String>();
		for (Integer i : a) {
			list.add(Integer.toString(i));
		}
		Collections.sort(list);
		Collections.reverse(list);
		System.out.println(list);
		StringBuilder s = new StringBuilder();
		for (String str : list) {
			s.append(str);
		}
		String str = s.toString();
		str = str.replace("0", "");
		if (str.equals("")) {
			str = "0";
		}
		return s.toString();
	}
}
