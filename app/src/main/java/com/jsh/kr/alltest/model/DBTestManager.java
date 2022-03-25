package com.jsh.kr.alltest.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.model.data.DBTest;
import com.jsh.kr.alltest.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class DBTestManager {
    private static final String TAG = DBTestManager.class.getSimpleName();

    private AllTestDBHelper dbHelper;
    private SQLiteDatabase db;


    public DBTestManager(Context context) {
        dbHelper = new AllTestDBHelper(context);
    }

    public void insertDBTest(String text, int integer) {
        db = dbHelper.getWritableDatabase();

        db.execSQL(
                "INSERT INTO "+ C.Database.TbDBTest.Name
                        + " (" + C.Database.TbDBTest.ColText + ", "+C.Database.TbDBTest.ColInt +")"
                        + " VALUES ("
                        + "\"" + text + "\", " + integer
                        + ")"
        );

        db.close();

    }

    public ArrayList<DBTest> selectDBTest() {
        db = dbHelper.getReadableDatabase();
        ArrayList<DBTest> dbTests = new ArrayList<>();
        Cursor cursor = null;

        String sql = "SELECT * FROM "+C.Database.TbDBTest.Name;

        cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            DBTest dbTest = new DBTest();

            try {
                int idx = -1;
                int indexColumnIdx = cursor.getColumnIndex(C.Database.TbDBTest.Idx);
                if (indexColumnIdx >= 0) {
                    idx = cursor.getInt(indexColumnIdx);
                }
                String text = cursor.getString(cursor.getColumnIndexOrThrow(C.Database.TbDBTest.ColText));
                int integer = cursor.getInt(cursor.getColumnIndexOrThrow(C.Database.TbDBTest.ColInt));

                dbTest.setIdx(idx);
                dbTest.setText(text);
                dbTest.setInteger(integer);

                dbTests.add(dbTest);
            } catch (IllegalArgumentException e) {
                LogUtil.d(DBTestManager.class.getSimpleName(), e.getLocalizedMessage());
            }

        }

        db.close();
        return dbTests;
    }

    public void deleteDBTest() {
        db = dbHelper.getWritableDatabase();

        db.execSQL(
                "DELETE FROM " + C.Database.TbDBTest.Name
        );

        db.close();
    }

    public void deleteDBTEstWithIdx(int idx) {
        db = dbHelper.getWritableDatabase();

        db.execSQL(
                "DELETE FROM " + C.Database.TbDBTest.Name
                        + " WHERE "+ C.Database.TbDBTest.Idx + "=" + idx
        );

        db.close();
    }

    public void initDBTest() {
        db = dbHelper.getWritableDatabase();

        deleteDBTest();

        db.execSQL(
                "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = \'" + C.Database.TbDBTest.Name + "\'"
        );

        db.close();
    }

    public void sqliteExport(Context context){
        try {
            File sd = AllTestDirectory.getExportDBDir();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
//                String currentDBPath = "//data//패키지명/databases//데이터베이스명";
//                String backupDBPath = "데이터베이스명.sqlite";
                String currentDBPath = "//data//" + context.getPackageName() + "/databases//" + C.Database.Name;
                String backupDBPath = C.Database.Name + ".sqlite";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                LogUtil.d(TAG, "export current file exist : "+ currentDB.exists());

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
                if(backupDB.exists()){
                    Toast.makeText(context, "DB Export Complete!!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
    }
}

