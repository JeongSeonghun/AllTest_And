package com.jsh.kr.alltestkt.model

import android.webkit.JavascriptInterface

class JavaScriptManager {

    private var listener: OnJavaScriptManagerListener? = null

    fun setJavaScriptManagerListener(listener: OnJavaScriptManagerListener?) {
        this.listener = listener
    }

    @JavascriptInterface
    fun onResult(result: String?) {
        listener?.onResult(result)
    }

    @JavascriptInterface
    fun getRandomValue() {
        listener?.getRandomValue()
    }

    interface OnJavaScriptManagerListener {
        fun onResult(result: String?)
        fun getRandomValue()
    }

    companion object {
        val TAG: String = JavaScriptManager::class.java.simpleName
    }
}