package scjp.chapter1.accessControl;

// Default access is just as public within the same package. 
// Outside package, it is not visible!
// Not even through INHERITANCE!

public class TestDefaultPackageModifierSamePKG {
	public static void main(String[] args) {

		TestDefaultPackageModifier tPM = new TestDefaultPackageModifier();

		// public instance variable accessible.
		tPM.a = 11;

		System.out.println("The value of a is changed: It is : " + tPM.a);
		// public method accessible
		tPM.setA(12);

		// Same as above.
		System.out.println("The value of a is changed: It is now : " + tPM.getA());
	}
}
