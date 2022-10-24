package com.geckolabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MediaNotesDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "NoteDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "MediaNote";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String MEDIA_FILE = "mediaFile";
    private static final String TYPE = "mediaId";
    private static final String NOTE_ID = "noteId";

    public MediaNotesDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE+ " TEXT,"
                + DESCRIPTION + " TEXT,"
                + TYPE + " TEXT,"
                + NOTE_ID + " INTEGER,"
                + MEDIA_FILE + " TEXT);";


        sqLiteDatabase.execSQL(query);

    }
    public void addNewAudioNote(String title,String description) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TITLE,title );
        values.put(DESCRIPTION,description);
        values.put(TYPE,"audio");
        values.put(NOTE_ID,1);
        values.put(MEDIA_FILE,"test.mp3");

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
