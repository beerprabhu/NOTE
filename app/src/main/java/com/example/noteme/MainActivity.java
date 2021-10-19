package com.example.noteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.noteme.AddNote;
import com.example.noteme.Note;
import com.example.noteme.adapter.NoteAdapter;
import com.example.noteme.NoteDatabase;
import com.example.noteme.R;
import com.example.noteme.adapter.OtherNoteAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView,others;
    private List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NoteDatabase db = new NoteDatabase(this);

        notes = db.getNotes();


    recyclerView = findViewById(R.id.listofnotes);
    recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes);
    recyclerView.setAdapter(adapter);

others = findViewById(R.id.others);
        others.setLayoutManager(new GridLayoutManager(this,2));
        OtherNoteAdapter adapter1 = new OtherNoteAdapter(this,notes);
        others.setAdapter(adapter1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add)
        {
            Intent intent = new Intent(this, AddNote.class);
            startActivity(intent);

            Toast.makeText(this, "Add button is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}