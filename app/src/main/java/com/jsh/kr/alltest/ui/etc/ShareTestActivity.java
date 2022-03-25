package com.jsh.kr.alltest.ui.etc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityShareTestBinding;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class ShareTestActivity extends BaseActivity {

   private ActivityShareTestBinding binding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_share_test);
      initUI();
   }

   private void initUI() {
      binding.shareBtn.setOnClickListener(v -> share());
      binding.mailBtn.setOnClickListener(v -> shareMail());
      binding.facebookBtn.setOnClickListener(v -> moveFaceBook());
   }

   private void share() {
      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("text/plain");
      intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_link_message));

      Intent chooser = Intent.createChooser(intent, getString(R.string.share_title));
      startActivity(chooser);
   }

   private void shareMail() {
      Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null));
      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
      emailIntent.putExtra(Intent.EXTRA_TEXT, "");
      startActivity(Intent.createChooser(emailIntent, ""));
   }

   private void moveFaceBook() {
      Uri uri = Uri.parse(getString(R.string.facebook_link));
      Intent intent = new Intent(Intent.ACTION_VIEW, uri);
      startActivity(intent);
   }

}
