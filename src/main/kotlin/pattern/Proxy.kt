package pattern

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


interface IPlayer {
    fun play()
}

class RealPlayer(val name: String) : IPlayer {

    override fun play() {
        println("玩家 $name 在玩游戏")
    }

}

class PlayerProxy(val name: String, val duration: String) : IPlayer {
    var count: Int = 0
    val player: IPlayer = RealPlayer(name)

    override fun play() {
        player.play()
        println("花了 $duration 分钟帮 $name 打怪升级")
    }

}

internal interface Hello {
    fun morning(name: String?)
}

interface UserService {
    fun addUser(name:String)
    fun updateUser()
}

class UserServiceImpl : UserService {
    override fun addUser(name:String) {
        println("添加一个用户:$name")
    }

    override fun updateUser() {
        println("更新一个用户")
    }
}

class UserServiceImpl2 {
    fun addUser(name:String) {
        println("添加一个用户:$name")
    }

    fun updateUser() {
        println("更新一个用户")
    }
}

class DynamicProxy : InvocationHandler {

    private lateinit var target: Any // 目标对象

    public fun bind(target: Any): Any {
        this.target = target
        return Proxy.newProxyInstance(target.javaClass.classLoader, target.javaClass.interfaces, this)
    }

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
//        val result: Any? = if (args?.isNotEmpty() == true) {
//            method.invoke(target, args)
//        } else {
//            method.invoke(target)
//        }
        val result : Any? = if (method.name.equals("addUser")) {
            method.invoke(target, args!![0])
        } else {
            method.invoke(target)
        }
        println("拿个小本本记录一下")
        return result
    }

}


fun main() {
    //静态代理，按实现方式可分为 远程代理 虚拟代理 安全代理 智能指引
    val player = PlayerProxy("小明", "20")
    player.play()

    //动态代理1
    val handler = InvocationHandler { proxy, method, args ->
        println(method)
        if (method.name.equals("morning")) {
            println("Good morning, " + args[0])
        }
        null
    }
    val hello = Proxy.newProxyInstance(
        Hello::class.java.classLoader, arrayOf<Class<*>>(Hello::class.java),  // 传入要实现的接口
        handler
    ) as Hello // 传入处理调用方法的InvocationHandler
    hello.morning("Bob")

    //动态代理2 增强方法
    val dynamicProxy = DynamicProxy()
    val userService = dynamicProxy.bind(UserServiceImpl()) as UserService
    userService.addUser("ss")
    userService.updateUser()

    //动态代理3 cglib动态代理 不需要target实现接口

    val cgLibProxy = CGLibProxy()
    val userService2 = cgLibProxy.bind(UserServiceImpl2()) as UserServiceImpl2
    userService2.addUser("aa")
    userService2.updateUser()
}

class CGLibProxy : MethodInterceptor {
    private var target // 目标对象
            : Any? = null

    fun bind(target: Any?): Any {
        this.target = target
        val enhancer = Enhancer()
        //设置父类
        enhancer.setSuperclass(this.target!!.javaClass)
        //设置回调函数
        enhancer.setCallback(this)
        //创建子类(代理对象)
        return enhancer.create()
    }

    @Throws(Throwable::class)
    override fun intercept(obj: Any, method: Method, args: Array<Any>, methodProxy: MethodProxy): Any {
        val result = methodProxy.invokeSuper(obj, args)
        println("拿个小本本记录一下")
        return result
    }
}