package com.jsh.kr.alltest.custom.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsh.kr.alltest.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomView extends FrameLayout {

   /**
    * mImageView.
    */
   private ImageView mImageView;
   /**
    * mTextView.
    */
   private TextView mTextView;
   /**
    * mImageSRCId.
    */
   private Integer mImageSRCId;
   /**
    * mTextColorId.
    */
   private Integer mTextColorId;
   /**
    * mImageSelectedSRCId.
    */
   private Integer mImageSelectedSRCId;
   /**
    * mTextSelectedColorId.
    */
   private Integer mTextSelectedColorId;
   /**
    * mOrientation.
    */
   private Integer mOrientation;
   /**
    * text.
    */
   private String mText;
   /**
    * text size.
    */
   private Float mTextSize;
   /**
    * ImageView and TextView space size
    */
   private int mSpace;


   public CustomView(@NonNull Context context) {
      this(context, null, 0);
   }

   public CustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public CustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      getAttrs(context, attrs);

      if (mOrientation == 0) {
         LayoutInflater.from(context).inflate(R.layout.ui_custom, this);
      } else {
         LayoutInflater.from(context).inflate(R.layout.ui_custom, this);
      }

      mImageView = findViewById(R.id.iv_custom);
      mTextView = findViewById(R.id.tv_custom);

      init();
   }

   private void init() {
      LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTextView.getLayoutParams();

      params.setMargins(mSpace, params.topMargin, params.rightMargin, params.bottomMargin);
   }

   public void setImage(int imageResId) {
      mImageView.setImageResource(imageResId);
   }

   public void setText(int textResId) {
      mTextView.setText(textResId);
   }

   /**
    * getAttrs.
    *
    * @param context context.
    * @param attrs   attrs.
    */
   private void getAttrs(final Context context, final AttributeSet attrs) {
      TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
      mImageSRCId = ta.getResourceId(R.styleable.CustomView_it_src, 0);
      mImageSelectedSRCId = ta.getResourceId(R.styleable.CustomView_it_selectedSrc, mImageSRCId);
      mTextColorId = ta.getColor(R.styleable.CustomView_it_textColor, 0);
      mTextSelectedColorId = ta.getColor(R.styleable.CustomView_it_textSelectedColor, mTextColorId);
      mText = ta.getString(R.styleable.CustomView_it_text);
//        mTextSize = ta.getDimension(R.styleable.CustomView_it_textSize, Utils.sp2px(context, INT_VALUE_14));
      mOrientation = ta.getInt(R.styleable.CustomView_it_orientation, 0);
      mSpace = ta.getDimensionPixelSize(R.styleable.CustomView_it_space, 0);
      ta.recycle();
   }
}

