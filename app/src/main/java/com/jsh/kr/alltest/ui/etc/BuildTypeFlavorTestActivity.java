package com.jsh.kr.alltest.ui.etc;

import android.os.Bundle;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.AppDataManager;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

public class BuildTypeFlavorTestActivity extends BaseActivity {

   private AppDataManager dataManager;

   private TextView tv_bf_test_build;
   private TextView tv_bf_test_flavor;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_build_type_falvor_test);

      initUI();
      initData();
   }

   private void initUI() {
      tv_bf_test_build = findViewById(R.id.tv_bf_test_build);
      tv_bf_test_flavor = findViewById(R.id.tv_bf_test_flavor);
   }

   private void initData() {
      dataManager = new AppDataManager();

      initBuildTypeData();
      initFlavorData();
   }

   private void initBuildTypeData() {
      StringBuilder sb = new StringBuilder();

      // base
      sb.append("**base\n");
      sb.append("type : ").append(dataManager.getAppBuildType()).append("\n");

      // add config field
      sb.append("\n**add config field\n");
      sb.append("LOG_DEBUG : ").append(dataManager.getCustomFieldLogDebug()).append("\n");

      tv_bf_test_build.setText(sb.toString());
   }

   private void initFlavorData() {
      StringBuilder sb = new StringBuilder();

      // add config field
      sb.append("\n**add config field\n");
      sb.append("EXPLAIN : ").append(dataManager.getCustomFieldExplain()).append("\n");

      // res value
      sb.append(getString(R.string.testFlavorResVal));

      tv_bf_test_flavor.setText(sb.toString());
   }
}
