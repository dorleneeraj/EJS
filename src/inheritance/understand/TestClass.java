package inheritance.understand;

public class TestClass {
	public static void main(String[] args) {
		SuperClass superClass = new SuperClass();
		superClass.publicMethod();
		// no private method can be called.

		SubClass subClass = new SubClass();
		subClass.publicMethod(); // As said, public method is available
		subClass.printX();
	}
}
