package leetcode




class LRUCache(private val capacity: Int) {
    internal class Node() {
        var key = 0
        var value = 0
        var prev: Node? = null
        var next: Node? = null

        constructor(_key: Int, _value: Int) : this() {
            key = _key
            value = _value
        }
    }

    private val cache: MutableMap<Int, Node> = HashMap()
    private var size = 0
    private val head: Node = Node()
    private val tail: Node = Node()

    init {
        // 使用伪头部和伪尾部节点
        head.next = tail
        tail.prev = head
    }

    operator fun get(key: Int): Int {
        val node = cache[key] ?: return -1
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        val node = cache[key]
        if (node == null) {
            if (size >= capacity) {
                val remove = tail.prev!!
                removeNode(remove)
                cache.remove(remove.key)
                size --
            }
            val newNode = Node(key, value)
            // 添加至双向链表的头部
            addToHead(newNode)
            // 添加进哈希表
            cache[key] = newNode
            ++size
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value
            moveToHead(node)
        }
    }

    private fun addToHead(node: Node) {
        node.prev = head
        node.next = head.next
        head.next!!.prev = node
        head.next = node
    }

    private fun removeNode(node: Node) {
        node.prev!!.next = node.next
        node.next!!.prev = node.prev
    }

    private fun moveToHead(node: Node) {
        removeNode(node)
        addToHead(node)
    }
}


fun main() {
    val lruCache = LRUCache(2)
    println(lruCache.put(1, 1))
    println(lruCache.get(1))
    println(lruCache.put(2, 2))

    println(lruCache.get(1))
    println(lruCache.put(3, 3))
    println(lruCache.get(2))
    println(lruCache.put(4, 4))
    println(lruCache.get(1))
    println(lruCache.get(3))
    println(lruCache.get(4))

}