package com.jsh.kr.alltestkt.ui.etc

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.databinding.ActivityPermissionContractsTestBinding
import com.jsh.kr.alltestkt.ui.BaseActivity

class PermissionContractsTestActivity: BaseActivity(), OnClickListener {

    private lateinit var binding: ActivityPermissionContractsTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionContractsTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAllCheck.setOnClickListener(this)
        binding.btnForegroundLocation.setOnClickListener(this)
        binding.btnBackgroundLocation.setOnClickListener(this)
        binding.btnPhoneNum.setOnClickListener(this)
        binding.btnFile.setOnClickListener(this)
        binding.btnCamera.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_all_check -> {
                allCheck()
            }
            R.id.btn_foreground_location -> {
                checkForegroundLocation()
            }
            R.id.btn_background_location -> {
                checkBackgroundLocation()
            }
            R.id.btn_phone_num -> {
                clickPhoneNum()
            }
            R.id.btn_file -> {
                checkFile()
            }
            R.id.btn_camera -> {
                checkCamera()
            }
        }
    }

    private fun allCheck() {}

    private fun checkForegroundLocation() {}

    private fun checkBackgroundLocation() {}

    private fun clickPhoneNum() {}

    private fun checkFile() {}

    private fun checkCamera() {}
}