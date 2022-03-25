package com.jsh.kr.alltest.ui.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.adapter.ListClickTestAdapter;
import com.jsh.kr.alltest.ui.BaseActivity;

/**
 * cause : a button in item isn't clicked
 * -> OnItemClick don't operate when view that have button event
 */
public class ListViewClickActivity extends BaseActivity {

    private ListView lv_list_click_test;
    private ListClickTestAdapter clickTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_click);

        lv_list_click_test = (ListView) findViewById(R.id.lv_list_click_test);

        clickTestAdapter = new ListClickTestAdapter();
        clickTestAdapter.setCustomClickListener(new ListClickTestAdapter.CustomListClickListener() {
            @Override
            public void onItemClick(int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListViewClickActivity.this);
                builder.setMessage("click pos "+pos);
                builder.create().show();
            }
        });

        lv_list_click_test.setAdapter(clickTestAdapter);
    }
}
