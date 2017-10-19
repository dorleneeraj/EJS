package data.structure.linkedlist;

// Definition for singly-linked list.
class ListNode {
	public int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}

}

public class Solution {
	public int lPalin(ListNode A) {
		ListNode B = null;
		ListNode helper = A;
		while (null != helper) {
			ListNode temp = helper;
			if (null == B) {
				B = new ListNode(temp.val);
			} else {
				int a = helper.val;
				ListNode T = new ListNode(a);
				T.next = B;
				B = T;
			}
			helper = helper.next;
		}

		boolean isPalindrome = true;
		while (null != A.next) {
			if (A.val != B.val) {
				isPalindrome = false;
				break;
			}
			A = A.next;
			B = B.next;
		}

		return isPalindrome ? 1 : 0;
	}

	public static void main(String[] args) {
		ListNode A = new ListNode(1);
		ListNode B = new ListNode(1);
		ListNode C = new ListNode(1);
		ListNode D = new ListNode(1);
		A.next = B;
		B.next = C;
		C.next = D;
		System.out.println(new Solution().lPalin(A));
		System.out.println(new Solution().deleteDuplicates(A));
	}

	public ListNode deleteDuplicates(ListNode a) {
		ListNode p1 = a;
		ListNode p2 = null;

		while (null != p1.next) {
			p2 = p1.next;
			int val1 = p1.val;
			int val2 = p2.val;

			while (val1 == val2) {
				p2 = p2.next;
				if (null != p2) {
					val2 = p2.val;
				} else {
					break;
				}
			}

			p1.next = p2;
			if (null != p1.next) {
				p1 = p1.next;
			}

		}

		return a;
	}

}
