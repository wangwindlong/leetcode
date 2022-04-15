package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

data class Ball(var hits: Int)
fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // 产生下一个数字
        delay(100) // 等待 0.1 秒
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
//    channel.consumeEach {println("Processor #$id received $it")}
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}
suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // 在循环中接收球
        ball.hits++
        println("$name $ball")
        delay(3000) // 等待一段时间
        table.send(ball) // 将球发送回去
    }
}
fun main() = runBlocking {
    //获取前10个素数
//    var cur = numbersFrom(2)
//    repeat(10) {
//        val prime = cur.receive()
//        println(prime)
//        cur = filter(cur, prime)
//    }

    val producer = produceNumbers()
    repeat(5) { launchProcessor(it, producer) }
    delay(950)
    producer.cancel() // 取消协程生产者从而将它们全部杀死


    val table = Channel<Ball>() // 一个共享的 table（桌子）
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball(0)) // 乒乓球
    delay(10000) // 延迟 10 秒钟

    coroutineContext.cancelChildren() // 取消所有的子协程来让主协程结束
}

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) send(x++) // 从 start 开始过滤整数流
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) {
        println("filter x=$x prime=$prime")
        if (x % prime != 0) send(x)
    }
}