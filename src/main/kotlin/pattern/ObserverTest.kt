package pattern


interface Observable {
    fun click()
    fun observe(observer2: Observer2)
    fun detach(observer2: Observer2)
}

interface Observer2 {
    fun onClick()
}

class Button : Observable {
    val observers: MutableList<Observer2> = arrayListOf()
    override fun click() {
        for (observer in observers) {
            observer.onClick()
        }
    }

    override fun observe(observer2: Observer2) {
        if (observers.contains(observer2)) {
            observers.remove(observer2)
        }
        observers.add(observer2)
    }

    override fun detach(observer2: Observer2) {
        observers.remove(observer2)
    }

}

class TextObserver : Observer2 {
    override fun onClick() {
        println("改变字体")
    }
}
class ColorObserver : Observer2 {
    override fun onClick() {
        println("改变颜色")
    }
}

fun main() {
    val button = Button();
    button.observe(TextObserver())
    button.observe(ColorObserver())
    button.click()
}