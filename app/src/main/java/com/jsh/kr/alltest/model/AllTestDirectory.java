package com.jsh.kr.alltest.model;

import android.content.Context;
import android.os.Environment;
import android.provider.MediaStore;

import com.jsh.kr.alltest.util.LogUtil;

import java.io.File;
import java.io.IOException;

public class AllTestDirectory {
    private static final String TAG = AllTestDirectory.class.getSimpleName();
    private static File imageCacheDir;
    private static File imageDir;
    private static File exportDBDir;
    private static File recordFileDir;

    public static void init(Context ctx){
        LogUtil.d(TAG, "init()");

        imageCacheDir = new File(ctx.getExternalCacheDir(), "img");
        if(!imageCacheDir.exists()){
            if(imageCacheDir.mkdir()) {
                LogUtil.d(TAG, "create imageCacheDir");
                createNoMediaFile(imageCacheDir);
            }

        }

        String imgPath = Environment.getExternalStorageDirectory() + File.separator + "alltest";
        imageDir = new File(imgPath, "alltest_img");
        LogUtil.d(TAG, "path :"+imgPath);
        LogUtil.d(TAG, "path exist:"+imageDir.exists());
        if(!imageDir.exists()){
            if(imageDir.mkdirs()) {
                LogUtil.d(TAG, "create imageDir");
            } else {
                LogUtil.d(TAG, "create fail imageDir");
            }

        }

        exportDBDir = new File(imgPath, "exportDB");
        if(!exportDBDir.exists()){
            if(exportDBDir.mkdirs()) {
                LogUtil.d(TAG, "create exportDBDir");
            } else {
                LogUtil.d(TAG, "create fail exportDBDir");
            }

        }

        recordFileDir = new File(imgPath, "record");
        if(!recordFileDir.exists()){
            if(recordFileDir.mkdirs()) {
                LogUtil.d(TAG, "create recordFileDir");
            } else {
                LogUtil.d(TAG, "create fail recordFileDir");
            }

        }
    }

    public static void createNoMediaFile(File dir) {
        File noMedia = new File(dir, MediaStore.MEDIA_IGNORE_FILENAME);
        try {
            boolean isCreated = noMedia.createNewFile();
            LogUtil.d(TAG, "create file check : " + isCreated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getImageCacheDir(){
        return imageCacheDir;
    }

    public static File getImageDir() {
        return imageDir;
    }

    public static File getExportDBDir() {
        return exportDBDir;
    }

    public static File getRecordFileDir() {
        return recordFileDir;
    }
}

