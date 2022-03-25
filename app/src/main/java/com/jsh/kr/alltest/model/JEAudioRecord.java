package com.jsh.kr.alltest.model;

import android.content.ContentValues;
import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JEAudioRecord {

   private MediaRecorder recorder;
   private File recordFile;
   private Context ctx;

   public JEAudioRecord(Context ctx){
      this.ctx = ctx;
   }

   public void record(){
      if(recorder != null){
         recorder.stop();
         recorder.release();
         recorder = null;
      }

      recordFile = createFile();

      recorder = new MediaRecorder();

      recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
      recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
      recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
      recorder.setOutputFile(recordFile.getAbsolutePath());

      try{
         recorder.prepare();
         recorder.start();
      } catch (Exception e){
         e.printStackTrace();
      }

   }

   public void recordPause() {
      if (recorder == null) {
         return;
      }

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         recorder.pause();
      }
   }

   public void recordResume() {
      if (recorder == null) {
         return;
      }

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         recorder.resume();
      }
   }

   public void recordStop(){
      if(recorder == null){
         return;
      }

      recorder.stop();
      recorder.release();
      recorder = null;

      // 미디어 앨범 등록용 녹음파일 정보
      ContentValues values = new ContentValues(10);
      values.put(MediaStore.MediaColumns.TITLE, "Recorded");
      values.put(MediaStore.Audio.Media.ALBUM, "Audio Album");
      values.put(MediaStore.Audio.Media.ARTIST, "Mike");
      values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio");
      values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
      values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
      values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis()/1000);
      values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
      values.put(MediaStore.Audio.Media.DATA, recordFile.getAbsolutePath());

      Uri audioUri = ctx.getContentResolver().insert(
              MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values
      );

      if(audioUri == null){
         return;
      }

   }



   public File createFile(){
      SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");

      String fileName = format.format(new Date())+"_record.mp3";
      File file = new File(AllTestDirectory.getRecordFileDir(),fileName);

      return file;
   }


   public File[] getFileList(){
      FilenameFilter filenameFilter = new FilenameFilter() {
         @Override
         public boolean accept(File dir, String name) {
            if(name.endsWith("mp3")){
               return true;
            }
            return false;
         }
      };

      return recordFile.listFiles(filenameFilter);
   }
}