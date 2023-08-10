package com.jsh.kr.alltest.ui.etc;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_AUDIO;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_MEDIA_VIDEO;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivityPermissionContractsTestBinding;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

public class PermissionContractsTestActivity extends BaseActivity implements View.OnClickListener {

    private ActivityPermissionContractsTestBinding binding;

    private ActivityResultLauncher<String[]> permissionAllLauncher;
    private ActivityResultLauncher<String[]> foregroundLocationLauncher;
    private ActivityResultLauncher<String[]> backgroundLocationLauncher;
    private ActivityResultLauncher<String> backgroundLocationAddLauncher;

    private ActivityResultLauncher<String> phoneNumLauncher;

    private ActivityResultLauncher<String[]> fileLauncher;

    private ActivityResultLauncher<String> cameraLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermissionContractsTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAllCheck.setOnClickListener(this);
        binding.btnForegroundLocation.setOnClickListener(this);
        binding.btnBackgroundLocation.setOnClickListener(this);
        binding.btnPhoneNum.setOnClickListener(this);
        binding.btnFile.setOnClickListener(this);
        binding.btnCamera.setOnClickListener(this);

        initLauncher();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_all_check) {
            allCheck();
        } else if (view.getId() == R.id.btn_foreground_location) {
            checkForegroundLocation();
        } else if (view.getId() == R.id.btn_background_location) {
            checkBackgroundLocation();
        } else if (view.getId() == R.id.btn_phone_num) {
            clickPhoneNum();
        } else if (view.getId() == R.id.btn_file) {
            checkFile();
        } else if (view.getId() == R.id.btn_camera) {
            checkCamera();
        }
    }

    private void initLauncher() {
        permissionAllLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), (granted) -> {
            boolean isGranted = true;
            for (String key : granted.keySet()) {
                if (Boolean.FALSE.equals(granted.get(key))) {
                    isGranted = false;
                    break;
                }
            }
            if (isGranted) {
                LogUtil.d("test", "all permission ok");
            } else {
                LogUtil.d("test", "all permission deny");
            }
        });

        foregroundLocationLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), (granted) -> {
            boolean isGranted = true;
            for (String key : granted.keySet()) {
                if (Boolean.FALSE.equals(granted.get(key))) {
                    isGranted = false;
                    break;
                }
            }
            if (isGranted) {
                LogUtil.d("test", "foreground permission ok");
            } else {
                LogUtil.d("test", "foreground permission deny");
            }
        });

        backgroundLocationLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), (granted) -> {
            boolean isGranted = true;
            for (String key : granted.keySet()) {
                if (Boolean.FALSE.equals(granted.get(key))) {
                    isGranted = false;
                    break;
                }
            }
            if (isGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (ActivityCompat.checkSelfPermission(this, ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        LogUtil.d("test", "background permission ok");
                    } else {
                        LogUtil.d("test", "background permission need add check");
                        backgroundLocationAddLauncher.launch(ACCESS_BACKGROUND_LOCATION);
                    }
                } else {
                    LogUtil.d("test", "background permission ok");
                }
            } else {
                LogUtil.d("test", "background permission deny");
            }
        });

        backgroundLocationAddLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), (isGranted) -> {
            if (isGranted) {
                LogUtil.d("test", "background permission ok");
            } else {
                LogUtil.d("test", "background permission deny");
            }
        });

        phoneNumLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), (isGranted) -> {
            if (isGranted) {
                LogUtil.d("test", "phone permission ok");
            } else {
                LogUtil.d("test", "phone permission deny");
            }
        });

        fileLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), (granted) -> {
            boolean isGranted = true;
            for (String key : granted.keySet()) {
                if (Boolean.FALSE.equals(granted.get(key))) {
                    isGranted = false;
                    break;
                }
            }

            if (isGranted) {
                LogUtil.d("test", "file permission ok");
            } else {
                LogUtil.d("test", "file permission deny");
            }
        });

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), (isGranted) -> {
            if (isGranted) {
                LogUtil.d("test", "camera permission ok");
            } else {
                LogUtil.d("test", "camera permission deny");
            }
        });
    }

    private void allCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // 30(r, 11) 부터 쓰기 권한 무시
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // 33(t, 13) 부터 파일 읽기 권한 분리
                    // 필요에 따라 분리된 파일 읽기 권한 선택 사용
                    if (ActivityCompat.checkSelfPermission(this, READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setMessage("file, camera 권한 허용 상태");
                        dialog.show();
                    } else {
                        if (shouldShowRequestPermissionRationale(READ_MEDIA_IMAGES)
                                || shouldShowRequestPermissionRationale(READ_MEDIA_AUDIO)
                                || shouldShowRequestPermissionRationale(READ_MEDIA_VIDEO)
                                || shouldShowRequestPermissionRationale(CAMERA)) {
                            showSettingAlert("all");
                        } else {
                            // 직접 할 경우 requestPermissions 함수 호출
                            permissionAllLauncher.launch(new String[]{READ_MEDIA_IMAGES, READ_MEDIA_AUDIO, READ_MEDIA_VIDEO, CAMERA});
                        }
                    }
                } else {
                    if (ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setMessage("file, camera 권한 허용 상태");
                        dialog.show();
                    } else {
                        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)
                                || shouldShowRequestPermissionRationale(CAMERA)) {
                            showSettingAlert("all");
                        } else {
                            // 직접 할 경우 requestPermissions 함수 호출
                            permissionAllLauncher.launch(new String[]{READ_EXTERNAL_STORAGE, CAMERA});
                        }
                    }
                }
            } else {
                if (ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("file, camera 권한 허용 상태");
                    dialog.show();
                } else {
                    if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)
                            || shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)
                            || shouldShowRequestPermissionRationale(CAMERA)) {
                        showSettingAlert("all");
                    } else {
                        // 직접 할 경우 requestPermissions 함수 호출
                        permissionAllLauncher.launch(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA});
                    }
                }
            }

        }
    }

    private void checkForegroundLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("위치 권한 허용 상태");
                dialog.show();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)) {
                    showSettingAlert("위치");
                } else {
                    foregroundLocationLauncher.launch(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION});
                }
            }
        }

    }

    private void checkBackgroundLocation() { // Foreground, Background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 백그라운드 단독 요청일 경우 팝업 미제공(설정화면)
            // arget 30으로 3개 권한 같이 사용 할 경우 권한 팝업 미제공(권한 설정 불가), 29까지는 3개 권한 같이 사용 가능
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("위치 권한 허용 상태");
                    dialog.show();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)
                                || shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION)) {
                            LogUtil.d("test", "check setting permission");
                        } else {
                            // 최초에도 false return
//                            if (shouldShowRequestPermissionRationale(ACCESS_BACKGROUND_LOCATION)) {
//                                LogUtil.d("test", "check setting background");
//                            }
                            backgroundLocationLauncher.launch(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION});
                        }

                    } else {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)
                                || shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION)) {
                            LogUtil.d("test", "check setting permission");
                        } else {
                            // 항상 true return
//                            if (shouldShowRequestPermissionRationale(ACCESS_BACKGROUND_LOCATION)) {
//                                LogUtil.d("test", "check setting background");
//                            }
                            backgroundLocationLauncher.launch(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, ACCESS_BACKGROUND_LOCATION});
                        }

                    }
                }
            } else {
                if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("위치 권한 허용 상태");
                    dialog.show();
                } else {
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)
//                            || ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)) {
//                        showSettingAlert("위치");
//                    } else {
//                        backgroundLocationLauncher.launch(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION});
//                    }
                    backgroundLocationLauncher.launch(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION});
                }
            }

        }
    }

    private void clickPhoneNum() {
        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED) {
                phoneNumLauncher.launch(READ_PHONE_NUMBERS);
            } else {
                binding.tvPhoneNum.setText(tMgr.getLine1Number());
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                phoneNumLauncher.launch(READ_PHONE_STATE);
            } else {
                binding.tvPhoneNum.setText(tMgr.getLine1Number());
            }
        }

    }

    private void checkFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // 30(r, 11) 부터 쓰기 권한 무시
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // 33(t, 13) 부터 파일 읽기 권한 분리
                    // 필요에 따라 분리된 파일 읽기 권한 선택 사용
                    if (ActivityCompat.checkSelfPermission(this, READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setMessage("file 권한 허용 상태");
                        dialog.show();
                    } else {
                        if (shouldShowRequestPermissionRationale(READ_MEDIA_IMAGES)
                                || shouldShowRequestPermissionRationale(READ_MEDIA_AUDIO)
                                || shouldShowRequestPermissionRationale(READ_MEDIA_VIDEO)) {
                            showSettingAlert("all");
                        } else {
                            // 직접 할 경우 requestPermissions 함수 호출
                            fileLauncher.launch(new String[]{READ_MEDIA_IMAGES, READ_MEDIA_AUDIO, READ_MEDIA_VIDEO});
                        }
                    }
                } else {
                    if (ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setMessage("file 권한 허용 상태");
                        dialog.show();
                    } else {
                        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                            showSettingAlert("all");
                        } else {
                            // 직접 할 경우 requestPermissions 함수 호출
                            fileLauncher.launch(new String[]{READ_EXTERNAL_STORAGE});
                        }
                    }
                }
            } else {
                if (ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("file 권한 허용 상태");
                    dialog.show();
                } else {
                    if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)
                            || shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                        showSettingAlert("all");
                    } else {
                        // 직접 할 경우 requestPermissions 함수 호출
                        fileLauncher.launch(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE});
                    }
                }
            }

        }
    }

    private void checkCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("camera 권한 허용 상태");
                dialog.show();
            } else {
                if (shouldShowRequestPermissionRationale(CAMERA)) {
                    showSettingAlert("all");
                } else {
                    // 직접 할 경우 requestPermissions 함수 호출
                    cameraLauncher.launch(CAMERA);
                }
            }
        }
    }

    private void showSettingAlert(String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage( String.format("%s 권한 허용이 필요합니다.", title));
        dialog.setPositiveButton("확인", (dialog1, which) -> {
            // TODO: 설정 화면 이동 등 처리
        });
        dialog.show();
    }
}
