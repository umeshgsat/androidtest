package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes_db";
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_TIME_STAMP = "timestamp";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTE +
            " TEXT," + COLUMN_TIME_STAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    long insertNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE, note);
        long id = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    Note getNote(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NOTE, COLUMN_TIME_STAMP},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            Note note = new Note(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TIME_STAMP)));

            cursor.close();
            return note;
        }
        return null;
    }

    List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        String selectionQuery = " SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_TIME_STAMP + " DESC ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectionQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(COLUMN_TIME_STAMP)));
                notes.add(note);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return notes;
    }

    public int getCount() {
        SQLiteDatabase db = getReadableDatabase();

        String selectionQuery = " SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectionQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public int update(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE, note.getNote());

       return db.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(note.getId())});
    }

    public void delete(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME);
        db.close();
    }
}
