package com.jsh.kr.alltestkt.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.manager.KotlinTestManager
import com.jsh.kr.alltestkt.manager.KotlinTestManager.KotlinTestClass as Test1 // as 이름 지정. 사용시 주의
import com.jsh.kr.alltestkt.model.base.KotlinTestClass as Test2
import com.jsh.kr.alltestkt.model.base.KotlinTestData
import com.jsh.kr.alltestkt.ui.BaseActivity
import com.jsh.kr.alltestkt.util.LogUtil
import com.jsh.kr.alltestkt.view.KotlinTestDataView

// typealias(타입 별명). 다른 사람과 같이 개발 시 사용 주의.(문서로 남기는 것 같이 공유할 방안 필요)
typealias DataView = KotlinTestDataView
/**
 * https://medium.com/@joongwon/kotlin-kotlin-%ED%82%A4%EC%9B%8C%EB%93%9C-%EB%B0%8F-%EC%97%B0%EC%82%B0%EC%9E%90-%ED%95%B4%EB%B6%80-1-hard-keywords-3062f5fe2d11
 * https://medium.com/@joongwon/kotlin-idioms-84052fd751a0
 *
 * let apply run with
 * https://www.androidhuman.com/lecture/kotlin/2016/07/06/kotlin_let_apply_run_with/
 * https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29
 */
class KotlinBaseTestActivity : BaseActivity(), View.OnClickListener{
    // val -> final, property(getter)
    private val TAG = javaClass.canonicalName

    // var property(getter, setter)
    private var abc : String

    private var tvCompFun : TextView? = null
    private var btnRefresh : Button? = null
    private var tvDate  : TextView? = null
    private var uiTestData : DataView? = null

    private val manager = KotlinTestManager()

    init {
        abc = ""
        val bcd = "bcd"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_base_test)

