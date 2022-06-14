package leetcode;

import java.util.Stack;

public class s_reverseList_206 extends BaseListNode {
    //暴力法 O(n) / O(N)
    public static ListNode reverseList(ListNode head) {
        Stack<ListNode> nodes = new Stack();
        while (head != null) {
            nodes.push(head);
            head = head.next;
        }
        ListNode first = nodes.pop();
        ListNode node = first;
        while (!nodes.isEmpty()) {
            node.next = nodes.pop();
            node = node.next;
        }
        node.next = null; //注意此处需置空，否则会产生环
        return first;
    }
    //指针逆序 1 -> 2 -> 3 -> null  null <- 1 <- 2 <- 3 使用一个临时变量prev记录上一次的节点
    public static ListNode reverseList2(ListNode head) {
        return reverseList2(head, null);
    }
    //转换 前3个元素 1 -> 2 -> 3 -> 4 -> null    null <- 4 <- 1 <- 2 <- 3
    public static ListNode reverseList2(ListNode head, ListNode tail) {
        ListNode prev = tail == null ? null : tail.next;
        ListNode newTail = prev;
        ListNode cur = head;
        while (cur != newTail) {//只要还没到tail
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
//        ListNode node = s_reverseList_206.reverseList(root);
//        printNodes(node);
        printNodes(s_reverseList_206.reverseList2(root));
//        printNodes(s_reverseList_206.reverseList2(root, root.next.next));
    }
}
