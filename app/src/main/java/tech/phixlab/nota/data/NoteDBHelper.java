package tech.phixlab.nota.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Note.db";


    public NoteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    private static final String SQL_CREATE_NOTES =
            "CREATE TABLE " + NoteContract.NoteEntry.TABLE_NAME + " (" +
                    NoteContract.NoteEntry._ID + " INTEGER PRIMARY KEY," +
                    NoteContract.NoteEntry.COLUMN_NAME_TITLE + " TEXT," +
                    NoteContract.NoteEntry.COLUMN_NAME_NOTE_BODY + " TEXT, " +
                    NoteContract.NoteEntry.COLUMN_NAME_NOTE_DATE + " TEXT)";

    private static final String SQL_DELETE_NOTES =
            "DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE_NAME;





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NOTES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_NOTES);
        onCreate(db);

    }


}
