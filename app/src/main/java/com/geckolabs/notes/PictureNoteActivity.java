package com.geckolabs.notes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.geckolabs.dao.MediaNotesDB;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class PictureNoteActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView picView;
    Bitmap selectedImageBitmap;
    ImageButton photoButton;
    Uri selectedImageUri;

    EditText txtAddAudioTitle;
    EditText txtAddAudioDescription;
    Button btnAudioNoteDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_note);

        picView = findViewById(R.id.picView);
        photoButton = findViewById(R.id.btnTakePic);
        photoButton.setOnClickListener(new  View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imageChooser();
//                dispatchTakePictureIntent();
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        MediaNotesDB db = new MediaNotesDB(this);
        txtAddAudioTitle = findViewById(R.id.txtAddAudioTitle);
        txtAddAudioDescription = findViewById(R.id.txtAddAudioDescription);
        btnAudioNoteDone = findViewById(R.id.btnAudioNoteDone);
//        db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString());

        btnAudioNoteDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log1","in On click");
                db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString(),selectedImageUri.toString());
            }
        });
    }
    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        selectedImageUri = data.getData();
                        Log.d("LogUri", String.valueOf(selectedImageUri));

                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        picView.setImageBitmap(selectedImageBitmap);
                    }
                }
            });

    public void addImageNote(View view) {
        MediaNotesDB db = new MediaNotesDB(this);
        txtAddAudioTitle = view.findViewById(R.id.txtAddAudioTitle);
        txtAddAudioDescription = view.findViewById(R.id.txtAddAudioDescription);
        btnAudioNoteDone = view.findViewById(R.id.btnAudioNoteDone);
        db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString(),selectedImageUri.toString());

    }
//line breaker......................
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            picView.setImageBitmap(imageBitmap);
//            galleryAddPic();
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        File storageDir = Environment.getExternalStorageDirectory();
        File image = File.createTempFile(
                "example",  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }



}