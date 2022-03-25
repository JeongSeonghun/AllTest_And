package com.jsh.kr.alltest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jsh.kr.alltest.AllTestApplication;
import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.service.TestIntentService;

public class TestReceiverNoFilter extends BroadcastReceiver {

   @Override
   public void onReceive(Context context, Intent intent) {
      if (intent == null) return;

      if (C.TestAction.ACTION_ONE.name().equals(intent.getAction())) {
         String data = intent.getStringExtra(C.Extra.EXTRA_KEY_STRING);
         AllTestApplication.getApplication().getAppData().setTestStringData(data);
      }
   }

   public static void sendTestDataWithIntentService(Context context, String message) {
      Intent intent = new Intent(context, TestReceiverNoFilter.class);
      intent.setAction(C.TestAction.ACTION_ONE.name());
      intent.putExtra(C.Extra.EXTRA_KEY_STRING, message);

      context.sendBroadcast(intent);

      TestIntentService.startTestService(context, C.TestType.TYPE_WITH_STRING_DATA.name(), message);
   }
}
