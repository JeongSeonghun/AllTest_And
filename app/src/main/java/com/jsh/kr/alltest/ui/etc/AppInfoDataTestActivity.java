package com.jsh.kr.alltest.ui.etc;


import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.AppDataManager;
import com.jsh.kr.alltest.receiver.BatteryCheckReceiver;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltestlib.util.TestCusLocalBroadcast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;

/**
 * meta data
 *  http://superakira.tistory.com/entry/Android-android-meta-data-사용하기 [한거장의 성장일기]
 */
public class AppInfoDataTestActivity extends BaseActivity {

    private TextView tv_app_info_meta;
    private TextView tv_app_info_build_type;
    private TextView tv_app_info_build_version;
    private TextView tv_app_info_version;
    private TextView tv_app_info_battery;
    private TextView tv_app_info_memory;

    private AppDataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info_data_test);

        initUI();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BatteryCheckReceiver.getInstance().stop(this);
    }

    private void initUI() {
        // meta
        tv_app_info_meta = findViewById(R.id.tv_app_info_meta);
        // build
        tv_app_info_build_type = findViewById(R.id.tv_app_info_build_type);
        tv_app_info_build_version = findViewById(R.id.tv_app_info_build_version);
        // package
        tv_app_info_version = findViewById(R.id.tv_app_info_version);
        // battery
        tv_app_info_battery = findViewById(R.id.tv_app_info_battery);
        //memory
        tv_app_info_memory = findViewById(R.id.tv_app_info_memory);
    }

    private void initData() {
        dataManager = new AppDataManager();
        initMetaData();
        initBuildData();
        initPackageInfo();
        IntentFilter filter = new IntentFilter();
        filter.addCategory(C.BroadCast.CATEGORY_LOCAL);
        filter.addAction(C.BroadCast.BatteryStatus);
        TestCusLocalBroadcast.registerReceiver(this, receiver, filter);
        getMemoryInfo();

        // test
        BatteryCheckReceiver.getInstance().start(this);
    }

    private void initMetaData() {
        String metaString = dataManager.getMetaDataString(this, "meta.test");

        if (metaString == null) metaString = "no data";

        tv_app_info_meta.setText(metaString);
    }

    private void initBuildData() {
        tv_app_info_build_type.setText(dataManager.getAppBuildType());
        tv_app_info_build_version.setText("sdk int "+dataManager.getAppBuildVersion());
    }

    private void initPackageInfo() {
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);

            String versionName = pi.versionName;

            tv_app_info_version.setText(versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setBatteryStatus() {
        String form = "remain %1$d %2$s";
        SimpleDateFormat dateForm = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        tv_app_info_battery.setText(String.format(Locale.KOREA, form,
                BatteryCheckReceiver.getInstance().getBatteryStatus().getLevel(), dateForm.format(new Date())));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) return;

            String action = intent.getAction();
            LogUtil.d("app data receiver", "action : "+action);
            if (C.BroadCast.BatteryStatus.equals(action)) {
                setBatteryStatus();
            }
        }
    };

    int UNIT_MB = 1024*1024;

    private void getMemoryInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (am != null) {
            am.getMemoryInfo(memoryInfo);
            tv_app_info_memory.setText("able : "+memoryInfo.availMem/UNIT_MB + ", total : "+memoryInfo.totalMem/UNIT_MB);
        }
    }
}
