package com.jsh.kr.alltest.service;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import com.jsh.kr.alltest.model.JobScheduleManager;
import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TestChargeJobService extends JobService {
    public static final int JOB_ID = 401;
    private final static String TAG = TestChargeJobService.class.getSimpleName();

    public static boolean isRepeat = true;

    private JobParameters parameters;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        LogUtil.d(TAG, "onStartJob");
        parameters = jobParameters;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    LogUtil.d(TAG, "onStartJob thread");
                    onFinishedAndReSchedule();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        return true;
    }

    // 작업 완료 전 트리거 변경으로 인한 종료 시
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        LogUtil.d(TAG, "onStopJob");
        return false;
    }

    public void onFinishedAndReSchedule() {
        if (parameters == null) return;

        // true : rescheduling, false : close
        jobFinished(parameters, false);

        if (isRepeat) {
            new JobScheduleManager().startChargeJobService(getApplicationContext(), 2000, 1000);
        }
    }
}

