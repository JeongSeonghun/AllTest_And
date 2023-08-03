package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.adapter.DragAndDropAdaptor;
import com.jsh.kr.alltest.custom.helper.DragAndDropHelper;
import com.jsh.kr.alltest.ui.BaseActivity;

public class DragAndDropListActivity extends BaseActivity {

    private RecyclerView rvList;
    private DragAndDropAdaptor adaptor;

    private ItemTouchHelper touchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop_list);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        adaptor = new DragAndDropAdaptor();
        rvList.setAdapter(adaptor);

        DragAndDropHelper helper = new DragAndDropHelper(adaptor);
        touchHelper = new ItemTouchHelper(helper);
        touchHelper.attachToRecyclerView(rvList);
        adaptor.setDragListener(viewHolder -> touchHelper.startDrag(viewHolder));

    }
}
