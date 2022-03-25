package com.jsh.kr.alltestlib.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class TestCusLocalBroadcast {
    public static void registerReceiver(@NonNull Context context, BroadcastReceiver receiver, IntentFilter filter) {
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    public static void unregisterReceiver(@NonNull Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    public static void send(@NonNull Context context, Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
