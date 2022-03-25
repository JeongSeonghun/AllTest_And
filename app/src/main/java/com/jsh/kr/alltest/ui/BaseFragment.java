package com.jsh.kr.alltest.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(this.getClass().getSimpleName(), "onCreate()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(this.getClass().getSimpleName(), "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(this.getClass().getSimpleName(), "onPause()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(this.getClass().getSimpleName(), "onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtil.d(this.getClass().getSimpleName(), "onAttach()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d(this.getClass().getSimpleName(), "onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(this.getClass().getSimpleName(), "onDestroy()");
    }
}
