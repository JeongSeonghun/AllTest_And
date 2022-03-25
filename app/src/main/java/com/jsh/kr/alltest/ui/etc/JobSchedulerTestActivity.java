package com.jsh.kr.alltest.ui.etc;


import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityJobSchedulerTestBinding;
import com.jsh.kr.alltest.model.JobScheduleManager;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * JobScheduler Test
 *
 * setPeriodic above sdk N issue
 * https://stackoverflow.com/questions/38344220/job-scheduler-not-running-on-android-n
 */
public class JobSchedulerTestActivity extends BaseActivity implements View.OnClickListener {
   private final static String TAG = JobSchedulerTestActivity.class.getSimpleName();
   private ActivityJobSchedulerTestBinding binding;

   private JobScheduleManager scheduleManager = new JobScheduleManager();

   private final long interval = 1000;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_job_scheduler_test);
      init();
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         scheduleManager.cancelAll(this);
      }
   }

   private void init() {
      binding.btnJobStart.setOnClickListener(this);
      binding.btnJobChargeStart.setOnClickListener(this);
      showCurrentVersion();
   }

   private void showCurrentVersion() {
      String form = "current sdk int : %1$d (limit above 21)";

      binding.tvJobCurrentOs.setText(String.format(Locale.KOREA, form, Build.VERSION.SDK_INT));
   }

   private void startJobService() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         scheduleManager.startJobService(this, interval);
      }
   }

   private void startChangeJobService() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         scheduleManager.startChargeJobService(this, 2000, 1000);
      }
   }

   @Override
   public void onClick(View view) {
      int id = view.getId();
      if (id == R.id.btn_job_start) {
         startJobService();
      } else if (id == R.id.btn_job_charge_start) {
         startChangeJobService();
      }
   }
}

