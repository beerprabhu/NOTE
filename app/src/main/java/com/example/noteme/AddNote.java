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

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitles, noteDetails,notepin;
    String todaysdate;
    String currentTime;
    Calendar c;
    String pin = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noteTitles = findViewById(R.id.noteTitles);
        noteDetails = findViewById(R.id.noteDetails);
        notepin = findViewById(R.id.notepin);
        notepin.setText(pin);
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
        if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Delete btn", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.pin) {

            pin = "1";
            Toast.makeText(this, pin, Toast.LENGTH_SHORT).show();
        }


        if (item.getItemId() == R.id.save) {
            Toast.makeText(this, pin, Toast.LENGTH_SHORT).show();

            if (pin.equals("1")) {
                Toast.makeText(this, pin, Toast.LENGTH_SHORT).show();

                Note note = new Note(noteTitles.getText().toString(), noteDetails.getText().toString(), todaysdate, currentTime, "1");
                NoteDatabase db = new NoteDatabase(this);
                db.addNote(note);
                Toast.makeText(this, "Save btn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, pin, Toast.LENGTH_SHORT).show();

                Note note = new Note(noteTitles.getText().toString(), noteDetails.getText().toString(), todaysdate, currentTime, "0");
                NoteDatabase db = new NoteDatabase(this);
                db.addNote(note);
                Toast.makeText(this, "Save btn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }

        }
        return super.onOptionsItemSelected(item);
    }
    }


