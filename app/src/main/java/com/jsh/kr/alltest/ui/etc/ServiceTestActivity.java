package com.jsh.kr.alltest.ui.etc;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.service.ForegroundTestService;
import com.jsh.kr.alltest.service.StartTestService;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Service test
 * https://developer.android.com/about/versions/oreo/background?hl=ko
 */
public class ServiceTestActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_service_start;
    private Button btn_service_stop;
    private Button btn_service_foreground_change;
    private Button btn_foreground_service_start;
    private Button btn_foreground_service_stop;
    private Button btn_background_loc_start;
    private Button btn_background_loc_stop;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        initUI();
        // TODO: bind service 추가 필요
    }

    private void initUI() {
        btn_service_start = findViewById(R.id.btn_service_start);
        btn_service_stop = findViewById(R.id.btn_service_stop);
        btn_service_foreground_change = findViewById(R.id.btn_service_foreground_change);
        btn_foreground_service_start = findViewById(R.id.btn_foreground_service_start);
        btn_foreground_service_stop = findViewById(R.id.btn_foreground_service_stop);
        btn_background_loc_start = findViewById(R.id.btn_background_loc_start);
        btn_background_loc_stop = findViewById(R.id.btn_background_loc_stop);

        btn_service_start.setOnClickListener(this);
        btn_service_stop.setOnClickListener(this);
        btn_service_foreground_change.setOnClickListener(this);
        btn_foreground_service_start.setOnClickListener(this);
        btn_foreground_service_stop.setOnClickListener(this);
        btn_background_loc_start.setOnClickListener(this);
        btn_background_loc_stop.setOnClickListener(this);
    }

    // sdk O, background limit.
    // after about 1 minutes. a service is closed
    private void testServiceStart() {
        Intent intentService = new Intent(this, StartTestService.class);
        startService(intentService);
    }

    private void testServiceStop() {
        Intent intentService = new Intent(this, StartTestService.class);
        stopService(intentService);
    }

    private void testServiceStartForeground() {
        Intent intentService = new Intent(this, ForegroundTestService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // if run startForeground() until 5 seconds, occur ANR
            // exist issue
            startForegroundService(intentService);
        } else {
            startService(intentService);
        }
    }

    // app background test
    private void testServiceLateStart() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // sdk O, limit background. when app is background, don't start a service after a few time
                // about 2 minutes,
//                testServiceStart();
                testServiceStartForeground();
            }
        }, 3*60*1000);
    }

    private void testForegroundServiceStart() {
        Intent intentService = new Intent(this, ForegroundTestService.class);
        startService(intentService);
    }

    private void testForegroundServiceStop() {
        Intent intentService = new Intent(this, ForegroundTestService.class);
        stopService(intentService);
    }

    private void testBackLocServiceStart() {
        // TODO: 권한 및 로직 추가 구현 필요
//        Intent intentService = new Intent(this, BackgroundLocationTestService.class);
//        startService(intentService);
    }

    private void testBackLocServiceStop() {
        // TODO: 권한 및 로직 추가 구현 필요
//        Intent intentService = new Intent(this, BackgroundLocationTestService.class);
//        stopService(intentService);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_service_start) {
            testServiceStart();
        } else if (id == R.id.btn_service_stop) {
            testServiceStop();
        } else if (id == R.id.btn_service_foreground_change) {
            testServiceLateStart();
        } else if (id == R.id.btn_foreground_service_start) {
            testForegroundServiceStart();
        } else if (id == R.id.btn_foreground_service_stop) {
            testForegroundServiceStop();
        } else if (id == R.id.btn_background_loc_start) {
            testBackLocServiceStart();
        } else if (id == R.id.btn_background_loc_stop) {
            testBackLocServiceStop();
        }
    }
}

