package pattern

//https://www.runoob.com/design-pattern/adapter-pattern.html 第一条评论

interface Card {
    fun readCard():String
    fun writeCard(texg:String):Int
}
class SDCard(val name:String):Card {

    override fun readCard(): String {
        return "hello from $name sdcard"
    }

    override fun writeCard(texg: String): Int {
        return 1
    }

}

interface SDCardModule {
    fun readSDCard(card: Card):String
}

class Computer : SDCardModule {

    override fun readSDCard(card: Card): String {
        return card.readCard()
    }

}


interface TFCard {
    fun readTFCard():String
}

class TFCardModule(val name:String):TFCard {
    override fun readTFCard(): String {
        return "hello from tfcard $name"
    }

}

class TFCardAdapter(val tfCard: TFCard):Card {
    override fun readCard(): String {
        return tfCard.readTFCard()
    }

    override fun writeCard(texg: String): Int {
        return 2
    }

}


fun main() {
    val computer = Computer()
    val sdcard = SDCard("游戏")
    println(computer.readSDCard(sdcard))

    val tfAdapter = TFCardAdapter(TFCardModule("相机卡"))
    println(computer.readSDCard(tfAdapter))
}