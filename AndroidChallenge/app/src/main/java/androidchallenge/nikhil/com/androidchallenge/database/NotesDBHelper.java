package androidchallenge.nikhil.com.androidchallenge.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by Nikhil on 22-06-2016.
 */

public class NotesDBHelper extends SQLiteOpenHelper {

    public static NotesDBHelper notesDBHelper;
    private static final String DATABASE_NAME = "Notes.db";
    private static final int DATABASE_VERSION = 1;


    public static NotesDBHelper getInstance(Context context) {
        if (notesDBHelper == null) {
            notesDBHelper = new NotesDBHelper(context);
        }
        return notesDBHelper;
    }

    private NotesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NotesDBMetaData.CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NotesDBMetaData.DROP_TABLE_NOTES);
        db.execSQL(NotesDBMetaData.CREATE_TABLE_NOTES);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(NotesDBMetaData.FETCH_DATA_NOTES, null);
        return res;
    }

    public int getId(int listItemPosition){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(NotesDBMetaData.FETCH_DATA_NOTES, null);
        Log.e("nikhil","Movement :"+res.moveToPosition(listItemPosition));
        return res.getInt(res.getColumnIndex(NotesDBMetaData.COLUMN_ID));
    }



    public boolean insertNames(String note, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesDBMetaData.COLUMN_NOTE, note);
        contentValues.put(NotesDBMetaData.COLUMN_NOTE_DATE, new Date(System.currentTimeMillis()).toString());
        contentValues.put(NotesDBMetaData.COLUMN_NOTE_COLOR, color);
        db.insert(NotesDBMetaData.TABLE_NOTES, null, contentValues);
        return true;
    }

    public int removeFromDB(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NotesDBMetaData.TABLE_NOTES,NotesDBMetaData.COLUMN_ID +"=? ",new String[]{id+""});
    }

    public void deleteNote(String note,String color, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NotesDBMetaData.TABLE_NOTES,NotesDBMetaData.COLUMN_NOTE +" =? and " +NotesDBMetaData.COLUMN_NOTE_DATE
                +" =? and " +NotesDBMetaData.COLUMN_NOTE_COLOR+" =?" ,new String[]{note,color,date});
    }

}
