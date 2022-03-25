package com.jsh.kr.alltest.ui.etc;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import java.util.Locale;

import androidx.annotation.Nullable;

/**
 * Motion Event test
 *
 */
public class EventTestActivity extends BaseActivity {
   private final static String TAG = EventTestActivity.class.getSimpleName();
   private Button btn_event_touch;
   private TextView tv_event_touch;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_event_test);
      initUI();
   }

   private void initUI() {
      btn_event_touch = findViewById(R.id.btn_event_touch);
      tv_event_touch = findViewById(R.id.tv_event_touch);

      btn_event_touch.setOnTouchListener(touchListener);
   }

   private void showStateTouch(MotionEvent event) {
      String statusMsg = null;
      switch (event.getAction()) {
         case MotionEvent.ACTION_DOWN:
            statusMsg = getString(R.string.action_down);
            showMoveStatus(event);
            break;
         case MotionEvent.ACTION_UP:
            statusMsg = getString(R.string.action_up);
            break;
         case MotionEvent.ACTION_MOVE:
            showMoveStatus(event);
//                statusMsg = getString(R.string.action_none);
            break;
         default:
            statusMsg = String.format(Locale.KOREA, getString(R.string.action_form), event.getAction());
            break;
      }
      if (statusMsg != null) {
         LogUtil.d(TAG, "touch action : "+statusMsg);
         tv_event_touch.setText(statusMsg);
      }
   }

   private void showMoveStatus(MotionEvent event) {
      float x = event.getX();
      float y = event.getY();
      LogUtil.d(TAG, "touch action x y : "+x + "/" + y);
   }

   private View.OnTouchListener touchListener = new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
         if (v.getId() == R.id.btn_event_touch) {
            showStateTouch(event);
         }
         return false;
      }
   };
}

