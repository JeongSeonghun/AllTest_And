package com.jsh.kr.alltest.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.util.LogUtil;

public class SpinnerTestAdapter extends BaseAdapter {

    private final static String TAG = SpinnerTestAdapter.class.getSimpleName();

    private final String[] items = new String[]{"men", "women"};

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public String getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogUtil.d(TAG, "getView()"+position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_spinner_test, parent, false);
        }else {
            LogUtil.d(TAG, "getView tag "+(String)convertView.getTag());
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_spinner_test);

        textView.setText(getItem(position));

        convertView.setTag("normal");

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LogUtil.d(TAG, "getDropdownView()"+position);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_spinner_test_dropdown, parent, false);
        }else{
            LogUtil.d(TAG, "getDropDownView tag "+(String)convertView.getTag());
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_spinner_test_drop);

        textView.setText(getItem(position));

        convertView.setTag("dropdown");
        return convertView;
    }
}
