package scjp.chapter1.accessControl;

// In this class, everything is Public. That is the class itself is public and 
// all the methods and the instance variables are public as well
public class TestPublicModifier {

	public int a = 10;

	public void setA(int val) {
		a = val;
	}

	public int getA() {
		return a;
	}
}
