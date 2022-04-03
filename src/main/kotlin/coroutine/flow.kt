package coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class Flow {

    suspend fun test() {
        coroutineScope {

        }
    }
}

fun main() = runBlocking {
    val flow = flow {
        for (i in 1..30) {
            delay(100)
            emit(i)
        }
    }
    val result = flow.conflate().onEach { delay(1000) }.toList()
    println(result)
    flow.collectLatest { println(it) }


    val stateFlow = MutableStateFlow<Int>(0)
    for (i in 1..30) {
        delay(100)
        stateFlow.value = (i)
    }
    stateFlow.collect() {
        println("stateFlow collect $it")
    }

    return@runBlocking
}