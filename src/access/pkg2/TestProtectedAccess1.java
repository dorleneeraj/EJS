package access.pkg2;

/*
 * Now, DifferentPkgSubClass class and TestProtectedAccess1 are in the same package.
 * DifferentPkgSubClass has access to the protected members of the ParentClass
 * through inheritance. Let us check if we can access those protected members through 
 * reference from this class. 
 * 
 * What will happen is that, those protected members will be private??? once DifferentPkgSubClass 
 * inherits them. Hence they will not be accessible using reference outside the class.
 */

public class TestProtectedAccess1 {
	public void accessProtected() {
		DifferentPkgSubClass dPSC = new DifferentPkgSubClass();

		// dPSC.printX(); <----- This is not available.
	}
}
