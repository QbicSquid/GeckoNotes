package com.geckolabs.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuizDAO extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "quiz";
    private static final String TABLE_NAME = "quiz";
    private static final String QID_COL = "id";
    private static final String Q_NAME = "name";
    private static final String Q_ANS = "ans";
    private static final String Q_OPT = "opt";

    public QuizDAO(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String quiz_create_query = "CREATE TABLE " + TABLE_NAME + " ("
                + QID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  Q_NAME + " TEXT,"
                + Q_ANS + " TEXT,"
                 + Q_OPT + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(quiz_create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String quiz_drop_table_query = " DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(quiz_drop_table_query);
        onCreate(sqLiteDatabase);

    }
}
