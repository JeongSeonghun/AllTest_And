package com.jsh.kr.alltestkt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsh.kr.alltestkt.util.LogUtil

open class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(this.javaClass.simpleName, "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(this.javaClass.simpleName, "onStart()")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d(this.javaClass.simpleName, "onStop()")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d(this.javaClass.simpleName, "onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(this.javaClass.simpleName, "onDestroy()")
    }

    override fun finish() {
        super.finish()
        LogUtil.d(this.javaClass.simpleName, "finish()")
    }

}
