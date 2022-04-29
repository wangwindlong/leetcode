package coroutine
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.*

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // 启动的协程数量
    val k = 1000 // 每个协程重复执行同一动作的次数
    val time = measureTimeMillis {
        coroutineScope { // 协程的作用域
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

//@Volatile // 在 Kotlin 中 `volatile` 是一个注解  无效果
var counter = 0
//val counter = AtomicInteger()
//使用单线程上下文，速度很慢
val counterContext = newSingleThreadContext("CounterContext")
val mutex = Mutex()

//actor参考 actor.kt
//结论：速度：简单数据结构(14ms) == 粗粒度线程(15ms) > mutex(302ms) > actor(470ms) > 细粒度线程限制(633ms)
fun main() = runBlocking {
//    withContext(counterContext) {// 粗粒度限制 速度更快 15ms
    withContext(Dispatchers.Default) {// 默认会启动cpu个线程，最少为2
        massiveRun {
//            counter++
//            counter.incrementAndGet() //适用于简单数据结构 14 ms
            // 将每次自增限制在单线程上下文中
            withContext(counterContext) { //细粒度单线程限制 速度很慢 633ms
                counter++
            }

            // 用锁保护每次自增
//            mutex.withLock { //302 ms
//                counter++
//            }
        }
    }
    println("Counter = $counter")
}
