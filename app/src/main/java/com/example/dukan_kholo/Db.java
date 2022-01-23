package com.example.dukan_kholo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {
    String Databasename = "dukankholo.db";

    public Db(@Nullable Context context) {
        super(context, "dukankholo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(Id integer primary key autoincrement,Username text,Email text,Password text)");
        sqLiteDatabase.execSQL("Create Table Startupdetail(Id integer primary key autoincrement , Startupname text , Amount Integer , Person Integer , Idea text)");    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists user");
        sqLiteDatabase.execSQL("Drop Table If Exists Startupdetail");

    }

    public boolean Insertrecord(String Username, String Email, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datainserted = new ContentValues();
        datainserted.put("Username", Username);
        datainserted.put("Email", Email);
        datainserted.put("Password", Password);
        long success = db.insert("user", null, datainserted);
        if (success > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startupdetailinsert(String Startupname , Integer Amount , Integer Person, String Idea){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues startupinsert = new ContentValues();
        startupinsert.put("Startupname" , Startupname);
        startupinsert.put("Amount" , Amount);
        startupinsert.put("Person" , Person);
        startupinsert.put("Idea" , Idea);
        long Startupresult = db.insert("Startupdetail" , null , startupinsert);
        if (Startupresult > 0 ){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean Startupname(String startupname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor startupcheck = db.rawQuery("Select * From Startupdetail Where Startupname= ?" , new String[]{startupname});
        if (startupcheck.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }


    public boolean checkemail(String useremail) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor find = db.rawQuery("select * from user where Email = ?", new String[]{useremail});
        if (find.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean loginemail(String uemail, String upswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor find = db.rawQuery("select * from user where Email = ? and Password = ?", new String[]{uemail, upswd});
        if (find.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

