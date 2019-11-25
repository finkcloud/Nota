package tech.phixlab.nota.data;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.phixlab.nota.NoteDetail;
import tech.phixlab.nota.R;
import tech.phixlab.nota.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context mContext;
    private ArrayList<Note> mNoteList;
    AlertDialog.Builder builder;


    public NoteAdapter(Context context, ArrayList<Note> notelist){
        this.mContext = context;
        this.mNoteList = notelist;

    }


    @Override
    public NoteViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        // inflate the recycler view with row item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder( NoteViewHolder holder,final int position) {
        // get refence to the list of object
        Note note = mNoteList.get(position);

        //set its data
        holder.noteTitleTv.setText(trimTtile(note.getTitle(), 23));
        holder.noteBodyTv.setText(trimTtile(note.getBody(), 50));
        holder.noteTitleDrop.setText("" + getFirstChar(note.getTitle()));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open another activity on item click
                Intent intent = new Intent(mContext, NoteDetail.class);
                intent.putExtra("title", mNoteList.get(position).getTitle()); // put title data in Intent
                intent.putExtra("body", mNoteList.get(position).getBody()); // put body data in Intent
                intent.putExtra("date", mNoteList.get(position).getDateTime()); // put datetime data in Intent
                mContext.startActivity(intent); // start Intent
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DeleteNoteDialog("Hey watch out", "Are you sure you want to delete this note",
                        true,"Delete", "Cancel", position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }


    // show no interne alert
    private void DeleteNoteDialog(String title, String msg, boolean cancelable, String posBtn, String negBtn, final int position) {
        //Setting message manually and performing action on button click
        builder = new AlertDialog.Builder(mContext); // alert
        builder.setMessage(msg)
                .setTitle(title)
                .setCancelable(cancelable)
                .setPositiveButton(posBtn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mNoteList.remove(position);
                        deleteNote(position);

                    }
                })
                .setNegativeButton(negBtn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                       dialog.dismiss();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.show();
    }


    // delete note using id
    private boolean deleteNote(int i){

        // Initializing db with access using current context
        DataSource ds = new DataSource(mContext);

        // open db for writing
        ds.openDb();

       return ds.deleteNote(i);
    }



    private String trimTtile(String title, int limit){
        if (title.length() > limit){
            String newTitle = title.substring(0, limit);
            return  newTitle + " ...";
        }
        return title;
    }


    private char getFirstChar(String title){

        char first = title.charAt(0);
        return first;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitleTv;
        TextView noteBodyTv;
        TextView noteTitleDrop;


        public NoteViewHolder( View itemView) {
            super(itemView);
            noteTitleTv = itemView.findViewById(R.id.note_title);
            noteBodyTv = itemView.findViewById(R.id.note_body);
            noteTitleDrop = itemView.findViewById(R.id.title_drop);
        }
    }
}
