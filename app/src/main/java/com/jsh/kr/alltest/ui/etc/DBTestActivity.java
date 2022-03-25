package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.DBTestManager;
import com.jsh.kr.alltest.model.data.DBTest;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBTestActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_db_text;
    private EditText et_db_integer;
    private Button btn_db_insert;
    private Button btn_db_clear;
    private Button btn_db_init;
    private Button btn_db_export;
    private ListView lv_db;

    private DBTestAdapter adapter = null;

    private DBTestManager dbTestUtil = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        initUI();
        initData();
    }

    private void initUI() {
        et_db_text = findViewById(R.id.et_db_text);
        et_db_integer = findViewById(R.id.et_db_integer);
        btn_db_insert = findViewById(R.id.btn_db_insert);
        btn_db_clear = findViewById(R.id.btn_db_clear);
        btn_db_init = findViewById(R.id.btn_db_init);
        btn_db_export = findViewById(R.id.btn_db_export);
        lv_db = findViewById(R.id.lv_db);

        btn_db_insert.setOnClickListener(this);
        btn_db_clear.setOnClickListener(this);
        btn_db_init.setOnClickListener(this);
        btn_db_export.setOnClickListener(this);

        adapter = new DBTestAdapter();
        adapter.setItemClickListener(dbItemClickListener);
        lv_db.setAdapter(adapter);
    }

    private void initData() {
        dbTestUtil = new DBTestManager(this);

        ArrayList<DBTest> dbTests = dbTestUtil.selectDBTest();

        adapter.refresh(dbTests);
    }

    private void insert() {
        String text = et_db_text.getText().toString();
        String integerStr = et_db_integer.getText().toString();
        int integer = 0;
        if(integerStr.length() > 0) {
            integer = Integer.valueOf(integerStr);
        }

        dbTestUtil.insertDBTest(text, integer);

        et_db_text.setText("");
        et_db_integer.setText("");

        initData();
    }

    private void delete(DBTest dbTest) {
        if(dbTest != null) {
            dbTestUtil.deleteDBTEstWithIdx(dbTest.getIdx());
            initData();
        }
    }

    private void clear() {
        dbTestUtil.deleteDBTest();
        initData();
    }

    private void init() {
        dbTestUtil.initDBTest();
        et_db_text.setText("");
        et_db_integer.setText("");
        initData();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_db_insert) {
            insert();
        } else if (id == R.id.btn_db_clear) {
            clear();
        } else if (id == R.id.btn_db_init) {
            init();
        } else if (id == R.id.btn_db_export) {
            dbTestUtil.sqliteExport(this);
        }
    }

    private OnDBItemClickListener dbItemClickListener = new OnDBItemClickListener() {
        @Override
        public void onDelete(DBTest dbTest) {
            delete(dbTest);
        }
    };

    private class DBTestAdapter extends BaseAdapter {

        private ArrayList<DBTest> dbTests;
        private OnDBItemClickListener itemClickListener;

        public void refresh(ArrayList<DBTest> dbTests) {
            this.dbTests = dbTests;
            notifyDataSetChanged();
        }

        void setItemClickListener(OnDBItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public int getCount() {
            if(dbTests != null) {
                return dbTests.size();
            }
            return 0;
        }

        @Override
        public DBTest getItem(int position) {
            if(dbTests != null && position >= 0 && position < getCount()) {
                return dbTests.get(position);
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
            if(convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_db, null);

                viewHolder = new ViewHolder(convertView);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                if(viewHolder == null) {
                    viewHolder = new ViewHolder(convertView);
                }
            }

            DBTest dbTest = getItem(position);
            viewHolder.pos = position;
            viewHolder.tv_db_idx.setText(String.valueOf(dbTest.getIdx()));
            viewHolder.tv_db_text.setText(dbTest.getText());
            viewHolder.tv_db_integer.setText(String.valueOf(dbTest.getInteger()));

            convertView.setTag(viewHolder);
            return convertView;
        }

        private class ViewHolder {
            int pos = -1;
            TextView tv_db_idx;
            TextView tv_db_text;
            TextView tv_db_integer;
            Button btn_db_delete;

            ViewHolder(View view) {
                tv_db_idx = view.findViewById(R.id.tv_db_idx);
                tv_db_text = view.findViewById(R.id.tv_db_text);
                tv_db_integer = view.findViewById(R.id.tv_db_integer);
                btn_db_delete = view.findViewById(R.id.btn_db_delete);

                btn_db_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBTest dbTest = getItem(pos);
                        if(itemClickListener != null) {
                            itemClickListener.onDelete(dbTest);
                        }
                    }
                });
            }
        }

    }

    interface OnDBItemClickListener {
        void onDelete(DBTest dbTest);
    }
}

