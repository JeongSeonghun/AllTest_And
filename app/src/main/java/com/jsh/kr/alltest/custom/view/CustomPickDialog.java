package com.jsh.kr.alltest.custom.view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.jsh.kr.alltest.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomPickDialog extends Dialog implements NumberPicker.OnValueChangeListener{

   private TextView tv_pick_title;
   private NumberPicker pick_year;
   private NumberPicker pick_month;
   private NumberPicker pick_day;
   private Button btn_pic_cancel;
   private Button btn_pic_ok;

   private Calendar calendar;
   private Date date;

   private OnChangeDateListener listener;

   private CustomPickDialog(@NonNull Context context) {
      super(context);
   }

   private CustomPickDialog(@NonNull Context context, int themeResId) {
      super(context, themeResId);
   }

   private CustomPickDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
      super(context, cancelable, cancelListener);
   }

   public interface OnChangeDateListener{
      void onChangeDate(Date date);
   }

   public static CustomPickDialog create(Context context, OnChangeDateListener listener){
      CustomPickDialog pickDialog = new CustomPickDialog(context);
      pickDialog.setCancelable(false);

      pickDialog.date = new Date();
      pickDialog.listener = listener;

      return pickDialog;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.dialog_pick);

      init();
   }

   private void init(){
      tv_pick_title = (TextView) findViewById(R.id.tv_pick_title);
      pick_year = (NumberPicker) findViewById(R.id.pick_year);
      pick_month = (NumberPicker) findViewById(R.id.pick_month);
      pick_day = (NumberPicker) findViewById(R.id.pick_day);
      btn_pic_cancel = (Button) findViewById(R.id.btn_pic_cancel);
      btn_pic_ok = (Button) findViewById(R.id.btn_pic_ok);

      btn_pic_cancel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });

      btn_pic_ok.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dateSelect();
         }
      });

      pick_year.setOnValueChangedListener(this);
      pick_month.setOnValueChangedListener(this);
      pick_day.setOnValueChangedListener(this);

      pick_year.setWrapSelectorWheel(false);

      calendar = Calendar.getInstance();
      calendar.setTime(date);
      tv_pick_title.setText(makeDateText());

      initDate();
   }

   private void initDate(){
      initYear();
      initMonth();
      initDay();
   }

   private void initYear() {
      int currentYear = calendar.get(Calendar.YEAR);
      int maxYear = calendar.getActualMaximum(Calendar.YEAR);

      pick_year.setMinValue(currentYear);
      pick_year.setValue(currentYear);
      pick_year.setMaxValue(maxYear);

   }

   private void initMonth() {
      // Canledar month 0 ~ 11
      int currentMonth = calendar.get(Calendar.MONTH) + 1;

      pick_month.setMinValue(1);
      pick_month.setMaxValue(12);
      pick_month.setValue(currentMonth);
   }

   private void initDay() {
      int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
      int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

      pick_day.setMinValue(1);
      pick_day.setMaxValue(lastDay);
      pick_day.setValue(currentDay);
   }

   private void dateSelect() {
      if(listener != null) {
         listener.onChangeDate(calendar.getTime());
      }
      dismiss();
   }

   private String makeDateText(){
      SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
      return format.format(calendar.getTime());
   }

   @Override
   public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
      switch (picker.getId()) {
         case R.id.pick_year:
            calendar.set(Calendar.YEAR, newVal);
            initDay();
            tv_pick_title.setText(makeDateText());
            break;
         case R.id.pick_month:
            // Canledar month 0 ~ 11
            calendar.set(Calendar.MONTH, newVal-1);
            initDay();
            tv_pick_title.setText(makeDateText());
            break;
         case R.id.pick_day:
            calendar.set(Calendar.DAY_OF_MONTH, newVal);
            tv_pick_title.setText(makeDateText());
            break;
      }
   }
}
