package com.jsh.kr.alltest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jsh.kr.alltest.aidl.IAIDLTestInterfaceImpl;

import androidx.annotation.Nullable;

public class AIDLService extends Service {

   @Override
   public void onCreate() {
      super.onCreate();
   }

   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
      return IAIDLTestInterfaceImpl.getInstance();
   }
}
