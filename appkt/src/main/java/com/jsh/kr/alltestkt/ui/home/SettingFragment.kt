package com.jsh.kr.alltestkt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.ui.BaseFragment
import com.jsh.kr.alltestkt.util.AppDataUtil

class SettingFragment : BaseFragment(), CompoundButton.OnCheckedChangeListener {

    private var sw_setting_before_move: Switch? = null
    private var sw_mode_login: Switch? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        initUI(view)
        initData()

        return view
    }

    private fun initUI(view: View) {
        sw_setting_before_move = view.findViewById(R.id.sw_setting_before_move)
        sw_mode_login = view.findViewById(R.id.sw_mode_login)

        sw_setting_before_move!!.setOnCheckedChangeListener(this)
        sw_mode_login!!.setOnCheckedChangeListener(this)
    }

    private fun initData() {
        sw_mode_login!!.isChecked = AppDataUtil.isLoginMode
        sw_setting_before_move!!.isChecked = AppDataUtil.isMaintainTestMode
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.sw_mode_login -> AppDataUtil.isLoginMode = isChecked
            R.id.sw_setting_before_move -> AppDataUtil.isMaintainTestMode = isChecked
        }
    }
}