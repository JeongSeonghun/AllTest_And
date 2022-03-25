package com.jsh.kr.alltest.viewmodel;

import android.app.Application;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel extends AndroidViewModel {
   public final MutableLiveData<Integer> clickRandom = new MutableLiveData<>();
   public final MutableLiveData<String> log = new MutableLiveData<>();

   public TestViewModel(@NonNull Application application) {
      super(application);
   }

   @Override
   protected void onCleared() {
      super.onCleared();
   }

   public void getRandomNum() {
      clickRandom.postValue(new Random().nextInt(20));
   }
}
