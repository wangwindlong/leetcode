import java.util.*

class queuetest {

}

data class A(val x:String, val y:String){
}
fun main() {
    val queue = LinkedList<String>()
    queue.add("1")
    queue.add("2")
    queue.add("3")
    queue.add("4")
    println(queue)

    val top = queue.pop()
    println(top)
    println(queue)

    val top2 = queue.peek()
    println(top2)
    println(queue)

    while (queue.size > 0) {
        println(queue.poll())
    }
    println(queue)

    val a = A("x", "y")
    println(a)
    println(a.copy(y="yy"))

}