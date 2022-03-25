package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.view.CustomPickDialog;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * custom DatePicker test
 * because DatePicker have no limit date issue
 */
public class CustomPickTestActivity extends BaseActivity implements View.OnClickListener{

   TextView tv_pick_test_date;
   Button btn_pick_test_date;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_custom_pick_test);

      tv_pick_test_date = findViewById(R.id.tv_pick_test_date);
      btn_pick_test_date = findViewById(R.id.btn_pick_test_date);

      btn_pick_test_date.setOnClickListener(this);

   }

   private void showDateDialog() {
      CustomPickDialog dialog = CustomPickDialog.create(this, new CustomPickDialog.OnChangeDateListener() {
         @Override
         public void onChangeDate(Date date) {
            tv_pick_test_date.setText(makeDateText(date));
         }
      });

      dialog.show();
   }

   private String makeDateText(Date date){
      SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
      return format.format(date);
   }

   @Override
   public void onClick(View v) {
      switch (v.getId()) {
         case R.id.btn_pick_test_date:
            showDateDialog();
            break;
      }
   }
}
