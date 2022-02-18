package pattern

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


fun main() {
    val player = PlayerProxy("小明", "20")
    player.play()
}