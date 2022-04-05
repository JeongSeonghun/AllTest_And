package com.jsh.kr.alltest.wear.message;

import android.content.Context;
import android.os.Handler;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.util.TestAppLocalBroadcast;

public class WMessageReceiver {
   private Context context;

   public WMessageReceiver(Context context) {
      this.context = context;
   }

   public void onMessageReceive(String msg) {
      TestAppLocalBroadcast.send(context, C.BroadCast.WearMessageReceive, msg);
   }
}
