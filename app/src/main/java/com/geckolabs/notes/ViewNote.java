package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geckolabs.dao.NoteDB;
import com.geckolabs.dao.model.Note;

public class ViewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

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

        int noteId = this.getIntent().getIntExtra("noteId" , 0);
        NoteDB noteDB = new NoteDB(this);
        Note note =  noteDB.getOne(noteId);

        TextView title = findViewById(R.id.note_name_view);

        title.setText(note.getTitle());
    }


}