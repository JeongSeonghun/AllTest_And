package com.jsh.kr.alltest.ui.etc;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityPhoneStateCheckBinding;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.TestTextUtil;

import java.text.DecimalFormat;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class PhoneStateCheckActivity extends BaseActivity implements View.OnClickListener {
    private final static String TAG = PhoneStateCheckActivity.class.getSimpleName();

    private ActivityPhoneStateCheckBinding binding;
    private StringBuilder sbLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_state_check);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addLog("resume phone state "+makeCallStateText(getCallState()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        addLog("pause phone state "+makeCallStateText(getCallState()));
    }

    private void init() {
        binding.btnPhoneStateRefresh.setOnClickListener(this);

        sbLog = new StringBuilder();
        initTelListener();
        checkMemory();
    }

    private void refresh() {
        checkMemory();
        addLog("refresh phone state "+makeCallStateText(getCallState()));
    }

    private void initTelListener() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            manager.listen(telStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    private int getCallState() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            return manager.getCallState();
        }

        return -999;
    }

    private void addLog(String log) {
        Log.d(TAG, log);

        sbLog.append(TestTextUtil.makeCurrentTime())
                .append(" ")
                .append(log)
                .append("\n");
        binding.tvPhoneStateLog.setText(sbLog.toString());
    }

    private void addTelLog(String state, String num) {
        String log = makeTelLog(state, num);

        addLog(log);
    }

    private String makeTelLog(String state, String num) {
        String form = "num : %1$s, state : %2$s";
        return String.format(form, num, state);
    }

    private String makeCallStateText(int state) {
        String stateText;
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                stateText = "통화 종료";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                stateText = "통화 수신 중";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                stateText = "통화 중";
                break;
            default:
                stateText = "Unknown";
                break;
        }
        return stateText;
    }

    private void checkMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        String form = "free / total (free percent)\n%1$f / %2$f MB(%3$s)";
        DecimalFormat df = new DecimalFormat("0.#");

        if (am != null) {
            am.getMemoryInfo(memoryInfo);
            // result unit byte
            double unitMega = 1024 * 1024;
            double total = memoryInfo.totalMem / unitMega;
            double free = memoryInfo.availMem / unitMega;
            double freePercent = free / total * 100;
            addLog(String.format(Locale.KOREA, form, free, total, df.format(freePercent)));
        }
    }

    private PhoneStateListener telStateListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            addTelLog(makeCallStateText(state), incomingNumber);
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_phone_state_refresh) {
            refresh();
        }
    }
}

