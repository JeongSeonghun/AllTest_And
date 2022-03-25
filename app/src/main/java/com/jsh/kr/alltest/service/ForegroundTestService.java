package com.jsh.kr.alltest.service;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.util.DownCounter;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.util.OnDownCounterListener;

import androidx.annotation.Nullable;

public class ForegroundTestService extends Service {
    private final static String TAG = ForegroundTestService.class.getSimpleName();

    private final int maxCnt = 20;

    private DownCounter downCounter = new DownCounter(maxCnt);

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        startDownCount();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        startTestServiceForeground();
    }

    private void startTestServiceForeground() {
        // icon 없으면 내용 표시 안됨
        Notification notification = null;
        LogUtil.d(TAG, "init sdk : "+ Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // android 8 부터는 channel 추가 필요
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if(notificationManager != null) {
                NotificationChannel notiChannel = notificationManager.getNotificationChannel(C.Notification.FOREGROUND_TEST_SERVICE_CHANNEL_ID);
                if (notiChannel == null) {
                    notiChannel = new NotificationChannel(C.Notification.FOREGROUND_TEST_SERVICE_CHANNEL_ID, C.Notification.FOREGROUND_TEST_SERVICE_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                    notiChannel.setDescription(getString(R.string.notification_fore_test_channel_desc));
                    notiChannel.enableLights(true);
                    notificationManager.createNotificationChannel(notiChannel);
                    LogUtil.d(TAG, "createNotificationChannel");
                }
            }

            notification = new Notification.Builder(this.getBaseContext(),C.Notification.FOREGROUND_TEST_SERVICE_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(TAG)
                    .setContentText(getString(R.string.notification_fore_test_desc))
//                    .setChannelId(C.Notification.FOREGROUND_TEST_SERVICE_CHANNEL_ID)
                    .build();
        }else {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(TAG)
                    .setContentText(getString(R.string.notification_fore_test_desc))
                    .build();
        }

        startForeground(C.Notification.FOREGROUND_TEST_SERVICE_NOTIGY_ID, notification);
    }

    private void stopTestServiceForeground() {
        LogUtil.d(TAG, "stopTestServiceForeground");
        stopForeground(true);
    }

    private void startDownCount() {
        downCounter.setDownCountListener(new OnDownCounterListener() {
            @Override
            public void onTick(long cnt) {
                if (cnt <= 0) {
                    stopForegroundTestService();
                }
            }
        });
        downCounter.start();
    }

    void stopForegroundTestService() {
        LogUtil.d(TAG, "stopForegroundTestService");
        stopSelf();
    }

    private void stopCounter() {
        LogUtil.d(TAG, "stopCounter : "+downCounter.isRunning());
        if (downCounter.isRunning()) {
            downCounter.stopCount();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCounter();
        stopTestServiceForeground();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

