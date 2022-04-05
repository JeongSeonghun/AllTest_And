package com.jsh.kr.alltestwear.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jsh.kr.alltestwear.R;

import androidx.annotation.Nullable;

public class IntroActivity extends BaseWearActivity{

   private final int DELAY_TIME = 3000;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_intro);

      startTimer();
   }

   private void startTimer() {
      new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
            next();
         }
      }, DELAY_TIME);
   }

   private void next() {
      Intent intentAct = new Intent(this, MainActivity.class);

      startActivity(intentAct);

      finish();
   }
}
