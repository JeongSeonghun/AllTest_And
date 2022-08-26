package com.jsh.kr.alltestkt.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.constant.C
import com.jsh.kr.alltestkt.model.base.MainMoveData
import java.util.ArrayList

abstract class BaseMainMenuFragment : BaseFragment() {
    private var tab_main: TabLayout? = null
    private var vp_main: ViewPager? = null
    private var tv_main_title: TextView? = null

    private var mainBasePagerAdapter: MainBasePagerAdapter? = null

    private val moveDataArrayList = ArrayList<MainMoveData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_main_menu, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        tab_main = view.findViewById(R.id.tab_main)
        vp_main = view.findViewById(R.id.vp_main)
        tv_main_title = view.findViewById(R.id.tv_main_title)

        mainBasePagerAdapter = MainBasePagerAdapter(childFragmentManager)
        val moveDataArrayList = initMoveDataList()
        mainBasePagerAdapter!!.initData(moveDataArrayList)
        vp_main!!.adapter = mainBasePagerAdapter

        tab_main!!.setupWithViewPager(vp_main)

        tv_main_title!!.setText(initTitle())
    }

    fun move(className: String?) {
        if (className == null || className.isEmpty()) {
            return
        }

        for (moveData in moveDataArrayList) {
            if (className == moveData.actClass.simpleName) {
                val intentAct = Intent(activity, moveData.actClass)
                startActivityForResult(intentAct, C.RequestCode.MoveTest)
            }
        }
    }

    abstract fun initMoveDataList(): ArrayList<MainMoveData>

    abstract fun initTitle(): Int

    inner class MainBasePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private var moveDataArrayList: ArrayList<MainMoveData>? = null
        private var tagList = ArrayList<String>()
        private var fragments = ArrayList<BaseMainFragment>()

        init {
            addTag(C.TAG.All)
        }

        fun initData(moveDataArrayList: ArrayList<MainMoveData>) {
            this.moveDataArrayList = moveDataArrayList
            initTagList()
        }

        private fun initTagList() {
            if (moveDataArrayList != null && moveDataArrayList!!.size > 0) {
                getTagFragment(C.TAG.All).initData(moveDataArrayList!!)

                for (moveData in moveDataArrayList!!) {
                    var tag: String? = moveData.tag

                    if (tag == null || tag.isEmpty()) {
                        tag = C.TAG.Etc
                    }

                    if (!tagList.contains(tag)) {
                        addTag(tag)
                    }

                    getTagFragment(tag).addData(moveData)
                }
            }
        }

        private fun addTag(tag: String) {
            if (!tagList.contains(tag)) {
                tagList.add(tag)
                fragments.add(BaseMainFragment())
            }
        }

        private fun getTagFragment(tag: String): BaseMainFragment {
            val idx = tagList.indexOf(tag)

            return fragments[idx]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            val fragment = fragments[position]
            return tagList[position] + "(" + fragment.listSize + ")"
        }

        override fun getItem(position: Int): Fragment {
//            fragment.refresh();
            return fragments[position]
            //            return fragments.get(position);
            //            return new Fragment();
        }

        override fun getCount(): Int {
            return tagList.size
        }
    }
}