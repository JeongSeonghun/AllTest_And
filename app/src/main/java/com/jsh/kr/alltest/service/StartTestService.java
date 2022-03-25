package com.jsh.kr.alltest.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jsh.kr.alltest.util.DownCounter;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.util.OnDownCounterListener;

import androidx.annotation.Nullable;

public class StartTestService extends Service {

    private final static String TAG = StartTestService.class.getSimpleName();

    private DownCounter downCounter = new DownCounter(120);

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "onCreate()");
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "onStartCommand()");
//        return START_NOT_STICKY;
        return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        downCounter.setDownCountListener(new OnDownCounterListener() {
            @Override
            public void onTick(long cnt) {
                if (cnt == 0) {
//                    stopSelf();
                    downCounter.stopCount();
                    downCounter = new DownCounter(120);
                    downCounter.start();
                }
            }
        });

        downCounter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy()");
        downCounter.stopCount();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

