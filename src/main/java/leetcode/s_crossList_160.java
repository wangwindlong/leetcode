package leetcode;

import java.util.HashSet;
import java.util.Set;

//判断两个链表是否交叉
public class s_crossList_160 extends BaseListNode {
    //暴力法，复杂度 O(m+n) O(m+n)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur1 = headA;
        while (cur1 != null) {
            set.add(cur1);
            cur1 = cur1.next;
        }
        cur1 = headB;
        while (cur1  != null) {
            if (set.contains(cur1)) return cur1;
            cur1 = cur1.next;
        }
        return cur1;
    }

    //双指针法 每个指针分别遍历 两个链表，然后判断是否有一样的  O(m+n)  O(1)
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        //注意这个条件 如果没有交叉 当两个指针分别遍历完m+n后两个都是null，分别指向对方的末节点
        while (cur1 != cur2) { //如果有交叉，则cur1 == cur2 返回该值即可
            cur1 = cur1 == null ? headB : cur1.next; //遍历完A 开始遍历B
            cur2 = cur2 == null ? headA : cur2.next; //遍历完B 开始遍历A
        }
        return cur1; //注意此值， 返回当前的指针，如果有交叉则指向当前的值，或者返回末节点 null
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
