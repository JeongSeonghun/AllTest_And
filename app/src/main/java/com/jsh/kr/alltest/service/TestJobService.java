package com.jsh.kr.alltest.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;

import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TestJobService extends JobService {
    private static final String TAG = TestJobService.class.getSimpleName();
    public static final int JOB_ID = 2001;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        LogUtil.d(TAG, "onStartJob");
        TestJobTask task = new TestJobTask();
        task.execute(jobParameters);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        LogUtil.d(TAG, "onStopJob");
        return false;
    }

    private class TestJobTask extends AsyncTask<JobParameters, Void, Void> {

        @Override

        public Void doInBackground(JobParameters... params) {
            LogUtil.d(TAG, "doInBackground");
            JobParameters jobParams = params[0];

            jobFinished(jobParams, false);

            return null;

        }

    }

}

