package data.structure.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval other = (Interval) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}

}

public class MergeIntervals {
	public static void main(String[] args) {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		// (296229, 649172), (1945510, 2253924), (2442397, 2609869), (3382475,
		// 3638791), (4269170, 4519990), (4542304, 4951235), (5059919, 5209063),
		// (5737903, 6287103), (6324257, 7086712), (7341605, 7451478), (7794892,
		// 8682598), (9049295, 9168973), (9741848, 11161992), (11360009,
		// 11492811), (11980922, 13706312), (14580986, 14609481), (14833579,
		// 14911113), (16329220, 16516134), (17065845, 17710634), (17891017,
		// 18013942), (18034155, 18124840), (19656628, 19732725), (19923918,
		// 22132050), (22289812, 22407157), (23326802, 23356057), (23513524,
		// 23725035), (23740420, 23849872), (23892519, 24106649), (24650474,
		// 25327340), (25512554, 25708098), (26113607, 26148510), (26461570,
		// 28107170), (28503351, 28649439), (28823773, 29041243), (29650842,
		// 29844820), (30862352, 31771585), (33554194, 35406294), (36369287,
		// 37368060), (37742037, 37954121), (39314988, 40081405), (40130310,
		// 40376591), (40564668, 41502228), (41611945, 41640975), (41868418,
		// 41901380), (42293370, 42658025), (42801953, 43197735), (44301349,
		// 44435389), (44458313, 46093739), (47944077, 48154966), (48560249,
		// 48862030), (50346057, 50703389), (51044695, 51205625), (51224599,
		// 51946037), (52683008, 52869497), (53714142, 53817277), (54244776,
		// 54338172), (54424724, 54638760), (55449198, 55462854), (56110630,
		// 56331932), (56989072, 57519608), (57694731, 57715971), (58052278,
		// 58933241), (58939215, 59760287), (60019840, 60845981), (60868853,
		// 61460319), (61727178, 61886565), (62020630, 62566864), (62854733,
		// 62960246), (64242476, 64472889), (67024730, 70150161), (70318456,
		// 70340388), (71984531, 72430821), (73082931, 73382670), (73575721,
		// 74145720), (75237846, 78515227), (78858679, 79377991), (79432524,
		// 79906523), (80603396, 82356394), (83781317, 84254302), (86312813,
		// 87852050), (88710332, 89039960), (89242771, 89543639), (89869003,
		// 90376285), (90401553, 90823077), (91291000, 93022152), (93836498,
		// 94228052), (94750831, 95148291), (95312139, 95858829), (95914619,
		// 96651283), (97659785, 98224801), (98559082, 99273652)
		// intervals.add(new Interval(1, 2));
		// intervals.add(new Interval(3, 5));
		// intervals.add(new Interval(6, 7));
		// intervals.add(new Interval(8, 10));
		// intervals.add(new Interval(12, 16));
		// intervals.add(new Interval(2141136, 6363582));
		// intervals.add(new Interval(49844342, 69670172));
		// intervals.add(new Interval(75717793, 86352601));
		intervals.add(new Interval(3, 5));
		intervals.add(new Interval(8, 10));

		// System.out.println(intervals);
		System.out.println(new MergeIntervals().insert2(intervals,
				new Interval(10, 3)));
		// System.out.println(intervals);

	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {

		if (newInterval.end < newInterval.start) {
			int temp = newInterval.end;
			newInterval.end = newInterval.start;
			newInterval.start = temp;
		}
		boolean success = false;

		if (intervals.isEmpty()) {
			intervals.add(newInterval);
			success = true;
		}
		for (int i = 0; i < intervals.size(); i++) {
			Interval oldInterval = intervals.get(i);
			if (newInterval.start >= oldInterval.start
					&& newInterval.start < oldInterval.end) {
				if (newInterval.end <= oldInterval.end) {
					// mergeInterval(intervals, i, newInterval);
					// actually there is no need to merge. The new interval is
					// already merged.
					success = true;
					break;
				} else if (i == intervals.size() - 1) {
					mergeInterval(intervals, i, newInterval);
					success = true;
					break;
				} else if (intervals.get(i + 1).start > newInterval.end) {
					mergeInterval(intervals, i, newInterval);
					success = true;
					break;
				} else {
					// merge more than one intervals
					for (int j = i + 1; j < intervals.size(); j++) {
						Interval interval2 = intervals.get(j);
						if (interval2.end >= newInterval.end) {
							mergeInterval(intervals, i, j, newInterval);
							success = true;
							break;
						}
					}
					break;
				}
			} else if (newInterval.start < oldInterval.start) {
				if (newInterval.end < oldInterval.start) {
					if (i == 0) {
						intervals.add(0, newInterval);
					} else {
						intervals.add(i, newInterval);
					}
					success = true;
					break;
				} else if (newInterval.end <= oldInterval.end) {
					mergeInterval(intervals, i, newInterval);
					success = true;
					break;
				} else if (i == intervals.size() - 1) {
					mergeInterval(intervals, i, newInterval);
					success = true;
					break;
				} else if (intervals.get(i + 1).start > newInterval.end) {
					mergeInterval(intervals, i, newInterval);
					success = true;
					break;
				} else {
					// merge more than one intervals
					for (int j = i + 1; j < intervals.size(); j++) {
						Interval interval2 = intervals.get(j);
						if (interval2.end >= newInterval.end) {
							mergeInterval(intervals, i, j, newInterval);
							success = true;
							break;
						}
					}
					mergeInterval(intervals, 0, intervals.size() - 1,
							newInterval);
					success = true;
					break;
				}
			}
		}

		if (!success) {
			intervals.add(newInterval);
		}
		return intervals;
	}

	private void mergeInterval(ArrayList<Interval> intervals, int i, int j,
			Interval newInterval) {
		Interval interval1 = intervals.get(i);
		Interval interval2 = intervals.get(j);
		List<Interval> intervalsToBeRemoved = new ArrayList<Interval>();
		for (int k = i + 1; k <= j; k++) {
			intervalsToBeRemoved.add(intervals.get(k));
		}
		for (Interval iVal : intervalsToBeRemoved) {
			intervals.remove(iVal);
		}
		int smallest = interval1.start < newInterval.start ? interval1.start
				: newInterval.start;
		int largest = interval2.end > newInterval.end ? interval2.end
				: newInterval.end;

		interval1.start = smallest;
		interval1.end = largest;
	}

	public void mergeInterval(ArrayList<Interval> intervals, int parentIndex,
			Interval childInterval) {

		Interval parentInterval = intervals.get(parentIndex);
		if (childInterval.end > parentInterval.end) {
			parentInterval.end = childInterval.end;
		}
		if (childInterval.start < parentInterval.start) {
			parentInterval.start = childInterval.start;
		}
	}

	public ArrayList<Interval> insert2(ArrayList<Interval> intervals,
			Interval newInterval) {
		int size = intervals.size();
		if (size == 0) {
			intervals.add(newInterval);
			return intervals;
		}
		boolean inserted = false;
		for (int i = 0; i < size; i++) {
			Interval iIter = intervals.get(i);
			if (iIter.start > newInterval.start) {
				intervals.add(i, newInterval);
				inserted = true;
				break;
			}
		}
		if (!inserted) {
			intervals.add(newInterval);
		}
		// merge and return
		return merge(intervals);
	}

	public ArrayList<Interval> merge(ArrayList<Interval> a) {
		Stack<Interval> st = new Stack<Interval>();
		st.push(a.get(0));
		for (int i = 1; i < a.size(); i++) {
			Interval top = st.peek();
			Interval iterInt = a.get(i);
			if (top.end >= iterInt.start) {
				Interval newInt = mergeIntervals(top, iterInt);
				st.pop();
				st.push(newInt);
			} else {
				st.push(iterInt);
			}
		}
		ArrayList<Interval> answer = new ArrayList<Interval>();
		while (!st.isEmpty()) {
			answer.add(st.pop());
		}
		Collections.reverse(answer);
		return answer;
	}

	public Interval mergeIntervals(Interval a, Interval b) {
		int min = Math.min(a.start, b.start);
		int max = Math.max(a.end, b.end);
		return (new Interval(min, max));
	}

}
