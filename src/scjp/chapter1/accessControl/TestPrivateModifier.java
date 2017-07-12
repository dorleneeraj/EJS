package scjp.chapter1.accessControl;

public class TestPrivateModifier {
	private int a = 10;
	private static int b = 12;

	public static void main(String[] args) {
		TestPrivateModifier tPM = new TestPrivateModifier();
		tPM.a = 12;
		System.out.println("Private method accessible through instance only in class code: " + tPM.getA());
		TestPrivateModifier.justAMethod();
		tPM.justAMethod2();
	}

	private int getA() {
		// private member accessible within the class body.
		return a;
	}

	private void setA(int val) {
		// private member modified within the class body.
		int b = this.getA();
		System.out.println("Previous value of a is: " + b);
		this.a = val;
	}

	public static void justAMethod() {
		TestPrivateModifier tPM = new TestPrivateModifier();
		tPM.setA(15);
		System.out.println("Private method accessible through instance only in class code." + tPM.getA());
	}

	private void justAMethod2() {
		TestPrivateModifier tPM = new TestPrivateModifier();
		tPM.setA(18);
		System.out.println("Private method accessible through instance only in class code." + tPM.getA());
	}

}
