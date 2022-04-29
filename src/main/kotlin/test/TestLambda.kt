package test

import java.util.*
import kotlin.random.Random


typealias T = (Int) -> Int

class Testtt(val a: Int, val b: Int, val c: (T) -> Unit) {
    fun test() {
        val aa = LinkedList<Int>()
    }
}

fun main() {
    val ran = Random.nextInt(10, 100)
    var res = 0
    val ttt = Testtt(5, 6) { l ->
        res = l(ran)
    }
    val lamb: T = { s ->
        println("in c s = $s")
        s * s
    }
    val lamb2: T = { s ->
        println("in c s = $s")
        s + s
    }
    ttt.c(lamb)
    println(res)
    ttt.c(lamb2)
    println(res)
}