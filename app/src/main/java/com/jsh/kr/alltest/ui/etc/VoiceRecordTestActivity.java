package com.jsh.kr.alltest.ui.etc;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.AllTestDirectory;
import com.jsh.kr.alltest.model.JEAudioRecord;
import com.jsh.kr.alltest.model.JEMediaPlay;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;

public class VoiceRecordTestActivity extends BaseActivity implements View.OnClickListener {
   private final static String TAG = VoiceRecordTestActivity.class.getSimpleName();

   private final int STATE_RECORD = 101;
   private final int STATE_RECORD_PAUSE = 102;
   private final int STATE_RECORD_STOP = 103;

   private Button btn_audio_record_start;
   private Button btn_audio_record_pause;
   private Button btn_audio_record_stop;
   private Button btn_audio_record_resume;
   private Button btn_record_file_play;
   private Button btn_record_file_stop;
   private TextView tv_record_state;
   private TextView tv_media_play_state;
   private TextView tv_media_play_time;
   private TextView tv_media_duration;
   private TextView tv_media_play_file;

   private JEAudioRecord audioRecord;
   private JEMediaPlay mediaPlay;

   private int recordState = JEMediaPlay.STATE_UNKOSWN;
   private int mediaState = JEMediaPlay.STATE_UNKOSWN;


   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_voice_record_test);
      initUI();
   }

   private void initUI() {

      btn_audio_record_start = findViewById(R.id.btn_audio_record_start);
      btn_audio_record_pause = findViewById(R.id.btn_audio_record_pause);
      btn_audio_record_stop = findViewById(R.id.btn_audio_record_stop);
      btn_audio_record_resume = findViewById(R.id.btn_audio_record_resume);
      btn_record_file_play = findViewById(R.id.btn_record_file_play);
      btn_record_file_stop = findViewById(R.id.btn_record_file_stop);
      tv_record_state = findViewById(R.id.tv_record_state);
      tv_media_play_state = findViewById(R.id.tv_media_play_state);
      tv_media_play_time = findViewById(R.id.tv_media_play_time);
      tv_media_duration = findViewById(R.id.tv_media_duration);
      tv_media_play_file = findViewById(R.id.tv_media_play_file);

      btn_audio_record_start.setOnClickListener(this);
      btn_audio_record_stop.setOnClickListener(this);
      btn_audio_record_pause.setOnClickListener(this);
      btn_audio_record_resume.setOnClickListener(this);
      btn_record_file_play.setOnClickListener(this);
      btn_record_file_stop.setOnClickListener(this);

      audioRecord = new JEAudioRecord(this);
      mediaPlay = new JEMediaPlay();

      mediaPlay.setMediaPlayListener(mediaPlayListener);

   }

   @Override
   public void onClick(View v) {
      switch (v.getId()){
         case R.id.btn_audio_record_start:
            recordState = STATE_RECORD;
            audioRecord.record();
            setRecordState();
            break;
         case R.id.btn_audio_record_stop:
            recordState = STATE_RECORD_STOP;
            audioRecord.recordStop();
            setRecordState();
            break;
         case R.id.btn_audio_record_pause:
            recordState = STATE_RECORD_PAUSE;
            audioRecord.recordPause();
            setRecordState();
            break;
         case R.id.btn_audio_record_resume:
            recordState = STATE_RECORD;
            audioRecord.recordResume();
            setRecordState();
            break;
         case R.id.btn_record_file_play:
            FileListActivity.show(this, AllTestDirectory.getRecordFileDir());
            break;
         case R.id.btn_record_file_stop:
            mediaPlay.stop();
            break;
      }
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if(resultCode == RESULT_OK){
         if(requestCode == C.RequestCode.FileList){
            LogUtil.d(TAG, "path : "+data.getStringExtra(C.Extra.EXTRA_KEY_PATH));
            File file = new File(data.getStringExtra(C.Extra.EXTRA_KEY_PATH));
            tv_media_play_file.setText(file.getName());
            tv_media_duration.setText("");

            if(file.getName().endsWith("mp3")){
               mediaPlay.setSource(file);
               mediaPlay.play();
            }
         }
      }
   }

   JEMediaPlay.JEMediaPlayListener mediaPlayListener = new JEMediaPlay.JEMediaPlayListener() {
      @Override
      public void onPrepare(int duration) {
         SimpleDateFormat format = new SimpleDateFormat("mm:ss");

         tv_media_duration.setText(format.format(new Date(duration)));
      }

      @Override
      public void onPlayState(String time, int state) {
         mediaState = state;
         setMediaState(time);
      }
   };

   public void setRecordState(){
      tv_record_state.setText(getMediaStateStr(recordState));
   }

   public void setMediaState(String time){
      tv_media_play_state.setText(getMediaStateStr(mediaState));
      tv_media_play_time.setText(time);
   }

   public String getMediaStateStr(int state){
      switch (state){
         case JEMediaPlay.STATE_PREPARE:
            return getString(R.string.prepare);
         case JEMediaPlay.STATE_PALY:
            return getString(R.string.play);
         case JEMediaPlay.STATE_STOP:
         case STATE_RECORD_STOP:
            return getString(R.string.stop);
         case STATE_RECORD:
            return getString(R.string.record);
         case STATE_RECORD_PAUSE:
            return getString(R.string.pause);
         case JEMediaPlay.STATE_UNKOSWN:
         default:
            return getString(R.string.unknown);
      }
   }


}
