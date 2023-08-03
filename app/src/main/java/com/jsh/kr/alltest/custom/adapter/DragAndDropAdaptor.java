package com.jsh.kr.alltest.custom.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.custom.helper.DragAndDropHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragAndDropAdaptor extends RecyclerView.Adapter<DragAndDropAdaptor.DragItemView> implements DragAndDropHelper.OnItemMoveListener {

    private List<String> list = new ArrayList<>();

    private OnStartDragListener dragListener;

    public DragAndDropAdaptor() {
        for (int num = 0 ; num < 10 ; num ++) {
            list.add("title" + num);
        }
    }

    public void setDragListener(OnStartDragListener listener) {
        this.dragListener = listener;
    }

    @NonNull
    @Override
    public DragItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DragItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_drop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DragItemView holder, int position) {
        holder.setTitle("title" + position);
        this.dragListener.onDragStart(holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMove(Integer fromPos, Integer toPos) {
        Collections.swap(list, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
    }

    public class DragItemView extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        public DragItemView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setTitle(String txt) {
            tvTitle.setText(txt);
        }

        public void setDragState(Boolean isDrag) {
            if (isDrag) {
                tvTitle.setBackgroundColor(Color.parseColor("#f1f131"));
            } else {
                tvTitle.setBackgroundColor(Color.parseColor("#f1f1f1"));
            }
        }
    }

    public interface OnStartDragListener {
        void onDragStart(DragItemView viewHolder);
    }
}
