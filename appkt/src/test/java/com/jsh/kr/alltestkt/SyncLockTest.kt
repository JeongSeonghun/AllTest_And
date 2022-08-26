package com.jsh.kr.alltestkt

import org.junit.Test

class SyncLockTest {

    @Test
    fun testInstanceLock() {
        // class lock. -> method, block
        val one = SyncFunClassTest()
        val two = SyncFunClassTest()
        val th = Thread {
            one.syncFun1("Thread 1")
        }

        val th2 = Thread {
            one.syncFun2("Thread 2")
        }

        val th3 = Thread {
            one.noSyncFun("Thread 3")
        }

        val th4 = Thread {
            one.syncBlockFun("Thread 4")
        }

        val th5 = Thread {
            two.syncFun1("Thread 5")
        }

        th.start()
        th2.start()
        th3.start() // one noSyncFun lock 미공유로 동기처리 없음.
        th4.start()
        th5.start() // two instance는 달라 lock 미공유로 동기처리 없음.

        // unit test에서 thread test를 위해 추가
        th.join()
        th2.join()
        th3.join()
        th4.join()
        th5.join()
    }

    @Test
    fun testClassLock() {
        // class lock. -> static method
        val one = SyncFunClassTest()

        val th = Thread {
            SyncFunClassTest.syncStaticClassFun("Thread 1")
        }

        val th2 = Thread {
            SyncFunClassTest.syncStaticClassFun("Thread 2")
        }

        val th3 = Thread {
            one.syncFun1("Thread 3")
        }

        th.start()
        th2.start()
        th3.start() // class lock과 미공유로 별도 동작

        // unit test에서 thread test를 위해 추가
        th.join()
        th2.join()
        th3.join()
    }

    @Test
    fun syncBlockTest() {
        val one = SyncFunClassTest()
        val two = SyncFunClassTest()
        val th = Thread {
            one.syncBlockOther("Thread 1")
        }

        val th2 = Thread {
            one.syncBlockOther("Thread 2")
        }

        val th3 = Thread {
            one.syncFun1("Thread 3")
        }

        val th4 = Thread {
            two.syncBlockOther("Thread 4")
        }

        val th5 = Thread {
            one.syncBlockOtherClass("Thread 5")
        }

        val th6 = Thread {
            two.syncBlockOtherClass("Thread 6")
        }

        val th7 = Thread {
            SyncFunClassTest.syncStaticClassBlockFun("Thread 7")
        }

        th.start()
        th2.start() // th one과 lock 공유
        th3.start() // th one 내부 sync(SyncOtherTest) 객체 미사용으로 lock 미공유
        th4.start() // th one과 lock 미공유
        th5.start() // th one, th4 two 와는 lock 미공유
        th6.start() // th5 one과 lock 공유
        th7.start()

        // thread 1, thread2는 동기화로 1,2가 섞이지 않은 상태로 표시
        // thread 5, thread6은 동기화로 5,6이 섞이지 않은 상태로 표시

        // unit test에서 thread test를 위해 추가
        th.join()
        th2.join()
        th3.join()
        th4.join()
        th5.join()
        th6.join()
        th7.join()
    }
}

class SyncFunClassTest {
    private val sync = SyncOtherTest()

    @Synchronized
    fun syncFun1(name: String) { // instance lock, synchronized method
        println("sync func 1 start : $name")
        Thread.sleep(200)
        println("sync func 1 stop : $name")
    }

    @Synchronized
    fun syncFun2(name: String) { // instance lock
        println("sync func 2 start : $name")
        Thread.sleep(200)
        println("sync func 2 stop : $name")
    }

    fun syncBlockFun(name: String) {
        synchronized(this) { // instance lock. synchronized block
            println("sync block func start : $name")
            Thread.sleep(200)
            println("sync block func stop : $name")
        }
    }

    fun noSyncFun(name: String) {
        println("no sync func : $name")
    }

    fun syncBlockOther(name: String) {
        synchronized(sync) { // instance lock
            println("sync block other start : $name")
            sync.syncOtherFun(name)
            Thread.sleep(200)
            println("sync block other stop : $name")
        }
    }

    fun syncBlockOtherClass(name: String) {
        val sync = SyncOtherTest()
        synchronized(SyncOtherTest::class.java) { // class lock
            println("sync block other class start : $name")
            sync.syncOtherFun(name)
            Thread.sleep(200)
            println("sync block other class stop : $name")
        }
    }

    companion object {
        @Synchronized
        fun syncStaticClassFun(name: String) { // class lock. static synchronized method
            println("sync static class func start : $name")
            Thread.sleep(200)
            println("sync static class func stop : $name")
        }

        fun syncStaticClassBlockFun(name: String) {
            synchronized(SyncFunClassTest::class.java) { // class lock. static synchronized block
                println("sync static class block func start : $name")
                Thread.sleep(200)
                println("sync static class block func stop : $name")
            }
        }
    }
}

class SyncOtherTest {
    @Synchronized
    fun syncOtherFun(name: String) {
        println("sync inner func start : $name")
        Thread.sleep(200)
        println("sync inner func stop : $name")
    }
}