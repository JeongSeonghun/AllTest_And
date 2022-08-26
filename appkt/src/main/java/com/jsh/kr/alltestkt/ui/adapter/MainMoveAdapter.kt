package com.jsh.kr.alltestkt.ui.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.constant.C
import com.jsh.kr.alltestkt.model.base.MainMoveData
import com.jsh.kr.alltestkt.util.AppDataUtil
import java.util.ArrayList

class MainMoveAdapter(var activity: Activity) : BaseAdapter() {

    private var moveDataArrayList: ArrayList<MainMoveData>? = null

    init {

    }

    fun refresh(moveDataArrayList: ArrayList<MainMoveData>) {
        this.moveDataArrayList = moveDataArrayList
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (moveDataArrayList != null) {
            moveDataArrayList!!.size
        } else 0
    }

    override fun getItem(position: Int): MainMoveData? {
        // return if (position >= 0 && position < count) {
        return if (position in 0..(count - 1)) {
            moveDataArrayList!![position]
        } else null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        var holder: ViewHolder?
        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_move, null)

            holder = ViewHolder(view)
        } else {
            holder = view.tag as ViewHolder?
            if (holder == null) {
                holder = ViewHolder(view)
            }
        }

        val moveData = getItem(position)

        holder.btnMainMove.setText(moveData!!.btnNameId)
        setState(holder, moveData)

        holder.position = position
        view!!.tag = holder
        return view
    }

    private fun move(pos: Int) {
        val mainMoveData = getItem(pos)
        AppDataUtil.maintainTestAct = mainMoveData!!.actClass.name
        val intentAct = Intent(activity, mainMoveData.actClass)
        activity.startActivityForResult(intentAct, C.RequestCode.MoveTest)
    }

    private fun setState(holder: ViewHolder, data: MainMoveData) {
        val stateId: Int
        val colorText: String

        when (data.state) {
            C.State.Complete -> {
                stateId = R.string.state_complete
                colorText = C.StateColor.Complete
            }
            C.State.Proceed -> {
                stateId = R.string.state_proceed
                colorText = C.StateColor.Proceed
            }
            C.State.Resolution -> {
                stateId = R.string.state_resolution
                colorText = C.StateColor.Resolution
            }
            C.State.Test -> {
                stateId = R.string.state_test
                colorText = C.StateColor.Test
            }
            C.State.Close -> {
                stateId = R.string.state_close
                colorText = C.StateColor.Close
            }
            C.State.No_State -> {
                stateId = R.string.state_no
                colorText = C.StateColor.No_State
            }
            else -> {
                stateId = R.string.state_no
                colorText = C.StateColor.No_State
            }
        }

        holder.tvMainState.setText(stateId)
        holder.tvMainState.setTextColor(Color.parseColor(colorText))
    }

    private inner class ViewHolder internal constructor(view: View) {
        internal val btnMainMove: Button = view.findViewById(R.id.btn_main_move)
        internal val tvMainState: TextView = view.findViewById(R.id.tv_main_state)
        internal var position: Int = 0

        init {
            btnMainMove.setOnClickListener { move(position) }
        }
    }
}