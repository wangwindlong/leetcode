package test

object TestObject {

    class KotlinShop<T> {
        fun buy() : T {
            return null as T
        }

        fun refund(item: T) : Float {
            return 1f
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
       val shop: KotlinShop<out Any>  = KotlinShop<String>()
       val shop2: KotlinShop<in String>  = KotlinShop<Any>()
       val list: MutableList<out Any>  = ArrayList<String>() //

//       val list2: List<in String>  = ArrayList<Any>() //不能这么写 因为List已经有out
    }
}