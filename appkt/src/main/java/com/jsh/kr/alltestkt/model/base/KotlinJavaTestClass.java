package com.jsh.kr.alltestkt.model.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KotlinJavaTestClass {

   public KotlinJavaTestClass() {

   }

   public static final String TAG = "abc";
   private String testMsg;
   private boolean isFlag;

   public String getTestMsg() {
      return testMsg;
   }

   public void setTestMsg(String testMsg) {
      this.testMsg = testMsg;
   }

   public boolean isFlag() {
      return isFlag;
   }

   public void setFlag(boolean flag) {
      isFlag = flag;
   }

   public @NonNull String makeTextMsg(@Nullable String msg) {
      return msg;
   }

   public static String makeText(int num) {
      return "Kotlin Java test "+ num;
   }
}
