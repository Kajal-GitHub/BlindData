package com.example.blinddata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BlindData.db";
    public static final String TABLE_NAME = "blind_table";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Mobile_No";
    public static final String COL_4="Email";
    public static final String COL_5="Password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(" CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Mobile_No TEXT,Email TEXT,Password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
    onCreate(db);
    }


    public Cursor display_name()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + DatabaseHelper.TABLE_NAME ,null);
        return  cursor;
    }
}
