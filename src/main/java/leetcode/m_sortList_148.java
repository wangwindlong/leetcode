package leetcode;

//链表排序
public class m_sortList_148 extends BaseListNode {
    //分治，O(nlogn) O(logn)
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
        ListNode fast = head;
        while (fast != end) { //用快慢指针找到中间点，然后从两头分治递归
            slow = slow.next;
            fast = fast.next;
            if (fast != end) fast = fast.next;
        }
        ListNode l1 = sortList(head, slow);
        ListNode l2 = sortList(slow, end);
        return s_mergeList_21.mergeTwoLists(l1, l2);
    }

    //自底向上归并排序 O(nlogn) O(1)

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(20);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(-4);
        node1.next.next.next.next = new ListNode(5);
        printNodes(m_sortList_148.sortList(node1));
    }

}
