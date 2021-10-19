package com.example.noteme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.noteme.databinding.ActivityDetailNoteBinding;

public class DetailNoteActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDetailNoteBinding binding;
private TextView mDetails;
    NoteDatabase db;
Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
mDetails = findViewById(R.id.detailsoffNotes);
        Intent i = getIntent();
        Log.i("IDa",i+"");
        long id = i.getLongExtra("ID",0);

      db= new NoteDatabase(this);
         note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());

        mDetails .setText(note.getContent());
        Toast.makeText(this,"ID ->"+id, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(DetailNoteActivity.this).create();
                alertDialog.setTitle("Delete Note");
                alertDialog.setMessage("Are you sure to delete this Note");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteNote(note.getID());
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                Toast.makeText(DetailNoteActivity.this,"clicked",Toast.LENGTH_SHORT).show();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.edit_text:
                Toast.makeText(this, "Edit btn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, EditNoteActivity.class);
                intent.putExtra("ID", note.getID());
                startActivity(intent);
            case R.id.share:
                Toast.makeText(this, "share btn", Toast.LENGTH_SHORT).show();
                Intent email = new Intent(Intent.ACTION_SEND);
                String[] TO = {""};
                email.putExtra(Intent.EXTRA_EMAIL, TO);
                email.putExtra(Intent.EXTRA_SUBJECT, note.getTitle());
                email.putExtra(Intent.EXTRA_TEXT, note.getContent());

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

//        if(item.getItemId() == R.id.edit_text) {
////
//            Toast.makeText(this, "Edit btn", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this,EditNoteActivity.class);
//            intent.putExtra("ID",note.getID());
//            startActivity(intent);
//
//        }
            default:
                return super.onOptionsItemSelected(item);
        }}
}