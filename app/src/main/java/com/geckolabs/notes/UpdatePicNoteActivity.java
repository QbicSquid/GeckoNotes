package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.geckolabs.dao.MediaNotesDB;

import java.io.IOException;

public class UpdatePicNoteActivity extends AppCompatActivity {
    EditText txtTitle;
    EditText txtDescription;
    ImageView imgView;
    Button BtnSaveUpdate;
    Integer picNoteId;

    Bitmap selectedImageBitmap;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pic_note);

        MediaNotesDB db = new MediaNotesDB(this);
        BtnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription =findViewById(R.id.txtDescription);
        imgView = findViewById(R.id.imgView);
        picNoteId = 15;

        //Get Details From DB And Display
        PicNoteModel picNoteModel= db.getSinglePicNote(picNoteId);
//        Log.d("des",picNoteModel.getDescription());
//        Log.d("title",picNoteModel.getTitle());
//        Log.d("path",picNoteModel.getMediaPath());
        String stringImageUri = "file://"+picNoteModel.getMediaPath();
        selectedImageUri= Uri.parse(stringImageUri);
//        IVPreviewImage.setImageURI(selectedImageUri);
        try {
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setImageBitmap(selectedImageBitmap);
        txtTitle.setText(picNoteModel.getTitle());
        txtDescription.setText(picNoteModel.getDescription());

        //Update a pic Note
        BtnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("before",picNoteModel.getDescription());
//                String test = "test";
                picNoteModel.setDescription(txtDescription.getText().toString());
                picNoteModel.setTitle(txtTitle.getText().toString());
                Log.d("after",picNoteModel.getDescription());
                db.updateSinglePicNote(picNoteModel);
                Log.d("Log1","in On click in update");
//                finish();

            }
        });
    }
}