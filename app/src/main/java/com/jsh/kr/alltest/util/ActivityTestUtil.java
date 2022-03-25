package com.jsh.kr.alltest.util;

import android.content.Intent;

import com.jsh.kr.alltest.ui.etc.start.StartTestActivity;
import com.jsh.kr.alltest.ui.etc.start.TestActivity1;
import com.jsh.kr.alltest.ui.etc.start.TestActivity2;
import com.jsh.kr.alltest.ui.etc.start.TestFinishActivity;

/**
 * http://kylblog.tistory.com/21
 */
public class ActivityTestUtil {
   public static Class[] actList = {StartTestActivity.class, TestActivity1.class, TestActivity2.class, TestFinishActivity.class};
   public static String[] flagList = {"No"
           , "FLAG_ACTIVITY_SINGLE_TOP"
           , "FLAG_ACTIVITY_NO_HISTORY"
           , "FLAG_ACTIVITY_REORDER_TO_FRONT"
           , "FLAG_ACTIVITY_CLEAR_TOP"
           , "FLAG_ACTIVITY_NEW_TASK"
           , "FLAG_ACTIVITY_CLEAR_TASK"
   };

   public static String[] flagList2 = {"No"
           , "FLAG_ACTIVITY_NO_ANIMATION"
           , "FLAG_ACTIVITY_MULTIPLE_TASK"
   };

   public static int getFlag(int pos) {
      int flag = -1;
      switch (pos) {
         case 1:
            flag = Intent.FLAG_ACTIVITY_SINGLE_TOP;
            break;
         case 2:
            flag = Intent.FLAG_ACTIVITY_NO_HISTORY;
            break;
         case 3:
            flag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
            break;
         case 4:
            flag = Intent.FLAG_ACTIVITY_CLEAR_TOP;
            break;
         case 5:
            flag = Intent.FLAG_ACTIVITY_NEW_TASK;
            break;
         case 6:
            flag = Intent.FLAG_ACTIVITY_CLEAR_TASK;
            break;
      }

      return flag;
   }

   public static int getFlag2(int pos) {
      int flag = -1;

      switch (pos) {
         case 1:
            flag = Intent.FLAG_ACTIVITY_NO_ANIMATION;
            break;
         case 2:
            flag = Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
            break;
      }

      return flag;
   }


}

