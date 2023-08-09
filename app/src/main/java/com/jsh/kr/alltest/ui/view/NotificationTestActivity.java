package com.jsh.kr.alltest.ui.view;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * Notification Test
 *
 * bridging mode
 * https://developer.android.com/training/wearables/notifications/bridger#java
 */
public class NotificationTestActivity extends BaseActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private final String TAG = this.getClass().getSimpleName();

    private Button btn_notification_show;
    private Switch sw_setting_bridging_mode;
    private Switch sw_setting_bridging_phone;
    private EditText et_notification_delay;
    private Spinner sp_notification_important;

    /**
     * if wear app installed in watch
     * true : display notification in watch
     * false : no display notification in watch
     */
    private boolean isSetBridgeTag = false;

    /**
     * true : display notification in watch
     * false : no display notification in watch
     */
    private boolean issetBridgePhone = false;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        initUI();
    }

    private void initUI() {
        btn_notification_show = findViewById(R.id.btn_notification_show);
        sw_setting_bridging_mode = findViewById(R.id.sw_setting_bridging_mode);
        sw_setting_bridging_phone = findViewById(R.id.sw_setting_bridging_phone);
        et_notification_delay = findViewById(R.id.et_notification_delay);
        sp_notification_important = findViewById(R.id.sp_notification_important);

        btn_notification_show.setOnClickListener(this);
        sw_setting_bridging_mode.setOnCheckedChangeListener(this);
        sw_setting_bridging_phone.setOnCheckedChangeListener(this);

        initChannelImportantData();
    }

    private void initChannelImportantData() {
        String[] importants;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            importants = new String[]{
//                    String.valueOf(NotificationManager.IMPORTANCE_NONE)
//                    , String.valueOf(NotificationManager.IMPORTANCE_MIN)
                    String.valueOf(NotificationManager.IMPORTANCE_LOW)
                    , String.valueOf(NotificationManager.IMPORTANCE_DEFAULT)
                    , String.valueOf(NotificationManager.IMPORTANCE_HIGH)
            };

        } else {
            importants = new String[]{
                    String.valueOf(-2)
                    , String.valueOf(-1)
                    , String.valueOf(0)
                    , String.valueOf(1)
                    , String.valueOf(2)
            };
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, importants);
        sp_notification_important.setAdapter(arrayAdapter);
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        int delay = et_notification_delay.getText().toString().isEmpty() ? 0 : Integer.valueOf(et_notification_delay.getText().toString());
        String channelId = getSelectChannelId();

        if(notificationManager == null) {
            return;
        }

        if(channelId != null && channelId.length() > 0) {
            createChannel(notificationManager);
        }

        builder = createNotificationBuilder(channelId);

        Notification notification = builder.build();

        if(delay > 0) {
            delayNotify(delay*1000, notificationManager, notification);
        } else {
            notificationManager.notify(C.Notification.TEST_NOTI_ID, notification);
        }

    }

    private NotificationCompat.Builder createNotificationBuilder(String channelId) {
        NotificationCompat.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(this, channelId);
        } else {
            builder =new NotificationCompat.Builder(this);
        }

        builder.setSmallIcon(R.mipmap.ic_stat_ac_unit)
                .setContentTitle(TAG)
                .setContentText("test notification");

        setAddOption(builder);
        setBridgeMode(builder);

        return builder;
    }

    private void createChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notiChannelDefault = notificationManager.getNotificationChannel(C.Notification.TEST_NOTI_CHANNEL_ID);
            NotificationChannel notiChannelHigh= notificationManager.getNotificationChannel(C.Notification.TEST_HIGH_NOTI_CHANNEL_ID);
            NotificationChannel notiChannelLow= notificationManager.getNotificationChannel(C.Notification.TEST_LOW_NOTI_CHANNEL_ID);

            if(notiChannelDefault == null) {
                notiChannelDefault = new NotificationChannel(C.Notification.TEST_NOTI_CHANNEL_ID
                        , C.Notification.TEST_NOTI_CHANNEL_NAME
                        , NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notiChannelDefault);
            }

            if(notiChannelHigh == null) {
                notiChannelHigh = new NotificationChannel(C.Notification.TEST_HIGH_NOTI_CHANNEL_ID
                        , C.Notification.TEST_HIGH_NOTI_CHANNEL_NAME
                        , NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notiChannelHigh);
            }

            if(notiChannelLow == null) {
                notiChannelLow = new NotificationChannel(C.Notification.TEST_LOW_NOTI_CHANNEL_ID
                        , C.Notification.TEST_LOW_NOTI_CHANNEL_NAME
                        , NotificationManager.IMPORTANCE_LOW);
                notificationManager.createNotificationChannel(notiChannelLow);
            }

        }
    }

    private String getSelectChannelId() {

        String importantStr = (String) sp_notification_important.getSelectedItem();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O && importantStr == null || importantStr.length() <= 0) {
            return null;
        }

        int important = Integer.valueOf(importantStr);
        String channelId;
        switch (important) {
            case NotificationManager.IMPORTANCE_HIGH:
                channelId = C.Notification.TEST_HIGH_NOTI_CHANNEL_ID;
                break;
            case NotificationManager.IMPORTANCE_LOW:
                channelId = C.Notification.TEST_LOW_NOTI_CHANNEL_ID;
                break;
            case NotificationManager.IMPORTANCE_DEFAULT:
            default:
                channelId = C.Notification.TEST_NOTI_CHANNEL_ID;
                break;
        }
        return channelId;
    }

    private void setAddOption(NotificationCompat.Builder builder) {
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setContentIntent(makePendingIntent());

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String importantStr = (String) sp_notification_important.getSelectedItem();
            int important = 4;
            if (importantStr != null && importantStr.length() > 0) {
                important = Integer.valueOf(importantStr);
            }

            builder.setPriority(important);
        }
    }

    private void setBridgeMode(NotificationCompat.Builder builder) {

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_heart_outline_white_24dp,
                        getString(R.string.app_name), makePendingIntent())
                        .build();

        if (isSetBridgeTag) {
            builder.extend(new NotificationCompat.WearableExtender().setBridgeTag("foo")
//                    .setDisplayIntent(makePendingIntent())
//                    .setCustomSizePreset(Notification.WearableExtender.SIZE_MEDIUM));
                    .addAction(action));
        }

        if (issetBridgePhone) {
            builder.setLocalOnly(true);
        }
    }

    private PendingIntent makePendingIntent() {
//        Intent displayIntent = new Intent(this, WearPendingTestActivity.class);
        Intent displayIntent = new Intent(this, NotificationTestActivity.class);
        displayIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent displayPendingIntent = PendingIntent.getActivity(this,
                0, displayIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return displayPendingIntent;
    }

    private void delayNotify(int seconds, final NotificationManager managerCompat, final Notification notification) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                managerCompat.notify(C.Notification.SERVICE_NOTI_ID, notification);
            }
        }, seconds);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_notification_show) {
            showNotification();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.sw_setting_bridging_mode) {
            isSetBridgeTag = isChecked;
        } else if (id == R.id.sw_setting_bridging_phone) {
            issetBridgePhone = isChecked;
        }
    }
}

