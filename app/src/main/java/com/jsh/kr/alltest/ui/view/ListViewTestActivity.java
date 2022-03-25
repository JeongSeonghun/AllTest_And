package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewTestActivity extends BaseActivity implements View.OnClickListener {

   private Button btn_list_add;
   private EditText et_list_add;
   private ListView lv_list_add;
   private RecyclerView rv_list_add;
   private Switch sw_list_add_mode;

   private TestAdapter adapter;
   private TestAdapterR adapterR;

   private boolean IS_TEST_LIST = false;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_listview_test);
      initUI();
   }

   private void initUI() {
      et_list_add = findViewById(R.id.et_list_add);
      btn_list_add = findViewById(R.id.btn_list_add);
      lv_list_add = findViewById(R.id.lv_list_add);
      rv_list_add = findViewById(R.id.rv_list_add);
      sw_list_add_mode = findViewById(R.id.sw_list_add_mode);

      btn_list_add.setOnClickListener(this);
      sw_list_add_mode.setOnCheckedChangeListener(checkedChangeListener);

      adapter = new TestAdapter();
      lv_list_add.setAdapter(adapter);
      lv_list_add.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

      LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
      rv_list_add.setLayoutManager(layoutManager);
      adapterR = new TestAdapterR();
      rv_list_add.setAdapter(adapterR);

   }

   private void initListViewShow() {
      if (IS_TEST_LIST) {
         lv_list_add.setVisibility(View.VISIBLE);
         rv_list_add.setVisibility(View.GONE);
      } else {
         lv_list_add.setVisibility(View.GONE);
         rv_list_add.setVisibility(View.VISIBLE);
      }
   }

   private void add() {
      String msg = et_list_add.getText().toString();

      if (IS_TEST_LIST) {
         adapter.addData(msg);
         adapter.notifyDataSetChanged();
      } else {
         adapterR.addData(msg);
         adapterR.notifyDataSetChanged();
      }
   }

   private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
         IS_TEST_LIST = !isChecked;
         initListViewShow();
      }
   };

   @Override
   public void onClick(View v) {
      switch (v.getId()) {
         case R.id.btn_list_add:
            add();
            break;
      }
   }

   private class TestAdapter extends BaseAdapter {
      private ArrayList<String> testList = new ArrayList<>();

      void addData(String data) {
         testList.add(data);
      }

      @Override
      public int getCount() {
         return testList.size();
      }

      @Override
      public String getItem(int position) {
         return testList.get(position);
      }

      @Override
      public long getItemId(int position) {
         return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {

         ViewHolder holder = null;
         if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_test, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
         } else {
            holder = (ViewHolder) convertView.getTag();
         }

         String msg = getItem(position);
         holder.setData(msg);

         return convertView;
      }

      class ViewHolder {
         private TextView tv_listview_test_add;

         ViewHolder(View view) {
            tv_listview_test_add = view.findViewById(R.id.tv_listview_test_add);

         }

         void setData(String msg) {
            tv_listview_test_add.setText(msg);
         }
      }
   }

   private class TestAdapterR extends RecyclerView.Adapter<TestAdapterR.ViewHolderR> {
      private ArrayList<String> testList = new ArrayList<>();

      void addData(String data) {
         testList.add(data);
      }

      private String getItem(int position) {
         return testList.get(position);
      }

      @NonNull
      @Override
      public ViewHolderR onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new ViewHolderR(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_test, parent, false));
      }

      @Override
      public void onBindViewHolder(@NonNull ViewHolderR holder, int position) {
         String msg = getItem(position);
         holder.setData(msg);
      }

      @Override
      public int getItemCount() {
         return testList.size();
      }

      class ViewHolderR extends RecyclerView.ViewHolder {
         private TextView tv_listview_test_add;
         public ViewHolderR(View itemView) {
            super(itemView);
            tv_listview_test_add = itemView.findViewById(R.id.tv_listview_test_add);
         }

         void setData(String msg) {
            tv_listview_test_add.setText(msg);
         }
      }
   }
}
