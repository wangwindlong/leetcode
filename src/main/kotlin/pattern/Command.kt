package pattern

interface Order {
    fun execute()
}

data class Stock(val name:String, val quantity:Int) {
    fun buy() {
        println("buy $name stock, quantity: $quantity")
    }

    fun sell() {
        println("sell $name stock, quantity: $quantity")
    }
}

class BuyStock(val stock: Stock):Order {
    override fun execute() {
        stock.buy()
    }
}

class SellStock(val stock: Stock):Order {
    override fun execute() {
        stock.sell()
    }
}

class Broker() {
    val orders = mutableListOf<Order>()
    fun takeOrder(order: Order) {
        orders.add(order)
    }
    fun placeOrder() {
        for (order in orders) {
            order.execute()
        }
        orders.clear()
    }
}

fun main() {

    val broker = Broker()

    broker.takeOrder(BuyStock(Stock("兴业银行", 100)))
    broker.takeOrder(SellStock(Stock("兴业银行", 10)))

    broker.placeOrder()
}