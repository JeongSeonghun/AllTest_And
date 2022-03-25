package com.jsh.kr.alltest.ui.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * vertical
 * https://android-dev-examples.blogspot.com/2014/09/android-custom-vertical-progressbar.html?showComment=1487215622920#c4985236080627797673
 *
 * image
 * https://www.kisspng.com/free/circular-progress-bar.html
 */
public class ProgressTestActivity extends BaseActivity implements View.OnClickListener {

    private ProgressBar pb_progress_test_round;
    private ProgressBar pb_progress_test_horizontal;
    private ProgressBar pb_progress_test_round_cus1;
    private ProgressBar pb_progress_test_horizontal_cus1;
    private ProgressBar pb_progress_test_vertical;
    private Button btn_progress_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_test);

        initUI();
    }

    private void initUI() {
        pb_progress_test_round = findViewById(R.id.pb_progress_test_round);
        pb_progress_test_horizontal = findViewById(R.id.pb_progress_test_horizontal);
        pb_progress_test_round_cus1 = findViewById(R.id.pb_progress_test_round_cus1);
        pb_progress_test_horizontal_cus1 = findViewById(R.id.pb_progress_test_horizontal_cus1);
        pb_progress_test_vertical = findViewById(R.id.pb_progress_test_vertical);
        btn_progress_dialog = findViewById(R.id.btn_progress_dialog);

        btn_progress_dialog.setOnClickListener(this);
    }

    private void showProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_progress_rotate, null, false);
        builder.setView(view);
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_progress_dialog:
                showProgressDialog();
                break;
        }
    }
}

