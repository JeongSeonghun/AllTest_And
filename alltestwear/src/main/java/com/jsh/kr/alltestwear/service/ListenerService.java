package com.jsh.kr.alltestwear.service;

import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.jsh.kr.alltestwear.C;

import java.io.UnsupportedEncodingException;

public class ListenerService extends WearableListenerService {

   private final String TAG = this.getClass().getSimpleName();

   @Override
   public void onCreate() {
      super.onCreate();
   }

   @Override
   public void onMessageReceived(MessageEvent messageEvent) {
      super.onMessageReceived(messageEvent);
      Log.d(TAG, "onMessageReceived: ");

      if (messageEvent.getPath().equals(C.PATH_WEAR)) {
         String response = null;
         try {
            response = new String(messageEvent.getData(), "UTF-8");
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }

//         Log.d(TAG, "onMessageReceived check front : "+ Util.checkFront(this));
         Log.d(TAG, "onMessageReceived: " + response);

      }
   }
}
