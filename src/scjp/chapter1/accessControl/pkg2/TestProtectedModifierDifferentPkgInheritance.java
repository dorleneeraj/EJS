package scjp.chapter1.accessControl.pkg2;

import scjp.chapter1.accessControl.TestProtectedModifier;

// Thus, protected members are accessible through inheritance only. And not by instance creation.

public class TestProtectedModifierDifferentPkgInheritance extends TestProtectedModifier {
	public static void main(String[] args) {
		TestProtectedModifierDifferentPkgInheritance tPMDPI = new TestProtectedModifierDifferentPkgInheritance();
		tPMDPI.setInheitedAValue(11);
		System.out.println("Testing the inherited getA method: " + tPMDPI.getA());

		tPMDPI.setA(12);
		System.out.println("Changed value is: " + tPMDPI.getInheitedAValue());

		tPMDPI.a = 13;
		System.out.println("Value of A is: " + tPMDPI.a);

	}

	public int getInheitedAValue() {
		return this.a;
	}

	public void setInheitedAValue(int val) {
		this.a = val;
	}
}
