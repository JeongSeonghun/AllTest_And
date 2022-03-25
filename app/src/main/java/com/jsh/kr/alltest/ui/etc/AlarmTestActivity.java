package com.jsh.kr.alltest.ui.etc;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * alarm test
 *
 * https://developer88.tistory.com/83
 */
public class AlarmTestActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

   private static final String TAG = AlarmTestActivity.class.getSimpleName();
   private final int Request_Code = 1;

   private RadioButton rb_alarm_time_dial;
   private RadioButton rb_alarm_time;
   private Button btn_alarm_time;
   private TextView tv_alarm_time;
   private Button btn_alarm_start;
   private RecyclerView rv_alarm_list;
   private EditText et_alarm_minutes;
   private EditText et_alarm_seconds;
   private EditText et_alarm_interval;

   private Date selectTime;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_alarm_test);
      initUI();
      registerAlarmBroadcast();
   }

   private void initUI() {
      btn_alarm_time = findViewById(R.id.btn_alarm_time);
      tv_alarm_time = findViewById(R.id.tv_alarm_time);
      btn_alarm_start = findViewById(R.id.btn_alarm_start);
      rv_alarm_list = findViewById(R.id.rv_alarm_list);
      rb_alarm_time_dial = findViewById(R.id.rb_alarm_time_dial);
      rb_alarm_time = findViewById(R.id.rb_alarm_time);
      et_alarm_minutes = findViewById(R.id.et_alarm_minutes);
      et_alarm_seconds = findViewById(R.id.et_alarm_seconds);
      et_alarm_interval = findViewById(R.id.et_alarm_interval);

      btn_alarm_time.setOnClickListener(this);
      btn_alarm_start.setOnClickListener(this);

      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      rv_alarm_list.setLayoutManager(linearLayoutManager);

      rb_alarm_time_dial.setOnCheckedChangeListener(this);
      rb_alarm_time.setOnCheckedChangeListener(this);

   }

   @Override
   public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
      int id = compoundButton.getId();
      if (id == R.id.rb_alarm_time_dial) {
         if (checked) rb_alarm_time.setChecked(false);
      } else if (id == R.id.rb_alarm_time) {
         if (checked) rb_alarm_time_dial.setChecked(false);
      }
   }

   private void registerAlarmBroadcast() {
      IntentFilter filter = new IntentFilter();
      filter.addAction(C.BroadCast.AlarmTest);

      registerReceiver(alarmReceiver, filter);
   }

   private void unRegisterAlarmBroadcast() {
      unregisterReceiver(alarmReceiver);
   }

   private BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
         if (intent == null) return;

         String action = intent.getAction();

         LogUtil.d(TAG, "broadcast receive : "+action);
         if (C.BroadCast.AlarmTest.equals(action)) {
            Toast.makeText(context, "alarm test", Toast.LENGTH_SHORT).show();
         }

      }
   };

   @Override
   protected void onDestroy() {
      super.onDestroy();
      unRegisterAlarmBroadcast();
   }

   private void showTimePickerDialog() {
      Calendar calendar = Calendar.getInstance();
      TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
         @Override
         public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            setSelectTime(hourOfDay, minute);
         }
      }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

      dialog.show();
   }

   private void setSelectTime(int hourOfDay, int minute) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
      calendar.set(Calendar.MINUTE, minute);

      selectTime = calendar.getTime();
      tv_alarm_time.setText(makeAlarmShowTime(selectTime.getTime()));
   }

   private long makeAfterTime() {
      Calendar calendar = Calendar.getInstance();
      int minutes = 0;
      int seconds = 0;
      String minStr = et_alarm_minutes.getText().toString();
      String secStr = et_alarm_seconds.getText().toString();

      if (!minStr.isEmpty()) {
         minutes = Integer.valueOf(minStr);
      }

      if (!secStr.isEmpty()) {
         seconds = Integer.valueOf(secStr);
      }

      calendar.add(Calendar.MINUTE, minutes);
      calendar.add(Calendar.SECOND, seconds);

      return calendar.getTimeInMillis();

   }

   private void startAlarm() {
      String intervalStr = et_alarm_interval.getText().toString();
      long time = -1;
      long interval = intervalStr.isEmpty() ? -1 : Long.valueOf(intervalStr);

      if (rb_alarm_time.isChecked()) {
         if (selectTime != null) {
            time = selectTime.getTime();
         }
      } else {
         time = makeAfterTime();
      }

      if (time == -1) return;

      if (interval > 0) {
         setAlarm(time);
      } else {
         setRepeatAlarm(time, interval);
      }
   }

   private void setAlarm(long time) {
      LogUtil.d(TAG, "setAlarm : "+makeAlarmShowTime(time));
      AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

      if (manager != null) {
         manager.set(AlarmManager.RTC, time, makeAlarmIntent());
      }

   }

   private void setRepeatAlarm(long time, long interval) {
      LogUtil.d(TAG, "setRepeatAlarm : "+makeAlarmShowTime(time));
      AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

      if (manager != null) {
         manager.setRepeating(AlarmManager.RTC, time, interval, makeAlarmIntent());
      }
   }

   private void cancelAlarm() {
      LogUtil.d(TAG, "cancelAlarm");
      AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

      if (manager != null) {
         manager.cancel(makeAlarmIntent());
      }
   }

   private String makeAlarmShowTime(long time) {
      SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss", Locale.KOREA);

      return form.format(new Date(time));
   }

   private PendingIntent makeAlarmIntent() {
      Intent intent = new Intent(C.BroadCast.AlarmTest);

      PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Request_Code, intent, PendingIntent.FLAG_ONE_SHOT);

      return pendingIntent;
   }

   @Override
   public void onClick(View view) {
      int id = view.getId();
      if (id == R.id.btn_alarm_time) {
         showTimePickerDialog();
      } else if (id == R.id.btn_alarm_start) {
         startAlarm();
      }
   }

   class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {


      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return null;
      }

      @Override
      public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      }

      @Override
      public int getItemCount() {
         return 0;
      }

      class ViewHolder extends RecyclerView.ViewHolder {

         public ViewHolder(View itemView) {
            super(itemView);
         }
      }
   }
}

