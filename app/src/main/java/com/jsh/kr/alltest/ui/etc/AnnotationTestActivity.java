package com.jsh.kr.alltest.ui.etc;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;

public class AnnotationTestActivity extends BaseActivity {

    private TextView tv_state;
    private TextView tv_log;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_test);
        tv_state = findViewById(R.id.tv_state);
        tv_log = findViewById(R.id.tv_log);

        init();
    }

    private void initData() {
        // state
        String version = "version " + Build.VERSION.SDK_INT;
        boolean isRead = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean isWrite = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        String permission = "permission read : " + isRead + ", write : " + isWrite;
        String showText  = version + "\n" +permission;
        tv_state.setText(showText);
    }

    private void init() {
        initData();

        // test
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            checkApiFunction();
        }
        checkTargetFunction();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            checkPermissionFunction();
        }
        checkPermissionMultiple();
        checkPermissionAll();

//        checkRes(1);
        checkRes(R.string.name);

        checkNotNull(null);
        checkNotNull("null");
        checkNullable(null);
        checkNullable("null");
    }

    void checkPermissionMultiple() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        checkPermissionMultiFunction();
    }

    void checkPermissionAll() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        checkPermissionMultiORFunction();
    }

    private void log(String text) {
        LogUtil.d("AnnotationTest", text);
        String before = tv_log.getText().toString();
        String show = before + "\n" + text;
        tv_log.setText(show);
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    void checkApiFunction() { // 지정 버전보다 낮은 경우 컴파일 에러
        log("checkApiFunction");

    }

    @TargetApi(Build.VERSION_CODES.R)
    void checkTargetFunction() {
        /*
        해당 부분(함수 및 클래스)은 정의한 버전 단말에서 api를 사용한다고 정의
        lint error는 발생하지 않으나 해당 버전보다 낮은 단말에서 실행시 runtime 오류 발생 (sdk 29단말이 api level 31에서 추가된 Api를 사용할 경우 오류 발생)
        => 해당 함수를 설정된 버전 이상에서만 사용하거나 함수 내에서 설정된 버전보다 낮은 버전에 실행되지 않도록 해야 함.
         */
        log("checkTargetFunction");

//        WindowMetrics projectionMetrics = getSystemService(WindowManager.class).getMaximumWindowMetrics(); // 빌드는 가능하나 sdk 31 아래 단말에서 실행 시 오류 발생
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics projectionMetrics = getSystemService(WindowManager.class).getMaximumWindowMetrics();
        }
    }

    @RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void checkPermissionFunction() {
        log("checkPermissionFunction");
    }

    @RequiresPermission(anyOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void checkPermissionMultiFunction() { // 권한이 2이상 필용한 경우
        log("checkPermissionMultiFunction");
    }

    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void checkPermissionMultiORFunction() { // 권한이 1개라도 필요한 경우
        log("checkPermissionMultiORFunction");
    }

    void checkRes(@StringRes int stringId) {
        log("checkRes : " + getString(stringId));
    }

    void checkNotNull(@NonNull String text) { // 함수 사용 시 null 사용 불가 알림-> null 처리 대상외. null인 경우 동작 보장 없음
        if (text == null) {
            log("checkNotNull null");
        } else {
            log("checkNotNull");
        }
    }

    void checkNullable(@Nullable String text) { // 함수 사용 시 null 사용 가능 알림
        if (text == null) {
            log("checkNullable null");
        } else {
            log("checkNullable");
        }
    }

}
