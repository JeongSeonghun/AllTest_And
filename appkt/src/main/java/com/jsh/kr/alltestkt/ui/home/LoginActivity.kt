package com.jsh.kr.alltestkt.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.jsh.kr.alltestkt.ui.BaseActivity
import com.jsh.kr.alltestkt.R

class LoginActivity : BaseActivity(), View.OnClickListener {

    private var et_id: EditText? = null
    private var et_pwd: EditText? = null
    private var btn_login: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUI()
    }

    private fun initUI() {
        et_id = findViewById(R.id.et_id)
        et_pwd = findViewById(R.id.et_pwd)
        btn_login = findViewById(R.id.btn_login)

        btn_login!!.setOnClickListener(this)
    }

    private fun login() {
        val id = et_id!!.text.toString()
        val pwd = et_pwd!!.text.toString()

//        UserInfoManager.doLoginRequest(this, id, pwd, object : BaseCallback<LoginResponse>() {
//            override fun onSuccess(response: LoginResponse?) {
//                super.onSuccess(response)
//                next()
//            }
//        })
    }

    private operator fun next() {
        goToMain()
    }

    private fun goToMain() {
        val intentAct = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intentAct)
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> login()
        }
    }
}