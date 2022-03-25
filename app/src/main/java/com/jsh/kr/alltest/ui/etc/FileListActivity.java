package com.jsh.kr.alltest.ui.etc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.adapter.FileListAdapter;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.io.File;

public class FileListActivity extends BaseActivity {

   private TextView path_file_list;
   private ListView lv_files;
   private FileListAdapter fileListAdapter;


   public static void show(Activity act, File dir){
      if(!dir.exists()){
         return;
      }

      Intent intentAct = new Intent(act, FileListActivity.class);
      intentAct.putExtra(C.Extra.EXTRA_KEY_PATH, dir.getAbsolutePath());
      act.startActivityForResult(intentAct, C.RequestCode.FileList);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_file_list);

      path_file_list = findViewById(R.id.path_file_list);
      lv_files = (ListView)findViewById(R.id.lv_files);

      lv_files.setOnItemClickListener(itemClickListener);

      fileListAdapter = new FileListAdapter();

      lv_files.setAdapter(fileListAdapter);

      setData();
   }

   private void setData(){
      String path = getIntent().getStringExtra(C.Extra.EXTRA_KEY_PATH);

      path_file_list.setText(path);
      if(!TextUtils.isEmpty(path)){
         File dir = new File(path);
         fileListAdapter.setDir(dir);
         fileListAdapter.notifyDataSetChanged();
      }
   }

   private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         File file = fileListAdapter.getItem(position);

         if(file == null || !file.exists()){
            return;
         }

         if(!file.isDirectory() && file.isFile()){
            closeAndSend(file);
         }
      }
   };

   private void closeAndSend(File file){
      Intent intent = new Intent();
      intent.putExtra(C.Extra.EXTRA_KEY_PATH, file.getAbsolutePath());
      setResult(RESULT_OK, intent);
      finish();
   }
}
