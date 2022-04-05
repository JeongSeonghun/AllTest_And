package com.jsh.kr.alltestwear.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.jsh.kr.alltestwear.C;
import com.jsh.kr.alltestwear.R;
import com.jsh.kr.alltestwear.message.MessageSender;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageSendActivity extends BaseWearActivity implements View.OnClickListener{

   private Button btn_message_send;
   private TextView tv_message_send;

   private MessageSender messageSender;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_message_send);

      initUI();
      initData();
   }

   private void initUI() {
      btn_message_send = findViewById(R.id.btn_message_send);
      tv_message_send = findViewById(R.id.tv_message_send);

      btn_message_send.setOnClickListener(this);
   }

   private void initData() {
      messageSender = new MessageSender(this);

      Wearable.getMessageClient(this).addListener(messageReceivedListener);
   }

   private void sendMessage() {
      String form = "MM/dd HH:mm:ss";
      SimpleDateFormat format = new SimpleDateFormat(form, Locale.KOREA);
      String msg = "test message : "+format.format(new Date());
      messageSender.sendMessage(this, msg);
   }

   private void setReceiveMessage(String msg) {
      tv_message_send.setText(msg);
   }

   @Override
   protected void onStop() {
      super.onStop();
   }

   @Override
   public void onClick(View v) {
      if (v.getId() == R.id.btn_message_send) {
         sendMessage();
      }
   }

   private MessageClient.OnMessageReceivedListener messageReceivedListener = new MessageClient.OnMessageReceivedListener() {
      @Override
      public void onMessageReceived(@NonNull MessageEvent messageEvent) {
         if(messageEvent.getPath().equals(C.PATH_WEAR)) {
            String response = null;
            try {
               response = new String(messageEvent.getData(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
            }
            setReceiveMessage(response);
         }
      }
   };
}
