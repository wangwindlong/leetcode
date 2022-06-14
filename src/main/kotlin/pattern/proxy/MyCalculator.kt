package pattern.proxy

class MyCalculator : Calculator {
    override fun add(i: Int, j: Int): Int {
        return i + j
    }

    override fun minus(i: Int, j: Int): Int {
        return i - j
    }
}