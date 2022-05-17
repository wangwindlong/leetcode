package leetcode;

public class BaseListNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "val=" + val;
        }
    }

    public static void printNodes(ListNode root) {
        ListNode head = root;
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
        System.out.println("遍历结束，根节点为：" + root);
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(10);
        node2.next.next = new ListNode(11);
        node2.next.next.next = new ListNode(12);
        node2.next.next.next.next = new ListNode(130);

        printNodes(s_crossList_160.getIntersectionNode2(node1, node1));
    }
}
