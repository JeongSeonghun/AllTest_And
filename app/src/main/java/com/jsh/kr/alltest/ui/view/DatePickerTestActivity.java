package com.jsh.kr.alltest.ui.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.TestDataUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * DatePicker base test
 */
public class DatePickerTestActivity extends BaseActivity implements View.OnClickListener{

    public static void show(Context context) {
        Intent intentAct = new Intent(context, DatePickerTestActivity.class);
        context.startActivity(intentAct);
    }

    private DatePicker date_picker;
    private EditText min_date_et;
    private EditText max_date_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_test);

        initUI();
    }

    private void initUI() {
        date_picker = (DatePicker) findViewById(R.id.date_picker);
        findViewById(R.id.pick_dialog).setOnClickListener(this);
        findViewById(R.id.time_pick_dialog).setOnClickListener(this);
        min_date_et = findViewById(R.id.min_date_et);
        max_date_et = findViewById(R.id.max_date_et);
    }

    private void showDialog() {

        Calendar calendar = Calendar.getInstance();
        int yewr = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Context context = this;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            // API 24 이상일 경우 시스템 기본 테마 사용
//            context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
//        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, yewr, month, day);

        DatePicker picker = datePickerDialog.getDatePicker();
        Date min = TestDataUtil.makeDate(min_date_et.getText().toString());
        Date max = TestDataUtil.makeDate(max_date_et.getText().toString());
        if (min != null) picker.setMinDate(min.getTime());
        if (max != null) picker.setMaxDate(max.getTime());

        datePickerDialog.show();

    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        int hDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, hDay, minute, false);

        timePickerDialog.show();
    }

    private String makeDateText(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.pick_dialog) {
            showDialog();
        } else if (id == R.id.time_pick_dialog) {
            showTimeDialog();
        }
    }
}
