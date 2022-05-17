package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//回文链表 判断链表是否是回文
public class s_huiwenlink_234 extends BaseListNode {
    // 时间和空间复杂度都是 O(N)
    public static boolean isPalindrome(ListNode head) {
        ListNode cur = head;
        List<ListNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i).val != list.get(list.size() - i - 1).val) return false;
        }
        return true;
    }
    static ListNode front = null;
    //递归详解 https://leetcode.cn/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
    public static boolean isPalindrome2(ListNode head) {
        front = head; // 时间和空间复杂度都是 O(N) 效率比上面还要低
        return _isPalindrome(head);
    }
    private static boolean _isPalindrome(ListNode cur) {
        if (cur != null) {
            if (!_isPalindrome(cur.next)) return false;
            if (front.val != cur.val) return false;
            front = front.next;
        }
        return true;
    }

    //时间O(N) 和空间复杂度O(1) 但是会修改链表结果
    public static boolean isPalindrome3(ListNode head) {
        if (head == null) {
            return true;
        }
        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(10);
        root.next = new ListNode(5);
        root.next.next = new ListNode(6);
        root.next.next.next = new ListNode(7);
        root.next.next.next.next = new ListNode(11);
        root.next.next.next.next.next = new ListNode(12);
        System.out.println(s_huiwenlink_234.isPalindrome(root));
        System.out.println(s_huiwenlink_234.isPalindrome2(root));
        System.out.println(s_huiwenlink_234.isPalindrome3(root));
    }
}