        initUI()
        initData()
    }

    private fun initUI() {
        tvCompFun = findViewById(R.id.tv_kotlin_test_comp_fun)
        btnRefresh = findViewById(R.id.btn_kotlin_test_refresh)
        tvDate = findViewById(R.id.tv_kotlin_test_date)
        uiTestData = findViewById(R.id.ui_kotlin_test_data)

        btnRefresh?.setOnClickListener(this)
        // object -> create anonymous class
//        var test:Int = 0 // -> no final, can access eternal
//        btnRefresh!!.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                test = 2
//            }
//        })
    }

    private fun initData() {
        LogUtil.d(TAG, "init data - abc = $abc")
        // value bcd of init() only can use to init()
//        LogUtil.d(TAG, "init data - bcd = $bcd")
        refresh()
        castTest()
        inTest()
        letApplyRunWithTest()
    }

    private fun showManagerDataOrDate() {
        val data= KotlinTestManager.dataCompanionVal
        val date= manager.makeTimeText()
        val txt = "$data $date"
        tvDate?.text = txt
    }

    private fun showTestData() {
        val dataList = ArrayList<KotlinTestData>()

        dataList.apply {
            addAll(manager.makeValueTestDataList())
            addAll(manager.makeLamdaTestDataList())
            addAll(manager.makeClassTestDataList())
            addAll(manager.makeLoopTestDataList())
        }

        uiTestData?.refresh(dataList)
    }

    private fun castTest() {
        val testList = ArrayList<String>()
        val form = "message : %1\$s"
        testList.apply{
            add(String.format(form, castTestFunUnsafe("msg")))
            add(String.format(form, castTestFunUnsafe(null)))
            // occur exception, because can't cast
//        add(String.format(form, castTestFunUnsafe(TestClass())))
            add(String.format(form, castTestFun("msg")))
            add(String.format(form, castTestFun(null)))
            add(String.format(form, castTestFun(TestClass())))

            add(String.format(form, "KotlinTestClass is ->" +castTestClassFun(Test1())))
            add(String.format(form, "KotlinTestClass is ->" +castTestClassFun(Test2())))
        }

        for (msg in testList) {
            LogUtil.d(TAG, "cast test -> $msg")
        }
    }

    private fun castTestFunUnsafe(obj : Any?) : String?{
        // unsafe casting -> when obj can't cast or is null occur exception
//        val x = obj as String
        // when obj is null return null. when obj can't cast occur exception
        val x = obj as String?

        return  x
    }
    private fun castTestFun(obj : Any?) : String? = obj as? String
    private fun castTestClassFun(obj : Any?) : Boolean = obj is Test1

    private fun inTest() {
        // for loop, range expressions, when expressions, generic ....

        // for loop
        val items: Array<Int> = arrayOf(1, 2, 3)
        for (num in items) LogUtil.d(TAG, "in for loop : $num")

        // range ?? -> can use !in
        for (cnt in 1..3) { // equal cnt in 1 util 3
            if (cnt !in 1..2) {
                LogUtil.d(TAG, "in range !in 1..2 : $cnt")
            } else {
                LogUtil.d(TAG, "in range : $cnt")
            }
        }
        for (cnt in 1..6 step 2) {
            LogUtil.d(TAG, "in range step 2: $cnt")
        }
        for (cnt in 3 downTo 1) {
            LogUtil.d(TAG, "in range down to: $cnt")
        }

        // contain
        val containOne : Boolean = 1 in items
        LogUtil.d(TAG, "in contain 1 : $containOne")

        // when -> switch => can use !in
        val wheMsg = {x : Int -> when(x) {
            in 1..3 -> "when cnt"
            4,5 -> "when 4,5"
            else -> "when else"
        }}
        LogUtil.d(TAG, "in when : "+wheMsg(1))
        LogUtil.d(TAG, "in when : "+wheMsg(4))
        LogUtil.d(TAG, "in when : "+wheMsg(6))
    }

    // scoping function
    private fun letApplyRunWithTest() {
        val list = ArrayList<String>()

        // non-nullable object and not need result
        with(list) {
            add("with first")
        }

        applyTestFun()
        alsoTestFun()
        letTestFun(null)
        letTestFun(TestClass())
        runTestFun()
    }

    // Implicit explicit
    // in -> Implicit send to receiver
    // block -> Implicit send use receiver
    // return -> receive object
    private fun applyTestFun() {
        // no use method. return itself, use only property in block
        //  when block function return other type object can't use. because return receive object
        // can access method and attr. when create and initialize. work sequence
        val testClass = TestClass().apply {
            varValue = "apply value"
//            showLog()
        }

        testClass.showLog()
    }

    // in -> Implicit send to receiver
    // block -> explicit send to param
    // return -> receive object
    private fun alsoTestFun() {
        // no use received object or no change attr of received object
        // when check side effect. before set property of received object check validation
        //  when block function return other type object can't use. because return receive object

        val testClass = TestClass().also {
            it.showLog()
        }

    }

    // in -> Implicit send to receiver
    // block -> explicit send to param
    // return -> block result
    private fun letTestFun(testClass : TestClass?) {
        // limit local value range
        // change nullable other object
        // when no null use method
        LogUtil.d(TAG, "let obj null : ${testClass == null}")
        // let. val randomNum is use once. able safe calls(.?)
        // -> use only this value in range
//        val randomNum = manager.makeRandomNum()
//        list.add("let randomNum : $randomNum")
        // lamda one param
//        manager.makeRandomNum().let { randomNum ->
//            list.add("let randomNum : $randomNum")
//        }
        manager.makeRandomNum().let {
            testClass?.varValue = "let randomNum $it"
        }
        testClass?.showLog()

        // change nullable object
        val testClass2 : TestClass2? = testClass?.let {
            TestClass2().apply { varValue = it.varValue }
        }

        // no null
        testClass2?.let{
            it.showLog()
            it.testFun()
        }
    }

    // in -> Implicit send to receiver
    // block -> Implicit send use receiver
    // return -> block result
    private fun runTestFun() {
        // need calculate. limit range of local values. change explicit received object Implicit object
        // run . let + with
        val testClass = TestClass()

        // change explicit received object Implicit object
        testClass.run {
            varValue = "run value"
            showLog()
        }

        // limit range of local values
        val ab : String = run {
            val a = "aaaa"
            val b = "bbbb"

            "$a $b"
        }

        testClass.varValue = ab
        testClass.showLog()
    }

    inner class TestClass {
        // property
        var varValue = "varValue"
            get() {
                return "field+$field"
            }
            set(value) {
                field = value
            }

        fun showLog() {
            LogUtil.d(TAG, "test class fun : $varValue");
        }
    }

    inner class TestClass2 {
        var varValue = "varValue2"

        fun showLog() {
            LogUtil.d(TAG, "test class2 fun : $varValue");
        }

        fun testFun() {

        }
    }

    private fun refresh() {
        showManagerDataOrDate()
        showTestData()
        tvCompFun!!.text = KotlinTestManager.makeTestText(manager.makeRandomNum())
    }

    override fun onClick(v: View) {
        // when -> switch, if
        when(v.id) {
            R.id.btn_kotlin_test_refresh -> refresh()
        }
    }

}