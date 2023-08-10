package com.jsh.kr.alltestkt.ui.etc

import android.Manifest
import android.Manifest.permission.READ_MEDIA_AUDIO
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.core.app.ActivityCompat
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.databinding.ActivityPermissionTestBinding
import com.jsh.kr.alltestkt.ui.BaseActivity
import java.util.Arrays

class PermissionTestActivity: BaseActivity(), OnClickListener {

    companion object {
        private const val REQUEST_PERMISSION_All = 1
        private const val REQUEST_PERMISSION_FOREGROUND_LOCATION = 2
        private const val REQUEST_PERMISSION_BACKGROUND_LOCATION = 3
        private const val REQUEST_PERMISSION_PHONE_NUM = 4
        private const val REQUEST_PERMISSION_FILE = 5
        private const val REQUEST_PERMISSION_CAMERA = 6
        private const val REQUEST_PERMISSION_BACKGROUND_LOCATION_ADD = 7
    }

    private lateinit var binding: ActivityPermissionTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAllCheck.setOnClickListener(this)
        binding.btnForegroundLocation.setOnClickListener(this)
        binding.btnBackgroundLocation.setOnClickListener(this)
        binding.btnPhoneNum.setOnClickListener(this)
        binding.btnFile.setOnClickListener(this)
        binding.btnCamera.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_all_check -> {
                allCheck()
            }
            R.id.btn_foreground_location -> {
                checkForegroundLocation()
            }
            R.id.btn_background_location -> {
                checkBackgroundLocation()
            }
            R.id.btn_phone_num -> {
                clickPhoneNum()
            }
            R.id.btn_file -> {
                checkFile()
            }
            R.id.btn_camera -> {
                checkCamera()
            }
        }
    }


    private fun allCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        READ_MEDIA_VIDEO
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        READ_MEDIA_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setMessage("file, camera 권한 허용 상태")
                    dialog.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            READ_MEDIA_IMAGES,
                            READ_MEDIA_AUDIO,
                            READ_MEDIA_VIDEO,
                            Manifest.permission.CAMERA
                        ),
                        REQUEST_PERMISSION_All
                    )
                }
            } else {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setMessage("file, camera 권한 허용 상태")
                    dialog.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                        ),
                        REQUEST_PERMISSION_All
                    )
                }
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("file, camera 권한 허용 상태")
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                    REQUEST_PERMISSION_All
                )
            }
        }
    }

    private fun checkFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        READ_MEDIA_VIDEO
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        READ_MEDIA_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setMessage("권한 허용 상태")
                    dialog.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_AUDIO, READ_MEDIA_VIDEO),
                        REQUEST_PERMISSION_FILE
                    )
                }
            } else {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setMessage("권한 허용 상태")
                    dialog.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_PERMISSION_FILE
                    )
                }
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("권한 허용 상태")
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                    REQUEST_PERMISSION_FILE
                )
            }
        }
    }

    private fun checkCamera() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("권한 허용 상태")
            dialog.show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION_CAMERA
            )
        }
    }

    private fun checkForegroundLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_PERMISSION_FOREGROUND_LOCATION
            )
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                || ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("위치 허용이 필요합니다.")
                dialog.setPositiveButton(
                    "확인"
                ) { dialog, which -> }
                dialog.show()
            }
        }
    }

    private fun checkBackgroundLocation() {
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
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        REQUEST_PERMISSION_BACKGROUND_LOCATION
                    )
                }
            } else {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                        ),
                        REQUEST_PERMISSION_BACKGROUND_LOCATION
                    )
                }
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    REQUEST_PERMISSION_BACKGROUND_LOCATION
                )
            }
        }
    }

    private fun clickPhoneNum() {
        // android.permission.READ_PHONE_STATE,android.permission.READ_SMS,android.permission.READ_PHONE_NUMBERS 중 하나라도 권한 있어야 함
        val tMgr = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
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
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_NUMBERS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_PHONE_NUMBERS),
                    REQUEST_PERMISSION_PHONE_NUM
                )
            } else {
                binding.tvPhoneNum.text = tMgr.line1Number
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_PHONE_STATE),
                    REQUEST_PERMISSION_PHONE_NUM
                )
            } else {
                binding.tvPhoneNum.text = tMgr.line1Number
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_BACKGROUND_LOCATION) {
            Log.d("testPermission", "check back permissions : " + permissions.contentToString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                        REQUEST_PERMISSION_BACKGROUND_LOCATION_ADD
                    )
                }
            }
        } else if (requestCode == REQUEST_PERMISSION_FOREGROUND_LOCATION) {
            Log.d("testPermission", "check fore permissions : " + permissions.contentToString())
        } else if (requestCode == REQUEST_PERMISSION_PHONE_NUM) {
            Log.d("testPermission", "check permissions phone Num : " + permissions.contentToString())
            var isOk = true
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isOk = false
                    break
                }
            }
            if (isOk) {
            }
        } else {
            Log.d("testPermission", "check permissions : " + permissions.contentToString())
        }
    }
}