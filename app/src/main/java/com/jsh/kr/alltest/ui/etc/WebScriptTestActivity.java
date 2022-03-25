package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.JavaScriptManager;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.Nullable;

/**
 * JavaScript test
 *
 */
public class WebScriptTestActivity extends BaseActivity implements JavaScriptManager.OnJavaScriptManagerListener{

   private WebView wv_script_test;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_web_script_test);

      initUI();
   }

   private void initUI() {
      wv_script_test = findViewById(R.id.wv_script_test);

      initWebView();
   }

   private void initWebView() {
      // security issue --> https://developer.android.com/training/articles/security-tips
      // --> when use java script in app, use setJavascriptEnabled
      wv_script_test.getSettings().setJavaScriptEnabled(true);

      JavaScriptManager javaScriptManager = new JavaScriptManager();
      javaScriptManager.setJavaScriptManagerListener(this);
      wv_script_test.addJavascriptInterface(javaScriptManager, JavaScriptManager.TAG);

      wv_script_test.setWebViewClient(new WebViewClient());

      wv_script_test.loadUrl(makeStartUrl());
   }

   private String makeStartUrl() {
      return "file:///android_asset/scripttest/index.html";
   }

   @Override
   public void onResult(String result) {
      LogUtil.d("script_test", "onResult :"+result);

   }

}

