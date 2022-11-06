package com.geckolabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.geckolabs.dao.model.Note;

public class NoteDB extends DBBase {
    public NoteDB(Context context) {
        super(context);
    }

    public static void createTableNote(SQLiteDatabase db) {
        String query = "CREATE TABLE note"
                + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT,"
                + "note_group INTEGER,"
                + "color INTEGER    "
                + ")";
        db.execSQL(query);
    }

    public long insert(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("note_group", note.getNote_group());
        values.put("color", note.getColor());

        long noteId = db.insert("note", null, values);
        return noteId;
    }


    public Note getOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM note WHERE id = " + Integer.toString(id);
        Cursor cursor = db.rawQuery(query, null);
        cursor.move(1);

        Note note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return note;
    }

    public Note[] getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM note";
        Cursor cursor = db.rawQuery(query, null);

        Note[] notes = new Note[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.move(1);
            notes[i] = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        }

        return notes;
    }
}
