package scjp.chapter3.boxingAndWrapperClass;

public class WrapperConstructors {

	public static void main(String[] args) {
		// Wrapper classes provide two different constructors.
		// One of them takes the primitive type as input and the other one
		// takes the String representation of the Type.
		// If the String cannot be parsed to the number representation, it
		// throws
		// NumberFormatException in case of NaN.

		// Integer i1 = new Integer(); // Cannot be left blank.
		Integer i2 = new Integer(7); // Primitive value as input
		Integer i3 = new Integer("123");
		String s1 = "800";
		Integer i4 = new Integer(s1);

		String s2 = "-300";
		Integer i5 = new Integer(s2);

		System.out
				.println("************************* Printing the values ***********************");
		System.out.println("i2:" + i2);
		System.out.println("i3:" + i3);
		System.out.println("i4:" + i4);
		System.out.println("i5:" + i5);
		System.out.println(i4 + i2);
		System.out.println(i5 + 200);

		Boolean b1 = new Boolean("faltu");
		if (b1) {
			System.out.println("Miracle!");
		} else {
			System.out.println("Anything else equates to false!");
		}

		// valueOf methods
		Integer a = new Integer(1);
		Integer b = new Integer(1);

		System.out.println("a==b? " + (a == b));

		Integer c = Integer.valueOf(1);
		Integer d = Integer.valueOf(1);

		System.out.println("c==d? " + (c == d));

		Integer a1 = Integer.valueOf(500);
		Integer a2 = Integer.valueOf(500);
		Integer a3 = Integer.valueOf(500);
		Integer a4 = Integer.valueOf(500);
		Integer a5 = Integer.valueOf(500);
		System.out.println("a1==a2? " + (a1 == a2));
		System.out.println("a1==a3? " + (a1 == a3));
		System.out.println("a1==a4? " + (a1 == a4));
		System.out.println("a1==a5? " + (a1 == a5));
		System.out.println("a5==a2? " + (a5 == a2));
	}
}
