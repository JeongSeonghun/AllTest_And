package com.jsh.kr.alltest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.MainMoveData;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public abstract class BaseMainMenuFragment extends BaseFragment{
    private TabLayout tab_main;
    private ViewPager vp_main;
    private TextView tv_main_title;

    private MainBasePagerAdapter mainBasePagerAdapter;

    private ArrayList<MainMoveData> moveDataArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_main_menu, container, false);

        initUI(view);

        return view;
    }

    private void initUI(View view) {
        tab_main = view.findViewById(R.id.tab_main);
        vp_main = view.findViewById(R.id.vp_main);
        tv_main_title = view.findViewById(R.id.tv_main_title);

        mainBasePagerAdapter = new MainBasePagerAdapter(getChildFragmentManager());
        ArrayList<MainMoveData> moveDataArrayList = initMoveDataList();
        mainBasePagerAdapter.initData(moveDataArrayList);
        vp_main.setAdapter(mainBasePagerAdapter);

        tab_main.setupWithViewPager(vp_main);

        tv_main_title.setText(initTitle());
    }

    public void move(String className) {
        if(className == null || className.isEmpty()) {
            return;
        }

        for(MainMoveData moveData : moveDataArrayList) {
            if (className.equals(moveData.getActClass().getSimpleName())) {
                Intent intentAct = new Intent(getActivity(), moveData.getActClass());
                startActivityForResult(intentAct, C.RequestCode.MoveTest);
            }
        }
    }

    public abstract ArrayList<MainMoveData> initMoveDataList();

    public abstract int initTitle();

    public class MainBasePagerAdapter extends FragmentPagerAdapter {
        private ArrayList<MainMoveData> moveDataArrayList;
        private ArrayList<String> tagList;
        private ArrayList<BaseMainFragment> fragments;

        public MainBasePagerAdapter(FragmentManager fm) {
            super(fm);
            init();
        }

        private void init() {
            tagList = new ArrayList<>();
            fragments = new ArrayList<>();

            addTag(C.TAG.All);
        }

        public void initData(ArrayList<MainMoveData> moveDataArrayList) {
            this.moveDataArrayList = moveDataArrayList;
            initTagList();
        }

        private void initTagList() {
            if (moveDataArrayList != null && moveDataArrayList.size() > 0) {
                getTagFragment(C.TAG.All).initData(moveDataArrayList);

                for(MainMoveData moveData : moveDataArrayList) {
                    String tag = moveData.getTag();

                    if (tag == null || tag.isEmpty()) {
                        tag = C.TAG.Etc;
                    }

                    if (!tagList.contains(tag)) {
                        addTag(tag);
                    }

                    getTagFragment(tag).addData(moveData);
                }
            }
        }

        private void addTag(String tag) {
            if(!tagList.contains(tag)) {
                tagList.add(tag);
                fragments.add(new BaseMainFragment());
            }
        }

        private BaseMainFragment getTagFragment(String tag) {
            int idx = tagList.indexOf(tag);

            return fragments.get(idx);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            BaseMainFragment fragment = fragments.get(position);
            return tagList.get(position) + "(" + fragment.getListSize() + ")";
        }

        @Override
        public Fragment getItem(int position) {
            BaseMainFragment fragment = fragments.get(position);
//            fragment.refresh();
            return fragment;
//            return fragments.get(position);
//            return new Fragment();
        }

        @Override
        public int getCount() {
            return tagList.size();
        }
    }
}

