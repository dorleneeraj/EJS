package scjp.chapter1.accessControl;

// This class also tests inheritance within the same class. 
// As we know, it is not possible. 

// PRIVATE implies <-------> Only within the class body. Nowhere ELSE! PERIOD!.

public class TestPrivateModifierSamePKG extends TestPrivateModifier {

	// testing same package other class
	public static void main(String[] args) {
		TestPrivateModifier tPM = new TestPrivateModifier();
		// No private methods / instance variables can be accessed through
		// tPM.

		TestPrivateModifier.justAMethod();
		// ONLY this works. As it is static....

		TestPrivateModifier.b = 6; // NOT AVAILABLE. Even though it is static
	}

	public void justAMethod1() {
		// this.a = 100; // <------ NOT AVAILABLE
	}
}
