package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * 투명 배경 테스트
 */
public class TransparentActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
    }

}