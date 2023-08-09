package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.JavaScriptManager;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.util.TestTextUtil;

import androidx.annotation.Nullable;

/**
 * JavaScript test
 *
 */
public class WebScriptTestActivity extends BaseActivity implements JavaScriptManager.OnJavaScriptManagerListener{

   private WebView wvScriptTest;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_web_script_test);

      initUI();
   }

   private void initUI() {
      wvScriptTest = findViewById(R.id.wv_script_test);

      initWebView();
   }

   private void initWebView() {
      // security issue --> https://developer.android.com/training/articles/security-tips
      // --> when use java script in app, use setJavascriptEnabled
      wvScriptTest.getSettings().setJavaScriptEnabled(true);

      JavaScriptManager javaScriptManager = new JavaScriptManager();
      javaScriptManager.setJavaScriptManagerListener(this);
      wvScriptTest.addJavascriptInterface(javaScriptManager, JavaScriptManager.TAG);

      wvScriptTest.setWebViewClient(new WebViewClient());

      wvScriptTest.loadUrl(makeStartUrl());
   }

   private String makeStartUrl() {
      return "file:///android_asset/scripttest/index.html";
   }

   @Override
   public void onResult(String result) {
      LogUtil.d("script_test", "onResult :"+result);

      runOnUiThread(() -> {
//         wvScriptTest.evaluateJavascript("javascript:inputValue()", null);
         wvScriptTest.evaluateJavascript("javascript:inputValue()", (value) -> {
            LogUtil.d("script_test", "inputValue :"+value);
         });
      });
   }

   @Override
   public void getRandomValue() {
         runOnUiThread(() -> wvScriptTest.loadUrl(String.format("javascript:setRandomValue('%s')", TestTextUtil.makeRandomNumEng(5))));

   }

}

