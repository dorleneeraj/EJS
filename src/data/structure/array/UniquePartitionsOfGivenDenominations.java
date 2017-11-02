package data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniquePartitionsOfGivenDenominations {

	public static void main(String[] args) {
		System.out.println(coinchange4(
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 18, 24,
						23, 10, 16, 19, 2, 9, 5, 12, 17, 3, 28, 29, 4, 13, 15,
						8 })), 458));

//		System.out.println(coinchange2(new Integer[] { 18, 24, 23, 10, 16, 19,
//				2, 9, 5, 12, 17, 3, 28, 29, 4, 13, 15, 8 }, 458));
	}

	public static List<List<Integer>> printUniquePartitionsProgressive(
			int[] denominations, int amount) {
		int uniqueWaysOfChange = 0;
		List<List<Integer>> uniqueChangeList = null;
		if (amount == 0) {
			uniqueWaysOfChange = 0;
		} else if (denominations.length == 1) {
			int coin = denominations[0];
			if ((amount % coin) == 0) {
				uniqueChangeList = new ArrayList<List<Integer>>();
				List<Integer> partition = new ArrayList<Integer>();
				uniqueWaysOfChange = 1;
				int c = amount / coin;
				for (int b = 0; b < c; b++) {
					partition.add(coin);
				}
				uniqueChangeList.add(partition);
			} else {
				uniqueWaysOfChange = 0;
			}
		} else {
			int coin = denominations[0];
			int count = 0;
			uniqueChangeList = new ArrayList<List<Integer>>();
			while ((amount - (coin * count)) >= 0) {
				List<Integer> listToAdd = new ArrayList<Integer>();
				for (int add = 0; add < count; add++) {
					listToAdd.add(coin);
				}
				int[] newDenominations = new int[denominations.length - 1];
				int newAmount = (amount - (coin * count));
				int a = 0;
				for (int j = 1; j < denominations.length; j++) {
					newDenominations[a] = denominations[j];
					a++;
				}
				if ((newAmount > 0) && newDenominations.length > 0) {
					List<List<Integer>> waysForChangeToAppend = printUniquePartitionsProgressive(
							newDenominations, newAmount);
					uniqueWaysOfChange += waysForChangeToAppend.size();
					for (List<Integer> list : waysForChangeToAppend) {
						list.addAll(listToAdd);
						uniqueChangeList.add(list);
					}
				} else if (newAmount == 0) {
					uniqueWaysOfChange = uniqueWaysOfChange + 1;
					// listToAdd.add(coin);
					uniqueChangeList.add(listToAdd);
				}
				count++;
			}
		}
		return uniqueChangeList;
	}

	static public int coinchange3(int[] denominations, int amount) {
		Arrays.sort(denominations);
		int uniqueWaysOfChange = 0;
		if (amount == 0) {
			uniqueWaysOfChange = 0;
		} else if (denominations.length == 1) {
			int coin = denominations[0];
			// check if the single available coin can form the given amount.
			if ((amount % coin) == 0) {
				uniqueWaysOfChange = 1;
			} else {
				uniqueWaysOfChange = 0;
			}
		} else {
			int coin = denominations[0]; // use this coin 0 or more times
			int count = 0;
			// while the given amount can include the occurrences of the
			// given
			// coin.
			while ((amount - (coin * count)) >= 0) {
				int[] newDenominations = new int[denominations.length - 1];
				int newAmount = (amount - (coin * count));
				int a = 0;
				for (int j = 1; j < denominations.length; j++) {
					newDenominations[a] = denominations[j];
					a++;
				}
				if ((newAmount > 0) && newDenominations.length > 0) {
					uniqueWaysOfChange = ((uniqueWaysOfChange % 1000007) + coinchange3(
							newDenominations, newAmount)) % 1000007;
				} else if (newAmount == 0) {
					uniqueWaysOfChange = ((uniqueWaysOfChange % 1000007) + 1) % 1000007;
				}
				count++;
			}

		}

		return uniqueWaysOfChange;
	}

	public static Map<Integer[], List<List<Integer>>> memo = new HashMap<Integer[], List<List<Integer>>>();

	public static List<List<Integer>> coinchange2(Integer[] denominations,
			int amount) {
		int uniqueWaysOfChange = 0;
		List<List<Integer>> uniqueChangeList = Collections.emptyList();
		if (memo.containsKey(denominations)) {
			uniqueChangeList = memo.get(amount);
		} else if (amount == 0) {
			uniqueWaysOfChange = 0;
		} else if (denominations.length == 1) {
			int coin = denominations[0];
			if ((amount % coin) == 0) {
				uniqueChangeList = new ArrayList<List<Integer>>();
				List<Integer> partition = new ArrayList<Integer>();
				uniqueWaysOfChange = 1;
				int c = amount / coin;
				for (int b = 0; b < c; b++) {
					partition.add(coin);
				}
				uniqueChangeList.add(partition);
			} else {
				uniqueWaysOfChange = 0;
			}
		} else {
			int coin = denominations[0];
			int count = 0;
			uniqueChangeList = new ArrayList<List<Integer>>();
			while ((amount - (coin * count)) >= 0) {
				List<Integer> listToAdd = new ArrayList<Integer>();
				for (int add = 0; add < count; add++) {
					listToAdd.add(coin);
				}
				Integer[] newDenominations = new Integer[denominations.length - 1];
				int newAmount = (amount - (coin * count));
				int a = 0;
				for (int j = 1; j < denominations.length; j++) {
					newDenominations[a] = denominations[j];
					a++;
				}
				if ((newAmount > 0) && newDenominations.length > 0) {
					List<List<Integer>> waysForChangeToAppend = coinchange2(
							newDenominations, newAmount);

					uniqueWaysOfChange += waysForChangeToAppend.size();
					for (List<Integer> list : waysForChangeToAppend) {
						list.addAll(listToAdd);
						uniqueChangeList.add(list);
					}
					memo.put(newDenominations, uniqueChangeList);
				} else if (newAmount == 0) {
					uniqueWaysOfChange = uniqueWaysOfChange + 1;
					// listToAdd.add(coin);
					uniqueChangeList.add(listToAdd);
				}
				count++;
			}
			memo.put(denominations, uniqueChangeList);
		}
		return uniqueChangeList;
	}

	static public int coinchange4(ArrayList<Integer> coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 0; i < coins.size(); i++) {
			for (int j = coins.get(i); j <= amount; j++) {
				dp[j] = (dp[j] + dp[j - coins.get(i)]) % 1000007;
			}
		}
		return dp[amount];
	}

}
