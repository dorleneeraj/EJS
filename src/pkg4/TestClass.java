package pkg4;

/*
 * We will import two packages, pkg2 and pkg3 here. These two packages exactly have same 
 * class. Let us check the behaviour of the scenario. 
 * What happens...?
 */

import pkg2.*;
import pkg2.MyClass;
import pkg3.*;

//remove the following comments to see...

// import pkg3.MyClass; // The import com.pkg3.MyClass collides with another import statement

public class TestClass {
	public static void main(String[] args) {

		MyClass mClass1 = new MyClass(); // When two different packages having
											// the same classes are imported and
											// we want
											// to make use of that class, we
											// have to explicitly import the
											// class.

		pkg3.MyClass mClass2 = new pkg3.MyClass(); // If you want to use
													// the class from
													// the
													// different pkg
													// with same name,
													// you have
													// to provide the
													// fully qualified
													// name as written.

	}
}
