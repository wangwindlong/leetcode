package test

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty
// 定义包含属性委托的类
class Example {
    var p: String by Delegate()
}

// 委托的类
class Delegate {
    var aaa = ""
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性, aaa值为${aaa}"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        aaa = value
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}

//类委托
class DelegateList<T>(private val innerlist:MutableCollection<T> = HashSet()): MutableCollection<T> by innerlist {

    var count = 0
    override fun add(element: T): Boolean {
        count++
        println("add element: $element count=$count")
        return innerlist.add(element)
    }
}

/**
 * 属性代理
 */
open class PropertyAware {
    val changeSupport = PropertyChangeSupport(this)
    fun addListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }
    fun removeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }


}

class Person(val name: String, age: Int, money: Int, male: Boolean, children: String = "jay") : PropertyAware() {
    var age: Int = age
        set(value) {
            //使用自己实现的代理方式，监听属性变化，需要主动注册属性监听器
            var oldVaule = field
            field = value
            changeSupport.firePropertyChange("age", oldVaule, value)
        }

    private val observer = { prop: KProperty<*>, old: Int, newvalue: Int ->
        changeSupport.firePropertyChange(prop.name, old, newvalue)
    }
    //使用kotlin自带的代理
    var money: Int by Delegates.observable(money, observer)
    //使用自定义代理
    var male: Boolean by ObservableProperty(male, changeSupport)
    //在map中存储数据,可以使用到其他场景如存入：数据库，cookie，在访问时添加验证更改通知等
    private val _attr = hashMapOf("child" to children)
    public fun setAttr(attrName: String, value: String) {
        _attr[attrName] = value
    }
    var child: String by _attr
}

class ObservableProperty(var propValue : Boolean, var changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person, prop: KProperty<*>) : Boolean = propValue
    operator fun setValue(p: Person, prop: KProperty<*>, newVaule: Boolean) {
        val old = propValue
        propValue = newVaule
        changeSupport.firePropertyChange(prop.name, old, propValue)
    }
}


fun main(args: Array<String>) {
    val e = Example()
    println(e.p)     // 访问该属性，调用 getValue() 函数

    e.p = "Runoob"   // 调用 setValue() 函数
    println(e.p)

    val d = DelegateList<String>()
    println(d.size)
    d.add("aaa")
    println(d.size)

    val list = listOf("1", 2, "3")
    println(list.filterIsInstance<String>())

    val hash = hashMapOf<String, Any>()
    hash.put("int", 1)
    hash.put("string", "string")
    hash.put("boolean", true)
    println("hashMap int=${hash["int"]}")
    println("hashMap string=${hash["string"]}")
    println("hashMap boolean=${hash["boolean"]}")

    val person = Person("你好", 30, 1000, true)
    person.addListener {
        println("property ${it.propertyName} changed from ${it.oldValue} to ${it.newValue}")
    }
    person.age = 33
    person.money = 10000
    person.male = false
    person.setAttr("child", "jay, kate, alice")
    person.setAttr("couple", "julie")
    println(person.child)

}
//
//public fun <T> Flow<T>.onStart(
//    action: suspend FlowCollector<T>.() -> Unit
//): Flow<T> = unsafeFlow { // Note: unsafe flow is used here, but safe collector is used to invoke start action
//    val safeCollector = SafeCollector<T>(this@unsafeFlow, currentCoroutineContext())
//    try {
//        safeCollector.action()
//    } finally {
//        safeCollector.releaseIntercepted()
//    }
//    collect(this) // directly delegate
//}
//
//inline fun <T> unsafeFlow( crossinline block: suspend FlowCollector<T>.() -> Unit): Flow<T> {
//    return object : Flow<T> {
//        override suspend fun collect(collector: FlowCollector<T>) {
//            block(collector)
//        }
//    }
//}
