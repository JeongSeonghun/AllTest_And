package com.jsh.kr.alltest.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.service.TestJobIntentService;
import com.jsh.kr.alltest.util.LogUtil;

import static android.os.BatteryManager.BATTERY_STATUS_UNKNOWN;
import static android.os.BatteryManager.EXTRA_LEVEL;
import static android.os.BatteryManager.EXTRA_PLUGGED;
import static android.os.BatteryManager.EXTRA_SCALE;
import static android.os.BatteryManager.EXTRA_STATUS;
import static android.os.BatteryManager.EXTRA_TEMPERATURE;

/**
 * https://developer.android.com/training/monitoring-device-state/battery-monitoring?hl=ko
 *
 * 백그라운드 제한으로 crash 발생 가능(백그라운드에서 startService 사용 불가) -> IntentService -> JobScheduler
 * https://medium.com/til-kotlin-ko/android-o%EC%97%90%EC%84%9C%EC%9D%98-%EB%B0%B1%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C-%EC%B2%98%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-jobintentservice-250af2f7783c
 */
public class BatteryCheckReceiver extends BroadcastReceiver {

    private static final BatteryCheckReceiver instance = new BatteryCheckReceiver();

    private BatteryStatus batteryStatus;

    public BatteryCheckReceiver(){
        batteryStatus = new BatteryStatus();
    }

    public static BatteryCheckReceiver getInstance() {
        return instance;
    }

    public void start(Context context) {
        context.registerReceiver(this, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public void stop(Context context) {
        context.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final int status = intent.getIntExtra(EXTRA_STATUS, BATTERY_STATUS_UNKNOWN);
        final int plugged = intent.getIntExtra(EXTRA_PLUGGED, 0);
        final int level = intent.getIntExtra(EXTRA_LEVEL, 0);
        final int scale = intent.getIntExtra(EXTRA_SCALE, -1);
        final int temperature = intent.getIntExtra(EXTRA_TEMPERATURE, 0);

        batteryStatus.setStatus(status);
        batteryStatus.setPlugged(plugged);
        batteryStatus.setLevel(level);
        batteryStatus.setScale(scale);
        batteryStatus.setTemperature(temperature);

        LogUtil.d("battery receiver", "batteryLever : "+level);

        // background issue OS 8.0 (intentService -> JobScheduler)
//        TestIntentService.startTestService(context, C.TestType.TYPE_BATTERY_CHANGE.name());
        TestJobIntentService.startTestService(context, C.TestType.TYPE_BATTERY_CHANGE.name());
    }

    public BatteryStatus getBatteryStatus() {
        return batteryStatus;
    }

    public class BatteryStatus {
        private int status;
        private int plugged;
        private int level;
        private int scale;
        private int temperature;

        public void setLevel(int level) {
            this.level = level;
        }

        public void setPlugged(int plugged) {
            this.plugged = plugged;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getLevel() {
            return level;
        }

        public int getPlugged() {
            return plugged;
        }

        public int getScale() {
            return scale;
        }

        public int getStatus() {
            return status;
        }

        public int getTemperature() {
            return temperature;
        }
    }
}

