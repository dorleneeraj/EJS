package scjp.chapter1.accessControl.pkg2;

import scjp.chapter1.accessControl.TestDefaultPackageModifier;

// Not POSSIBLE With Default access. They are not inherited in the subclass 
// OF the different package!.

public class TestDefaultPackageModifierDifferentPKGInheritancce extends TestDefaultPackageModifier {
	public static void main(String[] args) {

	}

	public void justAMethod() {
		this.a = 12; // <------ NOT AVAILABLE!
		this.getA(); // <------ NOT AVAILABLE!
		this.setA(15); // <------ NOT AVAILABLE!
	}

}
