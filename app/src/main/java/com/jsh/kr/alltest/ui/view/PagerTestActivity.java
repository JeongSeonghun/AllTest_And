package com.jsh.kr.alltest.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * ViewPager base test
 */
public class PagerTestActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager vp_pager_test;

    private Spinner sp_pager_cnt;
    private EditText et_pager_content;
    private Button btn_pager_refresh;
    private Spinner sp_pager_mode;

    private ViewPagerTestAdapter pagerTestAdapter;

    private final int[] layoutIds = new int[]{
            R.layout.item_pager_test1, R.layout.item_pager_test2, R.layout.item_pager_test3
    };

    private final int Mode_Default =1;
    private final int Mode_No_Position = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_test);

        initUI();
    }

    private void initUI() {
        vp_pager_test = (ViewPager) findViewById(R.id.vp_pager_test);

        pagerTestAdapter = new ViewPagerTestAdapter();

        vp_pager_test.setAdapter(pagerTestAdapter);

        initAddUI();
    }

    private void initAddUI() {
        sp_pager_cnt = findViewById(R.id.sp_pager_cnt);
        et_pager_content = findViewById(R.id.et_pager_content);
        btn_pager_refresh = findViewById(R.id.btn_pager_refresh);
        sp_pager_mode = findViewById(R.id.sp_pager_mode);

        btn_pager_refresh.setOnClickListener(this);

        initPageCntSpinner();
        initModeSpinner();
    }

    private void initPageCntSpinner() {
        ArrayList<String> list = new ArrayList<>();
        for(int cnt = 1 ; cnt <= layoutIds.length ; cnt ++) {
            list.add(String.valueOf(cnt));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_pager_cnt.setAdapter(arrayAdapter);
    }

    private void initModeSpinner() {
        String[] list = {"Normal", "NoPosition"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_pager_mode.setAdapter(arrayAdapter);
    }

    private void refresh() {
        String content = et_pager_content.getText().toString();
        String cntStr = (String) sp_pager_cnt.getSelectedItem();
        int pageCnt = Integer.parseInt(cntStr);
        int mode = sp_pager_mode.getSelectedItemPosition() + 1;

        pagerTestAdapter.setMode(mode);
        pagerTestAdapter.refresh(pageCnt, content);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pager_refresh) {
            refresh();
        }
    }

    class ViewPagerTestAdapter extends PagerAdapter {

        private int pageCnt = 0;
        private String cont = null;
        private int mode;

        private void setMode(int mode) {
            this.mode = mode;
        }

        private void refresh(int cnt, String cont) {
            pageCnt = cnt;
            this.cont = cont;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return pageCnt;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            switch (mode) {
                case Mode_No_Position:
                    return POSITION_NONE;
                case Mode_Default:
                default:
                    return super.getItemPosition(object);
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(layoutIds[position], container, false);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}

