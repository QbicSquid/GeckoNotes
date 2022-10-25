package com.geckolabs.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOTE_TEXT + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public boolean addNewNote(NoteModel noteModel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TEXT, noteModel.getText());

         long insert = sqLiteDatabase.insert(TABLE_NAME, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }

//        sqLiteDatabase.close();
    }
    public boolean deleteOne(NoteModel noteModel){
        //find noteModel in db .if found delete it and return true
        //if not found return false
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + NOTE_ID + " = " + noteModel.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

//when the note comes up and read
    public List<NoteModel> getEveryone() {
        List<NoteModel>returnList = new ArrayList<>();

        //get data from database
        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop through the cursor(result set) and create new note objects. Put them into the return list.
            do{
                int id = cursor.getInt(0);
                String des = cursor.getString(0);

                NoteModel newNote = new NoteModel(id,des);
                returnList.add(newNote);
            } while(cursor.moveToNext());
        }
        else{
            //false do not add anything
        }
        //close both cursor and db
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

//delete the note





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
    }
}
