package com.jsh.kr.alltestkt.ui.home

import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.constant.C
import com.jsh.kr.alltestkt.model.base.MainMoveData
import com.jsh.kr.alltestkt.ui.BaseMainMenuFragment
import com.jsh.kr.alltestkt.ui.base.KotlinBaseTestActivity
import com.jsh.kr.alltestkt.ui.base.KotlinJavaTestActivity
import com.jsh.kr.alltestkt.ui.etc.PermissionContractsTestActivity
import com.jsh.kr.alltestkt.ui.etc.PermissionTestActivity

class ActionFragment : BaseMainMenuFragment() {

    private val moveDataArrayList = ArrayList<MainMoveData>()

    override fun initMoveDataList(): ArrayList<MainMoveData> {

        addActList(KotlinBaseTestActivity::class.java, R.string.move_kotlin_base_test, C.State.Test)
        addActList(KotlinJavaTestActivity::class.java, R.string.move_kotlin_java_test, C.State.Test)

        addActList(PermissionTestActivity::class.java, R.string.move_permission_test, C.State.Test)
        addActList(PermissionContractsTestActivity::class.java, R.string.move_permission_contracts_test, C.State.Test)

        return moveDataArrayList
    }

    private fun addActList(moveAct: Class<*>, titleId: Int, state: Int = C.State.No_State) {
        addList(moveAct, titleId, null, state)
    }

    private fun addList(moveAct: Class<*>, titleId: Int, tag: String?, state: Int) {
        val moveData = MainMoveData(moveAct, titleId).apply {
            this.tag = tag
            this.state = state
        }

        moveDataArrayList.add(moveData)
    }

    override fun initTitle(): Int {
        return R.string.menu_action
    }
}