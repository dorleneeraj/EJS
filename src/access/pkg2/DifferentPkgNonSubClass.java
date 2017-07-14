package access.pkg2;

import access.pkg1.ParentClass;

public class DifferentPkgNonSubClass {
	// This is a non subclass of the parent class. Let us check what happens.
	// As the parent class is public, the class is visible from here.
	// But the protected members won't be visible through reference.
	// In fact they will all be hidden. No way to access them unless your class
	// explicitly provides the getter method.

	public void accessProtectedMembersOtherPkg() {
		ParentClass pC = new ParentClass();
		// pC.printX(); <------ NOT AVAILABLE!

		// The method printX() from the type ParentClass is not visible

		// nor the following is possible

		// System.out.println("" + pC.X); // same error as above.
	}
}
