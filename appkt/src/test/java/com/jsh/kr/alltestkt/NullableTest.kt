package com.jsh.kr.alltestkt

import org.junit.Test
import kotlin.random.Random

class NullableTestTest {

    // null safety
    var nullableValue: String? = null
    var NotNullValue: String = ""

    @Test
    fun nullableTest() {
        nullableValueTest()
        nullableClassTest()
    }

    private fun nullableValueTest() {
        val abc = makeTestText()

        println("abc isNull "+(abc == null))
        // !! -> when abc is null, exception
//        println(abc!!)
        if (abc != null) println(abc)

        // grammar error
//        testFun(abc)
//        testFun(abc!!)
        if (abc != null) testFun(abc)
        testFun2(abc)
    }

    // .? "safe calls"
    private fun nullableClassTest() {
        val testClass = makeTestClass()

        println("testClass isNull "+(testClass == null))
        testClass?.showLog()

        println("testClass default value : ${testClass?.varValue}")
        testClass?.varValue = "abc"
        println("testClass set value : ${testClass?.varValue}")
    }

    private fun testFun(message : String) {
        println("testFun($message)")
    }
    private fun testFun2(message : String?) {
        println("testFun2($message)")
    }

    private fun makeTestText() : String?{
        val returnNull = makeRandomNum() % 2 == 0
        return if (returnNull) {
            "abc"
        } else {
            null
        }
    }

    private fun makeTestClass() : TestInnerClass? {
        val returnNull = makeRandomNum() % 2 == 0
        return if (returnNull) {
            TestInnerClass()
        } else {
            null
        }
    }

    private fun makeRandomNum(): Int {
        return Random.nextInt(100)
    }

    inner class TestInnerClass {
        // property
        var varValue = "varValue"
            get() {
                return "field+$field"
            }
            set(value) {
                field = value
            }

        fun showLog() {
            println("test class fun : $varValue")
        }
    }

}