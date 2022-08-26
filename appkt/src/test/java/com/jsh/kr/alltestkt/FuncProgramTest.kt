package com.jsh.kr.alltestkt

import org.junit.Test

import org.junit.Assert.*

/**
 * 함수형 프로그래밍
 * ex) javascript, kotlin
 * 함수가 객체
 * * 객체지향 c++, java
 * * 절차지향 c
 */

fun checkTest() { // 함수가 일급 객체, 포함 관계 최상위 -> 내부에 변수, 함수, 클래스 모두 가능
    var temp = "temp"
    class CkTemp {

    }
    fun abc() {

    }
}

class FuncProgramTest {

    @Test
    fun test() {
        // 익명함수
        val ckFun = fun(){println("check func")}
        val ckFun2 = fun(a: Int, b: Int){a + b} // unit 반환
        val ckLambdaFun: () -> Unit = {println("check lambda func")} // 익명함수 -> 람다
        val ckFun3 = ::ckFun3 // ckFun3 참조
        val sum1 = { a:Int, b: Int -> a + b} // 인자 -> 리턴
        val sum2: (Int, Int) -> Int = { a, b -> a + b} // 인자 타입 생략
        val welcome: (String) -> String = {name -> "welcome $name"}
        val welcome2: (String) -> String = {"welcome $it"} // 인자 선언 생략(인자가 1개인 경우)

        ckFun()
        ckLambdaFun()
        ckFun2(1, 2)
        ckFun3()

        println("check sum")
        assertEquals(sum1(1, 2), sum2(1, 2))

        val msg1 = welcome("apple")
        println("check welcome $msg1")
        val msg2 = welcome2("one")
        println("check welcome $msg2")
    }

    private fun ckFun3() {
        println("check func copy")
    }

    @Test
    fun testPureFunc() {
        var changeVar = 2

        val pureFun = {a: Int, b: Int -> a * b} // 순수 함수 같은 인자에 대해 같은 결과만 제공
        val diffFun = {a: Int, b: Int -> a * b + changeVar}

        assertEquals(6, pureFun(2, 3))
        assertEquals(8, diffFun(2, 3))

        changeVar = 3
        assertEquals(6, pureFun(2, 3)) // 결과 동일
//        assertEquals(8, diffFun(2, 3))
        assertEquals(9, diffFun(2, 3)) // changeVar에 따라 결과 다름

    }

    @Test
    fun testProcess() {
        val processAdd = {one: Int, two: Int -> one + two}
        val processMinus = {one: Int, two: Int -> one - two}
        val processMulti = {one: Int, two: Int -> one * two}
        val process: (a: Int, b: (Int, Int) -> Int) -> Int = {a, b -> a * b(3, 2)}
        val processResFunc: (a: Int, b: Int) -> ((Int, Int) -> Int) = {a, b -> {c: Int, d: Int -> (a + b +c) * d}}

        val result = processAdd(2, processMulti(3, 2))
        assertEquals(8, result)

        var isAdd = true
        if (isAdd) { // process 에 processAdd 사용
            println("check add")
            assertEquals(10, process(2, processAdd))
        }

        isAdd = false
        if (!isAdd) { // process 에 processMinus 사용
            println("check minus")
            assertEquals(2, process(2, processMinus)) // processMinus 이름으로 호출

            assertEquals(2, process(2, ::minus)) // 참조에 의한 minus 호출
        }

        println("check return func") // process에 함수를 리턴 받아 인자로 사용
        assertEquals(32, process(2, processResFunc(2, 3)))

    }

    fun minus(one: Int, two: Int) = one - two
}