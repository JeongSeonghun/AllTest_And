package com.jsh.kr.alltest.custom.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.AppDataUtil;
import com.jsh.kr.alltest.model.MainMoveData;

import java.util.ArrayList;

public class MainMoveAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<MainMoveData> moveDataArrayList;

    private MainMoveAdapter(){}

    public MainMoveAdapter(Activity activity) {
        this.activity = activity;
    }

    public void refresh(ArrayList<MainMoveData> moveDataArrayList) {
        this.moveDataArrayList = moveDataArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(moveDataArrayList != null) {
            return moveDataArrayList.size();
        }
        return 0;
    }

    @Override
    public MainMoveData getItem(int position) {
        if(position >=0 && position < getCount()) {
            return moveDataArrayList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_move, null);

            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
            if(holder == null) {
                holder = new ViewHolder(convertView);
            }
        }

        MainMoveData moveData = getItem(position);

        holder.btn_main_move.setText(moveData.getBtnNameId());
        setState(holder, moveData);

        holder.position = position;
        convertView.setTag(holder);
        return convertView;
    }

    private void move(int pos) {
        MainMoveData mainMoveData = getItem(pos);
        AppDataUtil.getInstance().setMaintainTestAct(mainMoveData.getActClass().getName());
        Intent intentAct = new Intent(activity, mainMoveData.getActClass());
        activity.startActivityForResult(intentAct, C.RequestCode.MoveTest);
    }

    private void setState(ViewHolder holder, MainMoveData data) {
        int stateId;
        String colorText;

        switch (data.getState()) {
            case C.State.Complete:
                stateId = R.string.state_complete;
                colorText = C.StateColor.Complete;
                break;
            case C.State.Proceed:
                stateId = R.string.state_proceed;
                colorText = C.StateColor.Proceed;
                break;
            case C.State.Resolution:
                stateId = R.string.state_resolution;
                colorText = C.StateColor.Resolution;
                break;
            case C.State.Test:
                stateId = R.string.state_test;
                colorText = C.StateColor.Test;
                break;
            case C.State.Close:
                stateId = R.string.state_close;
                colorText = C.StateColor.Close;
                break;
            case C.State.No_State:
            default:
                stateId = R.string.state_no;
                colorText = C.StateColor.No_State;
                break;
        }

        holder.tv_main_state.setText(stateId);
        holder.tv_main_state.setTextColor(Color.parseColor(colorText));
    }

    private class ViewHolder {
        Button btn_main_move;
        TextView tv_main_state;
        int position;

        ViewHolder(View view) {
            btn_main_move = view.findViewById(R.id.btn_main_move);
            tv_main_state = view.findViewById(R.id.tv_main_state);

            btn_main_move.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    move(position);
                }
            });
        }
    }
}

