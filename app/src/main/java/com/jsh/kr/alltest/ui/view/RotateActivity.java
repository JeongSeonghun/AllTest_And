package com.jsh.kr.alltest.ui.view;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.ArrayList;

/**
 * rotate force display
 *
 * AndroidManifest.xml screenOrientation 속성값을 portrait(세로) 또는 landscape(가로) 로 설정
 *
 * ScreenOrientationEnforcer
 */
public class RotateActivity extends BaseActivity implements View.OnClickListener{

   ArrayList<String> pckNames;
   ListView lv_test;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_rotate);

      initUI();
      init();
   }

   private void initUI() {
      lv_test= (ListView)findViewById(R.id.lv_test);

      findViewById(R.id.btn_rotate_land).setOnClickListener(this);
      findViewById(R.id.btn_rotate_prot).setOnClickListener(this);
      findViewById(R.id.btn_rotate_sensor).setOnClickListener(this);

   }

   private void init() {
//        setList();
   }

   private void setLandscape() {
      this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
   }

   private void setPortrait() {
      this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
   }

   private void setSensor() {
      this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
   }

   @Override
   public void onClick(View v) {
      int id = v.getId();
      if (id == R.id.btn_rotate_land) {
         setLandscape();
      } else if (id == R.id.btn_rotate_prot) {
         setPortrait();
      } else if (id == R.id.btn_rotate_sensor) {
         setSensor();
      }
   }

}

