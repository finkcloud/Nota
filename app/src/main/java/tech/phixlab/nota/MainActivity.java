package tech.phixlab.nota;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import tech.phixlab.nota.data.DataSource;
import tech.phixlab.nota.data.NoteAdapter;
import tech.phixlab.nota.model.Note;

public class MainActivity extends AppCompatActivity {

    // get the reference of RecyclerView
    RecyclerView recyclerView;
    NoteAdapter musicAdapter;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // load noted from db
        loadNote();

        FloatingActionButton fab = findViewById(R.id.fabSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void loadNote(){

        //  get access to db with current context
        DataSource datasource = new DataSource(getApplicationContext());

        // open db
        datasource.openDb();

        // load all notes into list
        final ArrayList<Note> notesList = datasource.getAllNotes();

        // close db after all
        datasource.closeDb();


        if(notesList.size() > 0){
            // find recyclerview
            recyclerView =  findViewById(R.id.note_list);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

            // set a LinearLayoutManager with default vertical orientation  and 1 column
            linearLayoutManager = new LinearLayoutManager(this);

            // set list to layout manager
            recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

            //check if music adapter is empty
            if(musicAdapter == null){
                musicAdapter = new NoteAdapter(MainActivity.this,  notesList);

                recyclerView.setAdapter(musicAdapter); // sets adapter to hold the image

            }
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
