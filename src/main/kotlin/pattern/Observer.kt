package pattern

class Subject(var name: String) {
    val observers = arrayListOf<Observer>()

    fun updateName(name: String) {
        this.name = name
        notifyUpdate()
    }

    private fun notifyUpdate() {
        for (observer in observers) {
            observer.update()
        }
    }

    fun addObserver(observer: Observer) = observers.add(observer)

}
abstract class Observer(val subject: Subject) {
    abstract fun update()
}

fun main() {
    val subject = Subject("init")
    subject.addObserver(object : Observer(subject) {
        override fun update() {
            println("Observer1 subject update new value = ${subject.name}")
        }
    })
    subject.addObserver(object : Observer(subject) {
        override fun update() {
            println("Observer2 subject update new value = ${subject.name}")
        }
    })
    subject.updateName( "world")
}
