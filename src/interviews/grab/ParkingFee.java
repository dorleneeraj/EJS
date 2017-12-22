package interviews.grab;

public class ParkingFee {
	public static void main(String[] args) {
		ParkingFee pFee = new ParkingFee();
		System.out.println(pFee.solution("10:00", "13:21"));
		System.out.println(pFee.solution("9:42", "11:42"));
		System.out.println(pFee.solution("9:42", "9:48"));

	}

	public int solution(String E, String L) {
		int totalFees = 0;
		int entryFees = 2;
		int firstHourFees = 3;
		int hourlyFees = 4;

		String[] entryDetails = E.split(":");
		String[] exitDetails = L.split(":");

		int entryHour = Integer.parseInt(entryDetails[0]);
		int entryMinute = Integer.parseInt(entryDetails[1]);
		int exitHour = Integer.parseInt(exitDetails[0]);
		int exitMinute = Integer.parseInt(exitDetails[1]);

		if (exitHour < entryHour) {
			// invalid input
			return -1;
		}

		int totalWaitingHours = -1; // including the partial hour completed.
		int totalMinutesWaiting = 0;
		int extraMinutes = 0;

		totalMinutesWaiting = (exitHour - entryHour) * 60;
		totalMinutesWaiting = totalMinutesWaiting - entryMinute;
		totalMinutesWaiting = totalMinutesWaiting + exitMinute;
		if (totalMinutesWaiting <= 0) {
			return -1;
		}

		totalWaitingHours = totalMinutesWaiting / 60;
		extraMinutes = totalMinutesWaiting % 60;

		totalFees = totalFees + entryFees;
		totalFees = totalFees + firstHourFees;
		// subtract 1 hour

		totalWaitingHours = totalWaitingHours - 1;
		totalFees = totalFees + totalWaitingHours * hourlyFees;
		if (extraMinutes > 0) {
			totalFees = totalFees + 4;
		}

		return totalFees;
	}
}
