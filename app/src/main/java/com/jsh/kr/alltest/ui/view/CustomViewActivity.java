package com.jsh.kr.alltest.ui.view;


import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.view.CustomView;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * custom view test
 * use custom Attributes
 */
public class CustomViewActivity extends BaseActivity {

   private CustomView img_custom;
   private CustomView img_custom2;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_custom_view);

      initUI();

      init();
   }

   private void initUI() {
      img_custom = findViewById(R.id.img_custom);
      img_custom2 = findViewById(R.id.img_custom2);

   }

   private void init() {
      img_custom.setImage(R.drawable.images);
      img_custom.setText(R.string.image);

      img_custom2.setImage(R.drawable.images);
      img_custom2.setText(R.string.image);
   }
}

