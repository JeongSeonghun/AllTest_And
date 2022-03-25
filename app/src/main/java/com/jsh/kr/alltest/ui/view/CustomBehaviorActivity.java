package com.jsh.kr.alltest.ui.view;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

/**
 * action bar icon size
 * https://developer.android.com/guide/practices/ui_guidelines/icon_design_action_bar
 *
 * dependence
 * http://blog.naver.com/PostView.nhn?blogId=pistolcaffe&logNo=221017061017
 * CoordinatorLayout -> AppBarLayout -> CollapsingToolbarLayout -> toolbar
 * when toolbar no is child in CollapsingToolbarLayout, set toolbarId
 */
public class CustomBehaviorActivity extends BaseActivity {

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout toolbarLayout;
    private Toolbar toolbar;

    private ImageView iv_test_float;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);

        initUI();

    }

    private void initUI() {
        appBarLayout = findViewById(R.id.appbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbar = findViewById(R.id.toolbar);

        initBar();

        // instead FloatingActionButton
        iv_test_float = findViewById(R.id.iv_test_float);
        iv_test_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void initBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // show home button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // change home button image(*need a right size icon because disable change size)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        }
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // init action buttons
        getMenuInflater().inflate(R.menu.menu_custom_vehavior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // home button(left button)
            case android.R.id.home:
                Toast.makeText(this, "click home", Toast.LENGTH_SHORT).show();
                return true;
            // action button(right button)
            case R.id.menu_share:
                Toast.makeText(this, "click share", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
