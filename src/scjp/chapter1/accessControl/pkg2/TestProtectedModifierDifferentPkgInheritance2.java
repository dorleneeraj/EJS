package scjp.chapter1.accessControl.pkg2;

import scjp.chapter1.accessControl.TestProtectedModifier;

public class TestProtectedModifierDifferentPkgInheritance2 extends TestProtectedModifier {
	public static void main(String[] args) {
		// Now, TestProtectedModifierDifferentPkgInheritance2 extends
		// TestProtectedModifier
		// But still, if we create an instance of TestProtectedModifier, here in
		// this class's
		// code, it will not be able to access the protected members...
	}

	public void justAMethod() {
		TestProtectedModifier tPM = new TestProtectedModifier();

		tPM.a = 12; // <------ NOT AVAILABLE!
		tPM.getA(); // <------ NOT AVAILABLE!
		tPM.setA(14); // <------ NOT AVAILABLE!

		// Thus, only inheritance works in the case of protected modifiers.
		// Only the subclass has access to them and no one else.

	}
}
