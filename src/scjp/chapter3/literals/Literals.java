package scjp.chapter3.literals;

/**
 * 
 * @author neeraj
 *
 *
 */

/*
 * Literals are fairly straight forward things. Meaning, literals are nothing
 * but source code representation of values. Means, the values that we directly
 * type into our code are literals.
 * 
 * They don't need any computation.
 */
public class Literals {
	public static void main(String[] args) {
		// Following are integer literals
		int x = 6; // Here '6' is a literal. Because we directly typed it into
					// our code.
					// So integer literals are nothing but integer values that
					// we type into our code.
		// Similarly, following are some of the literals for different datatypes
		float f = 7.8F;
		double d = 9.8D;
		String s = "Neeraj";
		char c = 'a';

		char someChar = 999;
		System.out.println(someChar);

		// Some garbage value or some character that we can't decipher gets
		// printed. What does that mean?
		// It means that, characters are internally positive / unsigned
		// integers. But while we type or print them,
		// they are printed depending upon the character encoding we use. For
		// ex. For 16-bit integers we typically
		// use unicode character set or unicode encoding. Hence in that unicode,
		// 999 will imply the character that we
		// are seeing and hence it prints that character and not simply 999 as
		// the integer would have.

		System.out.println((int) someChar); // See the difference.
		// So when we type in a character, "encoding" comes into the picture!

		System.out.println(c);
		System.out.println((int) c);
		System.out.println((char) 97);
	}
}
