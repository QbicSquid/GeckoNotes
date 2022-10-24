package com.geckolabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuizDAO extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "QuizDB";
    private static final String QTABLE_NAME1 = "Quiz";
    private static final String QTABLE_NAME2 = "Question";
    private static final String QTABLE_NAME3 = "Answer";

    private static final String Quiz_ID = "quizId";
    private static final String Quiz_Title = "quizTitle";

    private static final String Q_ID = "qId";
    private static final String Q_Type = "qType";
    private static final String Q_Text = "qText";

    private static final String QAns_ID = "qAnsID";
    private static final String QAns_Text = "qAnsText";
    private static final String QAns_Correct = "qAnsCorrect";

    public QuizDAO(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String quiz_create_query = "CREATE TABLE " + QTABLE_NAME1 + " ("
                + Quiz_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + Quiz_Title + " TEXT)";

        String quiz_create_query2 = "CREATE TABLE " + QTABLE_NAME2 + " ("
                + Q_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Q_Type + " TEXT,"
                + Q_Text + " TEXT,"
                +"quizId integer, FOREIGN KEY('quizId') REFERENCES "
                +QTABLE_NAME1 + "('quizId')"
                +")";

        String quiz_create_query3 = "CREATE TABLE " +  QTABLE_NAME3 + " ("
                + QAns_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QAns_Text + " TEXT,"
                + QAns_Correct + " NUMERIC,"
                +"qId integer, FOREIGN KEY('qId') REFERENCES "
                +QTABLE_NAME2 + "('qId')"
                +")";

        sqLiteDatabase.execSQL(quiz_create_query);
        sqLiteDatabase.execSQL(quiz_create_query2);
        sqLiteDatabase.execSQL(quiz_create_query3);

    }

    public long addNewQuiz(String title){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Quiz_Title, title);

        long l = sqLiteDatabase.insert(QTABLE_NAME1, null,values);

        sqLiteDatabase.close();
        return l;
    }

    public void addNewQuestion(String type, String ques){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Q_Type, type);
        values.put(Q_Text, ques);

        long l = sqLiteDatabase.insert(QTABLE_NAME2, null,values);

        sqLiteDatabase.close();
    }

    public void addAnswer (String text, Boolean correct){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QAns_Text, text);
        values.put(QAns_Correct, correct);

        sqLiteDatabase.insert(QTABLE_NAME3, null,values);

        sqLiteDatabase.close();
    }

    public String[] getQuizList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " SELECT " + Quiz_Title + " FROM " + QTABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[] quizList = new String[cursor.getCount()];

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.move(1);
            quizList[i] = cursor.getString(0);
        }
        return quizList;
    }

    public String[] getQuesList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query2 = " SELECT " + Q_ID + " FROM " + QTABLE_NAME2;
        Cursor cursor = sqLiteDatabase.rawQuery(query2, null);
        String[] quesList = new String[cursor.getCount()];

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.move(1);
            quesList[i] = cursor.getString(0);
        }
        return quesList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String quiz_drop_table_query1 = " DROP TABLE IF EXISTS " + QTABLE_NAME1;
        sqLiteDatabase.execSQL(quiz_drop_table_query1);
        onCreate(sqLiteDatabase);

        String quiz_drop_table_query2 = " DROP TABLE IF EXISTS " + QTABLE_NAME2;
        sqLiteDatabase.execSQL(quiz_drop_table_query2);
        onCreate(sqLiteDatabase);

        String quiz_drop_table_query3 = " DROP TABLE IF EXISTS " + QTABLE_NAME3;
        sqLiteDatabase.execSQL(quiz_drop_table_query3);
        onCreate(sqLiteDatabase);

    }
}
