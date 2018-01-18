package inheritance.understand;

// This super class has one public method and one private method.

public class SuperClass {
	int x;
	
	public void publicMethod() {
		System.out.println("In public method");

		// now, give a call to the privateMethod()
		// What will happen when we will call subClass.publicMethod()?
		privateMethod();
	}

	private void privateMethod() {
		System.out.println("In private method");
		x = 10;
	}
}
