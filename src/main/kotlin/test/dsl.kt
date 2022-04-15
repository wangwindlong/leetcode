package test

import java.util.HashMap


typealias Factory<TR>  = TR.() -> Unit

class View(val name: String) {
    private var viewDelegates: HashMap<Any, ViewDelegate<*>> = HashMap()

    init {
        factory?.invoke(this@View)
    }

    abstract class ViewDelegate<T>(val viewType: Any) {
        abstract fun onCreateVH() : T
    }

    fun register(delegate: ViewDelegate<*>) {
        println("register delegate=$delegate")
        viewDelegates[delegate.viewType]
    }

    companion object {
        var factory: Factory<View>? = null
            internal set
    }
}

fun View.Companion.factory(factory: Factory<View>) {
    this.factory = factory
}
//inline fun View.Test.init((T.() -> Unit)? = null) {
//
//}

fun main() {
    View.factory {
        register(object : View.ViewDelegate<String>(0) {
            override fun onCreateVH(): String {
                return "布局1"
            }
        })
    }
    View("测试布局")
    View("测试布局2")
}
