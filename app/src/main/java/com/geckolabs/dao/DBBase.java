package com.geckolabs.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBBase extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    protected static final String DB_NAME = "coursedb";

    // below int is our database version
    protected static final int DB_VERSION = 1;

    public DBBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableNote(db);
    }

    private void createTableNote(SQLiteDatabase db) {
        String query = "CREATE TABLE note"
                + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "note_group INTEGER,"
                + "color INTEGER"
                + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
