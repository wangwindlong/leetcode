package test


interface ITest2 {
    var property: Boolean
    var name: String
    infix fun then(other: ITest2) : ITest2

    companion object:ITest2 {
        override var property: Boolean = true
        override var name: String = "这是原始默认"
        override fun then(other: ITest2): ITest2 = other
    }
}
interface ITest {
    var property: Boolean
}

class Test(val ttt: String): ITest {
    override var property: Boolean = false
    fun test(test:Int) {
        println("test called ttt=$ttt test params=$test")
    }
}

data class DefaultParams(val ttt: String? = "hellow")

fun main() {
    val ttt = Test("测试")
    ttt.property = true
    println(ttt.property)

    val de = DefaultParams(null)
    println(de.ttt)
    val ttts = ITest2 then object : ITest2 {
        override var property: Boolean = false
        override var name: String = "这是临时产生的1"
        override fun then(other: ITest2): ITest2 {
            return this
        }
    }
    println(ttts.then(object : ITest2 {
        override var property: Boolean = false
        override var name: String = "这是临时产生的2"
        override fun then(other: ITest2): ITest2 {
            return this
        }
    }).name)
}