package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.TextView;

import com.geckolabs.dao.NoteDB;
import com.geckolabs.dao.model.Note;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffe1a8")));

        ImageButton addNoteBtn = (ImageButton) findViewById(R.id.add_note_button);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.frag_container,new NewNoteFragment(),null).commit();
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.back_home_buttom);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton quizButton = (ImageButton) findViewById(R.id.quizes_button);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizNewSet.class);
                startActivity(intent);
            }
        });

        showNotes();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("newNoteNeeded", false)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frag_container, new NewNoteFragment(), null).commit();
        }
    }

    public void showNotes() {
        Log.d("LOG", "called");

        NoteDB db = new NoteDB(this);
        Note[] notes = db.getAll();
        Log.d("LOG", Integer.toString(notes.length));

        LinearLayout layout = (LinearLayout) findViewById(R.id.notes);
        for (Note note : notes) {
            Log.d("LOG", note.getTitle());
            TextView text = new TextView(this);
            text.setText(note.getTitle());
            text.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
            text.setBackgroundColor(Color.CYAN);

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ViewNoteActivity.class);
                    intent.putExtra("noteId", note.getId());
                    startActivity(intent);
                }
            });

            layout.addView(text);
        }
    }
}

