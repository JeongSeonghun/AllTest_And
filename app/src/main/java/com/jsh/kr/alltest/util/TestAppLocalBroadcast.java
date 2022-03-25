package com.jsh.kr.alltest.util;

import android.content.Context;
import android.content.Intent;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltestlib.util.TestCusLocalBroadcast;

import androidx.annotation.NonNull;

public class TestAppLocalBroadcast extends TestCusLocalBroadcast {
    public static void send(@NonNull final Context context, @C.BroadCast.Action final String action) {
        Intent intent = baseIntent(action);
        send(context, intent);
    }

    public static void send(@NonNull final Context context, @C.BroadCast.Action final String action, final int data) {
        Intent intent = baseIntent(action);
        intent.putExtra(C.BroadCast.Data, data);
        send(context, intent);
    }

    public static void send(@NonNull final Context context, @C.BroadCast.Action final String action, final String data) {
        Intent intent = baseIntent(action);
        intent.putExtra(C.BroadCast.Data, data);
        send(context, intent);
    }

    private static Intent baseIntent(String action) {
        Intent intent = new Intent(action);
        intent.addCategory(C.BroadCast.CATEGORY_LOCAL);

        return intent;
    }
}
