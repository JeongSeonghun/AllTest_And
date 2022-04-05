package com.jsh.kr.alltest.wear.service;

import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.jsh.kr.alltest.wear.WearC;
import com.jsh.kr.alltest.wear.message.WMessageReceiver;

import java.nio.charset.StandardCharsets;

public class GoogleWearListenerService extends WearableListenerService {

   private final static String TAG = GoogleWearListenerService.class.getSimpleName();

   private WMessageReceiver messageReceiver;

   @Override
   public void onCreate() {
      super.onCreate();
      messageReceiver = new WMessageReceiver(this);
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
   }

   @Override
   public void onMessageReceived(MessageEvent messageEvent) {
      super.onMessageReceived(messageEvent);
      Log.d(TAG, "onMessageReceived");
      if (messageEvent.getPath().equals(WearC.PATH_MOBILE)) {
         String response = null;
         response = new String(messageEvent.getData(), StandardCharsets.UTF_8);
         Log.d(TAG, "onMessageReceived : " + response);
         messageReceiver.onMessageReceive(response);
      }
   }
}
