package com.jsh.kr.alltestex;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltestlib.AIDLStateCallback;
import com.jsh.kr.alltestlib.AIDLTestInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AIDLTestActivity extends AppCompatActivity {

   private Button update_state_btn;
   private TextView update_state_tv;
   private TextView callback_int_tv;

   private AIDLTestInterface mRemoteService;

   private ServiceConnection mConnection = new ServiceConnection() {
      @Override
      public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
         Log.d("AIDL Test", "onServiceConnected");
         mRemoteService = AIDLTestInterface.Stub.asInterface(iBinder);
         registerCallback();
      }

      @Override
      public void onServiceDisconnected(ComponentName componentName) {
         Log.d("AIDL Test", "onServiceDisconnected");
         mRemoteService = null;
      }
   };

   // 최신 버전에는 Stub 추가하여 사용
   private AIDLStateCallback.Stub mCallback = new AIDLStateCallback.Stub() {
      @Override
      public void onServiceStateChanged(int status) throws RemoteException {
         Log.d("AIDL Test", "onServiceStateChanged");
         runOnUiThread(new Runnable() {
            @Override
            public void run() {
               callback_int_tv.setText(String.valueOf(status));
            }
         });

      }
   };

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_aidl_test);
      initUI();
      connectService();
   }

   private void initUI() {
      update_state_btn = findViewById(R.id.update_state_btn);
      update_state_tv = findViewById(R.id.update_state_tv);
      callback_int_tv = findViewById(R.id.callback_int_tv);

      update_state_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            if (mRemoteService != null) {
               try {
                  update_state_tv.setText(String.valueOf(mRemoteService.isAvailable()));
               } catch (RemoteException e) {
                  e.printStackTrace();
               }
            }
         }
      });
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      disconnectService(); // disconnect service at onDestroy
   }

   private void connectService() {
      Intent i = new Intent();
      i.setAction("com.kr.alltest.service.aidltest");
      i.setPackage("com.jsh.kr.alltest");
      bindService(i, mConnection, Context.BIND_AUTO_CREATE);
   }

   private void disconnectService() {
      unRegisterCallback();
      unbindService(mConnection);
   }

   private void registerCallback() {
      if (mRemoteService != null) {
         try {
            mRemoteService.registerCallback(mCallback);
         } catch (RemoteException e) {
            e.printStackTrace();
         }
      }
   }

   private void unRegisterCallback() {
      if (mRemoteService != null) {
         try {
            mRemoteService.unregisterCallback(mCallback);
         } catch (RemoteException e) {
            e.printStackTrace();
         }
      }
   }
}
