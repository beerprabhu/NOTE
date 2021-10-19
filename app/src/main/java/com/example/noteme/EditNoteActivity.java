package com.example.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class EditNoteActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitles, noteDetails,noteid;
    String todaysdate;
    String currentTime;
    Calendar c;
    private Note note;
   private NoteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("New Note");
        Intent i = getIntent();
        Log.i("IDa",i+"");
        long id = i.getLongExtra("ID",0);

         db = new NoteDatabase(this);
        note = db.getNote(id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(note.getTitle());
        noteid = findViewById(R.id.noteid);
        noteTitles = findViewById(R.id.noteTitles);
        noteDetails = findViewById(R.id.noteDetails);
        noteTitles.setText(note.getTitle());
        noteDetails.setText(note.getContent());
        noteid.setText(String.valueOf(note.getID()));
        noteTitles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get current date and time

        c = Calendar.getInstance();
        todaysdate = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
        Log.d("Date", "Date and Time: " + todaysdate + " and " + currentTime);

    }

    private String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.delete:

                Toast.makeText(this, "Delete btn", Toast.LENGTH_SHORT).show();
            case R.id.save:

                if (noteTitles.getText().length() != 0) {
                    note.setTitle(noteTitles.getText().toString());
                    note.setContent(noteDetails.getText().toString());

                    note.setDate(todaysdate);
                    note.setTime(currentTime);
                    int id = db.editNote(note);
                    if (id == note.getID()) {
                        Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Error on updateing", Toast.LENGTH_SHORT).show();

                    }

                    startActivity(new Intent(this, MainActivity.class));
                }
            default:
                return super.onOptionsItemSelected(item);

//            if(item.getItemId() == R.id.delete) {
//            Toast.makeText(this, "Delete btn", Toast.LENGTH_SHORT).show();
//        }
//        if(item.getItemId() == R.id.save) {
//            if(noteTitles.getText().length() !=0)
//            {
//                note.setTitle(noteTitles.getText().toString());
//                note.setContent(noteDetails.getText().toString());
//
//                        note.setDate(todaysdate);
//                        note.setTime(currentTime);
//                int id = db.editNote(note);
//                if(id==note.getID())
//                {
//                    Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
//
//                }
//                else
//                {
//                    Toast.makeText(this, "Error on updateing", Toast.LENGTH_SHORT).show();
//
//                }
//
//                startActivity(new Intent(this,MainActivity.class));
//            }
//
//        }
        }
    }
    }
