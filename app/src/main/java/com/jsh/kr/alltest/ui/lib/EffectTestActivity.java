package com.jsh.kr.alltest.ui.lib;

import android.os.Bundle;

import com.airbnb.lottie.LottieDrawable;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityEffectTestBinding;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class EffectTestActivity extends BaseActivity {

    private ActivityEffectTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_effect_test);

        initUI();
    }

    private void initUI() {
        binding.animationView2.setAnimation("gradient-fill-animation.json");
//        binding.animationView2.loop(true);
        binding.animationView2.setRepeatMode(LottieDrawable.RESTART);
        binding.animationView2.setRepeatCount(LottieDrawable.INFINITE);
        binding.animationView2.playAnimation();
    }
}
