package com.jsh.kr.alltest.custom.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.util.ActivityTestUtil;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class StartActTestView extends LinearLayout implements View.OnClickListener {

   private Class[] actList = ActivityTestUtil.actList;
   private String[] flagList = ActivityTestUtil.flagList;
   private String[] flagList2 = ActivityTestUtil.flagList2;

   private TextView tv_start_test_task;
   private Spinner sp_act;
   private Spinner sp_flag;
   private Spinner sp_flag2;
   private Spinner sp_flag3;
   private Button btn_start_act;

   private RadioButton rb_act_anim_no;
   private RadioButton rb_act_anim_left;
   private RadioGroup rg_act_anim;

   private Activity activity;

   public StartActTestView(Context context) {
      this(context, null, 0);
   }

   public StartActTestView(Context context, @Nullable AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public StartActTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      LayoutInflater.from(context).inflate(R.layout.ui_start_test, this);
      initUI();
      initData();
   }

   private void initUI() {
      tv_start_test_task = findViewById(R.id.tv_start_test_task);
      sp_act = findViewById(R.id.sp_act);
      sp_flag = findViewById(R.id.sp_flag);
      sp_flag2 = findViewById(R.id.sp_flag2);
      sp_flag3 = findViewById(R.id.sp_flag3);
      btn_start_act = findViewById(R.id.btn_start_act);

      btn_start_act.setOnClickListener(this);

      addUI();
   }

   private void addUI() {
      rg_act_anim = findViewById(R.id.rg_act_anim);
      rb_act_anim_no = findViewById(R.id.rb_act_anim_no);
      rb_act_anim_left = findViewById(R.id.rb_act_anim_left);

      rg_act_anim.setOnCheckedChangeListener(checkedChangeListener);
   }

   private void initData() {

      sp_act.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, makeActStringList()));
      sp_flag.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, flagList));
      sp_flag2.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, flagList));
      sp_flag3.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, flagList2));

   }

   public void setActivity(Activity activity) {
      this.activity = activity;
      tv_start_test_task.setText("task id: "+activity.getTaskId());
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

      final int flag = ActivityTestUtil.getFlag(sp_flag.getSelectedItemPosition());
      final int flag2 = ActivityTestUtil.getFlag(sp_flag2.getSelectedItemPosition());
      final int flag3 = ActivityTestUtil.getFlag2(sp_flag3.getSelectedItemPosition());

      Intent intentAct = new Intent(activity, selClass);

      if (flag != -1) {
         intentAct.addFlags(flag);
      }

      if (flag2 != -1) {
         intentAct.addFlags(flag2);
      }

      if (flag3 != -1) {
         intentAct.addFlags(flag3);
      }

      activity.startActivity(intentAct);
      setAnim();
   }

   private void setAnim() {
      switch (rg_act_anim.getCheckedRadioButtonId()) {
         case R.id.rb_act_anim_no:
            break;
         case R.id.rb_act_anim_left:
            activity.overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
            break;
      }
   }

   @Override
   public void onClick(View v) {
      switch (v.getId()) {
         case R.id.btn_start_act:
            startAct();
            break;
      }
   }

   private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
         switch (checkedId) {
            case R.id.rb_act_anim_no:
               break;
            case R.id.rb_act_anim_left:
               break;
         }
      }
   };
}

