package scjp.chapter1.accessControl.pkg2;

import scjp.chapter1.accessControl.TestDefaultPackageModifier;

// Default access is ONLY package SPECIFIC. It is NOT AVAILABLE OUTSIDE 
// THE PACKAGE.

public class TestDefaultPackageModifierDifferentPKG {
	public static void main(String[] args) {

		TestDefaultPackageModifier tPM = new TestDefaultPackageModifier();
		tPM.a = 11; // <------ NOT AVAILABLE!
		tPM.setA(12); // <------ NOT AVAILABLE!
		tPM.getA(); // <------ NOT AVAILABLE!

	}
}
