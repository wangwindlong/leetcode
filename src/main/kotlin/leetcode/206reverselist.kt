package leetcode

fun reverseList(root: BaseListNode.ListNode, tail: BaseListNode.ListNode? = null): BaseListNode.ListNode? {
    var prev = if (tail == null) return null else tail.next
    var cur = root
    while (prev != tail) {
       val tmp = cur.next
        cur.next = prev
        prev = cur
        cur = tmp
    }
    return cur
}

fun main() {
    val root = BaseListNode.ListNode(0)

    root.next = BaseListNode.ListNode(1)
    root.next.next = BaseListNode.ListNode(2)
    root.next.next.next = BaseListNode.ListNode(3)
    root.next.next.next.next = BaseListNode.ListNode(4)
    BaseListNode.printNodes(reverseList(root))
}