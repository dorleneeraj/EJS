package com.my.pkg.multithreading;

import java.math.BigInteger;
import static java.lang.Long.valueOf;

public class PrimeCencus {

	public static void main(String[] args) {
		System.out.println("total: " + new PrimeCounter(2, 1000000000).count());
	}													    			

	private static final class PrimeCounter {
		private final long low;
		private final long high;

		PrimeCounter(long low, long high) {
			if (low < 2)
				throw new IllegalArgumentException("provide a number >= 2");
			if (high < low)
				throw new IllegalArgumentException("invalid, high: " + high + " < " + low);
			this.low = low;
			this.high = high;
		}

		private long count() {
			long c = 0L;
			long from = low - 1;
			BigInteger t = BigInteger.valueOf(from);
			while (true) {
				long pp = t.nextProbablePrime().longValue();
				if (pp > high)
					break;
				c += 1;
				// System.out.println(pp);
				t = BigInteger.valueOf(pp);
			}
			return c;
		}
	}
}