package com.jsh.kr.alltestkt.util

import android.util.Log
import com.jsh.kr.alltestkt.BuildConfig

object LogUtil {
    private val isDebug = BuildConfig.DEBUG

    @JvmStatic
    fun d(tag: String?, message: String?) {
        if (isDebug && tag != null && message != null) {
            Log.d(tag, message)
        }
    }
    @JvmStatic
    fun e(tag: String?, message: String?) {
        if (isDebug && tag != null && message != null) {
            Log.e(tag, message)
        }
    }
    @JvmStatic
    fun e(tag: String?, message: String?, e: Throwable) {
        if (isDebug && tag != null && message != null) {
            Log.e(tag, message, e)
        }
    }
}