package pkg1;

/*
 * Let us implement the interface that has field "x" already declared. 
 * In interface, members are implicitly public static final.
 * With this implementation of interface, try and declare a field with 
 * the same identifier and check what happens.
 * 
 */

public class TestClass2 implements TestInterface1 {

	// whoa! I could declare it. Now how to access the member from
	// TestInterface1?
	// For this, I will have to read further the rules of inheritance.
	// Something like, only methods are inherited, and not the members...
	// ...Not sure about it right now..

	static int X = 17;

	public static void main(String[] args) {

		System.out.println("" + X);
		System.out.println("" + TestInterface1.X);
		System.out.println("" + Y); // We will get to know about why this works
									// in the later chapters.
									// These are nothing but properties/ rules
									// of inheritance in JAVA
									// which the developers must follow.
	}

}
