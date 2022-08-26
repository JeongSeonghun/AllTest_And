package com.jsh.kr.alltestkt

import org.junit.Test
import kotlin.concurrent.thread

class ThreadTest {

    @Test
    fun testThread() {
        val temp = TestThread()
        val temp2 = TestThread()

        temp.start()
        temp2.start()

        // unit test가 바로 종료되므로 join으로 temp, temp2 끝날때까지 기다림.
        temp.join()
        temp2.join()
    }

    @Test
    fun testKtThread() {
        thread {
            for (num in 1 .. 10) {
                println("check kotlin thread num $num")
            }
        }
        println("check kotlin thread starting")

        // unit test가 바로 종료되므로 sleep으로 종료 임시 제한
        Thread.sleep(1000)
    }

    inner class TestThread: Thread() {
        override fun run() {
            super.run()

            println("Thread start ${this.id}")

            for (num in 1 .. 10) {
                println("${this.id} check num $num")
                sleep(200)
            }

            println("Thread stop ${this.id}")
        }
    }
}