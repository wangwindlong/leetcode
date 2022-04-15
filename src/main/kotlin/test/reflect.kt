package test

data class Content(val name: String = "content") {

    class Pool(val content: Content) {
        fun test() = println("pool test")
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
fun main() {
//    val content = Content()
//    val c = Content::class.java.kotlin
//    println("java.kotlin= $c")
//    val c1 = content.javaClass.kotlin
//    c1.members.forEach {
//        println("c1 members item = $it")
//    }
    Content.setCallbak {
        println("setCallbak invoked this=$this")
        test()
    }
    Content()

}