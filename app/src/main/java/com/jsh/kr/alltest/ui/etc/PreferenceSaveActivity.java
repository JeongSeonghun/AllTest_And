package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.PreferenceModel;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Preference Test
 */
public class PreferenceSaveActivity extends BaseActivity implements View.OnClickListener{

    private Spinner sp_prefer_save_type;
    private EditText et_prefer_save_value;
    private Button btn_prefer_save;
    private TextView tv_prefer_save_data;
    private EditText et_prefer_save_key;

    private final String TYPE_STRING = "String";
    private final String TYPE_INT = "int";
    private final String[] types = {TYPE_STRING, TYPE_INT};

    private PreferenceModel preferenceModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_save);

        initUI();
        initData();
    }

    private void initUI() {
        sp_prefer_save_type = findViewById(R.id.sp_prefer_save_type);
        et_prefer_save_value = findViewById(R.id.et_prefer_save_value);
        btn_prefer_save = findViewById(R.id.btn_prefer_save);
        tv_prefer_save_data = findViewById(R.id.tv_prefer_save_data);
        et_prefer_save_key = findViewById(R.id.et_prefer_save_key);

        btn_prefer_save.setOnClickListener(this);

        sp_prefer_save_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initInputType(types[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData() {
        preferenceModel = new PreferenceModel();
        preferenceModel.initPrefTest();

        initTypeSpinnerData();
        initSaveData();
    }

    private void initTypeSpinnerData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, types);

        sp_prefer_save_type.setAdapter(arrayAdapter);

    }


    private void initInputType(String type) {
        if (TYPE_STRING.equals(type)) {
            et_prefer_save_value.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        } else if (TYPE_INT.equals(type)) {
            et_prefer_save_value.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        }
    }

    private void initSaveData() {
        tv_prefer_save_data.setText(preferenceModel.getPreferenceTestAllDataString());
    }

    private void save() {
        String inputKey = et_prefer_save_key.getText().toString();
        String inputData = et_prefer_save_value.getText().toString();

        String selectType = types[sp_prefer_save_type.getSelectedItemPosition()];
        if (TYPE_STRING.equals(selectType)) {
            preferenceModel.setTestStringData(inputKey, inputData);
        } else if (TYPE_INT.equals(selectType)) {
            preferenceModel.setTestIntData(inputKey, Integer.parseInt(inputData));
        }

        initSaveData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_prefer_save) {
            save();
        }
    }


}

