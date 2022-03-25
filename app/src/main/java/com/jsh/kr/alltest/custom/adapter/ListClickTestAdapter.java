package com.jsh.kr.alltest.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;

public class ListClickTestAdapter extends BaseAdapter implements View.OnClickListener{

    private CustomListClickListener clickListener;

    public void setCustomClickListener(CustomListClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * when ListView or parent View height is wrap_contents or 0dp, getView() is called many times
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_list_click_test, parent, false);

            convertView.setOnClickListener(this);

            holder = new ViewHolder();
            holder.tv_list_click_test = (TextView) convertView.findViewById(R.id.tv_list_click_test);
            holder.btn_list_click_test = (Button) convertView.findViewById(R.id.btn_list_click_test);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.position = position;
        holder.tv_list_click_test.setText("item"+position);

        convertView.setTag(holder);
        return convertView;
    }

    private class ViewHolder{
        public int position;
        public TextView tv_list_click_test;
        public Button btn_list_click_test;
    }

    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();

        int position = holder.position;

        if(clickListener != null){
            clickListener.onItemClick(position);
        }
    }

    public interface CustomListClickListener{
        void onItemClick(int pos);
    }
}

