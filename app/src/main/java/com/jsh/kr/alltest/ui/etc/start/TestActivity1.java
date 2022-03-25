package com.jsh.kr.alltest.ui.etc.start;

import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.view.StartActTestView;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 *  startActivity test
 */
public class TestActivity1 extends BaseActivity {

   private StartActTestView ui_start_test;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_test);

      initUI();
   }

   private void initUI() {
      ui_start_test = findViewById(R.id.ui_start_test);

      ui_start_test.setActivity(this);
   }

}
