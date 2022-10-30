package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MediaTestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_testing);

        Button btnAddPicNote;
        btnAddPicNote=findViewById(R.id.btnAddPicNote);
        btnAddPicNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaTestingActivity.this, PictureNoteActivity.class);
                startActivity(intent);
            }
        });

        Button btnShowPicNote;
        EditText picNoteId;
        btnShowPicNote = findViewById(R.id.btnShowPicNote);
        picNoteId = findViewById(R.id.editTextNumber);
        btnShowPicNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaTestingActivity.this, MediaNotesActivity.class);
                startActivity(intent);
            }
        });


    }
}