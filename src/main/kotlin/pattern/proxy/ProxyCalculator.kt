package pattern.proxy

import java.lang.reflect.Proxy

class ProxyCalculator {

    companion object {
        @JvmStatic
        fun getInstance(c: Class<*>): Calculator {
            //这种写法需要传 实际的接口实现过来
//            return Proxy.newProxyInstance(c.classLoader, c.interfaces) { proxy, method, args ->
            //这种写法不需要，直接用类名就可以
            return Proxy.newProxyInstance(c.classLoader, arrayOf(c)) { proxy, method, args ->
                if (method.name == "add") {
                    return@newProxyInstance args.get(0) as Int + args.get(1) as Int
                } else {
                    return@newProxyInstance args.get(0) as Int - args.get(1) as Int
                }
            } as Calculator
        }
    }
}

fun main() {
    println(ProxyCalculator.getInstance(Calculator::class.java).add(1, 2))
}