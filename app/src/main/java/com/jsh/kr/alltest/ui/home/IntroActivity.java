package com.jsh.kr.alltest.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

public class IntroActivity extends BaseActivity {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        next();
    }

    private void startTimer() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        }, 500);
    }

    private void goToMain() {
        Intent intentAct = new Intent(this, MainActivity.class);
        intentAct.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intentAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentAct);
        finish();
    }

    private void next() {
        startTimer();
    }
}
