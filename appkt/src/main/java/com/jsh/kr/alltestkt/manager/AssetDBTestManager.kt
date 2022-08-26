package com.jsh.kr.alltestkt.manager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.google.gson.Gson
import com.jsh.kr.alltestkt.constant.C
import com.jsh.kr.alltestkt.model.FileHistoryData
import com.jsh.kr.alltestkt.util.Util
import java.io.IOException
import java.util.*

class AssetDBTestManager {
    private val AssetFolderName = "fileread"

    private var dbHelper: AllTestDBHelper? = null
    private var db: SQLiteDatabase? = null

    fun init(context: Context) {
        dbHelper = AllTestDBHelper(context)

        initAssetFile(context)
    }

    private fun initAssetFile(context: Context) {
        val assetFileNames = ArrayList<String>()

        try {
            val assetFiles = context.assets.list(AssetFolderName)

            assetFileNames.addAll(Arrays.asList(*assetFiles!!))
            for (fileName in assetFileNames) {
                val readData = readFileData(context, fileName)
                val searchData = selectFileHistory(fileName)
                if (readData == null) {
                    continue
                }

                if (searchData == null) {
                    addFileHistory(context, readData)
                } else {
                    updateFileHistory()
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun addFileHistory(context: Context, fileHistoryData: FileHistoryData) {
        insertFileHistTory(context, fileHistoryData)
    }

    private fun insertFileHistTory(context: Context, fileHistoryData: FileHistoryData) {
        db = dbHelper!!.writableDatabase

        db!!.execSQL("INSERT INTO " + C.Database.TbFileHistory.Name
                + " (" + C.Database.TbFileHistory.ColName
                + ", " + C.Database.TbFileHistory.ColDate
                + ") VALUES('" + fileHistoryData.name + "', '" + fileHistoryData.date + "')"
        )
    }

    private fun updateFileHistory() {
        // todo make update
    }

    private fun readFileData(context: Context, fileName: String): FileHistoryData? {
        var historyData: FileHistoryData? = null
        val data = Util.getJsonFromAssets(context, "$AssetFolderName/$fileName")
        if (data.length > 0) {
            historyData = Gson().fromJson(data, FileHistoryData::class.java)
            historyData!!.name = fileName
        }
        return historyData
    }

    private fun selectFileHistory(fileName: String): FileHistoryData? {
        db = dbHelper!!.readableDatabase
        var historyData: FileHistoryData? = null
        val cursor = db!!.rawQuery("SELECT * FROM " + C.Database.TbFileHistory.Name
                + " WHERE " + C.Database.TbFileHistory.ColName + "='" + fileName + "'", null)

        if (cursor != null) {
            if (cursor.moveToNext()) {
                val nameIdx = cursor.getColumnIndex(C.Database.TbFileHistory.ColName)
                val dateIdx = cursor.getColumnIndex(C.Database.TbFileHistory.ColDate)
                val name = cursor.getString(nameIdx)
                val date = cursor.getString(dateIdx)

                historyData = FileHistoryData()
                historyData.name = name
                historyData.date = date

            }
            cursor.close()
        }

        return historyData
    }
}