package access.pkg1;

public class SamePkgAccessProtected {

	public void accessProtected() {
		ParentClass pC = new ParentClass();
		System.out.println("Value of X is: " + pC.X);
		pC.printX(); // As you can see, different class from the same package
		// have direct access to the protected members "through reference"
	}
}
