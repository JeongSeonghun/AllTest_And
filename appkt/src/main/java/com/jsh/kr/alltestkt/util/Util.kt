package com.jsh.kr.alltestkt.util

import android.app.ActivityManager
import android.content.Context
import androidx.annotation.StringRes
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.constant.C
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * readLine()
 * https://stackoverflow.com/questions/39500045/in-kotlin-how-do-i-read-the-entire-contents-of-an-inputstream-into-a-string
 *
 * jvmStatic
 * https://stackoverflow.com/questions/51811391/utils-classes-in-kotlin
 * todo when convert project all class, need recheck util classes
 */
object Util {
    /**
     * use ex)
     * urlPath = Demo
     * String jsonStr = Utils.getJsonFromAssets(SDMApplication.getContext(), "demojson/" + urlPath + ".json");
     * @param context
     * @param fileName
     * @return
     */
    @JvmStatic // java file...
    fun getJsonFromAssets(context: Context, fileName: String): String {
        var line = ""
        val result = StringBuilder()
        val inputReader: InputStreamReader
        var inputStream: InputStream? = null
        var bufReader: BufferedReader? = null
        try {
            // todo 확인 필요
            inputStream = context.resources.assets.open(fileName)
            inputReader = InputStreamReader(inputStream!!, "utf-8")
            bufReader = BufferedReader(inputReader)
//            while ((line = bufReader.readLine()) != null) {
//                result.append(line)
//            } ==> error
            return inputStream.bufferedReader().use(BufferedReader::readText)
//            bufReader.close()
//            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

//        return result.toString()
        return ""
    }

    @JvmOverloads
    fun getStringResourceByName(context: Context, aString: String, @StringRes defaultId: Int = R.string.error_default): String {
        val packageName = context.packageName
        val resId = context.resources.getIdentifier(aString, "string", packageName)
        return if (resId == 0) {
            context.resources.getString(defaultId)
        } else {
            context.getString(resId)
        }
    }

    @JvmOverloads
    fun getStringResourceIdByName(context: Context, aString: String, @StringRes defaultId: Int = R.string.error_default): Int {
        val packageName = context.packageName
        val resId = context.resources.getIdentifier(aString, "string", packageName)
        return if (resId == 0) {
            defaultId
        } else {
            resId
        }
    }


    fun isBackgound(context: Context): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        if (am != null) {
            val proceses = am.runningAppProcesses
            //프로세서 전체를 반복
            for (process in proceses) {
                if (process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {

                    if (process.processName == C.PACKAGE) {
                        return true
                    }

                }
            }
        }

        return false
    }
}