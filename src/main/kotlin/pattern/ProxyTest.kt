package pattern

import java.lang.IllegalArgumentException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


interface IWork {
    fun dowork()
}

class Worker(val name: String) : IWork {
    override fun dowork() {
        println("$name 在工作")
    }

}

class Counter(val name: String, val duration: Int): IWork {
    var worker = Worker(name)
    override fun dowork() {
        worker.dowork()
        println("${worker.name} 工作了 $duration 个小时")
    }

}

interface IUser {
    fun addUser(name:String)
}

class Proxy(val target: Any): InvocationHandler {
    open var obj: Any? = null
    init {
        obj = Proxy.newProxyInstance(target.javaClass.classLoader, target.javaClass.interfaces, this)
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        var result: Any? = null
        method?.let {
            println("invoke called method.name=${it.name} args= ${args!![0]}")
            when (it.name) {
                "addUser" -> {
                    result = it.invoke(target, args[0])
                }
                else -> {
                    println("没有找到对应方法")
                }
            }
        }
        return result ?: throw IllegalArgumentException("返回值错误")
    }

}

fun main() {
    val worker: IWork = Counter("张三", 5)
    worker.dowork()
    (Proxy(object : IUser{
        override fun addUser(name: String) {
            println("添加一个用户：$name")
        }

    }).obj as IUser).addUser("测试")

}