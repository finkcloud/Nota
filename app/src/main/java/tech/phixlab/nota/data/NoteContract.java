package tech.phixlab.nota.data;

import android.provider.BaseColumns;

public final class NoteContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private NoteContract() {}
    /* Inner class that defines the table contents */
    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_TITLE = "note_title";
        public static final String COLUMN_NAME_NOTE_BODY = "note_body";
        public static final String COLUMN_NAME_NOTE_DATE = "note_date";
    }
}
