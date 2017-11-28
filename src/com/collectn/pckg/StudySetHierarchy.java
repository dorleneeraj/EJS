package com.collectn.pckg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class StudySetHierarchy {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Collection<Integer> collection;
		AbstractCollection<Integer> abstractCollection;
		Set<Integer> justASet;
		AbstractSet<Integer> abstractSet;
		HashSet<String> hashSet;
		LinkedHashSet<Integer> linkedHashSet;
		CopyOnWriteArraySet<Integer> conpyOnWriteArraySet;
		// EnumSet<Integer> enumSet;
		SortedSet<Integer> sortedSet;
		NavigableSet<Integer> navigableSet;
		TreeSet<Integer> treeSet;
		ConcurrentSkipListSet<Integer> concurrentSkipListSet;

		hashSet = new HashSet<String>();
		hashSet.add("neeraj");
		hashSet.add("Dorle");
		hashSet.add("Ketki");
		System.out.println(hashSet);

		File f = new File("hashSet.txt");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hashSet);
		oos.close();

		HashSet<String> copyHS;
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		copyHS = (HashSet<String>) ois.readObject();
		System.out.println(copyHS);

		MyNonSerailizableClass obj1 = new MyNonSerailizableClass();
		MyNonSerailizableClass obj2 = new MyNonSerailizableClass();
		MyNonSerailizableClass obj3 = new MyNonSerailizableClass();
		MyNonSerailizableClass obj4 = new MyNonSerailizableClass();
		MyNonSerailizableClass obj5 = new MyNonSerailizableClass();
		HashSet<MyNonSerailizableClass> h = new HashSet<MyNonSerailizableClass>();
		h.add(obj1);
		h.add(obj2);
		h.add(obj3);
		h.add(obj4);
		h.add(obj5);

		File f2;
		try {
			f2 = new File("somefile.txt");
			fos = new FileOutputStream(f2);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(h);
			oos.close();
		} catch (Exception e) {
			System.out.println("First Exception");
			e.printStackTrace();
		}
		MySerializableClass obj6 = new MySerializableClass();
		MySerializableClass obj7 = new MySerializableClass();
		MySerializableClass obj8 = new MySerializableClass();

		HashSet<MySerializableClass> h2 = new HashSet<MySerializableClass>();
		h2.add(obj6);
		h2.add(obj7);
		h2.add(obj8);

		try {
			f2 = new File("somefile.txt");
			fos = new FileOutputStream(f2);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(h2);
			oos.close();
		} catch (Exception e) {
			System.out.println("Second Exception");
			e.printStackTrace();
		}
		
		
		
		
		
	}
}

class MyNonSerailizableClass {
	int i1 = 50;
}

class MySerializableClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6932736392765871517L;
	int i1 = 60;
	//MyNonSerailizableClass c1 = new MyNonSerailizableClass();

}