package com.jsh.kr.alltest.custom.behavior;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Created by lenovo-r1 on 2018-02-22.
 * http://dktfrmaster.blogspot.com/2018/03/coordinatorlayout.html
 * http://hatti.tistory.com/entry/android-Behavior
 */

public class CustomBehavior extends CoordinatorLayout.Behavior<ImageView>{

   public CustomBehavior() {
   }

   public CustomBehavior(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   // 반응할 상황을 결정
   @Override
   public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ImageView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
      LogUtil.d("behavior", "onNestedPreScroll : "+axes);
      // when touch toolbar
      return directTargetChild instanceof Toolbar;

      // when vertical scroll
//        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
   }

   // 반을 할 view 변경
   @Override
   public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ImageView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
      LogUtil.d("behavior", "onNestedPreScroll : "+dx +"/"+dy);

   }

   // 변화 감지할 view 확인
   @Override
   public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
//        return super.layoutDependsOn(parent, child, dependency);
      return dependency instanceof AppBarLayout;
   }

   @Override
   public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
      float tranY = dependency.getTranslationY();
      float height = dependency.getHeight();
      float y = dependency.getY();
      if(dependency instanceof AppBarLayout) {
         float totalRange = ((AppBarLayout) dependency).getTotalScrollRange();
         float scale = (totalRange - Math.abs(y)) / totalRange;
         LogUtil.d("behavior", "getTotalScrollRange / scale : "+totalRange + "/" + scale);
         child.setScaleX(scale);
         child.setScaleY(scale);
      }

      LogUtil.d("behavior", "getTranslationY : "+tranY);
      LogUtil.d("behavior", "getHeight : "+height);
      LogUtil.d("behavior", "getY : "+y);
      LogUtil.d("behavior", "child getHeight : "+child.getHeight());
      LogUtil.d("behavior", "child getTranslationY : "+child.getTranslationY());
      LogUtil.d("behavior", "child getY : "+child.getY());
      child.setTranslationX(y);

      return false;
   }

}
