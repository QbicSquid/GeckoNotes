package com.geckolabs.dao;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.geckolabs.notes.NoteModel;
import java.util.ArrayList;
import java.util.List;

public class TextDB extends SQLiteOpenHelper{

    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "NoteModel";
    private static final String NOTE_ID = "id";
//    private static final String NOTE_TYPE= "type";
    private static final String NOTE_TEXT = "des";

    public TextDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //this is calls the first time a database is accessed. there should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +NOTE_TEXT + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }


    public boolean addNewNote(NoteModel noteModel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TEXT, noteModel.getText());
        //cv.put(NOTE_ID, noteModel.getId());

        long insert = sqLiteDatabase.insert(TABLE_NAME, null, cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor viewData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        return cursor;
    }

    public boolean DeleteOne(NoteModel noteModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = " DELETE FROM " + TABLE_NAME + " WHERE " + NOTE_ID + " = " + noteModel.getId();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public boolean updateOne(NoteModel noteModel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.d("in update one",noteModel.getText());
        cv.put(NOTE_TEXT, noteModel.getText());
        int status = sqLiteDatabase.update(TABLE_NAME,cv,NOTE_ID +" =?",
                new String[]{String.valueOf(noteModel.getId())});
        sqLiteDatabase.close();
        if(status==-1){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
    }


}
