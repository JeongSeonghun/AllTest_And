package com.jsh.kr.alltest.model;


import android.media.MediaPlayer;
import android.media.TimedText;

import com.jsh.kr.alltest.util.LogUtil;

import java.io.File;
import java.io.IOException;

public class JEMediaPlay {
   public static final int STATE_UNKOSWN = -1;
   public static final int STATE_PREPARE = 0;
   public static final int STATE_PALY = 1;
   public static final int STATE_STOP = 2;
   public static final int STATE_STOPING = 3;

   public static final int NoError = -1;
   public static final int UnNkown = 100;

   private MediaPlayer player;
   private File source;
   private JEMediaPlayListener listener;
   private int state = STATE_UNKOSWN;

   public JEMediaPlay(){

   }

   public void init(){
      player = new MediaPlayer();
      player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
         @Override
         public boolean onInfo(MediaPlayer mp, int what, int extra) {
            return false;
         }
      });
      player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
         @Override
         public void onPrepared(MediaPlayer mp) {
            if(listener != null){
               state = STATE_PALY;
               listener.onPrepare(mp.getDuration());
            }
         }
      });
      player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
         @Override
         public void onCompletion(MediaPlayer mp) {
            if(listener != null){
               LogUtil.d("JEMedia play", "onCompletion : "+mp.isPlaying());
               state = STATE_STOP;
               listener.onPlayState("", state);

            }
         }
      });
      player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
         @Override
         public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
         }
      });
      player.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
         @Override
         public void onTimedText(MediaPlayer mp, TimedText text) {
            if(listener != null){
               listener.onPlayState(text.getText(), state);
            }
         }
      });

      try {
         player.setDataSource(source.getAbsolutePath());
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void setSource(File source){
      this.source = source;
   }

   public void setMediaPlayListener(JEMediaPlayListener listener){
      this.listener = listener;
   }

   public void play(){
      if(player != null){
         player.release();
         player = null;
      }
      init();

      try {
         listener.onPlayState("", STATE_PREPARE);
         player.prepare();
         player.start();
         listener.onPlayState("", STATE_PALY);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void pause(){
      if(player.isPlaying()){
         player.pause();
      }
   }

   public void stop(){
      if(player != null && player.isPlaying()){
         player.stop();
         player.release();
      }
   }

   public interface JEMediaPlayListener{
      void onPrepare(int duration);
      void onPlayState(String time, int state);
   }
}
