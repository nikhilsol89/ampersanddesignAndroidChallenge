package androidchallenge.nikhil.com.androidchallenge.application;

import android.app.Application;

import androidchallenge.nikhil.com.androidchallenge.database.NotesDBHelper;

/**
 * Created by charurani on 23-06-2016.
 */

public class MainApplication extends Application {

    public static NotesDBHelper notesDBHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        notesDBHelper = NotesDBHelper.getInstance(this);

    }

    public static NotesDBHelper getNotesDBHelper() {
        return notesDBHelper;
    }
}
