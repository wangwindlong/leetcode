package leetcode;

import java.util.HashSet;
import java.util.Set;

//链表是否有环
public class s_cycleList_141 extends BaseListNode {
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head.next, slow = head;
        while (fast != null) {//此处可简化为fast != slow 底下第二行判断可省
            if (fast.next == null || fast.next.next == null) return false;
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
    //暴力求解法
    public static boolean hasCycle(ListNode head) {
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) return true;
            set.add(cur);
            cur = cur.next;
        }
        return false;
    }



    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);
        node1.next.next.next.next.next = node1.next.next.next;
        System.out.println(s_cycleList_141.hasCycle2(node1));
    }
}
