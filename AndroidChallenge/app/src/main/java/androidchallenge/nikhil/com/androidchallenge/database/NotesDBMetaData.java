package androidchallenge.nikhil.com.androidchallenge.database;

/**
 * Created by charurani on 22-06-2016.
 */

public class NotesDBMetaData {

    public static final String TABLE_NOTES = "Notes";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NOTE = "Note";
    public static final String COLUMN_NOTE_DATE = "Date_Note";
    public static final String COLUMN_NOTE_COLOR = "Selected_Color";

    public static final String CREATE_TABLE_NOTES = "Create Table "+TABLE_NOTES + " ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOTE + " TEXT, "+COLUMN_NOTE_DATE + " TEXT, "+COLUMN_NOTE_COLOR + " TEXT)" ;

    public static final String DROP_TABLE_NOTES = "DROP TABLE IF EXISTS "+TABLE_NOTES;

    public static final String FETCH_DATA_NOTES = "SELECT * FROM "+TABLE_NOTES;


}
