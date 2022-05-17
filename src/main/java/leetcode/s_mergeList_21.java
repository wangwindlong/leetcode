package leetcode;

//合并两个有序链表
public class s_mergeList_21 extends BaseListNode {
    //迭代 O(n+m) / O(1)
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode cur = head;
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

    //递归  O(n+m) / O(n+m)
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1.next, list2);
            return list2;
        }
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
        node2.next.next.next.next = new ListNode(13);
//        printNodes(s_mergeList_21.mergeTwoLists(node1, node2));
        printNodes(s_mergeList_21.mergeTwoLists2(node1, node2));
    }
}
