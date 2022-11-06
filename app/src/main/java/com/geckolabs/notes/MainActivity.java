package com.geckolabs.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.TextView;

import com.geckolabs.dao.MediaNotesDB;
import com.geckolabs.dao.NoteDB;
import com.geckolabs.dao.model.Note;

import org.w3c.dom.Text;

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

//        Intent intent = new Intent(this, MediaTestingActivity.class);
//        intent.putExtra("noteId", 3);
//        startActivity(intent);
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
        MediaNotesDB mdb = new MediaNotesDB(this);
        Note[] notes = db.getAll();

//        db.insert(new Note("Note title", "some group", Color.CYAN));

        LinearLayout layout = (LinearLayout) findViewById(R.id.notes);

        // -----------------------------------------------------------------------------------------
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.fragment_view_picture_note, layout, true);

        ImageView imgView = findViewById(R.id.imgView);
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtDescription = findViewById(R.id.txtDescription);

        Bitmap selectedImageBitmap = null;
        Uri selectedImageUri;
        PicNoteModel picNoteModel= mdb.getSinglePicNote(3);
        String stringImageUri = "file://"+picNoteModel.getMediaPath();
        selectedImageUri= Uri.parse(stringImageUri);
//        IVPreviewImage.setImageURI(selectedImageUri);
        try {
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getApplicationContext().getContentResolver(), selectedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setImageBitmap(selectedImageBitmap);
        txtTitle.setText(picNoteModel.getTitle());
        txtDescription.setText(picNoteModel.getDescription());

        ImageView imgBtnUpdate = findViewById(R.id.imgBtnUpdate);
        ImageView imgBtnDelete = findViewById(R.id.imgBtnDelete);

        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log1","in On click in delete");
                int picId = picNoteModel.getId();
                mdb.deletePicNote(picId);

            }
        });

        //Update a pic Note
        imgBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("after",picNoteModel.getDescription());
                mdb.updateSinglePicNote(picNoteModel);
                Log.d("Log1","in On click in update");
                Intent intent = new Intent(getApplicationContext(), UpdatePicNoteActivity.class);
                intent.putExtra("id",picNoteModel.getId());
                startActivity(intent);

            }
        });
        // -----------------------------------------------------------------------------------------

        for(int i = 0; i < 30; i++)
        for (Note note : notes) {
            TextView text = new TextView(this);
            text.setText(note.getTitle());
            text.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
            text.setBackgroundColor(Color.CYAN);
            layout.addView(text);
        }


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