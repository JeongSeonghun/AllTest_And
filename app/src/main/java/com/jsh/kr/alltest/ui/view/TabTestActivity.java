package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.adapter.ViewPagerTestAdapter;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * TabLayout base test
 */
public class TabTestActivity extends BaseActivity {

    private TabLayout tb_tab_test;
    private ViewPager vp_tab_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);

        initUI();
    }

    private void initUI() {
        tb_tab_test = findViewById(R.id.tb_tab_test);
        vp_tab_test = findViewById(R.id.vp_tab_test);

        ViewPagerTestAdapter pagerTestAdapter = new ViewPagerTestAdapter();
        vp_tab_test.setAdapter(pagerTestAdapter);
        vp_tab_test.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tb_tab_test));

        tb_tab_test.setupWithViewPager(vp_tab_test);

        tb_tab_test.addOnTabSelectedListener(tabSelectedListener);

        initTab();
    }

    private void initTab() {

        tb_tab_test.getTabAt(0).setText(R.string.tab_test_tab1);
        tb_tab_test.getTabAt(1).setText(R.string.tab_test_tab2);
        tb_tab_test.getTabAt(1).setText(R.string.tab_test_tab3);

        for(int i=0; i < tb_tab_test.getTabCount(); i++) {
            View tab = ((ViewGroup) tb_tab_test.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(makeDPToPixel(22), 0, makeDPToPixel(22), 0);
            tab.requestLayout();
        }
    }

    private int makeDPToPixel(int dps){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if(vp_tab_test != null){
                vp_tab_test.setCurrentItem(tab.getPosition());
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }

    };
}

