package com.jsh.kr.alltest.ui.etc;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.aidl.IAIDLTestInterfaceImpl;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

public class AIDLTestActivity extends BaseActivity {
    private Switch state_sw;
    private EditText int_data_et;
    private Button send_btn;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);
        initUI();
    }

    private void initUI() {
        state_sw = findViewById(R.id.state_sw);
        int_data_et = findViewById(R.id.int_data_et);
        send_btn = findViewById(R.id.send_btn);

        send_btn.setOnClickListener(v -> send());
        state_sw.setOnCheckedChangeListener((compoundButton, b) -> {
            IAIDLTestInterfaceImpl.getInstance().setState(b);
        });
    }

    private void send() {
        String text = int_data_et.getText().toString();

        if (!TextUtils.isEmpty(text)) {
            handler.postDelayed(()-> {
                IAIDLTestInterfaceImpl.getInstance().broadcastToCurrentStateToClients(Integer.valueOf(text));
            }, 3000);
        }
    }
}
