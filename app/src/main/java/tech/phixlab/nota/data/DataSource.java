package tech.phixlab.nota.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tech.phixlab.nota.model.Note;

public class DataSource {
    private SQLiteDatabase database;
    private NoteDBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new NoteDBHelper(context);
    }

    public void openDb() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        dbHelper.close();
    }

    // save a note using the note object
    public boolean insertNote(Note note) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE, note.getTitle());
            initialValues.put(NoteContract.NoteEntry.COLUMN_NAME_NOTE_BODY, note.getBody());
            initialValues.put(NoteContract.NoteEntry.COLUMN_NAME_NOTE_DATE, note.getDateTime());
            didSucceed = database.insert(NoteContract.NoteEntry.TABLE_NAME, null, initialValues) > 0;
        } catch (Exception e) {
            //Do nothing -will return false if there is an exception //5
        }
        return didSucceed;
    }

    // delete note usng its id
    public boolean deleteNote(int noteId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete(NoteContract.NoteEntry.TABLE_NAME,
                    NoteContract.NoteEntry._ID + "=" + noteId, null) > 0;
        } catch (Exception e) {
            //Do nothing -return value already set to false
        }
        return didDelete;
    }



    // go through all notes and add it to an arraylist
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        try {

            String query = "SELECT * FROM "
                    + NoteContract.NoteEntry.TABLE_NAME
                    + " ORDER BY " +  NoteContract.NoteEntry._ID
                    + " DESC LIMIT 20";


            Cursor cursor = database.rawQuery(query, null);
            Note newNote;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newNote = new Note();
                newNote.setId(cursor.getInt(0));
                newNote.setTitle(cursor.getString(1));
                newNote.setBody(cursor.getString(2));
                newNote.setDateTime(cursor.getString(3));
                noteArrayList.add(newNote);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            noteArrayList = new ArrayList<>();
        }
        return noteArrayList;
    }


    // RETURNS LAST INSERTED NOTE ID
    public int getLastNoteId() {
        int lastId;
        try {
            String query = "Select MAX(note_id) from " + NoteContract.NoteEntry.TABLE_NAME;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }


}
