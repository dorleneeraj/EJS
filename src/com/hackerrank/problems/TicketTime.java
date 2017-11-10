package com.hackerrank.problems;

public class TicketTime {
	public static void main(String[] args) {
		System.out.println(waitingTime(new int[] { 2, 6, 3, 4, 5 }, 2));
		System.out.println(waitingTime(new int[] { 1, 1, 1, 1 }, 0));
		System.out.println(waitingTime(new int[] { 5, 5, 2, 3 }, 3));

		System.out.println(waitingTime2(new int[] { 2, 6, 3, 4, 5 }, 2));
		System.out.println(waitingTime2(new int[] { 1, 1, 1, 1 }, 0));
		System.out.println(waitingTime2(new int[] { 5, 5, 2, 3 }, 3));
	}

	static long waitingTime(int[] tickets, int p) {
		long time = 0;
		while (tickets[p] != 0) {
			for (int i = 0; i < tickets.length; i++) {
				if (tickets[i] > 0) {
					tickets[i] = tickets[i] - 1;
					time++;
				}
				if ((i == p) && (tickets[i] == 0)) {
					break;
				}
			}
		}
		return time;
	}

	static long waitingTime2(int[] tickets, int p) {
		long time = 0;
		for (int i = 0; i < tickets.length; i++) {
			if (i < p) {
				time += Math.min(tickets[i], tickets[p]);
			} else if (i == p) {
				time += tickets[p];
			} else {
				if (tickets[i] >= tickets[p]) {
					time += (tickets[p] - 1);
				} else {
					time += tickets[i];
				}
			}
		}
		return time;
	}

}
