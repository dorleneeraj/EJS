package pkg1;

/*
 * This is a simple project to understand what happens if we declare instance
 * variables with same identifiers but different access modifiers.
 * This will obviously have some errors, but just for reference, let them be.
 */

//remove the following comments to see...


public class TestClass1 {
	// default access
	
	/* int x = 1 ; */

	// public access
	/*public int x = 1; */ // This is not possible, for obvious reasons. For this
						// class
	// irrespective of their access modifiers, they are internal for the class
	// itself.
	// Hence the ambiguity. It will be the same for other modifiers as well.

	
	// Same as above.... So we can't have duplicate identifiers.
	
/*	
	private int x = 1;

	static final int x = 1;

	final String x = "Neeraj";
*/
}
