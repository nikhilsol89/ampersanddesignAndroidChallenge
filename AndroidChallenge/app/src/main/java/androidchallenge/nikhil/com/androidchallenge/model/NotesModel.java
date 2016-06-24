package androidchallenge.nikhil.com.androidchallenge.model;

import java.io.Serializable;

/**
 * Created by Nikhil on 22-06-2016.
 */

public class NotesModel implements Serializable {

    String note, noteDate, noteColor;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(String noteColor) {
        this.noteColor = noteColor;
    }
}
