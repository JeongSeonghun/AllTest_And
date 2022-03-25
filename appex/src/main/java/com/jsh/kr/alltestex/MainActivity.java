package com.jsh.kr.alltestex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.move_aidl_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveAIDLTest();
            }
        });
    }

    private void moveAIDLTest() {
        Intent intentAct = new Intent(this, AIDLTestActivity.class);
        startActivity(intentAct);
    }
}