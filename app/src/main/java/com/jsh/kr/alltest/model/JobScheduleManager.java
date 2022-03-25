package com.jsh.kr.alltest.model;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import com.jsh.kr.alltest.service.TestChargeJobService;
import com.jsh.kr.alltest.service.TestJobService;
import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.RequiresApi;

/**
 * https://tourspace.tistory.com/38
 */
public class JobScheduleManager {
    private final static String TAG = JobScheduleManager.class.getSimpleName();

    public final static int JOB_ID_TEST_JOB = 1;
    public final static int JOB_ID_TEST_CHARGE = 2;
    public final static int JOB_ID_TEST_INTENT = 1001;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startJobService(Context context, long interval) {
        JobInfo job;
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID_TEST_JOB, new ComponentName(context, TestJobService.class));
        // SDK N issue
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(interval);
        } else {
            builder.setPeriodic(interval);
        }
        job = builder.build();

        JobScheduler scheduler = (JobScheduler)context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        boolean hasBeenScheduled = false;

        if (scheduler != null) {
            for (JobInfo jobInfo : scheduler.getAllPendingJobs()) {
                if (jobInfo.getId() == TestJobService.JOB_ID) {
                    hasBeenScheduled  = true;
                }
            }
            LogUtil.d(TAG, "check scheduled "+hasBeenScheduled);
//                scheduler.cancel(TestJobService.JOB_ID);
            if (!hasBeenScheduled) {
                scheduler.schedule(job);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startChargeJobService(Context context, long minLatency, long deadline) {
        TestChargeJobService.isRepeat = true;
        ComponentName componentName = new ComponentName(context,
                TestChargeJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID_TEST_CHARGE, componentName);

        JobInfo info = builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                // minimum 15 minute
                .setMinimumLatency(minLatency)
                .setOverrideDeadline(deadline)
                .setRequiresCharging(true)
                .build();

        JobScheduler scheduler = (JobScheduler)context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (scheduler != null) {
            scheduler.schedule(info);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelSchedule(Context context, int scheduleId) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (scheduler != null) {
            scheduler.cancel(scheduleId);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelAll(Context context) {
        cancelSchedule(context, JOB_ID_TEST_JOB);
        TestChargeJobService.isRepeat = false;
        cancelSchedule(context, JOB_ID_TEST_CHARGE);
    }

}

