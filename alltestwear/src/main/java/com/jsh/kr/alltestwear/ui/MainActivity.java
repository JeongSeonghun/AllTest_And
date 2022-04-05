package com.jsh.kr.alltestwear.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.jsh.kr.alltestwear.R;

import java.io.UnsupportedEncodingException;

import androidx.annotation.Nullable;

public class MainActivity extends BaseWearActivity
//        implements MessageApi.MessageListener
//        , GoogleApiClient.ConnectionCallbacks
//        , GoogleApiClient.OnConnectionFailedListener{
        implements MessageClient.OnMessageReceivedListener
        , View.OnClickListener {

   private final String TAG = this.getClass().getSimpleName();

   private GoogleApiClient mGoogleApiClient;

   private static final String WEAR_PATH = "/wear";

   private Button btn_move_send;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      initUI();
   }

   private void initUI() {
      btn_move_send = findViewById(R.id.btn_move_send);

      btn_move_send.setOnClickListener(this);
   }

   @Override
   protected void onStart() {
      super.onStart();
//        Wearable.MessageApi.addListener(mGoogleApiClient, this);
      Wearable.getMessageClient(this).addListener(this);
   }

   @Override
   protected void onPause() {
      super.onPause();
   }

   @Override
   protected void onStop() {
//        Wearable.MessageApi.removeListener(mGoogleApiClient, this);
      Wearable.getMessageClient(this).removeListener(this);
      super.onStop();
   }

   @Override
   protected void onDestroy() {
      Wearable.getMessageClient(this).removeListener(this);
      super.onDestroy();
   }

   @Override
   public void onMessageReceived(MessageEvent messageEvent) {
      Log.d(TAG, "onMessageReceived: ");

      if (messageEvent.getPath().equals(WEAR_PATH)) {
         String response = null;
         try {
            response = new String(messageEvent.getData(), "UTF-8");
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }

         Log.d(TAG, "onMessageReceived: " + response);

      }
   }

   private void moveSend() {
      Intent intentAct = new Intent(this, MessageSendActivity.class);
      startActivity(intentAct);
   }

   @Override
   public void onClick(View v) {
      switch (v.getId()) {
         case R.id.btn_move_send:
            moveSend();
            break;
      }
   }
}
