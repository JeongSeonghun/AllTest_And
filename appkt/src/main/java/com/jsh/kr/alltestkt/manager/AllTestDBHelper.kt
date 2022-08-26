package com.jsh.kr.alltestkt.manager

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jsh.kr.alltestkt.constant.C

/**
 * 중요) app update 시 db 구조(ex column 추가)변경이 적용 되려면 DB version 높일 필요 있음
 */
class AllTestDBHelper : SQLiteOpenHelper {

    constructor(context: Context) : super(context, C.Database.Name, null, C.Database.Version) {}

    constructor(context: Context, errorHandler: DatabaseErrorHandler) : super(context, C.Database.Name, null, C.Database.Version, errorHandler) {}

    override fun onCreate(db: SQLiteDatabase) {
        createDBTestTable(db)
        createFileDisplayTable(db)
        createFileDisplayCategoryTable(db)
        createAssetDBTestTable(db)
        createFileHistoryTable(db)
    }

    private fun createDBTestTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + C.Database.TbDBTest.Name
                + " (" + C.Database.TbDBTest.Idx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", " + C.Database.TbDBTest.ColText + " TEXT"
                + ", " + C.Database.TbDBTest.ColInt + " INTEGER"
                + ")"
        )
    }

    private fun createFileDisplayTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + C.Database.TbFileDisplay.Name
                + " (" + C.Database.TbFileDisplay.Idx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", " + C.Database.TbFileDisplay.ColCategory + " TEXT"
                + ", " + C.Database.TbFileDisplay.ColSubCategory1 + " TEXT"
                + ", " + C.Database.TbFileDisplay.ColSubCategory2 + " TEXT"
                + ", " + C.Database.TbFileDisplay.ColFileName + " TEXT"
                + ", " + C.Database.TbFileDisplay.ColPath + " TEXT"
                + ")"
        )
    }

    private fun createFileDisplayCategoryTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + C.Database.TbFileDisplayCategory.Name
                + " (" + C.Database.TbFileDisplayCategory.ColIdx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", " + C.Database.TbFileDisplayCategory.ColParentId + " INTEGER" + " DEFAULT 0"
                + ", " + C.Database.TbFileDisplayCategory.ColLevel + " INTEGER"
                + ", " + C.Database.TbFileDisplayCategory.ColName + " TEXT"
                + ")"
        )
    }

    private fun createAssetDBTestTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + C.Database.TbAssetDBTest.Name
                + " (" + C.Database.TbAssetDBTest.ColIdx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", " + C.Database.TbAssetDBTest.ColName + " TEXT"
                + ")"
        )
    }

    private fun createFileHistoryTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + C.Database.TbFileHistory.Name
                + " (" + C.Database.TbFileHistory.ColIdx + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", " + C.Database.TbFileHistory.ColDate + " TEXT"
                + ", " + C.Database.TbFileHistory.ColName + " TEXT"
                + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion <= 1) {
            createFileDisplayTable(db)
            createFileDisplayCategoryTable(db)
        }

        if (oldVersion <= 2) {
            createAssetDBTestTable(db)
            createFileHistoryTable(db)
        }
    }
}