package test


data class Data(val page: Int, var name: String) {

}

class TestData(data: Data) : BaseTest(Params(data)) {
    data class DataInner(val page: Int, val name: String)
}

class TestData2(data: Data) : BaseTest(Params(data)) {
    data class DataInner2(val page: Int, val name: String)
}

open class BaseTest(val param: Params) {
}

data class Params(val data: Data)


fun main() {
    //结论 如果用外部 data class 则内容一样(不管是val还是var)都会相等，如果不用data 则每次创建都是不同的对象
    val data = Data(1, "TestData")
    val testdata = TestData(data)
    val testdata2 = TestData2(Data(1, "TestData"))
    println("testdata=${testdata.param}")
    println("testdata2=${testdata2.param}")
    println(testdata.param == testdata2.param)
}