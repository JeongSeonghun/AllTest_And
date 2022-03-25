package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecycleViewTestActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView rv_recycle_test;

    private RecycleTestAdapter recycleTestAdapter;

    private Button btn_recycle_vertical;
    private Button btn_recycle_horizontal;
    private Button btn_recycle_linear;
    private Button btn_recycle_grid;
    private Button btn_recycle_staggered_grid;

    private final int DefaultGridSpan = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_test);

        initUI();
    }

    private void initUI() {
        rv_recycle_test = findViewById(R.id.rv_recycle_test);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_recycle_test.setLayoutManager(linearLayoutManager);

        recycleTestAdapter = new RecycleTestAdapter();
        rv_recycle_test.setAdapter(recycleTestAdapter);

        initAddUI();
    }

    private void initAddUI() {
        btn_recycle_vertical = findViewById(R.id.btn_recycle_vertical);
        btn_recycle_horizontal = findViewById(R.id.btn_recycle_horizontal);
        btn_recycle_linear = findViewById(R.id.btn_recycle_linear);
        btn_recycle_grid = findViewById(R.id.btn_recycle_grid);
        btn_recycle_staggered_grid = findViewById(R.id.btn_recycle_staggered_grid);

        btn_recycle_vertical.setOnClickListener(this);
        btn_recycle_horizontal.setOnClickListener(this);
        btn_recycle_linear.setOnClickListener(this);
        btn_recycle_grid.setOnClickListener(this);
        btn_recycle_staggered_grid.setOnClickListener(this);
    }

    private void setRecycleVertical() {
        RecyclerView.LayoutManager layoutManager = rv_recycle_test.getLayoutManager();

        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager)layoutManager).setOrientation(StaggeredGridLayoutManager.VERTICAL);
        }
    }

    private void setRecycleHorizontal() {
        RecyclerView.LayoutManager layoutManager = rv_recycle_test.getLayoutManager();

        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager)layoutManager).setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
        }
    }

    private void setRecycleLinear() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_recycle_test.setLayoutManager(linearLayoutManager);
    }

    private void setRecycleGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, DefaultGridSpan);
        rv_recycle_test.setLayoutManager(gridLayoutManager);
    }

    private void setRecycleStaggeredGrid() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(DefaultGridSpan, StaggeredGridLayoutManager.VERTICAL);
        rv_recycle_test.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_recycle_vertical) {
            setRecycleVertical();
        } else if (id == R.id.btn_recycle_horizontal) {
            setRecycleHorizontal();
        } else if (id == R.id.btn_recycle_linear) {
            setRecycleLinear();
        } else if (id == R.id.btn_recycle_grid) {
            setRecycleGrid();
        } else if (id == R.id.btn_recycle_staggered_grid) {
            setRecycleStaggeredGrid();
        }
    }

    class RecycleTestAdapter extends RecyclerView.Adapter<RecycleTestAdapter.ViewHolder> {
        private final String[] conts = {
                getString(R.string.test_cont1)
                , getString(R.string.test_cont2)
                , getString(R.string.test_cont3)
                , getString(R.string.test_cont1)};

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_test, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv_recycle_item_cont.setText(conts[position]);
        }

        @Override
        public int getItemCount() {
            return conts.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_recycle_item_cont;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_recycle_item_cont = itemView.findViewById(R.id.tv_recycle_item_cont);
            }
        }
    }
}

