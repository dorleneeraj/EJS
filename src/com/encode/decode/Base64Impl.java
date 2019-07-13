package com.encode.decode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic Base64 encoding and decoding implementation.
 * <p>
 * <b>How Base64 Works?</b>
 * </p>
 * <p>
 * Base64 was originally invented to transfer data through channels that only
 * reliably supported text(ASCII) content. Well in such environments, how to
 * transfer images, videos etc? Eventually, on machines, data is just
 * <i>binary</i>. What base64 does is, it takes the raw bytes and converts(or
 * <b><i>encodes</b></i>) it to chosen set of characters that the channel
 * supports.
 * </p>
 * 
 * 
 * 
 * @author neeraj.dorle
 *
 */
public class Base64Impl {

	public static Map<Integer, Character> base64Map = new HashMap<>();
	public static final String PADDING_CHAR = "=";
	public static final List<Character> decodingIndexList = new ArrayList<>();

	static {
		base64Map.put(0, 'A');
		base64Map.put(1, 'B');
		base64Map.put(2, 'C');
		base64Map.put(3, 'D');
		base64Map.put(4, 'E');
		base64Map.put(5, 'F');
		base64Map.put(6, 'G');
		base64Map.put(7, 'H');
		base64Map.put(8, 'I');
		base64Map.put(9, 'J');
		base64Map.put(10, 'K');
		base64Map.put(11, 'L');
		base64Map.put(12, 'M');
		base64Map.put(13, 'N');
		base64Map.put(14, 'O');
		base64Map.put(15, 'P');
		base64Map.put(16, 'Q');
		base64Map.put(17, 'R');
		base64Map.put(18, 'S');
		base64Map.put(19, 'T');
		base64Map.put(20, 'U');
		base64Map.put(21, 'V');
		base64Map.put(22, 'W');
		base64Map.put(23, 'X');
		base64Map.put(24, 'Y');
		base64Map.put(25, 'Z');
		base64Map.put(26, 'a');
		base64Map.put(27, 'b');
		base64Map.put(28, 'c');
		base64Map.put(29, 'd');
		base64Map.put(30, 'e');
		base64Map.put(31, 'f');
		base64Map.put(32, 'g');
		base64Map.put(33, 'h');
		base64Map.put(34, 'i');
		base64Map.put(35, 'j');
		base64Map.put(36, 'k');
		base64Map.put(37, 'l');
		base64Map.put(38, 'm');
		base64Map.put(39, 'n');
		base64Map.put(40, 'o');
		base64Map.put(41, 'p');
		base64Map.put(42, 'q');
		base64Map.put(43, 'r');
		base64Map.put(44, 's');
		base64Map.put(45, 't');
		base64Map.put(46, 'u');
		base64Map.put(47, 'v');
		base64Map.put(48, 'w');
		base64Map.put(49, 'x');
		base64Map.put(50, 'y');
		base64Map.put(51, 'z');
		base64Map.put(52, '0');
		base64Map.put(53, '1');
		base64Map.put(54, '2');
		base64Map.put(55, '3');
		base64Map.put(56, '4');
		base64Map.put(57, '5');
		base64Map.put(58, '6');
		base64Map.put(59, '7');
		base64Map.put(60, '8');
		base64Map.put(61, '9');
		base64Map.put(62, '+');
		base64Map.put(63, '/');

		// Check if the map has been properly initialized with data...
		if (base64Map.size() == 64) {
			System.out.println("Map has been initialized properly...");
		} else {
			System.err.println("Map not initialized with proper data...");
			System.exit(1);
		}

		// Static loading to supplement decoding
		decodingIndexList.addAll(Arrays.asList(new Character[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' }));

		// Check if the list has been properly initialized with data...
		if (decodingIndexList.size() == 64) {
			System.out.println("List has been initialized properly...");
		} else {
			System.err.println("List not initialized with proper data...");
			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encodeToBase64("neeraj"));
		System.out.println(decodeFromBase64(encodeToBase64("neeraj")));

		// System.out.println(Integer.toBinaryString(27));
		// System.out.println(Integer.toBinaryString(63));

	}

	/**
	 * Converts plainText to the base64 encoded form.
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encodeToBase64(String plainText) {
		/**
		 * 1. Convert the data to bytes. 2. Group it in sizes of 6-bits. 3. Map each
		 * 6-bit data to the relevant character used by Base64. 4. Pad character(=)
		 * wherever necessary.
		 */

		StringBuilder encodedBuilder = new StringBuilder();
		String encodedString = "";
		int paddingToAdd = 0;

		// If length of plainText is divisible by 3, then no pads are required.
		// Else, pads required are equal to remainder of the length divided by 3.
		// i.e 0,1 or 2

		if ((plainText.length() % 3) != 0) {
			paddingToAdd = 3 - (plainText.length() % 3);
		}

		// convert the data to bytes...
		byte[] dataBytes = plainText.getBytes();

		// Convert the data to binary string representation...
		StringBuilder binaryRepresentation = new StringBuilder();
		for (byte b : dataBytes) {
			for (int i = 0; i < 8; i++) {
				binaryRepresentation.append((b & 128) == 0 ? 0 : 1);
				b <<= 1;
			}
		}
		System.out.println("Binary String: " + binaryRepresentation);
		// if length of binaryRepresentation is not perfectly divisible by 6,
		// Then add 0s to the end equal to the remainder of
		// (binaryRepresentation.length/6)

		if ((binaryRepresentation.length() % 6) != 0) {
			int zerosToAdd = 6 - (binaryRepresentation.length() % 6);
			for (int i = 0; i < zerosToAdd; i++) {
				binaryRepresentation.append("0");
			}
		}

		for (int i = 0; i < binaryRepresentation.length(); i += 6) {
			int beginIndex = i;
			int endIndex = ((beginIndex + 6) <= binaryRepresentation.length()) ? beginIndex + 6
					: binaryRepresentation.length();
			String binaryIndex = binaryRepresentation.substring(beginIndex, endIndex);
			int index = Integer.parseInt(binaryIndex, 2);
			encodedBuilder.append(base64Map.get(index));
		}
		for (int i = 0; i < paddingToAdd; i++) {
			encodedBuilder.append("=");
		}

		encodedString = encodedBuilder.toString();
		return encodedString;
	}

	/**
	 * Decodes the base64 Encoded string to ASCII text.
	 * 
	 * @param base64EncodedString
	 * @return
	 */
	public static String decodeFromBase64(String base64EncodedString) throws Exception {
		// Decoding: Convert 4 characters of the encoded string to 3 characters of
		// decoded string

		// get the encoded character, map it to index of decodingIndexList. Club 4 such
		// characters. This will result in 24 bytes, as the indexes require only 6 bits
		// to represent in binary.

		// Convert these 24 bytes to 3 ASCII characters...

		String decodedString = "";
		StringBuilder decodedStringBuilder = new StringBuilder();

		// check if base64EncodedString.length is divisible by 4. else it is an invalid
		// base64 encoded string
		// also check if pads are not more than 2

		/**
		 * Will add these minor details later.
		 */

		for (int i = 0; i < base64EncodedString.length(); i += 4) {
			int beginIndex = i;
			int endIndex = beginIndex + 4;
			String charsToProcess = base64EncodedString.substring(beginIndex, endIndex);
			StringBuilder binaryBuilder = new StringBuilder();
			for (int j = 0; j < charsToProcess.length(); j++) {
				String binaryString = Integer.toBinaryString(decodingIndexList.indexOf(charsToProcess.charAt(j)));
				int zerosToPrepend = 6 - binaryString.length();
				if (zerosToPrepend > 0) {
					StringBuilder zeroPrepender = new StringBuilder();
					for (int k = 0; k < zerosToPrepend; k++) {
						zeroPrepender.append("0");
					}
					zeroPrepender.append(binaryString);
					binaryString = zeroPrepender.toString();
				}
				binaryBuilder.append(binaryString);
			}
			String binaryOP = binaryBuilder.toString();

			Arrays.stream( // Create a Stream
					binaryOP.split("(?<=\\G.{8})") // Splits the input string into 8-char-sections (Since a char has 8
													// bits = 1 byte)
			).forEach(s -> // Go through each 8-char-section...
			decodedStringBuilder.append((char) Integer.parseInt(s, 2)) // ...and turn it into an int and then to a char
			);

		}

		decodedString = decodedStringBuilder.toString();
		return decodedString;
	}

}
