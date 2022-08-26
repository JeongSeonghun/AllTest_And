package com.jsh.kr.alltestkt

import org.junit.Test

import org.junit.Assert.*

class BaseClassTest {

    @Test
    fun test() {
        println("temp")
    }

    @Test
    fun varAndVal() {
        var variableData = 1
        val value = 11

        variableData = 2
//        value = 12 // compile error

        var varClass = CkTest()
        val valClass = CkTest()

        varClass.age = 2
        valClass.age = 3

        varClass = CkTest()
//        valClass = CkTest() // compile error
    }

    @Test
    fun dataClassTest() {
        var data1 = CkTestData(name = "one", msg = null)
        var data2 = data1
        var data3 = CkTestData(name = "one", msg = null)

        var data4 = CkTestData(name = "two", age = 20, msg = "msg")

        println("data check copy - data class")
        assertEquals(data1, data2) // 내부 값을 비교
        println("data check equal - data class")
        assertEquals(data1, data3) // 내부 값을 비교

        var dataCl1 = CkTest()
        var dataCl2 = dataCl1
        var dataCl3 = CkTest()

        println("data check copy - class")
        assertEquals(dataCl1, dataCl2) // class 주소가 동일
        println("data check equal - class")
//        assertEquals(dataCl1, dataCl3) // class 주소가 달라 err
        assertNotEquals(dataCl1, dataCl3)
    }

}

class CkTest {
    var name: String = ""
    var age: Int = 0
    var msg: String? = null

    fun testFun() {

    }
}

data class CkTestData(var name: String, var age: Int = 10, var msg: String?)

fun CkTest.testExtendFun() { } // 확장함수
fun CkTest.testExtendFun2(a: Int, b:Int) = a + b