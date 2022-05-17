package leetcode;

//合并多个升序链表
public class h_mergeList_23 extends BaseListNode {
    //暴力求解，O(k^2 * n) / O(1)   103ms(16.33%)/43MB(81.9%)
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = s_mergeList_21.mergeTwoLists(res, lists[i]);
        }
        return res;
    }

    //二分暴力求解(分治？)，O(kn×logk) / O(logk)， 类似方法见 binarysum 二路递归 1ms(100%)/43.5MB(25.1%)
    public static ListNode mergeKListsBinary(ListNode[] lists) {
        if (lists == null || lists.length <= 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    static ListNode merge(ListNode[] lists, int start, int end) {
        if (start >= end) {
            return lists[start];
        } else if (start == end - 1) {
            return s_mergeList_21.mergeTwoLists(lists[start], lists[end]);
        }
        int mid = (start + end) / 2;
        ListNode l1 = merge(lists, start, mid - 1);
        ListNode l2 = merge(lists, mid, end);
        //process
        return s_mergeList_21.mergeTwoLists(l1, l2);
    }
//    //优先队列迭代
//    public static ListNode mergeKLists3(ListNode[] lists) {
//        if (lists == null || lists.length == 0) return null;
//
//    }

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
        ListNode node3 = new ListNode(20);
        node3.next = new ListNode(21);
        node3.next.next = new ListNode(22);
        node3.next.next.next = new ListNode(23);
        node3.next.next.next.next = new ListNode(25);
        printNodes(h_mergeList_23.mergeKListsBinary(new ListNode[] {new ListNode(140), node1, node2, node3, new ListNode(141)}));
    }
}
