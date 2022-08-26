package com.jsh.kr.alltestkt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.model.base.MainMoveData
import com.jsh.kr.alltestkt.ui.adapter.MainMoveAdapter
import java.util.ArrayList

class BaseMainFragment : BaseFragment() {

    private var lv_move_base: ListView? = null

    private var moveAdapter: MainMoveAdapter? = null

    private var moveDataArrayList = ArrayList<MainMoveData>()

    val listSize: Int
        get() = moveDataArrayList.size

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_main, container, false)

        initUI(view)

        refresh()
        return view
    }

    private fun initUI(view: View) {
        lv_move_base = view.findViewById(R.id.lv_move_base)

//        moveAdapter = MainMoveAdapter(activity!!)
        moveAdapter = MainMoveAdapter(requireActivity())
        lv_move_base!!.adapter = moveAdapter
    }

    fun initData(moveData: ArrayList<MainMoveData>) {
        this.moveDataArrayList = moveData
    }

    fun addData(moveData: MainMoveData) {
        moveDataArrayList.add(moveData)
    }

    fun refresh() {
        moveAdapter?.refresh(moveDataArrayList)
    }

    interface OnMainFragmentListener
}