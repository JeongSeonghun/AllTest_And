package com.jsh.kr.alltest.ui.etc;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityIntentServiceTestBinding;
import com.jsh.kr.alltest.receiver.TestReceiverNoFilter;
import com.jsh.kr.alltest.service.TestIntentService;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class IntentServiceTestActivity extends BaseActivity implements View.OnClickListener {
   private ActivityIntentServiceTestBinding binding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_intent_service_test);

      init();
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      unregisterReceiver(receiver);
   }

   private void init() {
      binding.btnFromBroadcast.setOnClickListener(this);
      binding.btnFromIntent.setOnClickListener(this);
      binding.btnFromReceiver.setOnClickListener(this);

      IntentFilter intentFilter = new IntentFilter();
      intentFilter.addAction(C.TestAction.ACTION_ONE.name());
      intentFilter.addAction(C.TestAction.ACTION_TWO.name());
      registerReceiver(receiver, intentFilter);
   }

   private void startBroadcastAction() {
      binding.tvTestIntentServiceBroad.setText("");
      TestIntentService.startTestService(this, C.TestType.TYPE_WITH_STRING_DATA.name(), "data "+new Random().nextInt(10));
   }

   private void startIntentAction() {
      binding.tvTestIntentServiceBroad.setText("");
      Intent intent = new Intent();
      intent.setClassName("com.example.jseonghun35.alltestapp", "com.example.jseonghun35.alltestapp.services.TestIntentService");
      intent.setType(C.TestType.TYPE_ONLY_CMD.name());
      startService(intent);
   }

   private void startReceiverAction() {
      binding.tvTestIntentServiceBroad.setText("");
      TestReceiverNoFilter.sendTestDataWithIntentService(this, "receiverData"+new Random().nextInt(10));
   }

   private BroadcastReceiver receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
         if (intent == null) return;
         C.TestAction action = C.TestAction.findByName(intent.getAction());
         String message = null;

         if (action != null) {
            switch (action) {
               case ACTION_ONE:
                  break;
               case ACTION_TWO:
                  message = intent.getStringExtra(C.Extra.EXTRA_KEY_STRING);
                  showData(message);
                  break;
            }
         }
      }
   };

   public void showData(String message) {
      binding.tvTestIntentServiceBroad.setText(message);
   }

   @Override
   public void onClick(View view) {
      int id = view.getId();
      if (id == R.id.btn_from_broadcast) {
         startBroadcastAction();
      } else if (id == R.id.btn_from_intent) {
         startIntentAction();
      } else if (id == R.id.btn_from_receiver) {
         startReceiverAction();
      }
   }
}

