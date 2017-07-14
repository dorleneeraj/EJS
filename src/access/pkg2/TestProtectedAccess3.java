package access.pkg2;

public class TestProtectedAccess3 extends TestProtectedAccess2 {
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
