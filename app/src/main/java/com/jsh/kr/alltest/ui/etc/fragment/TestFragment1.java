package com.jsh.kr.alltest.ui.etc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestFragment1 extends BaseFragment {

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_test1, container, false);
      initUI(rootView);
      return rootView;
   }

   private void initUI(View rootView) {

   }

}

