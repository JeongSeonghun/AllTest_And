package com.jsh.kr.alltestkt.util

import android.content.Context
import com.jsh.kr.alltestkt.AllTestApplication
import com.jsh.kr.alltestkt.constant.C
import org.json.JSONException
import java.io.UnsupportedEncodingException

/**
 * Instantiates a new Sdm app data util.
 */
object AppDataUtil {

    private const val prefName = "AppData"

    /**
     * demo mode.
     */
    private const val DEMO_MODE = "DemoMode"

    /**
     * log in response.
     */
    private const val LOGINRESPONSE = "loginResponse"

    /**
     * when run app, show login activity
     */
    private const val MODE_LOGIN = "loginMode"

    /**
     * move before test mode
     */
    private const val MODE_MAINTAIN_TEST = "MaintainTestMode"

    private const val MAINTAIN_TEST_MENU = "maintainTestDataMenu"
    private const val MAINTAIN_TEST_ACT = "maintainTestDataAct"

    /**
     * Is demo mode boolean.
     *
     * @return the boolean
     */
    /**
     * Sets demo mode.
     *
     * @param isDemo the is demo
     */
    private var isDemoMode = false
    private var secKey: String? = null
    private var watch = 0

    var isLoginMode: Boolean = false
        set(value) {
            val pref = AllTestApplication.application!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()

            editor.putBoolean(MODE_LOGIN, value)

            editor.apply()
            field = value
        }

    var isMaintainTestMode = true
        set(value) {
            val pref = AllTestApplication.application!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()

            editor.putBoolean(MODE_MAINTAIN_TEST, value)

            editor.apply()
            field = value
        }
    var maintainTestMenu = C.Maintain.Menu_UI
        set(value) {
            val pref = AllTestApplication.application!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()

            editor.putInt(MAINTAIN_TEST_MENU, value)

            editor.apply()
            field = value
        }

    var maintainTestAct: String? = null
        set(value) {
            val pref = AllTestApplication.application!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()

            editor.putString(MAINTAIN_TEST_ACT, value)

            editor.apply()
            field = value
        }

    init {
        testInit()
    }

    private fun testInit() {
        LogUtil.d(prefName, "testInit")
        isDemoMode = false
        secKey = null
        watch = 0
        loadData()
    }

    private fun loadData() {
        val pref = AllTestApplication.application!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)

        isLoginMode = pref.getBoolean(MODE_LOGIN, false)
        isMaintainTestMode = pref.getBoolean(MODE_MAINTAIN_TEST, true)
        maintainTestMenu = pref.getInt(MAINTAIN_TEST_MENU, C.Maintain.Menu_UI)
        maintainTestAct = pref.getString(MAINTAIN_TEST_ACT, null)

    }

}
