package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}
fun main(): Unit = runBlocking {
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

//    val thread = Thread() {
//        for ( i in 1..1000) {
//            Thread.sleep(5000)
//            println(".")
//        }
//    }
//    thread.start()

    sequence { // 序列构建器
        for (i in 1..3) {
            Thread.sleep(100) // 假装我们正在计算
            yield(i) // 产生下一个值
        }
    }.forEach { value -> println(value) }

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

    flow { // 流构建器
        for (i in 1..3) {
            delay(100) // 假装我们在这里做了一些有用的事情
            val t = Thread.currentThread()
            println("创建冷流${t.name}:${t.id}")
//            Thread.sleep(100) //此时上面的所有协程代码都会被block
            emit(i) // 发送下一个值
        }
    }.collect { value -> println(value) } // 收集这个流
    println("done")
    fun simple() = flow {
        for (i in 1..3) {
            delay(100)
            println("Emitting $i")
            emit(i)
        }
    }
    withTimeoutOrNull(250) {
        simple().collect {value -> println("with timeout value=$value")}
    }

    val test = MutableStateFlow(0)
    test.emit(1)
    test.collect() {
        println("test collect stateflow = $it")
    } //不会终止程序
//    for (i in 10086..10088) {
//        test.emit(i)
//    }
//    flow { emit(1) }.flowOn(Dispatchers.IO).collect { println(it) }
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