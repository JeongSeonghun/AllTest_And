package com.jsh.kr.alltest.model;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jsh.kr.alltest.C;

/**
 * 중요) app update 시 db 구조(ex column 추가)변경이 적용 되려면 DB version 높일 필요 있음
 */
public class AllTestDBHelper extends SQLiteOpenHelper {

    public AllTestDBHelper(Context context) {
        super(context, C.Database.Name, null, C.Database.Version);
    }

    public AllTestDBHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, C.Database.Name, null, C.Database.Version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDBTestTable(db);
        createFileDisplayTable(db);
        createFileDisplayCategoryTable(db);
        createAssetDBTestTable(db);
        createFileHistoryTable(db);
    }

    private void createDBTestTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ C.Database.TbDBTest.Name
                + " ("+C.Database.TbDBTest.Idx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                +", "+ C.Database.TbDBTest.ColText + " TEXT"
                +", "+ C.Database.TbDBTest.ColInt + " INTEGER"
                +")"
        );
    }

    private void createFileDisplayTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ C.Database.TbFileDisplay.Name
                + " ("+C.Database.TbFileDisplay.Idx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", "+ C.Database.TbFileDisplay.ColCategory+ " TEXT"
                + ", "+ C.Database.TbFileDisplay.ColSubCategory1+ " TEXT"
                + ", "+ C.Database.TbFileDisplay.ColSubCategory2+ " TEXT"
                + ", "+ C.Database.TbFileDisplay.ColFileName+ " TEXT"
                + ", "+ C.Database.TbFileDisplay.ColPath+ " TEXT"
                +")"
        );
    }

    private void createFileDisplayCategoryTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ C.Database.TbFileDisplayCategory.Name
                + " ("+C.Database.TbFileDisplayCategory.ColIdx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", "+ C.Database.TbFileDisplayCategory.ColParentId+ " INTEGER" + " DEFAULT 0"
                + ", "+ C.Database.TbFileDisplayCategory.ColLevel+ " INTEGER"
                + ", "+ C.Database.TbFileDisplayCategory.ColName+ " TEXT"
                +")"
        );
    }

    private void createAssetDBTestTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ C.Database.TbAssetDBTest.Name
                + " ("+C.Database.TbAssetDBTest.ColIdx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                +", "+ C.Database.TbAssetDBTest.ColName + " TEXT"
                +")"
        );
    }

    private void createFileHistoryTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ C.Database.TbFileHistory.Name
                + " ("+C.Database.TbFileHistory.ColIdx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                +", "+ C.Database.TbFileHistory.ColDate + " TEXT"
                +", "+ C.Database.TbFileHistory.ColName + " TEXT"
                +")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion <= 1) {
            createFileDisplayTable(db);
            createFileDisplayCategoryTable(db);
        }

        if(oldVersion <= 2) {
            createAssetDBTestTable(db);
            createFileHistoryTable(db);
        }
    }
}

