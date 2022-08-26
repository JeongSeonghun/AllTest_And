package com.jsh.kr.alltestkt.manager

import com.jsh.kr.alltestkt.model.base.KotlinJavaTestClass
import com.jsh.kr.alltestkt.model.base.KotlinTestData
import com.jsh.kr.alltestkt.util.LogUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class KotlinTestManager {
    private val TAG = javaClass.canonicalName
    private val randomMax = 10

    init {
        KotlinSubClass().accessValue()
        anyTestFun(testMakeClass())
    }

    fun makeTimeText() :String {
        val form = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA)

        return form.format(Date())
    }

    fun makeTestData() : KotlinTestData {
        val testData = KotlinTestData()

//        testData.dataVal = "abc" // val data can't change
        testData.dataVar = "dataVar(" + makeRandomNum() + ")"

        return testData
    }

    fun makeRandomNum() : Int{
        return Random.nextInt(randomMax)
    }

    // Any -> java Object
    private fun anyTestFun(obj : Any) {
        // is -> java instance of, "smart cast"
        if (obj is KotlinSubClass) {
            obj.subClassFun()
        }
    }

    private fun testMakeClass() : KotlinTestOpenClass {
        return KotlinSubClass()
    }

    fun makeValueTestDataList() : ArrayList<KotlinTestData> {
        val dataList = ArrayList<KotlinTestData>()
        val testData = makeTestData()
        val testData2 = testData.copy()
        val testData3 = testData.copy()
        val a : String? = null

        // == -> equal()
        if (testData == testData2) {
            testData2.dataVar = "cde"
        }

        // Elvis
        testData3.dataVar = a?: "a null"

        dataList.add(testData)
        dataList.add(testData.copy(dataVal = "abc"))
        dataList.add(testData.copy(dataVar = "bcd"))
        dataList.add(testData2)
        dataList.add(testData3)

        return dataList
    }

    fun makeLamdaTestDataList() : ArrayList<KotlinTestData> {
        val dataList = ArrayList<KotlinTestData>()
        val testData = makeTestData()

        // lamda expression
        // {} start -> "->" left value, right body
        // when use lamda from val,var, Int, etc disable
        val sum = {x :Int, y: Int -> x+y}

        dataList.add(testData.copy(dataVal = "efg" + sum(makeRandomNum(), makeRandomNum())))

        return dataList
    }

    fun makeClassTestDataList() : ArrayList<KotlinTestData> {
        val dataList = ArrayList<KotlinTestData>()
        val testData = makeTestData()

        // when set default value in constructor, param that set default vale can omit
        val testConClass = KotlinTestManager.KotlinConTestClass("msg")
        dataList.add(testData.copy(dataVar = testConClass.msg))

        val testJavaClass = KotlinJavaTestClass()
        val text:String =testJavaClass.makeTextMsg(null)
        testJavaClass.testMsg = "testJavaClass ${makeRandomNum()}"
        val testData2 = KotlinTestData("test", testJavaClass.testMsg)
        testJavaClass.isFlag = false
        val testData3 = testData2.copy(dataVar = KotlinJavaTestClass.makeText(makeRandomNum()))
        dataList.add(testData2)
        dataList.add(testData3)

        return dataList
    }

    fun makeLoopTestDataList() : ArrayList<KotlinTestData> {
        val dataList = ArrayList<KotlinTestData>()

        for (cnt in 1..3) {
            dataList.add(KotlinTestData("loop", "cnt : $cnt"))
        }

        for (data in dataList) {
            if (data.dataVar.contains("2")) {
                data.dataVar += "2"
            }
        }

        return dataList
    }

    fun beforeTest(): String? {
        return beforeTestText()
    }

    fun beforeTestText(): String? {
        return if (java.util.Random().nextInt(2) % 2 == 0) "Not null" else null
    }

    // companion object -> static, top-level function
    // disable access class property, able access eternal property
    companion object {
        const val dataCompanionVal = "kotlin"
        fun makeTestText(num : Int) :String {
            return "companion fun text $num"
        }
    }

    class KotlinTestClass {
        val value = 100
        private val privateValue = 101
        public val publicValue = 102
        protected  val protectedValue = 103
        internal val internalValue = 104
        open val openValue = 105
        companion object {
            val valValue = 300
            // const ??
            const val constValValue = 301
        }
    }
    open class KotlinTestOpenClass {
        val value = 0
        private val privateValue = 1
        public val publicValue = 2
        protected  val protectedValue = 3
        internal val internalValue = 4
        open val openValue = 5

        fun showLog(msg : Int) {
            LogUtil.d("kotlin test", makeTestText(msg))
        }

        open fun accessValue() {
            val testClass = KotlinTestInnerClass()
            showLog(testClass.valueIn)
//            showLog(testClass.privateValueIn)
            showLog(testClass.publicValueIn)
//            showLog(testClass.protectedValueIn)
            showLog(testClass.internalValueIn)
            showLog(testClass.openValueIn)

            testClass.accessValue()
        }

        inner class KotlinTestInnerClass {
            val valueIn = 200
            private val privateValueIn = 201
            public val publicValueIn = 202
            protected  val protectedValueIn = 203
            internal val internalValueIn = 204
            open val openValueIn = 205
            fun accessValue() {
//                showLog(randomMax)
                showLog(value)
                showLog(this@KotlinTestOpenClass.value)
                showLog(privateValue)
                showLog(publicValue)
                showLog(protectedValue)
                showLog(internalValue)
                showLog(openValue)
                val testClass = KotlinTestClass()
                showLog(testClass.value)
//                showLog(testClass.privateValue)
                showLog(testClass.publicValue)
//                showLog(testClass.protectedValue)
                showLog(testClass.internalValue)
                showLog(testClass.openValue)
                showLog(KotlinTestClass.valValue)
                showLog(KotlinTestClass.constValValue)
            }

        }

    }

    //    class KotlinSubClass : KotlinTestClass() // no open class -> final class
    class KotlinSubClass : KotlinTestOpenClass() {
        override val openValue = 6
        override fun accessValue(){
            super.accessValue()
//            showLog(randomMax)
            showLog(value)
//            showLog(privateValue)
            showLog(publicValue)
            showLog(protectedValue)
            showLog(internalValue)
            showLog(openValue)
        }
        fun subClassFun() {
            LogUtil.d("KotlinSubClass", "subClassFun()")
        }
    }

    // kotlin can identify in interface
    interface KotlinTestInterface {
        val defaultTitle : String
        fun onListen()
    }
    class KotlinImplementClass : KotlinTestInterface {
        override val defaultTitle: String = "abc"

        override fun onListen() {
        }
    }
    abstract class KotlinAbstractClass {
        open val openValue = "openValue"
        protected open val protectValue = "protectValue"

        fun defaulFun() {

        }
        open fun openFun() {

        }
        abstract fun abstractFun()
    }
    class KotlinTest : KotlinAbstractClass() {
        override val openValue = "abstractOpenValue"
        // no access type is default up class type. protectValue -> protected
        override val protectValue = "absProtectValue"
        override fun abstractFun() {
        }

        override fun openFun() {
            super.openFun()
        }
    }

    class KotlinConTestClass (initMsg : String, initIsBoolean : Boolean = true) {
        //        val msg : String
//        val isBoolean : Boolean
//
//        init {
//            msg = initMsg
//            isBoolean = initIsBoolean
//        }
        val msg = initMsg
        val isBoolean = initIsBoolean
    }
}