package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.os.SystemClock;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityTimeTestBinding;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class TimeTestActivity extends BaseActivity {

   private ActivityTimeTestBinding binding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_time_test);

      initUI();
      init();
   }

   private void initUI() {
      binding.updateBtn.setOnClickListener(v -> init());
   }

   private void init() {
      showCurrentTime();
      showUptime();
      showElapsedRealTime();
   }

   private void showCurrentTime() {
      String form = "current Time : %1$s";
      SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);

      binding.tvTimeCurrent.setText(String.format(form, dateFormat.format(new Date(System.currentTimeMillis()))));
   }

   private void showUptime() {
      String form = "uptime : %1$s";

      long uptime = SystemClock.uptimeMillis();

      binding.tvTimeUptime.setText(String.format(form, loadTime(uptime)));
   }

   private void showElapsedRealTime() {
      String form = "elapsedReal time : %1$s";

      long elapsedReal = SystemClock.elapsedRealtime();

      binding.tvTimeElapsedReal.setText(String.format(form, loadTime(elapsedReal)));

   }

   private String loadTime(long duration) {
      StringBuilder sb = new StringBuilder();

      long remain = duration;
      long day = TimeUnit.MILLISECONDS.toDays(duration);
      remain -= TimeUnit.DAYS.toMillis(day);
      long hour = TimeUnit.MILLISECONDS.toHours(remain);
      remain -= TimeUnit.HOURS.toMillis(hour);
      long min = TimeUnit.MILLISECONDS.toMinutes(remain);
      remain -= TimeUnit.MINUTES.toMillis(min);
      long sec = remain / 1000;
      long millis = remain % 1000;

      if (day > 0) {
         sb.append(day).append("D ");
      }
      if (hour > 0) {
         sb.append(hour).append("H ");
      }
      if (min > 0) {
         sb.append(min).append("m ");
      }
      if (sec > 0) {
         sb.append(sec).append("s ");
      }
      sb.append(millis).append("millis");
      return sb.toString();
   }

}
