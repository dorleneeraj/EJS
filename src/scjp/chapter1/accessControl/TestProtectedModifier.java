package scjp.chapter1.accessControl;

// All the fields in this class are protected. Though the class itself is public. 
public class TestProtectedModifier {
	protected int a = 10;

	protected void setA(int val) {
		a = val;
	}

	protected int getA() {
		return a;
	}
}
