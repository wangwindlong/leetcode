package leetcode;

import java.util.Arrays;
import java.util.List;

//逆变k个一组的链表
public class h_reverseK_25 extends BaseListNode {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(); //记录新的头部
        hair.next = head;
        int count = 0;
        ListNode cur = head; //遍历的指针
        ListNode start = hair;// k个轮回的起始点的前一个

        while (cur != null) {
            if (count++ == k - 1) {
                ListNode[] node = reverseKGroup(start.next, cur);
                start.next = node[0];//将逆变后的头赋给上一次的next指针
                start = node[1];//逆变后的尾节点 设置为下一次的起始点
                cur = start.next;//继续遍历下一个循环
                count = 0;
            } else {
                cur = cur.next;
            }
        }
        return hair.next;
    }

    public static ListNode[] reverseKGroup(ListNode node, ListNode tail) {
        ListNode prev = tail == null ? tail : tail.next; //新链表的尾
        ListNode newTail = prev;
        ListNode cur = node;
        while (cur != newTail) { //只要还没到tail
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return new ListNode[]{tail, node}; //返回逆变后的 头和尾
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);
        printNodes(h_reverseK_25.reverseKGroup(node1, 3));
    }
}
