package com.jsh.kr.alltest.ui.etc;


import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivitySoundControlTestBinding;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class SoundControlTestActivity extends BaseActivity {

   private ActivitySoundControlTestBinding binding;

   private AudioManager audioManager;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_sound_control_test);

      init();
   }

   private void init() {
      binding.btnSoundVolumeSave.setOnClickListener(view -> {
         saveRingVolume();
      });

      binding.btnSoundVolumeMusicSave.setOnClickListener(view -> {
         saveMusicVolume();
      });

      binding.btnSoundVolumeSystemSave.setOnClickListener(view -> {
         saveSystemVolume();
      });

      binding.btnSoundVolumeAlarmSave.setOnClickListener(view -> {
         saveAlarmVolume();
      });

      binding.btnSoundVolumeAllSave.setOnClickListener(view -> {
         saveAllVolume();
      });

      binding.sbSoundVolume.setMax(100);
      binding.sbSoundVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            showRingVolume(i);
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
      });

      binding.sbSoundVolumeMusic.setMax(100);
      binding.sbSoundVolumeMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            showMusicVolume(i);
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
      });

      binding.sbSoundVolumeAll.setMax(100);
      binding.sbSoundVolumeAll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            showAllVolume(i);
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
      });

      binding.sbSoundVolumeSystem.setMax(100);
      binding.sbSoundVolumeSystem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            showSystemVolume(i);
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
      });

      binding.sbSoundVolumeSystem.setMax(100);
      binding.sbSoundVolumeSystem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            showAlarmVolume(i);
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
      });

      audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

      showCurrentVolume();
   }

   private int getMaxVolume(int streamType) {
      int maxVolume = 0;

      if (audioManager != null) {
         maxVolume = audioManager.getStreamMaxVolume(streamType);
      }

      return maxVolume;
   }

   private void saveVolume(int streamType, int volume) {
      if (audioManager != null) {
         audioManager.setStreamVolume(streamType, volume, AudioManager.FLAG_PLAY_SOUND);
      }
   }

   private void saveRingVolume() {
      int type = AudioManager.STREAM_RING;
      int per = binding.sbSoundVolume.getProgress();
      int volume = makeVolumeFromPer(type, per);

      saveVolume(type, volume);

      showCurrentRingVolume();
   }

   private void saveMusicVolume() {
      int type = AudioManager.STREAM_MUSIC;
      int per = binding.sbSoundVolumeMusic.getProgress();
      int volume = makeVolumeFromPer(type, per);

      saveVolume(type, volume);

      showCurrentMusicVolume();
   }

   private void saveSystemVolume() {
      int type = AudioManager.STREAM_SYSTEM;
      int per = binding.sbSoundVolumeSystem.getProgress();
      int volume = makeVolumeFromPer(type, per);

      saveVolume(type, volume);

      showCurrentSystemVolume();
   }

   private void saveAlarmVolume() {
      int type = AudioManager.STREAM_NOTIFICATION;
      int per = binding.sbSoundVolumeAlarm.getProgress();
      int volume = makeVolumeFromPer(type, per);

      saveVolume(type, volume);

      showCurrentAlarmVolume();
   }

   private void saveAllVolume() {
      int per = binding.sbSoundVolumeAll.getProgress();

      int maxRing = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
      int volumeRing = maxRing * per / 100;
      int maxMusic = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
      int volumeMusic = maxMusic * per / 100;
      int maxSystem = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
      int volumeSystem = maxSystem * per / 100;
      int maxAlarm = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
      int volumeAlarm = maxAlarm * per / 100;

      saveVolume(AudioManager.STREAM_RING, volumeRing);
      saveVolume(AudioManager.STREAM_MUSIC, volumeMusic);
      saveVolume(AudioManager.STREAM_SYSTEM, volumeSystem);
      saveVolume(AudioManager.STREAM_NOTIFICATION, volumeAlarm);

      showCurrentRingVolume();
      showCurrentMusicVolume();
      showCurrentSystemVolume();
      showCurrentAlarmVolume();
   }

   private int getCurrentVolume(int streamType) {
      int volume = 0;
      if (audioManager != null) {
         volume = audioManager.getStreamVolume(streamType);
      }
      return volume;
   }

   private int makeValueFromPer(int per, int max) {
      return max * per / 100;
   }
   private int makePerFromValue(int value, int max) {
      return max != 0 ? value * 100 / max : 0;
   }

   private int makeVolumeFromPer(int type, int per) {
      int max = getMaxVolume(type);
      return makeValueFromPer(per, max);
   }

   private int makePerFromVolume(int type, int volume) {
      int max = getMaxVolume(type);
      return makePerFromValue(volume, max);
   }

   private String makePerString(int per) {
      String form = "%1$d %%";
      return String.format(Locale.KOREA, form, per);
   }

   private void showCurrentRingVolume() {
      int type = AudioManager.STREAM_RING;
      int volume = getCurrentVolume(type);
      int per = makePerFromVolume(type, volume);

      binding.sbSoundVolume.setProgress(per);
//        showRingVolume(per);
   }

   private void showCurrentMusicVolume() {
      int type = AudioManager.STREAM_MUSIC;
      int volume = getCurrentVolume(type);
      int per = makePerFromVolume(type, volume);

      binding.sbSoundVolumeMusic.setProgress(per);
//        showMusicVolume(per);
   }

   private void showCurrentSystemVolume() {
      int type = AudioManager.STREAM_SYSTEM;
      int volume = getCurrentVolume(type);
      int per = makePerFromVolume(type, volume);

      binding.sbSoundVolumeSystem.setProgress(per);
//        showSystemVolume(per);
   }

   private void showCurrentAlarmVolume() {
      int type = AudioManager.STREAM_NOTIFICATION;
      int volume = getCurrentVolume(type);
      int per = makePerFromVolume(type, volume);

      binding.sbSoundVolumeAlarm.setProgress(per);
//        showAlarmVolume(per);
   }

   private void showRingVolume(int per) {
      binding.tvSoundVolume.setText(makePerString(per));
   }

   private void showMusicVolume(int per) {
      binding.tvSoundVolumeMusic.setText(makePerString(per));
   }

   private void showSystemVolume(int per) {
      binding.tvSoundVolumeSystem.setText(makePerString(per));
   }

   private void showAlarmVolume(int per) {
      binding.tvSoundVolumeAlarm.setText(makePerString(per));
   }

   private void showAllVolume(int per) {
      binding.tvSoundVolumeAll.setText(makePerString(per));
   }

   private void showCurrentVolume() {
      showCurrentRingVolume();
      showCurrentMusicVolume();
      showCurrentSystemVolume();
      showCurrentAlarmVolume();
   }

}

