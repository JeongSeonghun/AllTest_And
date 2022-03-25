package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;

/**
 * AppBarLayout base test
 */
public class ScrollActionBarTestActivity extends BaseActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_scroll_action_bar_test2);

      CollapsingToolbarLayout toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
      toolbar_layout.setTitle("");

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      toolbar.setTitle("test title");
//        toolbar.addView(getLayoutInflater().inflate(R.layout.toolbar_custom, null));

      setSupportActionBar(toolbar);

//        RecyclerView rcv_scroll_toolbar_test = findViewById(R.id.rcv_scroll_toolbar_test);
//        rcv_scroll_toolbar_test.setAdapter(new RecycleTestAdapter());
   }
}