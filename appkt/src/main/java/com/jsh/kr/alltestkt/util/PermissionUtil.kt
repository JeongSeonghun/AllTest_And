package com.jsh.kr.alltestkt.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.jsh.kr.alltestkt.AllTestApplication
import com.jsh.kr.alltestkt.constant.C
import java.util.ArrayList

object PermissionUtil {

    /**
     * check permission
     * intro
     * @param activity
     * @return
     */
    @JvmStatic
    fun checkPermission(activity: Activity): Boolean {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)

        return checkPermission(activity, permissions, C.RequestCode.PermissionRequest)
    }

    /**
     * check permission
     * image select
     * @param activity
     * @return
     */
    @JvmStatic
    fun checkImageSelectPermission(activity: Activity): Boolean {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        return checkPermission(activity, permissions, C.RequestCode.PermissionRequestImageSelect)
    }

    @JvmStatic
    fun checkMapPermission(activity: Activity): Boolean {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        return checkPermission(activity, permissions, C.RequestCode.PermissionRequestImageSelect)
    }

    @JvmStatic
    fun checkGPSPermission(): Boolean {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        return checkPermission(AllTestApplication.application!!, permissions)
    }

    @JvmStatic
    fun checkFileTestPermission(activity: Activity): Boolean {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        return checkPermission(activity, permissions, C.RequestCode.PermissionRequestFileTest)
    }

    /**
     * check ImageSelect permission result
     * @param permissions
     * @param grantResults
     * @return
     */
    private fun checkImageSelect(permissions: Array<String>, grantResults: IntArray): Boolean {
        var check = true

        for (grant in grantResults) {
            check = check && grant == PackageManager.PERMISSION_GRANTED
        }
        return check
    }

    private fun checkFileTest(permissions: Array<String>, grantResults: IntArray): Boolean {
        var check = true

        for (grant in grantResults) {
            check = check && grant == PackageManager.PERMISSION_GRANTED
        }
        return check
    }

    /**
     * use from activity onRequestPermissionsResult()
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return
     */
    @JvmStatic
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray): Boolean {
        var check = false
        when (requestCode) {
            C.RequestCode.PermissionRequest -> check = true
            C.RequestCode.PermissionRequestImageSelect -> check = checkImageSelect(permissions, grantResults)
            C.RequestCode.PermissionRequestFileTest -> check = checkFileTest(permissions, grantResults)
        }

        return check
    }

    private fun checkPermission(activity: Activity, permissions: Array<String>, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionList = ArrayList<String>()

            for (permission in permissions) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission)
                }
            }

            if (permissionList.size > 0) {
                val array = permissionList.toTypedArray()
                activity.requestPermissions(array, requestCode)
                return false
            }

        }
        return true
    }

    private fun checkPermission(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }

            }
        }
        return true
    }

    /**
     * permission check result must receive result from Activity onRequestPermissionsResult
     * @param activity
     * @param permissions
     * @param requestCode
     */
    private fun checkPermissionNoReturn(activity: Activity, permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, requestCode)
        }
    }
}