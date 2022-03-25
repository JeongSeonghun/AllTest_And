package com.jsh.kr.alltest.model;

import android.webkit.JavascriptInterface;

public class JavaScriptManager {

   public static final String TAG = JavaScriptManager.class.getSimpleName();
   private OnJavaScriptManagerListener listener;

   public void setJavaScriptManagerListener(OnJavaScriptManagerListener listener) {
      this.listener = listener;
   }

   @JavascriptInterface
   public void onResult(String result) {
      listener.onResult(result);
   }

   public interface OnJavaScriptManagerListener {
      void onResult(String result);
   }
}
