package com.jsh.kr.alltest.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.AppDataUtil;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import android.widget.Button;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private final Class[] allFragmentClass = {
            UIFragment.class, ActionFragment.class, SettingFragment.class};
    private final int tabCount = allFragmentClass.length;
    private final Button[] allTabButtons = new Button[tabCount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI(){
        allTabButtons[0] = (Button) findViewById(R.id.ui_btn);
        allTabButtons[1] = (Button) findViewById(R.id.action_btn);
        allTabButtons[2] = (Button) findViewById(R.id.setting_btn);

        for (Button btn : allTabButtons) {
            btn.setOnClickListener(this);
        }

        initMenu();

        LogUtil.d("test", "display height : "+getWindow().getDecorView().getHeight());

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("test", "display height : "+getWindow().getDecorView().getHeight());
    }

    private void initMenu() {
        int pageIdx = 0;

        switch (AppDataUtil.getInstance().getMaintainTestMenu()) {
            case C.Maintain.Menu_Action:
                pageIdx = 1;
                break;
            case C.Maintain.Menu_UI:
            default:
                break;
        }

        changeFragment(pageIdx);
        moveBeforeAct();
    }

    private void moveBeforeAct() {
        if (!AppDataUtil.getInstance().isMaintainTestMode()) {
            return;
        }

        switch (AppDataUtil.getInstance().getMaintainTestMenu()) {
            case C.Maintain.Menu_Action:
            case C.Maintain.Menu_UI:
                if(AppDataUtil.getInstance().getMaintainTestAct() == null)
                    return;
                try {
                    Class<?> cls = Class.forName(AppDataUtil.getInstance().getMaintainTestAct());
                    Intent intentAct = new Intent(this, cls);
                    startActivityForResult(intentAct, C.RequestCode.MoveTest);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ui_btn) {
            AppDataUtil.getInstance().setMaintainTestMenu(C.Maintain.Menu_UI);
            changeFragment(0);
        } else if (id == R.id.action_btn) {
            AppDataUtil.getInstance().setMaintainTestMenu(C.Maintain.Menu_Action);
            changeFragment(1);
        } else if (id == R.id.setting_btn) {
            changeFragment(2);
        }
    }

    public Fragment getCurrentFragment() {

        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment frag : fragments) {
            for (int i=0; i<tabCount; i++) {
                Class classes = allFragmentClass[i];
                if(frag.getClass() == classes){
                    if (!frag.isHidden()) {
                        return frag;
                    }
                }
            }

        }

        return null;
    }

    protected void changeFragment(int tabIndex) {

        Fragment currentFragment = getCurrentFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        Fragment fragment = getFragment(tabIndex);
        if (fragment == null) {
            fragment = createFragment(tabIndex);
            if (fragment == null) {
                return;
            }
            transaction.add(R.id.fragment_fl, fragment, allFragmentClass[tabIndex].getName());
        } else {
//            switch (tabIndex) {
//                case 0:
//                    ((UIFragment) getFragment(tabIndex)).initData();
//                    break;
//            }

            transaction.show(fragment);
        }

        transaction.commitAllowingStateLoss();

        for (int i=0; i<tabCount; i++) {
            if (tabIndex == i) {
                allTabButtons[i].setSelected(true);
            } else {
                allTabButtons[i].setSelected(false);
            }
        }
    }

    protected Fragment getFragment(int tabIndex) {
        return getSupportFragmentManager().findFragmentByTag(allFragmentClass[tabIndex].getName());
    }

    protected Fragment createFragment(int tabIndex) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) allFragmentClass[tabIndex].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case C.RequestCode.MoveTest:
                AppDataUtil.getInstance().setMaintainTestAct(null);
                break;
        }
    }
}
