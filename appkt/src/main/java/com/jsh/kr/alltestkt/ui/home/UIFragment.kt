package com.jsh.kr.alltestkt.ui.home

import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.constant.C
import com.jsh.kr.alltestkt.model.base.MainMoveData
import com.jsh.kr.alltestkt.ui.BaseMainMenuFragment
import java.util.ArrayList

class UIFragment : BaseMainMenuFragment() {
    private var moveDataArrayList = ArrayList<MainMoveData>()

    override fun initMoveDataList(): ArrayList<MainMoveData> {

//        addUIList(PagerTestActivity::class.java, R.string.move_viewpager, C.State.Test)
//        addIssueList(ListViewClickActivity::class.java, R.string.move_list_click_test, C.State.Resolution)

        return moveDataArrayList
    }

    private fun addUIList(moveAct: Class<*>, titleId: Int) {
        addList(moveAct, titleId, null, C.State.No_State)
    }

    private fun addUIList(moveAct: Class<*>, titleId: Int, state: Int) {
        addList(moveAct, titleId, null, state)
    }

    private fun addIssueList(moveAct: Class<*>, titleId: Int) {
        addList(moveAct, titleId, C.TAG.Issue, C.State.No_State)
    }

    private fun addIssueList(moveAct: Class<*>, titleId: Int, state: Int) {
        addList(moveAct, titleId, C.TAG.Issue, state)
    }

    private fun addList(moveAct: Class<*>, titleId: Int, tag: String?, state: Int) {
        val moveData = MainMoveData(moveAct, titleId).apply {
            this.tag = tag
            this.state = state
        }

        moveDataArrayList.add(moveData)
    }


    override fun initTitle(): Int {
        return R.string.menu_ui
    }
}