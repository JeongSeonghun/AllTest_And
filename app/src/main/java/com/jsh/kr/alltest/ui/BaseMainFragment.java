package com.jsh.kr.alltest.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.MainMoveData;
import com.jsh.kr.alltest.custom.adapter.MainMoveAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseMainFragment extends BaseFragment {

    private ListView lv_move_base;

    private MainMoveAdapter moveAdapter;

    private ArrayList<MainMoveData> moveDataArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_main, container, false);

        initUI(view);

        refresh();
        return view;
    }

    private void initUI(View view) {
        lv_move_base = view.findViewById(R.id.lv_move_base);

        moveAdapter = new MainMoveAdapter(getActivity());
        lv_move_base.setAdapter(moveAdapter);
    }

    public void initData(ArrayList<MainMoveData> moveData) {
        this.moveDataArrayList = moveData;
    }

    public void addData(MainMoveData moveData) {
        moveDataArrayList.add(moveData);
    }

    public void refresh() {
        moveAdapter.refresh(moveDataArrayList);
    }

    public int getListSize() {
        return moveDataArrayList.size();
    }

    public interface OnMainFragmentListener {

    }
}
