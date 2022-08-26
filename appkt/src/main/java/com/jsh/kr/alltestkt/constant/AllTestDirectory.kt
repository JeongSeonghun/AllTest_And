package com.jsh.kr.alltestkt.constant

import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import com.jsh.kr.alltestkt.util.LogUtil
import java.io.File
import java.io.IOException

object AllTestDirectory {
    private val TAG = AllTestDirectory::class.java.simpleName
    var imageCacheDir: File? = null
    var imageDir: File? = null
    var exportDBDir: File? = null

    fun init(ctx: Context) {
        LogUtil.d(TAG, "init()")

        imageCacheDir = File(ctx.externalCacheDir, "img")
        if (!imageCacheDir!!.exists()) {
            if (imageCacheDir!!.mkdir()) {
                LogUtil.d(TAG, "create imageCacheDir")
                createNoMediaFile(imageCacheDir!!)
            }

        }

        val imgPath = Environment.getExternalStorageDirectory().toString() + File.separator + "alltest"
        imageDir = File(imgPath, "alltest_img")
        LogUtil.d(TAG, "path :$imgPath")
        LogUtil.d(TAG, "path exist:" + imageDir!!.exists())
        if (!imageDir!!.exists()) {
            if (imageDir!!.mkdirs()) {
                LogUtil.d(TAG, "create imageDir")
            } else {
                LogUtil.d(TAG, "create fail imageDir")
            }

        }

        exportDBDir = File(imgPath, "exportDB")
        if (!exportDBDir!!.exists()) {
            if (exportDBDir!!.mkdirs()) {
                LogUtil.d(TAG, "create exportDBDir")
            } else {
                LogUtil.d(TAG, "create fail exportDBDir")
            }

        }

    }

    private fun createNoMediaFile(dir: File) {
        val noMedia = File(dir, MediaStore.MEDIA_IGNORE_FILENAME)
        try {
            noMedia.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
