package scjp.chapter1.accessControl;

// The class is public, but the members are package private/ default
public class TestDefaultPackageModifier {
	int a = 10;

	void setA(int val) {
		a = val;
	}

	int getA() {
		return a;
	}
}
