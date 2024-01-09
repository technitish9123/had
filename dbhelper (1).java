package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {

    private static final String dbname="comp";
    private static final String tbname = "employ";
    private static final int dbversion =1;
    public dbhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname,null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+tbname+"(uname VARCHAR(10),passw VARCHAR(10))"+";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tbname);
        onCreate(sqLiteDatabase);
    }
    public long adduser(String name, String pass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uname",name);
        cv.put("passw",pass);
        long result = sqLiteDatabase.insert(tbname,null,cv);
        sqLiteDatabase.close();
        return result;
    }
    public void update(String name, String pass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE "+tbname+" SET passw='"+pass+"' "+" WHERE uname='"+name+"'");
        sqLiteDatabase.close();
    }
    public void delete(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + tbname + " WHERE uname='" + name+"'");
        sqLiteDatabase.close();
    }
    public String display(Context ctx){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+tbname,null);
        String finalres = " ";
        while(cursor.moveToNext()) {
            finalres += cursor.getString(0) + ":" + cursor.getString(1);
        }
        return finalres;
    }
}