package tech.phixlab.nota;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteDetail extends AppCompatActivity {

    String noteTitle, noteBody, noteDateTime;
    TextView tvTitle, tvBody, tvDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //set up ui data
        setupUI();


        // init intent data
        Bundle intent = getIntent().getExtras();

        // get data from intent
        noteTitle  = intent.getString("title");
        noteBody  = intent.getString("body");
        noteDateTime  = intent.getString("date");


        // set data to ui view objects
        tvTitle.setText(noteTitle);
        tvBody.setText(noteBody);
        tvDateTime.setText(noteDateTime);




        FloatingActionButton fab = findViewById(R.id.fabShare);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareText = "";

                if(!noteTitle.equals("") && !noteBody.equals("") && !noteDateTime.equals("")){
                    shareText = noteTitle
                            + " \n "+ noteDateTime
                            + " \n \n \n " + noteBody;
                }

                //Convert file path into Uri for sharing
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/*");
                intent.putExtra(android.content.Intent.EXTRA_TEXT,shareText  );
                startActivity(Intent.createChooser(intent, "Share Via"));

            }
        });

    }

    private void  setupUI(){

        // get ui reference
        tvTitle = findViewById(R.id.note_detail_title);
        tvBody = findViewById(R.id.note_detail_body);
        tvDateTime = findViewById(R.id.date);

    }

}
