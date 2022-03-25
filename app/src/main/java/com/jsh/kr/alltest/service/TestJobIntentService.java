package com.jsh.kr.alltest.service;


import android.content.Context;
import android.content.Intent;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.model.JobScheduleManager;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.util.TestAppLocalBroadcast;
import com.jsh.kr.alltestlib.util.TestCusLocalBroadcast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

/**
 * https://aroundck.tistory.com/5799
 */
public class TestJobIntentService extends JobIntentService {
    public static final int JOB_ID = JobScheduleManager.JOB_ID_TEST_INTENT;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String type = intent.getType();
        C.TestType testType = C.TestType.findByName(type);
        String data = null;
        LogUtil.d("TestIntentService", "onHandle type : "+type);

        if (testType != null) {
            switch (testType) {
                case TYPE_ONLY_CMD:
                    sendBroadcastToTestActivity(testType.name());
                    break;
                case TYPE_WITH_STRING_DATA:
                    data = intent.getStringExtra(C.Extra.EXTRA_KEY_STRING);
                    sendBroadcastToTestActivity(testType.name() + " : " +data);
                    break;
                case TYPE_BATTERY_CHANGE:
                    TestAppLocalBroadcast.send(getApplicationContext(), C.BroadCast.BatteryStatus);
                    break;
            }
        }
    }


    public static void startTestService(Context context, String type) {
        Intent intent = new Intent(context, TestJobIntentService.class);
        intent.setType(type);

        enqueueWork(context, TestJobIntentService.class, JOB_ID, intent);
    }

    public static void startTestService(Context context, String type, String data) {
        Intent intent = new Intent(context, TestJobIntentService.class);
        intent.setType(type);
        intent.putExtra(C.Extra.EXTRA_KEY_STRING, data);

        enqueueWork(context, TestJobIntentService.class, JOB_ID, intent);
    }

    private void sendBroadcastToTestActivity(String message) {
        Intent intent = new Intent(C.TestAction.ACTION_TWO.name());
        intent.putExtra(C.Extra.EXTRA_KEY_STRING, message);

        sendBroadcast(intent);
    }
}

