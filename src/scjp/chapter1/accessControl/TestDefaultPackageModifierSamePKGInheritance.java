package scjp.chapter1.accessControl;

public class TestDefaultPackageModifierSamePKGInheritance extends TestDefaultPackageModifier {

	// Protected members are inherited as is in the classes within the same
	// package.
	public static void main(String[] args) {
		TestDefaultPackageModifierSamePKGInheritance tDPMSPI = new TestDefaultPackageModifierSamePKGInheritance();
		tDPMSPI.a = 12;
		System.out.println("Value of is: " + tDPMSPI.getA());
		tDPMSPI.setA(13);
		System.out.println("Value of is: " + tDPMSPI.a);
	}
}
