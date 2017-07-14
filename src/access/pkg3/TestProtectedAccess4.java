package access.pkg3;

import access.pkg2.TestProtectedAccess2;

public class TestProtectedAccess4 extends TestProtectedAccess2 {
	@Override
	public void accessProtected() {
		System.out.println("Value of x is: " + X);
		printX();
	}

	public static void main(String[] args) {
		TestProtectedAccess2 ta = new TestProtectedAccess2();
		ta.accessProtected();
	}
}
