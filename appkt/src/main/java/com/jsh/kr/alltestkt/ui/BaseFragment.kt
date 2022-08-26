package com.jsh.kr.alltestkt.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.jsh.kr.alltestkt.util.LogUtil

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(this.javaClass.simpleName, "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(this.javaClass.simpleName, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d(this.javaClass.simpleName, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d(this.javaClass.simpleName, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d(this.javaClass.simpleName, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(this.javaClass.simpleName, "onDestroy()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtil.d(this.javaClass.simpleName, "onDestroyView()")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtil.d(this.javaClass.simpleName, "onAttach()")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtil.d(this.javaClass.simpleName, "onDetach()")
    }

}