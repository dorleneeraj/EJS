package scjp.chapter1.accessControl.pkg2;

import scjp.chapter1.accessControl.*;

// The concerned package is imported. The class TestPublicModifier is public and hence 
// visible here. Consequently, all the public instances and methods will be accessible 
// from this class...
public class TestPublicModifierDifferentPKG {
	public static void main(String[] args) {

		TestPublicModifier tPM = new TestPublicModifier();

		// public instance variable accessible.
		tPM.a = 11;

		System.out.println("The value of a is changed: It is : " + tPM.a);
		// public method accessible
		tPM.setA(12);

		// Same as above.
		System.out.println("The value of a is changed: It is now : " + tPM.getA());
	}
}
