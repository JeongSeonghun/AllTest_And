package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

/**
 * thread test
 *
 */
public class ThreadTestActivity extends BaseActivity implements View.OnClickListener {
   private final static String TAG = ThreadTestActivity.class.getSimpleName();

   private final String Key_Time = "time";
   private final int What_Time = 1;

   private TextView tv_thread_time;
   private Button btn_thread_start;
   private Button btn_thread_pause;
   private Button btn_thread_stop;
   private Button btn_thread_stop_test;

   private TestThread testThread;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_thread_test);
      initUI();
   }

   private void initUI() {
      tv_thread_time = findViewById(R.id.tv_thread_time);
      btn_thread_start = findViewById(R.id.btn_thread_start);
      btn_thread_pause = findViewById(R.id.btn_thread_pause);
      btn_thread_stop = findViewById(R.id.btn_thread_stop);
      btn_thread_stop_test = findViewById(R.id.btn_thread_stop_test);

      btn_thread_start.setOnClickListener(this);
      btn_thread_stop.setOnClickListener(this);
      btn_thread_pause.setOnClickListener(this);
      btn_thread_stop_test.setOnClickListener(this);
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      stop();
   }

   private Handler handler = new Handler(Looper.getMainLooper()){
      @Override
      public void handleMessage(Message msg) {
         super.handleMessage(msg);
         Bundle data = msg.getData();
         if(data != null) {
            long time = data.getLong(Key_Time);
            showTime(time);
         }
      }
   };

   private void showTime(long time) {
      String form = "%1$d s";
      tv_thread_time.setText(String.format(Locale.KOREA, form, time));
   }

   private void start() {
      if(testThread == null) {
         testThread = new TestThread();
      }

      testThread.startTimer();
   }

   private void stop() {
      if (testThread != null) {
         testThread.stopTimer();
      }
   }

   private void pause() {
      if (testThread != null) {
         testThread.pauseTimer();
      }
   }

   @Override
   public void onClick(View v) {
      int id = v.getId();
      if (id == R.id.btn_thread_start) {
         start();
      } else if (id == R.id.btn_thread_pause) {
         pause();
      } else if (id == R.id.btn_thread_stop) {
         stop();
      } else if (id == R.id.btn_thread_stop_test) {
         testStop();
      }
   }

   // test no thread sleep in stop
   // --> result : interrupt not operate when have not sleep
   // ==> interrupt operate interrupt exception in sleep part
   boolean isTestStop = false;
   private void testStop() {
      isTestStop = true;
   }

   private class TestThread implements Runnable {
      private final int Status_Prepare = 1;
      private final int Status_Start = 2;
      private final int Status_Running = 3;
      private final int Status_Pause = 4;
      private final int Status_Stop = 5;

      private final int pauseSleepTime = 6 * 60 * 1000;
      private final int delayTime = 200;

      private int status = Status_Prepare;
      private long startTime;

      private Thread thread = null;

      TestThread() {
         init();
      }

      private void init() {
         LogUtil.d(TAG, "init");
         thread = new Thread(this);
         status = Status_Prepare;
      }

      @Override
      public void run() {
         status = Status_Running;
         startTime = System.currentTimeMillis();

         while (status == Status_Running || status == Status_Pause) {

            try {
               if (status == Status_Pause) {
                  LogUtil.d(TAG, "sleep pause start");
                  Thread.sleep(pauseSleepTime);
                  LogUtil.d(TAG, "sleep pause stop");
                  continue;
               }

               if (status == Status_Stop) {
                  break;
               }

               if(testStop()) {
                  continue;
               }

               sendMessage();

               LogUtil.d(TAG, "sleep delay start");
               Thread.sleep(delayTime);
               LogUtil.d(TAG, "sleep delay stop");

            } catch (InterruptedException e) {
               e.printStackTrace();
            }

         }
      }

      private boolean testStop() throws InterruptedException{
         if (isTestStop) {
            isTestStop = false;
            stop();

            sendMessage();

//                LogUtil.d(TAG, "sleep delay start");
//                Thread.sleep(delayTime);
//                LogUtil.d(TAG, "sleep delay stop");
            return true;
         }
         return false;
      }

      void startTimer() {
         LogUtil.d(TAG, "startTimer() : "+ status);

         if (status == Status_Pause) {
            LogUtil.d(TAG, "startTimer() restart");
            status = Status_Running;
            thread.interrupt();
         } else if (status == Status_Prepare || status == Status_Stop) {
            if (status == Status_Stop) {
               init();
            }

            LogUtil.d(TAG, "startTimer() start");
            status = Status_Running;
            thread.start();

         }
      }

      void pauseTimer() {
         LogUtil.d(TAG, "pauseTimer() : "+ status);
         if (status == Status_Running) {
            status = Status_Pause;
         }
      }

      void stopTimer() {
         LogUtil.d(TAG, "stopTimer() : "+ status);
         if(status != Status_Prepare) {
            thread.interrupt();
            status = Status_Stop;
         }
      }

      private void sendMessage() {
         long time = 0;
         Message message = Message.obtain(handler, What_Time);
         long temp = System.currentTimeMillis() - startTime;
         time = TimeUnit.MILLISECONDS.toSeconds(temp);

         LogUtil.d(TAG, "sendMessage msg : "+message);
         LogUtil.d(TAG, "sendMessage time : "+time + " / " + temp);

         if (message != null) {
            Bundle bundle = new Bundle();
            bundle.putLong(Key_Time, time);

            message.setData(bundle);

            handler.sendMessage(message);
         }
      }
   }
}

