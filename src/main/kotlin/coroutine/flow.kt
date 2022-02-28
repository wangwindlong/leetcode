package coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowTest {
    public fun main() {
        val flow = flow {
            for (i in 1..30) {
                delay(100)
                emit(i)
            }
        }
    }
}

