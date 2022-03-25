package com.jsh.kr.alltest.ui.etc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltestlib.util.ResourceUtil;
import com.jsh.kr.alltestlib.util.Util;

import androidx.annotation.Nullable;

/**
 * resource
 * https://developer.android.com/guide/topics/resources/string-resource?hl=ko
 *
 * style, theme
 * https://developer.android.com/guide/topics/ui/themes?hl=ko
 * attr style -> part(view)
 * attr theme -> all(app, activity), need parent theme
 */
public class ResourceTestActivity extends BaseActivity {

   private Button btn_change_color_test;
   private Button btn_change_color_theme;
   private Button btn_change_color_theme2;
   private TextView tv_change_color_test;
   private TextView tv_string_array_test;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_resource_test);
      initUI();
      checkSizeTest();
   }

   private void initUI() {
      btn_change_color_test = findViewById(R.id.btn_change_color_test);
      btn_change_color_theme = findViewById(R.id.btn_change_color_theme);
      btn_change_color_theme2 = findViewById(R.id.btn_change_color_theme2);
      tv_change_color_test = findViewById(R.id.tv_change_color_test);
      tv_string_array_test = findViewById(R.id.tv_string_array_test);

      initBtnColor();
      initTextColor();
      initTextStringArray();
   }

   private void initBtnColor() {
      btn_change_color_test.setTextColor(ResourceUtil.getColorStatesList(this, R.color.sample_button));
      btn_change_color_test.setBackground(ResourceUtil.getDrawable(this, R.drawable.btn_sample));

      btn_change_color_theme.setTextColor(ResourceUtil.getColorWithTheme(this, R.color.sample_button_attr));
      // is checkable above api 23(M)
      btn_change_color_theme2.setTextColor(ResourceUtil.getColorWithTheme(this, R.color.sample_button_attr, R.style.CustomTheme2));
   }

   private void initTextColor() {
      tv_change_color_test.setTextColor(ResourceUtil.getColor(this, R.color.colorAccent));
   }

   private void initTextStringArray() {
      String[] stringArray = ResourceUtil.getStringArray(this, R.array.test_string_array);
      StringBuilder sb = new StringBuilder();
      for (String str : stringArray) {
         if (sb.length() > 0) {
            sb.append(", ");
         }
         sb.append(str);
      }

      tv_string_array_test.setText(sb.toString());
   }

   private void checkSizeTest() {
      int px = Util.dpToPx(40);
      int dp = Util.pxToDp(px);
      LogUtil.d("DpPxCheck", "40dp -> px : "+px);
      LogUtil.d("DpPxCheck", "px -> dp : "+dp);
   }
}

