package com.jsh.kr.alltestkt.ui.etc

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.model.JavaScriptManager
import com.jsh.kr.alltestkt.ui.BaseActivity
import com.jsh.kr.alltestkt.util.LogUtil
import com.jsh.kr.alltestkt.util.TestTextUtil

class WebScriptTestActivity: BaseActivity(), JavaScriptManager.OnJavaScriptManagerListener {

    private lateinit var wvScriptTest: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_script_test)
        initUI()
    }

    private fun initUI() {
        wvScriptTest = findViewById(R.id.wv_script_test)

        initWebView()
    }

    private fun initWebView() {
        // security issue --> https://developer.android.com/training/articles/security-tips
        // --> when use java script in app, use setJavascriptEnabled
        wvScriptTest.settings.javaScriptEnabled = true

        val javaScriptManager = JavaScriptManager()
        javaScriptManager.setJavaScriptManagerListener(this)
        wvScriptTest.addJavascriptInterface(
            javaScriptManager,
            JavaScriptManager.TAG
        )
        wvScriptTest.webViewClient = WebViewClient()
        wvScriptTest.loadUrl(makeStartUrl())
    }

    private fun makeStartUrl(): String {
        return "file:///android_asset/scripttest/index.html"
    }

    override fun onResult(result: String?) {
        LogUtil.d("script_test", "onResult :$result")

        runOnUiThread {
//         wvScriptTest.evaluateJavascript("javascript:inputValue()", null)
            wvScriptTest.evaluateJavascript(
                "javascript:inputValue()"
            ) { value: String ->
                LogUtil.d(
                    "script_test",
                    "inputValue :$value"
                )
            }
        }
    }

    override fun getRandomValue() {
        runOnUiThread {
            wvScriptTest.loadUrl(
                String.format(
                    "javascript:setRandomValue('%s')",
                    TestTextUtil.makeRandomNumEng(5)
                )
            )
        }

    }
}