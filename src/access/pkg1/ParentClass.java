package access.pkg1;

/*
 * This class is to understand how the "protected" access modifier works. 
 */

public class ParentClass {

	/* Create a method and an instance member with protected access modifier. */
	protected int X = 10;

	protected void printX() {
		System.out.println("The value of X is: " + this.X);
		// As we can see here, the code within the class has direct access to
		// the
		// protected member
	}
}
