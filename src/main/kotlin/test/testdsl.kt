package test

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock

open class TAG(val tagname: String) {
    val child = mutableListOf<TAG>()

    fun <T: TAG> doInit(c : T, init: T.() -> Unit) {
        c.init()
        child.add(c)
    }

    override fun toString(): String {
        return "<$tagname> ${child.joinToString(" ")} </$tagname>"
    }
}
fun createHtml() = table {
    tr {
        item {

        }
    }
}

fun table(init: Table.() -> Unit): Table {
    return Table().apply(init)
}

class Table : TAG("table") {
    fun tr(init: Tr.() -> Unit) = doInit(Tr(), init)
}

class Tr : TAG("tr") {
    fun item(init: Item.() -> Unit) = doInit(Item(), init)
}

class Item : TAG("item") {

}

inline fun<T> sync(lock: Lock, test: String, block: (String) -> T):T {
    lock.lock()
    try {
        return block(test)
    } finally {
        lock.unlock()
    }
}

fun main() {
    println(createHtml())
    println(0x0 and 0x1)
    println(0x10 and 0x1)

    val l = ReentrantLock()
    println("lambda start")
    sync(l, "555") { string ->
        println("call in block string=$string")
    }
    println("lambda finised")
}