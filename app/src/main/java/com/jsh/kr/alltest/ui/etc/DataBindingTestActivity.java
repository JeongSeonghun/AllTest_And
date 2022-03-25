package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityDataBindingTestBinding;
import com.jsh.kr.alltest.model.data.BindingTestData;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * DataBinding test
 *
 * https://developer.android.com/topic/libraries/data-binding/?hl=ko
 */
public class DataBindingTestActivity extends BaseActivity {
   private ActivityDataBindingTestBinding binding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test);

      initUI();
   }

   private void initUI() {

      showMakeTestData();
   }

   private void showMakeTestData() {
      BindingTestData testData = new BindingTestData("test");

      binding.setTestData(testData);
   }

   private void setTestBindingText(String message) {
      binding.tvTestBinding.setText(message);
   }
}

