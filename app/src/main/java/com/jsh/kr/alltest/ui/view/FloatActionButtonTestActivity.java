package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * FloatActionButton base test
 *
 * https://github.com/codepath/android_guides/wiki/Handling-Scrolls-with-CoordinatorLayout
 *
 * Manifest NoActionBar style 지정으로 기본 action bar 제거
 */
public class FloatActionButtonTestActivity extends BaseActivity {
    RecyclerView rvToDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action_button_test);

        initUI();
    }

    private void initUI(){
        rvToDoList = (RecyclerView) findViewById(R.id.rvToDoList);

//        rvToDoList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvToDoList.setLayoutManager(layoutManager);

        rvToDoList.setAdapter(new RecycleTestAdapter());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private class RecycleTestAdapter extends RecyclerView.Adapter<RecycleTestAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycle_float_action_test, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv_recycle_toolbar_test.setText("text "+position);
        }

        @Override
        public int getItemCount() {
            return 40;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_recycle_toolbar_test;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_recycle_toolbar_test = (TextView) itemView.findViewById(R.id.tv_recycle_toolbar_test);
            }
        }
    }
}

