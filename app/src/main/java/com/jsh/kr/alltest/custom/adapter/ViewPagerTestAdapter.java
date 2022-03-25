package com.jsh.kr.alltest.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsh.kr.alltest.R;

import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerTestAdapter extends PagerAdapter {
    private int[] layoutIds = new int[]{
            R.layout.item_pager_test1, R.layout.item_pager_test2, R.layout.item_pager_test3
    };

    @Override
    public int getCount() {
        return layoutIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(layoutIds[position], container, false);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

