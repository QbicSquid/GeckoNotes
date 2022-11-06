package com.geckolabs.notes;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.TextView;

import com.geckolabs.dao.NoteDB;
import com.geckolabs.dao.model.Note;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffe1a8")));

        showNotes();
    }

//    public void addCourse(View view) {
//        NoteDB db = new NoteDB(this);
//        EditText input = (EditText) findViewById(R.id.courseInput);
//
//        db.addNewCourse(input.getText().toString());
//
//        String[] courses = db.getCourses();
//        for (String s : courses)
//            Log.d("LOGGG: ", s);
//        Context c = view.getContext();
//    }

    public void showNotes() {
        NoteDB db = new NoteDB(this);
        Note[] notes = db.getAll();

        LinearLayout layout = (LinearLayout) findViewById(R.id.notes);
        for (Note note : notes) {
            TextView text = new TextView(this);
            text.setText(note.getTitle());
            text.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
            text.setBackgroundColor(Color.CYAN);
            layout.addView(text);
        }

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
    }

//    public void delDB(View view) {
//        this.deleteDatabase("coursedb");
//    }

//    public void addButton(View view) {
//        LinearLayout layout = (LinearLayout) findViewById(R.id.courses);
//
//        layout.removeAllViews();
//
//        NoteDB db = new NoteDB(this);
//        String[] courses = db.getCourses();
//        for (String s : courses) {
//            Button button = new Button(this);
//            button.setPadding(5, 5, 5, 5);
//            button.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
//            button.setText(s);
//            layout.addView(button);
//        }
//    }
}