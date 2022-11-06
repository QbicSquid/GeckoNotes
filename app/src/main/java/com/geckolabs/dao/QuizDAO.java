package com.geckolabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.geckolabs.notes.AnswerModel;
import com.geckolabs.notes.QuestionModel;
import com.geckolabs.notes.QuizModel;


public class QuizDAO extends DBBase {

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
        super(context);
    }

    public static void createTablePictureNote(SQLiteDatabase sqLiteDatabase) {
        String quiz_create_query = "CREATE TABLE " + "Quiz" + " ("
                + "quizId" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "quizTitle" + " TEXT)";

        String quiz_create_query2 = "CREATE TABLE " + "Question" + " ("
                + "qId" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "qType" + " TEXT,"
                + "qText" + " TEXT,"
                + "quizId integer, FOREIGN KEY('quizId') REFERENCES "
                + "Quiz" + "('quizId')"
                + ")";

        String quiz_create_query3 = "CREATE TABLE " + "Answer" + " ("
                + "qAnsID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "qAnsText" + " TEXT,"
                + "qAnsCorrect" + " NUMERIC,"
                + "qId integer, FOREIGN KEY('qId') REFERENCES "
                + "Question" + "('qId')"
                + ")";

        sqLiteDatabase.execSQL(quiz_create_query);
        sqLiteDatabase.execSQL(quiz_create_query2);
        sqLiteDatabase.execSQL(quiz_create_query3);

    }
    //*

    public long addNewQuiz(String title) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Quiz_Title, title);

        long l = sqLiteDatabase.insert(QTABLE_NAME1, null, values);

        sqLiteDatabase.close();
        return l;
    }


    public long addNewQuestion(String type, String ques, Integer quizId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Q_Type, type);
        values.put(Q_Text, ques);
        values.put(Quiz_ID, quizId);

        long l = sqLiteDatabase.insert(QTABLE_NAME2, null, values);

        sqLiteDatabase.close();
        return l;
    }

    public void addAnswer(String text, Boolean correct, Integer questionId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QAns_Text, text);
        values.put(QAns_Correct, correct);
        values.put(Q_ID, questionId);

        sqLiteDatabase.insert(QTABLE_NAME3, null, values);

        sqLiteDatabase.close();
    }

    public String[] getQuizList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " SELECT " + Quiz_Title + " FROM " + QTABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[] quizList = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.move(1);
            quizList[i] = cursor.getString(0);
        }
        return quizList;
    }

    public String[] getQuesList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query2 = " SELECT " + Q_ID + " FROM " + QTABLE_NAME2;
        Cursor cursor = sqLiteDatabase.rawQuery(query2, null);
        String[] quesList = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.move(1);
            quesList[i] = cursor.getString(0);
        }
        return quesList;
    }


    public QuizModel getSingleQuizID(String quizTitle) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(QTABLE_NAME1, new String[]{Quiz_ID, Quiz_Title},
                Quiz_Title + "= ?", new String[]{quizTitle}
                , null, null, null);


        QuizModel quizModel;
        if (cursor != null) {
            cursor.moveToFirst();
            quizModel = new QuizModel(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
            return quizModel;
        }
        return null;
    }

    public QuestionModel getSingleQuizQuestions(Integer quizID) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(QTABLE_NAME2, new String[]{Q_ID, Q_Type, Q_Text, Quiz_ID},
                Quiz_ID + "= ?", new String[]{String.valueOf(quizID)}
                , null, null, null);


        QuestionModel questionModel;
        if (cursor != null) {
            cursor.moveToFirst();
            questionModel = new QuestionModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            return questionModel;
        }
        return null;
    }

    public AnswerModel getAnswerForQuestion(Integer queID) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(QTABLE_NAME3, new String[]{QAns_ID, QAns_Text, QAns_Correct, Q_ID},
                Q_ID + "= ?", new String[]{String.valueOf(queID)}
                , null, null, null);

        AnswerModel answerModel;
        if (cursor != null) {
            cursor.moveToFirst();
            answerModel = new AnswerModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            return answerModel;
        }
        return null;
    }

    public QuestionModel getAQuizQuestion(Integer queID) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(QTABLE_NAME2, new String[]{Q_ID, Q_Type, Q_Text, Quiz_ID},
                Q_ID + "= ?", new String[]{String.valueOf(queID)}
                , null, null, null);


        QuestionModel questionModel;
        if (cursor != null) {
            cursor.moveToFirst();
            questionModel = new QuestionModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            return questionModel;
        }
        return null;
    }

    public AnswerModel getQuestionAnswer(Integer ansID) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(QTABLE_NAME3, new String[]{QAns_ID, QAns_Text, QAns_Correct, Q_ID},
                Q_ID + "= ?", new String[]{String.valueOf(ansID)}
                , null, null, null);


        AnswerModel answerModel;
        if (cursor != null) {
            cursor.moveToFirst();
            answerModel = new AnswerModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            return answerModel;
        }
        return null;
    }

    //delete a quiz
    public void deleteQuizDB(Integer quizId, Integer queId, Integer ansId) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(QTABLE_NAME3, QAns_ID + " =?", new String[]{String.valueOf(ansId)});
        db.delete(QTABLE_NAME2, Q_ID + " =?", new String[]{String.valueOf(queId)});
        db.delete(QTABLE_NAME1, Quiz_ID + " =?", new String[]{String.valueOf(quizId)});
        db.close();
    }

    //update Answer
    public int updateAns(AnswerModel answerModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(QAns_Text, answerModel.getqAns());

        int status = db.update(QTABLE_NAME3, contentValues, QAns_ID + " =?",
                new String[]{String.valueOf(answerModel.getAnsID())});

        db.close();
        return status;
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
