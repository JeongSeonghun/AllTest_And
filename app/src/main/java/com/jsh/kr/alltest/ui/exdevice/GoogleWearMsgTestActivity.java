package com.jsh.kr.alltest.ui.exdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.TestAppLocalBroadcast;
import com.jsh.kr.alltest.wear.message.GoogleMessageSender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;

/**
 * google wear test
 * debug인 경우 앱을 폰에 설치해도 wear 단말에 설치 안됨. release인 경우 동기화 되며 설치됨.
 * app, wear는 application id가 동일해야 함.
 */
public class GoogleWearMsgTestActivity extends BaseActivity implements View.OnClickListener{

    private Button btn_send_msg;
    private TextView tv_receive_wear_msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_wear_msg_test);

        initUI();
        registWearBroadcast();
    }

    private void initUI() {
        btn_send_msg = findViewById(R.id.btn_send_msg);
        tv_receive_wear_msg = findViewById(R.id.tv_receive_wear_msg);

        btn_send_msg.setOnClickListener(this);
    }

    private void registWearBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory(C.BroadCast.CATEGORY_LOCAL);
        intentFilter.addAction(C.BroadCast.WearMessageReceive);
        TestAppLocalBroadcast.registerReceiver(this, broadcastReceiver, intentFilter);
    }

    private void sendMessage() {
        GoogleMessageSender sender = new GoogleMessageSender(this);
        String form = "MM/dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(form, Locale.KOREA);
        String msg = "test message : "+format.format(new Date());
        sender.sendMessage(msg);
    }

    private void setReceiveMessage(String msg) {
        if (msg != null) {
            tv_receive_wear_msg.setText(msg);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_msg) {
            sendMessage();
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null) {
                return;
            }

            String action = intent.getAction();
            if (C.BroadCast.WearMessageReceive.equals(action)) {
                setReceiveMessage(intent.getStringExtra(C.BroadCast.Data));
            }
        }
    };
}
