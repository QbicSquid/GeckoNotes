package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geckolabs.dao.MediaNotesDB;
import com.geckolabs.dao.NoteDB;
import com.geckolabs.dao.model.Note;

public class ViewNoteActivity extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        // setup action bar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffe1a8")));

        ImageButton addNoteBtn = (ImageButton) findViewById(R.id.add_note_button);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("newNoteNeeded", true);
                startActivity(intent);
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

        // get note information
        noteId = getIntent().getIntExtra("noteId", 0);
        NoteDB noteDB = new NoteDB(this);
        Note note =  noteDB.getOne(noteId);
        TextView title = findViewById(R.id.note_name_view);
        title.setText(note.getTitle());

        // get note picture information
        MediaNotesDB mediaDB = new MediaNotesDB(this);
        PicNoteModel pic = mediaDB.getPicbyNoteId(noteId);
        if (pic != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putInt("noteId", noteId);
            viewPictureNote fragment = new viewPictureNote();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.view_note_picture_frame, fragment, null).commit();
        } else {
            // Add Picture
            LinearLayout btnContainer = findViewById(R.id.view_note_button_contianer);
            Button addPicButton = new Button(this);
            addPicButton.setText("Add Picture");
            addPicButton.setTextColor(Color.WHITE);
            addPicButton.setBackgroundResource(R.drawable.gecko_button1);
            btnContainer.addView(addPicButton);

            // Event Listener for adding a picture
            addPicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), PictureNoteActivity.class);
                    intent.putExtra("noteId", noteId);
                    startActivity(intent);
                }
            });

            Button deleteButton = new Button(getApplicationContext());
            deleteButton.setText("Delete Note");
            deleteButton.setTextColor(Color.WHITE);
            deleteButton.setBackgroundResource(R.drawable.gecko_button1);
            btnContainer.addView(deleteButton);

            // Event Listener for adding a picture
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NoteDB db = new NoteDB(getApplicationContext());
                    db.delete(noteId);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

        }
    }


}