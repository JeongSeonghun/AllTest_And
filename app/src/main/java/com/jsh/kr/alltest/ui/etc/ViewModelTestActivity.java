package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.view.View;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityViewModelTestBinding;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.viewmodel.TestViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class ViewModelTestActivity extends BaseActivity {

   private static final String TAG = ViewModelTestActivity.class.getSimpleName();

   private ViewModelTestFragment1 fragment1;
   private ViewModelTestFragment2 fragment2;

   private TestViewModel viewModel;

   private ActivityViewModelTestBinding binding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_test);
      viewModel = ViewModelProviders.of(this).get(TestViewModel.class);
      initUI();
      subscribe();
      addLog("onCreate");
   }

   @Override
   protected void onStart() {
      super.onStart();
      addLog("onStart");
   }

   @Override
   protected void onRestart() {
      super.onRestart();
      addLog("onRestart");
   }

   @Override
   protected void onResume() {
      super.onResume();
      addLog("onResume");
   }

   @Override
   protected void onPause() {
      super.onPause();
      addLog("onPause");
   }

   @Override
   protected void onStop() {
      super.onStop();
      addLog("onStop");
   }

   private void initUI() {
      binding.viewModelFrag1Btn.setOnClickListener(v -> changeFrag1());
      binding.viewModelFrag2Btn.setOnClickListener(v -> changeFrag2());

      changeFrag1();
   }

   private void subscribe() {
      viewModel.log.observe(this, text -> {
         binding.logTv.setText(text);
         binding.mainSv.post(()-> binding.mainSv.fullScroll(View.FOCUS_DOWN));
      });
   }

   private void changeFrag1() {
      addLog("changeFrag1 -> fragCnt "+getSupportFragmentManager().getFragments().size());
      if (fragment1 == null) {
         Fragment fragment = getSupportFragmentManager().findFragmentByTag(ViewModelTestFragment1.TAG);
         if (fragment == null) {
            fragment1 = new ViewModelTestFragment1();
            addFragment(R.id.view_model_container_fl, fragment1, ViewModelTestFragment1.TAG);
         } else {
            fragment1 = (ViewModelTestFragment1) fragment;
         }

      }
      if (fragment2 != null) {
         hideFragment(fragment2);
      }
   }

   private void changeFrag2() {
      addLog("changeFrag2 -> fragCnt "+getSupportFragmentManager().getFragments().size());
      if (fragment2 == null){
         Fragment fragment = getSupportFragmentManager().findFragmentByTag(ViewModelTestFragment2.TAG);
         if (fragment == null) {
            fragment2 = new ViewModelTestFragment2();
            addFragment(R.id.view_model_container_fl, fragment2, ViewModelTestFragment2.TAG);
         } else {
            fragment2 = (ViewModelTestFragment2) fragment;
         }
      }

      showFragment(fragment2);
   }

   private void addFragment(int containerId, Fragment fragment, String tag) {
      FragmentManager fragmentManager = getSupportFragmentManager();

      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.add(containerId, fragment, tag);
      fragmentTransaction.addToBackStack(tag);
      fragmentTransaction.commit();
   }

   private void showFragment(Fragment fragment) {
      showFragment(fragment, false);
   }

   private void showFragment(Fragment fragment, boolean addToBackStack) {
      FragmentManager fragmentManager = getSupportFragmentManager();

      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.show(fragment);
      if(addToBackStack) {
         fragmentTransaction.addToBackStack(null);
      }
      fragmentTransaction.commit();
   }

   private void removeFragment(Fragment fragment) {
      FragmentManager fragmentManager = getSupportFragmentManager();

      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.remove(fragment);
      fragmentTransaction.commit();
   }

   public void hideFragment(Fragment fragment) {
      hideFragment(fragment, false);
   }

   public void hideFragment(Fragment fragment, boolean addToBackStack) {
      FragmentManager fragmentManager = getSupportFragmentManager();

      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.hide(fragment);
      if(addToBackStack) {
         fragmentTransaction.addToBackStack(null);
      }
      fragmentTransaction.commit();
   }

   private void addLog(String text) {
      LogUtil.d(TAG, text);
      if (viewModel != null) {
         StringBuilder sb = new StringBuilder();
         sb.append(viewModel.log.getValue());
         if (sb.length() > 0) sb.append("\n");
         sb.append(TAG);
         sb.append(" : ");
         sb.append(text);
         viewModel.log.postValue(sb.toString());
      }
   }

}

