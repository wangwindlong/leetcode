package test

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class testrunblock {

}
fun main() {
    runBlocking<Unit> {
        // this: CoroutineScope
        launch {
            // 在 runBlocking 作用域中启动一个新协程
            delay(1000L)
            println("World! ${Thread.currentThread().name} = ${Thread.currentThread().id}")
        }
        println("Hello, ${Thread.currentThread().name}= ${Thread.currentThread().id}")
    }
    println("frff, ${Thread.currentThread().name}= ${Thread.currentThread().id}")
}