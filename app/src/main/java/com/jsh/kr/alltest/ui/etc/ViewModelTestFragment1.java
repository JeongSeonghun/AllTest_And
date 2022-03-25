package com.jsh.kr.alltest.ui.etc;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.FragmentViewModelTest1Binding;
import com.jsh.kr.alltest.ui.BaseFragment;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.viewmodel.TestViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class ViewModelTestFragment1 extends BaseFragment {

   public static final String TAG = ViewModelTestFragment1.class.getSimpleName();

   private FragmentViewModelTest1Binding binding;
   private TestViewModel viewModel;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addLog("onCreate");
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_model_test1, container, false);
      viewModel = ViewModelProviders.of(requireActivity()).get(TestViewModel.class);
      binding.setViewModel(viewModel);
      binding.setLifecycleOwner(this);
      subscribeUi();
      addLog("onCreateView");
      return binding.getRoot();
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      addLog("onViewCreated");
   }

   @Override
   public void onAttach(@NonNull Context context) {
      super.onAttach(context);
      addLog("onAttach");
   }

   @Override
   public void onDetach() {
      super.onDetach();
      addLog("onDetach");
   }

   @Override
   public void onDestroyView() {
      super.onDestroyView();
      addLog("onDestroyView");
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
      addLog("onDestroy");
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      addLog("onActivityCreated");
   }

   private void subscribeUi() {
      viewModel.clickRandom.observe(requireActivity(), randomNum -> {
         LogUtil.d(TAG, "viewModel.clickRandom -> "+randomNum);
         binding.viewModelFrag1NumTv.setText(String.valueOf(randomNum));
      });
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

