package com.jsh.kr.alltest.ui.etc.start;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.ActivityTestUtil;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class TestFinishActivity extends BaseActivity implements View.OnClickListener{

   private Class[] actList = ActivityTestUtil.actList;
   private String[] flagList = ActivityTestUtil.flagList;
   private String[] flagList2 = ActivityTestUtil.flagList2;

   private Spinner sp_act;
   private Spinner sp_flag;
   private Spinner sp_flag2;
   private Button btn_start_act;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_test_finish);

      initUI();
      init();
   }

   private void initUI() {
      sp_act = findViewById(R.id.sp_act);
      sp_flag = findViewById(R.id.sp_flag);
      sp_flag2 = findViewById(R.id.sp_flag2);
      btn_start_act = findViewById(R.id.btn_start_act);

      btn_start_act.setOnClickListener(this);
   }

   private void init() {

      sp_act.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, makeActStringList()));
      sp_flag.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, flagList));
      sp_flag2.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, flagList2));

   }

   private ArrayList<String> makeActStringList() {
      ArrayList<String> list = new ArrayList<>();
      for(Class act : actList) {
         list.add(act.getSimpleName());
      }
      return list;
   }

   private void startAct() {
      Class selClass = actList[sp_act.getSelectedItemPosition()];

      int flag = ActivityTestUtil.getFlag(sp_flag.getSelectedItemPosition());
      int flag2 = ActivityTestUtil.getFlag2(sp_flag2.getSelectedItemPosition());

      Intent intentAct = new Intent(this, selClass);

      if(flag != -1 && flag2 != -1) {
         intentAct.setFlags(flag | flag2);
      } else if(flag != -1) {
         intentAct.setFlags(flag);
      }

      startActivity(intentAct);

   }

   @Override
   public void onClick(View v) {
      switch (v.getId()) {
         case R.id.btn_start_act:
            startAct();
            break;
      }
   }

}

