package pattern

abstract class BaseDrawable(val color: Color) {
    abstract fun draw()
}

interface Color {
    fun getColor(): String
}

class Drawable(color: Color) : BaseDrawable(color) {
    override fun draw() {
        println("用 ${color.getColor()} 来画图")
    }

}

class CircleDrawable(color: Color) : BaseDrawable(color) {
    override fun draw() {
        println("用 ${color.getColor()} 来画圆形")
    }
}

class RedColor : Color {
    override fun getColor(): String {
        return "红色"
    }
}
class BlueColor : Color {
    override fun getColor(): String {
        return "蓝色"
    }
}

fun main() {
    val red = RedColor()
    val blue = BlueColor()
    Drawable(red).draw()
    CircleDrawable(blue).draw()
}