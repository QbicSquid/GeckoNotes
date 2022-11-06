package com.geckolabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListDB extends SQLiteOpenHelper {
    public ListDB(Context context) {
        super(context, "table", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table listTable(ID INTEGER primary key AUTOINCREMENT, list TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists listTable");
    }
    public Boolean insertlist(String list)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("list", list);

        long result=DB.insert("listTable", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatelist(String list)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("list", list);

        Cursor cursor = DB.rawQuery("Select * from listTable where list= ?", new String[]{list});
        if (cursor.getCount() > 0) {
            long result = DB.update("listTable", contentValues, "list=?", new String[]{list});
            Log.d("updated list", String.valueOf(result));
            Log.d("updated list", list);
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }
    public Boolean deletedata (String list)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from listTable where list = ?", new String[]{list});
        if (cursor.getCount() > 0) {
            long result = DB.delete("listTable", "list=?", new String[]{list});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from listTable", null);
        return cursor;
    }

}
