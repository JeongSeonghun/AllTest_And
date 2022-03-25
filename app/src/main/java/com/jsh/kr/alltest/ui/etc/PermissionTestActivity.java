package com.jsh.kr.alltest.ui.etc;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;

public class PermissionTestActivity extends BaseActivity implements View.OnClickListener {

   private TextView tvLog;
   private Button btnAllCheck;
   private Button btnForegroundLocation;
   private Button btnBackgroundLocation;
   private Button btnPhoneNum;
   private TextView tvPhoneNum;

   private static int REQUEST_PERMISSION_All = 1;
   private static int REQUEST_PERMISSION_FOREGROUND_LOCATION = 2;
   private static int REQUEST_PERMISSION_BACKGROUND_LOCATION = 3;
   private static int REQUEST_PERMISSION_PHONE_NUM = 4;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_permission_test);

      tvLog = findViewById(R.id.tv_log);
      btnAllCheck = findViewById(R.id.btn_all_check);
      btnForegroundLocation = findViewById(R.id.btn_foreground_location);
      btnBackgroundLocation = findViewById(R.id.btn_background_location);
      btnPhoneNum = findViewById(R.id.btn_phone_num);
      tvPhoneNum = findViewById(R.id.tv_phone_num);

      btnAllCheck.setOnClickListener(this);
      btnForegroundLocation.setOnClickListener(this);
      btnBackgroundLocation.setOnClickListener(this);
      btnPhoneNum.setOnClickListener(this);
   }

   @Override
   public void onClick(View view) {
      int id = view.getId();
      if (id == R.id.btn_all_check) {
         allCheck();
      } else if (id == R.id.btn_foreground_location) {
         checkForegroundLocation();
      } else if (id == R.id.btn_background_location) {
         checkBackgroundLocation();
      } else if (id == R.id.btn_phone_num) {
         clickPhoneNum();
      }
   }

   private void allCheck() {

   }

   private void checkForegroundLocation() {
      if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
              || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_FOREGROUND_LOCATION);
         // target 30으로 할 경우 권한 팝업 미제공(권한 설정 불가), 29까지는 3개 권한 같이 사용 가능
//            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_FOREGROUND_LOCATION);
      } else {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && ActivityCompat.checkSelfPermission(this, ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_BACKGROUND_LOCATION)) {
               AlertDialog.Builder dialog = new AlertDialog.Builder(this);
               dialog.setMessage("항상서용이 필요합니다.");
               dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                     ActivityCompat.requestPermissions(PermissionTestActivity.this, new String[]{ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_FOREGROUND_LOCATION);
                  }
               });
               dialog.show();
            }
         }

      }
   }

   private void checkBackgroundLocation() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
         // 백그라운드 단독 요청일 경우 팝업 미제공(설정화면)
//            if (ActivityCompat.checkSelfPermission(this, ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
//            }

         // 백그라운드 요청이 아니라서 항상 허용 안보임
//            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
//            }

         // target 30으로 할 경우 권한 팝업 미제공(권한 설정 불가), 29까지는 3개 권한 같이 사용 가능
//            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
//            }

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
            }
         } else {
            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
            }
         }
      } else {
         if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                 || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
         }
      }

   }

   private void clickPhoneNum() {
      // android.permission.READ_PHONE_STATE,android.permission.READ_SMS,android.permission.READ_PHONE_NUMBERS 중 하나라도 권한 있어야 함
      TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        tvPhoneNum.setText(tMgr.getLine1Number());

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
         if (ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_PHONE_NUMBERS}, REQUEST_PERMISSION_PHONE_NUM);
         } else {
            tvPhoneNum.setText(tMgr.getLine1Number());
         }
      } else {
         if (ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_PHONE_STATE}, REQUEST_PERMISSION_PHONE_NUM);
         } else {
            tvPhoneNum.setText(tMgr.getLine1Number());
         }
      }
   }

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if (requestCode == REQUEST_PERMISSION_BACKGROUND_LOCATION && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
         Log.d("testPermission", "check back permissions : " + Arrays.toString(permissions));
         if (ActivityCompat.checkSelfPermission(this, ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_BACKGROUND_LOCATION);
         }
      } else if (requestCode == REQUEST_PERMISSION_FOREGROUND_LOCATION) {
         Log.d("testPermission", "check fore permissions : " + Arrays.toString(permissions));
      } else if (requestCode == REQUEST_PERMISSION_PHONE_NUM) {
         Log.d("testPermission", "check permissions phone Num : " + Arrays.toString(permissions));
         boolean isOk = true;
         for(int grant : grantResults) {
            if (grant != PackageManager.PERMISSION_GRANTED) {
               isOk = false;
               break;
            }
         }
         if (isOk) {

         }

      } else {
         Log.d("testPermission", "check permissions : " + Arrays.toString(permissions));
      }

   }
}

