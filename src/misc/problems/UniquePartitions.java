package misc.problems;

public class UniquePartitions {

	public static void main(String[] args) {
		printPartitions(4, 4, "");
	}

	static void printPartitions(int target, int maxValue, String suffix) {
		if (target == 0)
			System.out.println(suffix);
		else {
			for (int i = 1; i <= maxValue && i <= target; i++)
				printPartitions(target - i, i, i + " " + suffix);
		}
	}

}
