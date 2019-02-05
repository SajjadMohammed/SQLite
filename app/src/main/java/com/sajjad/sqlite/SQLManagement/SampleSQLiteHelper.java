package com.sajjad.sqlite.SQLManagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SampleSQLiteHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "SampleDB.db3";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    public SampleSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        copyDataBase();
    }

    private void copyDataBase() {
        this.getReadableDatabase();
        try {
            File file=new File(DB_PATH);
            if (!file.exists()) {
                copyDBFile();
                close();
            }
        } catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = mInput.read(buffer)) > 0) {
            mOutput.write(buffer, 0, length);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public List<SampleModel> GetAllSamples() {
        sqLiteDatabase = getReadableDatabase();
        List<SampleModel> eNumbersTableList = new ArrayList<>();
        Cursor c = sqLiteDatabase.query
                ("SampleTable", null, null, null, null, null, null);
        while (c.moveToNext()) {
            SampleModel eNumbersTable = new SampleModel(c.getInt(0),c.getString(1),c.getString(2));
            eNumbersTableList.add(eNumbersTable);
        }
        c.close();
        sqLiteDatabase.close();
        return eNumbersTableList;
    }

   /* void InserData(String userName, String userAddress, byte[] userImage) {
        SQLiteDatabase dbb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName", userName);
        contentValues.put("UserAddress", userAddress);
        contentValues.put("UserImage", userImage);
        dbb.insert("UserInfo", null, contentValues);
    }*/

     public void UpdateData(int sampleId,String sampleName, String sampleTemp) {
         SQLiteDatabase dbb = getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("samplename", sampleName);
         contentValues.put("sampletemp", sampleTemp);
         dbb.update("sampletable",contentValues,"sampleid="+sampleId,null);
     }
    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
