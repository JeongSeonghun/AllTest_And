package com.jsh.kr.alltest.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.util.TestAppLocalBroadcast;

import androidx.annotation.Nullable;

/**
 * background issue -> use TestJobIntentService
 */
public class TestIntentService extends IntentService {

   // base
   public TestIntentService() {
      super(TestIntentService.class.getSimpleName());
   }

   // base
   @Override
   protected void onHandleIntent(@Nullable Intent intent) {
      if (intent != null) {
         String type = intent.getType();
         C.TestType testType = C.TestType.findByName(type);
         String data = null;
         LogUtil.d("TestIntentService", "onHandle type : "+type);

         if (testType != null) {
            switch (testType) {
               case TYPE_ONLY_CMD:
                  sendBroadcastToTestActivity(testType.name());
                  break;
               case TYPE_WITH_STRING_DATA:
                  data = intent.getStringExtra(C.Extra.EXTRA_KEY_STRING);
                  sendBroadcastToTestActivity(testType.name() + " : " +data);
                  break;
               case TYPE_BATTERY_CHANGE:
                  TestAppLocalBroadcast.send(getApplicationContext(), C.BroadCast.BatteryStatus);
                  break;
            }
         }
      }
   }

   public static void startTestService(Context context, String type) {
      Intent intent = new Intent(context, TestIntentService.class);
      intent.setType(type);

      context.startService(intent);
   }

   public static void startTestService(Context context, String type, String data) {
      Intent intent = new Intent(context, TestIntentService.class);
      intent.setType(type);
      intent.putExtra(C.Extra.EXTRA_KEY_STRING, data);

      context.startService(intent);
   }

   private void sendBroadcastToTestActivity(String message) {
      Intent intent = new Intent(C.TestAction.ACTION_TWO.name());
      intent.putExtra(C.Extra.EXTRA_KEY_STRING, message);

      sendBroadcast(intent);
   }

}

