package com.jsh.kr.alltestkt

import org.junit.Test

class SynchronizeTest {

    @Test
    fun testFunc() {
        val counter1 = TestTempThread()
        val counter2 = TestTempThread()

        counter1.start()
        counter2.start()

        // unit test thread test용
        counter1.join()
        counter2.join()
    }

    @Test
    fun testBlock() {
        val counter1 = TestCounter(true)
        val counter2 = TestCounter(false)

        counter1.start()
        counter2.start()

        // unit test thread test용
        counter1.join()
        counter2.join()
    }

    object TestSyncModel {
        var isReady = false
        var isReadyOdd = true
        var isReadyEven = false
        var num: Int = 0

        @Synchronized
        fun count(id: Long) {
            println("check $id before : $num")
            num++
            println("check $id after : $num")
        }
    }

    inner class TestTempThread: Thread() {
        override fun run() {
            super.run()
            for (num in 1 .. 10) {
                println("check ${this.id} : $num")
                TestSyncModel.count(this.id)
                sleep(200)
            }
        }
    }

    inner class TestCounter(var isOdd: Boolean = false) : Runnable {

        private val MAX_COUNT = 10
        private var thread: Thread? = null
        private var num = 0
        private var result = 0

        override fun run() {
            println("check ${thread?.id} start idOdd: $isOdd")
            while (result < MAX_COUNT) {
                synchronized(this) {
                    num++
                    println("check ${thread?.id} num: $num")

                    if (isOdd && TestSyncModel.isReadyOdd) {
                        result++
                        if (result % 2 == 1) {
                            println("check ${thread?.id} odd num: $result")
                            TestSyncModel.isReadyOdd = false
                            TestSyncModel.isReadyEven = true
                        }
                    } else if (!isOdd && TestSyncModel.isReadyEven) {
                        result++
                        if (result % 2 == 0) {
                            println("check ${thread?.id} even num: $result")
                            TestSyncModel.isReadyOdd = true
                            TestSyncModel.isReadyEven = false
                        }
                    }
                }

            }
            thread = null
        }

        fun start() {
            if (thread != null) { thread!!.interrupt() }

            thread = Thread(this)
            thread?.start()
        }

        fun stop() {
            if (thread != null) { thread!!.interrupt() }
            thread = null
        }

        fun join() {
            thread?.join()
        }
    }
}