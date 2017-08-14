package general.pckg;

import java.lang.reflect.*;

public abstract strictfp class AbstractStrictfp {
	public abstract void abstractMethod();

	public void concreteMethod() {
	};

	public static void main(String args[]) {
		Method methods[] = AbstractStrictfp.class.getMethods();
		for (Method method : methods) {
			System.out.println("Method Name::" + method.getName());
			System.out.println("Modifiers::"
					+ Modifier.toString(method.getModifiers()));
			System.out.println();

		}
	}
}