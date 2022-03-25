package com.jsh.kr.alltest.ui.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * drawerLayout test
 * http://duzi077.tistory.com/167 [개발하는 두더지]
 */
public class LeftMenuActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_right_menu_open;
    private Button btn_right_menu_close;
    private CheckBox cb_right_menu_lock;
    private DrawerLayout dl_right_menu;
    private NavigationView nv_left_menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu);

        initUI();
    }

    private void initUI() {
        btn_right_menu_close = findViewById(R.id.btn_right_menu_close);
        btn_right_menu_open = findViewById(R.id.btn_right_menu_open);
        cb_right_menu_lock = findViewById(R.id.cb_right_menu_lock);
        dl_right_menu = findViewById(R.id.dl_right_menu);


        btn_right_menu_close.setOnClickListener(this);
        btn_right_menu_open.setOnClickListener(this);
        cb_right_menu_lock.setOnCheckedChangeListener(checkedChangeListener);

        addUI();
    }

    private void addUI() {
        nv_left_menu = findViewById(R.id.nv_left_menu);

        nv_left_menu.setNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void openRightMenu() {
        if (!dl_right_menu.isDrawerOpen(GravityCompat.END)) {
            dl_right_menu.openDrawer(GravityCompat.END);
        }
    }

    private void closeRightMenu() {
        if (dl_right_menu.isDrawerOpen(GravityCompat.END)) {
            dl_right_menu.closeDrawer(GravityCompat.END);
        }
    }

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (dl_right_menu.isDrawerOpen(GravityCompat.END)) {
                    dl_right_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                } else {
                    dl_right_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            } else {
                dl_right_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    };

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.item1:
                    Toast.makeText(LeftMenuActivity.this, "item1 clicked..", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2:
                    Toast.makeText(LeftMenuActivity.this, "item2 clicked..", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3:
                    Toast.makeText(LeftMenuActivity.this, "item3 clicked..", Toast.LENGTH_SHORT).show();
                    break;
            }

            dl_right_menu.closeDrawer(GravityCompat.START);

            return false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right_menu_open:
                openRightMenu();
                break;
            case R.id.btn_right_menu_close:
                closeRightMenu();
                break;
        }
    }
}
