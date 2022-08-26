package com.jsh.kr.alltestkt.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.constant.C
import com.jsh.kr.alltestkt.ui.BaseActivity
import com.jsh.kr.alltestkt.util.AppDataUtil

class MainActivity : BaseActivity(), View.OnClickListener {
    private val allFragmentClass = arrayOf<Class<*>>(UIFragment::class.java, ActionFragment::class.java, SettingFragment::class.java)
    private val tabCount = allFragmentClass.size
    private val allTabButtons = arrayOfNulls<Button>(tabCount)

    private val currentFragment: Fragment?
        get() {

            val fragments = this.supportFragmentManager.fragments
            if (fragments != null) {
                for (frag in fragments) {

                    for (i in 0 until tabCount) {
                        val classes = allFragmentClass[i]
                        if (frag.javaClass == classes) {
                            if (!frag.isHidden) {
                                return frag
                            }
                        }
                    }

                }
            }

            return null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        allTabButtons[0] = findViewById<View>(R.id.ui_btn) as Button
        allTabButtons[1] = findViewById<View>(R.id.action_btn) as Button
        allTabButtons[2] = findViewById<View>(R.id.setting_btn) as Button

        for (btn in allTabButtons) {
            btn?.setOnClickListener(this)
        }

        initMenu()

    }

    private fun initMenu() {
        var pageIdx = 0

        val menu = AppDataUtil.maintainTestMenu
        pageIdx = when (AppDataUtil.maintainTestMenu) {
            C.Maintain.Menu_Action -> 1
            C.Maintain.Menu_UI -> 0
            else -> 0
        }

        changeFragment(pageIdx)
        moveBeforeAct()
    }

    private fun moveBeforeAct() {
        when (AppDataUtil.maintainTestMenu) {
            C.Maintain.Menu_Action, C.Maintain.Menu_UI -> {
                try {
                    AppDataUtil.maintainTestAct?.let {
                        val cls = Class.forName(it)
                        val intentAct = Intent(this, cls)
                        startActivityForResult(intentAct, C.RequestCode.MoveTest)
                    }
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }

            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ui_btn -> {
                AppDataUtil.maintainTestMenu = C.Maintain.Menu_UI
                changeFragment(0)
            }
            R.id.action_btn -> {
                AppDataUtil.maintainTestMenu = C.Maintain.Menu_Action
                changeFragment(1)
            }
            R.id.setting_btn -> changeFragment(2)
        }
    }

    private fun changeFragment(tabIndex: Int) {

        val currentFragment = currentFragment

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        var fragment: Fragment? = getFragment(tabIndex)
        if (fragment == null) {
            fragment = createFragment(tabIndex)
            if (fragment == null) {
                return
            }
            transaction.add(R.id.fragment_fl, fragment, allFragmentClass[tabIndex].name)
        } else {
            //            switch (tabIndex) {
            //                case 0:
            //                    ((UIFragment) getFragment(tabIndex)).initData();
            //                    break;
            //            }

            transaction.show(fragment)
        }

        transaction.commitAllowingStateLoss()

        for (i in 0 until tabCount) {
            allTabButtons[i]?.isSelected = tabIndex == i
        }
    }

    private fun getFragment(tabIndex: Int): Fragment? {
        return supportFragmentManager.findFragmentByTag(allFragmentClass[tabIndex].name)
    }

    private fun createFragment(tabIndex: Int): Fragment? {
        var fragment: Fragment? = null

        try {
            fragment = allFragmentClass[tabIndex].newInstance() as Fragment
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return fragment
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            C.RequestCode.MoveTest -> AppDataUtil.maintainTestAct = null
        }
    }
}