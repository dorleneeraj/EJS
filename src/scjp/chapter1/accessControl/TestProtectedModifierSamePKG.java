package scjp.chapter1.accessControl;

// Method, instance variable is accessible means,it is accessible only through 
// instance creation.

public class TestProtectedModifierSamePKG {
	public static void main(String[] args) {
		// Same package. Class is visible.

		TestProtectedModifier tPM = new TestProtectedModifier();

		// protected is visible from the same package.
		tPM.a = 11;
		System.out.println("The value of a is changed. it is: " + tPM.a);

		// Test the protected methods.
		tPM.setA(12);
		System.out.println("Protected methods are accessible. The value is: " + tPM.getA());
	}
}
