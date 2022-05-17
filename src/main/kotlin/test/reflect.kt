package test

import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

data class Content(var name: String = "content") {
    fun ttt(type: Int) {
        println("$this called ttt type=$type")
    }

    class Pool(val content: Content) {
        fun test() = println("pool test content=$content")
    }
    init {
        println("Content init")
        callback?.apply { Pool(this@Content).invoke() }
    }

    companion object {
        var callback : Callback<Pool>? = null

        @JvmStatic
        fun setCallbak(cc: Callback<Pool>) {
            callback = cc
        }
    }
}
fun interface Callback<in T> {
    fun T.invoke()
}

val counter = 123
fun main() {
    Content.setCallbak {
        println("setCallbak invoked this=$this")
        test()
    }
    val content = Content("张三")
    val a = content.javaClass.kotlin
    val c = Content::class.java.kotlin
    println("java.kotlin= $c")
    println("java.kotlin= ${c == a}")
    c.memberProperties.forEach {
        println(it.name)
    }

    //返回的functin 是 kcallable 可以直接调用call执行函数，KCallable是所有属性和函数的超类
    val kFunction = content::ttt
    kFunction.call(111) //不提供类型检测
    kFunction.invoke(222) //提供类型检测
    //KFunctionN是编译器生成的类型，定义了若干参数的方法，以减小kotlin-reflect.jar的大小
    //同样KProperty也可以支持KCallable
    val property0 = ::counter //顶层属性，对应KProperty0 对象
    println("counter property0=${property0.get()}")
    val property = content::name
    property.setter.invoke("fff") //call("setter")/.invoke("fff")
    val value = property.getter.call() //.call()//invoke()
    println("content::name getter value=$value")
    //成员属性，对应KProperty1对象，get(对象实例)
    val testContent = Content("成员属性")
    val property1 = Content::name
    println("Content成员属性 name property1=${property1.get(testContent)}")
//    val test = Test::class.java.getDeclaredConstructor(String::class.java).newInstance("测试1")
    val test = Class.forName("test.Test").getDeclaredConstructor(String::class.java).newInstance("测试1")
    val methods = Test::class.java.getDeclaredMethod("test", Int::class.java)
    methods.invoke(test, 1)


//    val c1 = content.javaClass.kotlin
//    c1.members.forEach {
//        println("c1 members item = $it")
//    }
}