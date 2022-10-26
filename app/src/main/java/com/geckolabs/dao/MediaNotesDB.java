package com.geckolabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.geckolabs.notes.PicNoteModel;

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
    public void addNewAudioNote(String title,String description,String mediaPath) {

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
        values.put(MEDIA_FILE,mediaPath);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public String[] getPicNote(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE ID ="+ id;
        Cursor cursor = db.rawQuery(query, null);
        String[] courses = new String[cursor.getCount()];
        Log.d("InDAO","check");

        for (int i = 0; i < 6; i++) {
            cursor.move(1);
            courses[i] = cursor.getString(0);
//            courses[i] = cursor.getString(1);
            Log.d("InLoop",courses[i]);
        }

        return courses;
    }
    // Get a single Note
    public PicNoteModel getSinglePicNote(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,TYPE, NOTE_ID,MEDIA_FILE},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        PicNoteModel picNoteModel;
        if(cursor != null){
            cursor.moveToFirst();
            picNoteModel = new PicNoteModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            );
            return picNoteModel;
        }
        return null;
    }
    // Update a single PicNote
    public int updateSinglePicNote(PicNoteModel picNoteModel){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,picNoteModel.getTitle());
        contentValues.put(DESCRIPTION,picNoteModel.getDescription());
//        contentValues.put(TYPE,"audio");
//        contentValues.put(NOTE_ID,1);
//        contentValues.put(MEDIA_FILE,mediaPath);

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(picNoteModel.getId())});

        db.close();
        return status;
    }

    // Delete item
    public void deletePicNote(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
