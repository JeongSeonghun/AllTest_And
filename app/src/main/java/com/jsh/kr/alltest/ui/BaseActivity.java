package com.jsh.kr.alltest.ui;

import android.os.Bundle;

import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(this.getClass().getSimpleName(), "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(this.getClass().getSimpleName(), "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(this.getClass().getSimpleName(), "onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(this.getClass().getSimpleName(), "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(this.getClass().getSimpleName(), "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(this.getClass().getSimpleName(), "onDestroy()");
    }

    @Override
    public void finish() {
        super.finish();
        LogUtil.d(this.getClass().getSimpleName(), "finish()");
    }

}
