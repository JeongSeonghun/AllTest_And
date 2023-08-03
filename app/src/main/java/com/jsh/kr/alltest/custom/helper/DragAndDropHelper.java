package com.jsh.kr.alltest.custom.helper;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jsh.kr.alltest.custom.adapter.DragAndDropAdaptor;

public class DragAndDropHelper extends ItemTouchHelper.Callback {

    private OnItemMoveListener listener;

    public DragAndDropHelper(OnItemMoveListener listener) {
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        this.listener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//        if (viewHolder instanceof DragAndDropAdaptor.DragItemView) {
//            ((DragAndDropAdaptor.DragItemView) viewHolder).setDragState(false);
//        }
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
//        if (viewHolder instanceof DragAndDropAdaptor.DragItemView) {
//            ((DragAndDropAdaptor.DragItemView) viewHolder).setDragState(actionState == ItemTouchHelper.ACTION_STATE_DRAG);
//        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (viewHolder instanceof DragAndDropAdaptor.DragItemView) {
            ((DragAndDropAdaptor.DragItemView) viewHolder).setDragState(isCurrentlyActive);
        }
    }

    public interface OnItemMoveListener {
        void onMove(Integer fromPos, Integer toPos);
    }
}
