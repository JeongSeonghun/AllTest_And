package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.widget.Spinner;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.adapter.SpinnerTestAdapter;
import com.jsh.kr.alltest.ui.BaseActivity;

/**
 * Spinner base test
 */
public class SpinnerTestActivity extends BaseActivity {

    private Spinner sp_spinner_test;
    private SpinnerTestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);

        initUI();
    }

    private void initUI(){
        sp_spinner_test = (Spinner) findViewById(R.id.sp_spinner_test);

        testAdapter = new SpinnerTestAdapter();

        sp_spinner_test.setAdapter(testAdapter);
    }
}
