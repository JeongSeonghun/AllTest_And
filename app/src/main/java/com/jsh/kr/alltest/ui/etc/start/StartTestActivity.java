package com.jsh.kr.alltest.ui.etc.start;

import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.view.StartActTestView;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * startActivity test
 *
 *  animation
 *  https://dwfox.tistory.com/26
 *
 *  task
 *  https://m.blog.naver.com/PostView.nhn?blogId=unsrhythm&logNo=220239024030&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
 */
public class StartTestActivity extends BaseActivity {

   private StartActTestView ui_start_test;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_start_test);

      initUI();
   }

   private void initUI() {
      ui_start_test = findViewById(R.id.ui_start_test);

      ui_start_test.setActivity(this);
   }

}
