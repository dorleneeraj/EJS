package inheritance.understand;

public abstract class ConstructorChaining {

	public ConstructorChaining() {
		cacl();
	}

	public abstract void cacl();

	public static void main(String[] args) {
		SubClass1 sub = new SubClass1();
	}

}

class SubClass1 extends ConstructorChaining {

	Integer x;

	public SubClass1() {
		x = new Integer(10);
	}

	@Override
	public void cacl() {
		System.out.println("Value of x: " + x.toString());

	}

}
