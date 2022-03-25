package com.jsh.kr.alltest.custom.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 *
 * extends Behavior<T> : set act view
 *
 * 출처: http://iw90.tistory.com/270
 *
 * equal Default Behavior in FloatingActionButton
 * -> when snackBar up, button move up
 */
public class CustomFloatingButtonBehavior extends CoordinatorLayout.Behavior<ImageView>{

   public CustomFloatingButtonBehavior() {
   }

   public CustomFloatingButtonBehavior(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   /*
   return : true -> onDependentViewChanged()
    */
   @Override
   public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
//        return super.layoutDependsOn(parent, child, dependency);
      return dependency instanceof Snackbar.SnackbarLayout;
   }

   @Override
   public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
      float translationY = Math.min(0,dependency.getTranslationY() - dependency.getHeight());
      child.setTranslationY(translationY);

      return true;
   }
}
