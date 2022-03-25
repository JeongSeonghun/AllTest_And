package com.jsh.kr.alltest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.AppDataUtil;
import com.jsh.kr.alltest.ui.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SettingFragment extends BaseFragment
        implements CompoundButton.OnCheckedChangeListener{

    private Switch sw_setting_before_move;
    private Switch sw_mode_login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        initUI(view);
        initData();

        return view;
    }

    private void initUI(View view) {
        sw_setting_before_move = view.findViewById(R.id.sw_setting_before_move);
        sw_mode_login = view.findViewById(R.id.sw_mode_login);

        sw_setting_before_move.setOnCheckedChangeListener(this);
        sw_mode_login.setOnCheckedChangeListener(this);
    }

    private void initData() {
        sw_mode_login.setChecked(AppDataUtil.getInstance().isLoginMode());
        sw_setting_before_move.setChecked(AppDataUtil.getInstance().isMaintainTestMode());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.sw_mode_login) {
            AppDataUtil.getInstance().setLoginMode(isChecked);
        } else if (id == R.id.sw_setting_before_move) {
            AppDataUtil.getInstance().setMainTainTestMode(isChecked);
        }
    }
}

