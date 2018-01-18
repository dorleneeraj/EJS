package com.collectn.pckg.mapsandSets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TestClass {
	public static void main(String[] args) {

		Map<Employee, String> myMap = new HashMap<Employee, String>();
		Employee neeraj = new Employee("neeraj", 26);
		System.out.println(neeraj.hashCode());
		myMap.put(neeraj, "neeraj");

		System.out.println(myMap);

		neeraj.setAge(27);
		System.out.println(neeraj);

		System.out.println(myMap);
		System.out.println(myMap.containsKey(neeraj));

		System.out.println(myMap);
		Employee neerajOLD = new Employee("neeraj", 26);
		// myMap.put(neerajOLD, "neerajOld");
		System.out.println(neerajOLD.hashCode());
		System.out.println(myMap.containsKey(neerajOLD));
		System.out.println(myMap.get(neerajOLD));
		System.out.println(myMap);

		System.out.println("*******************************************");
		System.out.println("Set behaviour...");

		Set<Employee> empSet = new HashSet<Employee>();
		Employee ketki = new Employee("Ketki", 28);
		Employee rudra = new Employee("rudra", 28);
		Employee prerna = new Employee("prerna", 28);

		empSet.add(ketki);
		empSet.add(rudra);
		empSet.add(prerna);
		System.out.println(empSet);

		TreeSet<Employee> treeSet = new TreeSet<Employee>();
		treeSet.add(prerna);
		treeSet.add(ketki);
		treeSet.add(rudra);

		System.out.println(treeSet);


	}
}
