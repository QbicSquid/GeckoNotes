package com.geckolabs.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MediaNotesDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "MediaNote";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String MEDIA_FILE = "mediaFile";
    private static final String TYPE = "mediaId";
    private static final String NOTE_ID = "mediaId";

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

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
