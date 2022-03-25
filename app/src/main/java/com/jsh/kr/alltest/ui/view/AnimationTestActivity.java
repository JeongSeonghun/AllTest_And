package com.jsh.kr.alltest.ui.view;


import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * base animation
 *
 * http://mainia.tistory.com/2897 [녹두장군 - 상상을 현실로]
 * http://koreaparks.tistory.com/126
 */
public class AnimationTestActivity extends BaseActivity implements View.OnClickListener {

   private ImageView iv_animation_test;
   private Animation anim;
   private Button btn_anim_rotate_lr;
   private Button btn_anim_change_size;
   private Button bnt_anim_scale_alpha;
   private Button btn_anim_change_size_hv;
   private Button bnt_anim_scale_alpha_rev;
   private Button btn_anim_rotate_camera;
   private FrameLayout fl_animation_test;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_animation_test);

      initUI();
   }

   private void initUI() {
      iv_animation_test = findViewById(R.id.iv_animation_test);
      btn_anim_rotate_lr = findViewById(R.id.btn_anim_rotate_lr);
      btn_anim_change_size = findViewById(R.id.btn_anim_change_size);
      bnt_anim_scale_alpha = findViewById(R.id.bnt_anim_scale_alpha);
      btn_anim_change_size_hv = findViewById(R.id.btn_anim_change_size_hv);
      bnt_anim_scale_alpha_rev = findViewById(R.id.bnt_anim_scale_alpha_rev);
      btn_anim_rotate_camera = findViewById(R.id.btn_anim_rotate_camera);
      fl_animation_test = findViewById(R.id.fl_animation_test);

      btn_anim_rotate_lr.setOnClickListener(this);
      btn_anim_change_size.setOnClickListener(this);
      bnt_anim_scale_alpha.setOnClickListener(this);
      btn_anim_change_size_hv.setOnClickListener(this);
      bnt_anim_scale_alpha_rev.setOnClickListener(this);
      btn_anim_rotate_camera.setOnClickListener(this);

      startAnimRotateLR();

   }

   private void startAnimRotateLR() {
      // optical illusion
      anim = AnimationUtils.loadAnimation(this, R.anim.rotate_lr);
      iv_animation_test.startAnimation(anim);
   }

   private void startAnimChangeSize() {
      anim = AnimationUtils.loadAnimation(this, R.anim.change_size);
      iv_animation_test.startAnimation(anim);
   }

   private void startAnimChangeSizeAlpha() {
      anim = AnimationUtils.loadAnimation(this, R.anim.scale_alpha);
      anim.setAnimationListener(animationListener);
      iv_animation_test.startAnimation(anim);
   }

   private void startAnimChangeSizeHV() {
      anim = AnimationUtils.loadAnimation(this, R.anim.change_size2);
      iv_animation_test.startAnimation(anim);
   }

   private void startAnimChangeSizeAlphaReverse() {
      anim = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_reverse);
      anim.setAnimationListener(animationListener);
      iv_animation_test.startAnimation(anim);
   }

   private void startAnimRotateCamera() {
      anim = new CameraAnim();
      anim.setAnimationListener(animationListener);
      fl_animation_test.startAnimation(anim);
   }

   private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
         Toast.makeText(AnimationTestActivity.this, "onAnimationStart()", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onAnimationEnd(Animation animation) {
         Toast.makeText(AnimationTestActivity.this, "onAnimationEnd()", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
         Toast.makeText(AnimationTestActivity.this, "onAnimationRepeat()", Toast.LENGTH_SHORT).show();
      }
   };

   @Override
   public void onClick(View v) {
      int id = v.getId();
      if (id == R.id.btn_anim_rotate_lr) {
         startAnimRotateLR();
      } else if (id == R.id.btn_anim_change_size) {
         startAnimChangeSize();
      } else if (id == R.id.bnt_anim_scale_alpha) {
         startAnimChangeSizeAlpha();
      } else if (id == R.id.btn_anim_change_size_hv) {
         startAnimChangeSizeHV();
      } else if (id == R.id.bnt_anim_scale_alpha_rev) {
         startAnimChangeSizeAlphaReverse();
      } else if (id == R.id.btn_anim_rotate_camera) {
         startAnimRotateCamera();
      }
   }

   class CameraAnim extends Animation {
      float cx, cy;

      public void initialize(int width, int height, int parentWidth, int parentHeight) {
         super.initialize(width, height, parentWidth, parentHeight);
         cx = width/2;         // 좌표를 뷰의 중앙으로 지정
         cy = height/2;
         setDuration(3000);
         setInterpolator(new LinearInterpolator());
      }
      protected void applyTransformation(float interpolatedTime, Transformation t) {
         Camera cam = new Camera();
         /**
          *  360도 * 시간 - 시간이 0~1이므로 점진적으로 360도 회전
          */
//            cam.rotateX(360 * interpolatedTime);
         cam.rotateY(360 * interpolatedTime);
//            cam.rotateZ(360 * interpolatedTime);
         Matrix matrix = t.getMatrix();
         cam.getMatrix(matrix);
         // 회전 중심을 이미지 중심으로 하기 위해 카메라를 회전하기 전 중심을
         matrix.preTranslate(-cx, -cy);    // 원점으로 옮기고
         matrix.postTranslate(cx, cy);    // 회전 후 다시 원래 위치
      }
   }
}

