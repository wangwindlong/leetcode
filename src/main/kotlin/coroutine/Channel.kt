package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        println("close")
//        delay(1000)
        channel.close() // 我们结束发送
    }
    // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
    for (y in channel) println(y)
    println("Done!")

//    val mutex = Mutex()
//    mutex.withLock {
//
//    }
    val internalflow = MutableStateFlow(0)
    val outerFlow = internalflow.refCount()
    GlobalScope.launch {
        outerFlow.collect() {
            val t = Thread.currentThread()
            println("outer collect current thread=${t.name}:${t.id}")
            println("outer flow collect ${it}")
        }
    }
    GlobalScope.launch {
        internalflow.collect() {
            val t = Thread.currentThread()
            println("inner collect current thread=${t.name}:${t.id}")
            println("internalflow collect ${it}")
        }
    }
    launch {
        val t = Thread.currentThread()
        println("emit current thread=${t.name}:${t.id}")
        internalflow.value = 1
        delay(1000)
        internalflow.value = 2
    }

    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // 收集这个流
    simple().collect() { value -> println(value) }
    println()

}
fun simple() = flow { // 流构建器
    for (i in 1..3) {
        delay(100) // 假装我们在这里做了一些有用的事情
        emit(i) // 发送下一个值
    }
}

internal fun <T> StateFlow<T>.refCount(): StateFlow<T> =
    RefCountStateFlow(this)

private class RefCountStateFlow<T>(
    private val upStream: StateFlow<T>
) : StateFlow<T> {

    override val replayCache: List<T>
        get() = upStream.replayCache

    override val value: T
        get() = upStream.value

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        try {
            upStream.collect(collector)
        } finally {
        }
    }
}