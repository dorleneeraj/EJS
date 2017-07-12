package scjp.chapter1.accessControl.pkg2;

import scjp.chapter1.accessControl.*;

import scjp.chapter1.accessControl.TestProtectedModifier;

// This jave file is full of compilation errors. 
// WHY?
// The class which TestProtectedModifierDifferentPKG is trying to access is visible as it is public. 
// but the instance variables and the methods defined in that class are protected. 
// As the protected variables are not accessible outside the package, we get compilation errors. 
// But, protected variables can be accessed through inheritance but not through instance. Let us see 
// that scenario in the next example: TestProtectedModifierDifferentPkgInheritance

public class TestProtectedModifierDifferentPKG {

	public static void main(String[] args) {
		// Same package. Class is visible.

		TestProtectedModifier tPM = new TestProtectedModifier();

		// protected is visible from the same package.
		tPM.a = 11; // <------ COMPILATION ERROR
		System.out.println("The value of a is changed. it is: " + tPM.a); // <------
																			// COMPILATION
																			// ERROR

		// Test the protected methods.
		tPM.setA(12); // <------ COMPILATION ERROR
		System.out.println("Protected methods are accessible. The value is: " + tPM.getA()); // <------
																								// COMPILATION
																								// ERROR
	}
}
