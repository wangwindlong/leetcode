package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception

inline fun <T> unsafeFlow(crossinline block: suspend FlowCollector<T>.() -> Unit): Flow<T> {
    return object : Flow<T> {
        override suspend fun collect(collector: FlowCollector<T>) {
            collector.block()
        }
    }
}

public fun <T> Flow<T>.onStart(
    action: suspend FlowCollector<T>.() -> Unit
): Flow<T> = unsafeFlow { // Note: unsafe flow is used here, but safe collector is used to invoke start action
//    val safeCollector = SafeCollector<T>(this, currentCoroutineContext())
    try {
        this.action()
    } finally {
//        safeCollector.releaseIntercepted()
    }
    collect(this) // directly delegate
}


@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val flow1 = flow {
        for (i in 1..10) {
            delay(100)
            emit(i)
        }
    }.mapNotNull { it * 2 }
    val flow2 = flow {
        flow1.collect(object : FlowCollector<Int> {
            override suspend fun emit(value: Int) {
                this@flow.emit(value)
            }
        })
    }
    val flow3 = flow {
        flow2.collect(object : FlowCollector<Int> {
            override suspend fun emit(value: Int) {
                this@flow.emit("__ $value __")
            }
        })
    }
    flow3.collect {
        println("flow3 on emit value=$it")
    }

    flow1.collect(object : FlowCollector<Int> {
        override suspend fun emit(value: Int) {
            println("on emit value=$value")
        }
    })
    val result = flow1.conflate().onEach { delay(1000) }.toList()
    println(result)
    flow1.collectLatest { println(it) }


    launch {
        val stateFlow = MutableStateFlow<Int>(0)
        for (i in 1..30) {
            delay(100)
            stateFlow.value = (i)
        }
        stateFlow.collect() {
            println("stateFlow collect $it")
            return@collect
        }
    }

//    //单次throw后后续便不再执行 直接抛出异常
//    repeat(3) {
//        println("do something try : $it")
//        if (it == 0) throw Exception("test error")
//    }

    //replay 代表下次collect的时候能获取到的数据个数
    launch() {
        val hot = MutableSharedFlow<Int>(replay = 2)
        val downFlow = hot.distinctUntilChanged().flatMapLatest {
            flow {
                println("downFlow flow $it")
                emit(it * 2)
            }
        }.distinctUntilChanged()
        hot.tryEmit(1)
        hot.tryEmit(2)
        delay(100)
        downFlow.collect {
            println("downFlow collected $it")
        }

    }
    return@runBlocking
}