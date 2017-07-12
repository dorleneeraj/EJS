package scjp.chapter1.accessControl;

// In this package we will test the other public class, which has all the public fields
public class TestPublicModifierSamePKG {
	public static void main(String[] args) {
		// Both the classes are from same package. Class1 is public and hence
		// visible here.
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
