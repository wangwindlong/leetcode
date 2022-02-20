/*
 * @lc app=leetcode.cn id=146 lang=kotlin
 *
 * [146] LRU 缓存
 */

// @lc code=start
class LRUCache(val capacity: Int) {
    class Node {
        var key: Int = 0
        var value: Int = -1
        var prev: Node? = null
        var next: Node? = null

        constructor()

        constructor(_key: Int, _value: Int) {
            key = _key
            value = _value
        }
    }

    val cache = HashMap<Int, Node>()
    var size = 0
    val head = Node()
    val tail = Node()

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = cache[key] ?: return -1
        //如果存在 先删除node
        removeNode(node)
        //再插入到头部
        addToHead(node)
        return node.value
    }

    private fun addToHead(node: Node) {
        node.next = head.next
        node.prev = head
        head.next!!.prev = node
        head.next = node
    }

    private fun removeNode(node: Node) {
        node.next!!.prev = node.prev
        node.prev!!.next = node.next
    }

    private fun moveToHead(node: Node) {
        removeNode(node)
        addToHead(node)
    }

    fun put(key: Int, value: Int) {
        val node = cache[key]
        if (node == null) { //如果不存在则插入到头部，数量++
            if (size < capacity) { //未达到容量最大值
                size++
            } else {
                val removed = tail.prev!!
                //删除最后一个元素
                removeNode(removed)
                cache.remove(removed.key)
            }
            //插入到头部
            val newNode = Node(key, value)
            cache[key] = newNode
            addToHead(newNode)
        } else { //如果存在，则更新值
            node.value = value
            moveToHead(node)
        }
    }

}


class LRUCache2(val capacity: Int): LinkedHashMap<Int, Int>(capacity, 0.75f, true) {

    override fun get(key: Int): Int {
        return super.getOrDefault(key, -1)
    }

    override fun removeEldestEntry(eldest: Map.Entry<Int?, Int?>?): Boolean {
        return size > capacity
    }
}


fun main() {

    /**
     * 执行用时：972 ms, 在所有 Kotlin 提交中击败了46.34%的用户
    内存消耗：144.5 MB, 在所有 Kotlin 提交中击败了31.71%的用户
     */
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

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
// @lc code=end

