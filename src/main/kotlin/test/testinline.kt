package test

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Hour(private val v: Int) {
    fun toMinutes() = Minute(v * 60)
}

class Minute(val v: Int) {
    fun toSecond() = v * 60
}

@JvmInline
value class Hour2(private val v: Int) {
    init {
//        println(v)
    }

    fun toMinutes() = Minute2(v * 60)
}

class Minute2(val v: Int) {
    fun toSecond() = v * 60
}

@OptIn(ExperimentalTime::class)
fun main() {
    //see https://bytedance.host/210323kotlin-ex3/
    println( measureTime {
        var hour = Hour(1)
        repeat(600_000_000) {
            hour = Hour(it)
        }
        hour.toMinutes().v
    })
    println( measureTime {
        var hour = Hour2(1)
        repeat(600_000_000) {
            hour = Hour2(it)
        }
        hour.toMinutes().v
    })
    /**
     * 1.406991280s
     * 3.063247ms
     */
}