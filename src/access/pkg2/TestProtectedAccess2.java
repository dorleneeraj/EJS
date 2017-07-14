package access.pkg2;

public class TestProtectedAccess2 extends DifferentPkgSubClass {
	/*
	 * Now lets see if we can access those protected members.
	 */

	public TestProtectedAccess2() {
		System.out.println("Do nothing!");
	}

	@Override
	public void accessProtected() {
		System.out.println("Value of X in TestProtectedAccess2 is: " + X);
		printX();
	}

	public static void main(String[] args) {
		TestProtectedAccess2 ta = new TestProtectedAccess2();
		ta.accessProtected();
	}
}
