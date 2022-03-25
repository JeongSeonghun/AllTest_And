package com.jsh.kr.alltest.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.util.FileUtil;
import com.jsh.kr.alltest.util.LogUtil;

import java.io.File;

public class FileListAdapter extends BaseAdapter {

   private File dir = null;
   private File[] files = null;

   public void setDir(File dir){
      this.dir = dir;
      if(dir.exists()){
         this.files = dir.listFiles();
      }
   }

   @Override
   public int getCount() {
      if(files != null){
         return files.length;
      }
      return 0;
   }

   @Override
   public File getItem(int position) {
      if(files != null && position < files.length){
         return files[position];
      }
      return null;
   }

   @Override
   public long getItemId(int position) {
      return 0;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder viewHolder;

      if(convertView == null){
         LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         convertView = inflater.inflate(R.layout.item_file_list, null);

         viewHolder = new ViewHolder();
         viewHolder.tv_file_type = (TextView)convertView.findViewById(R.id.tv_file_type);
         viewHolder.tv_file_name = (TextView)convertView.findViewById(R.id.tv_file_name);
      }else{
         viewHolder = (ViewHolder)convertView.getTag();
      }

      File file = getItem(position);
      LogUtil.d("FileListAdapter", "list item : "+file);

      viewHolder.tv_file_type.setText(FileUtil.getFileTypeStr(file));
      viewHolder.tv_file_name.setText(file.getName());

      convertView.setTag(viewHolder);

      return convertView;
   }

   private class ViewHolder{
      TextView tv_file_type;
      TextView tv_file_name;
   }
}
