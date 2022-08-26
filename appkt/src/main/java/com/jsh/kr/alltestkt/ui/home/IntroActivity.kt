package com.jsh.kr.alltestkt.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.jsh.kr.alltestkt.ui.BaseActivity
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.manager.AssetDBTestManager
import com.jsh.kr.alltestkt.util.AppDataUtil
import com.jsh.kr.alltestkt.util.PermissionUtil

class IntroActivity : BaseActivity() {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        init()
    }

    private fun init() {
//        if (PermissionUtil.checkPermission(this)) {
//            next()
//        }
        next()
    }

    private fun startTimer() {
        mHandler.postDelayed({
            if (AppDataUtil.isLoginMode) {
                goToLogin()
            } else {
                goToMain()
            }
        }, 500)
    }

    private fun goToMain() {
        val intentAct = Intent(this, MainActivity::class.java)
        intentAct.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intentAct)
        finish()
    }

    private fun goToLogin() {
        val intentAct = Intent(this, LoginActivity::class.java)
        startActivity(intentAct)
        finish()
    }

    private operator fun next() {
        AssetDBTestManager().init(this)
        startTimer()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            next()
        }

    }
}