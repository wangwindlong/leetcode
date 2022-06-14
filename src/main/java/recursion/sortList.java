package recursion;

import leetcode.BaseListNode;

//合并两个链表
public class sortList extends BaseListNode {
    
    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    public static ListNode sortList(ListNode head, ListNode end) {
        if (head == null) return null;
        if (head.next == end) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        //找到中点
        while (fast != end) {
            slow = slow.next;
            fast = fast.next;
            if (fast != end) {
                fast = fast.next;
            }
        }
        ListNode left = sortList(head, slow);
        ListNode right = sortList(slow, end);
        return merge(left, right);
    }

    //21简单题 合并两个有序链表
    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        cur.next = node1 == null ? node2 : node1;
        return head.next;
    }

    public static void main(String[] args) {
//        ListNode node0 = new ListNode(1);
//        node0.next = new ListNode(2);
        ListNode node1 = new ListNode(11);
        node1.next = new ListNode(12);
        node1.next.next = new ListNode(30);
        node1.next.next.next = new ListNode(20);
        node1.next.next.next.next = new ListNode(15);
        node1.next.next.next.next.next = new ListNode(5);
//        printNodes(sortList.merge(node0, node1));
        printNodes(sortList.sortList(node1));
    }
    
}
