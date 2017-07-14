package access.pkg2;

import access.pkg1.ParentClass;

public class DifferentPkgSubClass extends ParentClass {

	protected int X = 5; // Such a definition is allowed (even though it seems
							// to be

	// duplicate, its not!)

	public DifferentPkgSubClass() {
		System.out.println("...Initializing...");
	}

	public static void main(String[] args) {
		DifferentPkgSubClass dPSC = new DifferentPkgSubClass();
		dPSC.accessProtected();
	}

	public void accessProtected() {
		// this class has direct access to the protected members through
		// inheritance as follows.
		// Its as if this class only has declared them...

		System.out.println("Value of x is: " + X);
		printX(); // --->> It prints value 10, as it is not over-ridden and
					// belong to the parent(Super) class.

		// The following code will not compile and give compilation errors..

		ParentClass pC = new ParentClass();

		// pC.printX();
		// System.out.println("Value of x is: " + pC.X);

		/*
		 * This is because, to access protected members using a reference, the
		 * respective classes have to be in the same package. It is only
		 * available using inheritance.
		 */

	}
}
