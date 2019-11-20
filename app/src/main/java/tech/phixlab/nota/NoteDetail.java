package tech.phixlab.nota;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

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
