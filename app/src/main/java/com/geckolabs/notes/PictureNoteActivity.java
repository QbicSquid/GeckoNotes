package com.geckolabs.notes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.geckolabs.dao.MediaNotesDB;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PictureNoteActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    int noteId;
    ImageView picView;
    Bitmap selectedImageBitmap;
    ImageButton photoButton;
    Uri selectedImageUri;

    EditText txtAddAudioTitle;
    EditText txtAddAudioDescription;
    Button btnAudioNoteDone;

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    ImageButton cameraBtn;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        noteId = getIntent().getIntExtra("noteId", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_note);

        picView = findViewById(R.id.picView);

        // Open Camera
        cameraBtn = findViewById(R.id.btnCamaraPic);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyPermissions();
            }
        });


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
//                db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString(),selectedImageUri.toString());
                db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString(),currentPhotoPath, noteId);
                Toast.makeText(PictureNoteActivity.this,"Picture Note Added",Toast.LENGTH_SHORT).show();
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
                        File imageFile = new File(String.valueOf(selectedImageUri));
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
//        db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString(),selectedImageUri.toString());
        db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString(),currentPhotoPath, noteId);

    }
//Line Breaker...............................
    private void verifyPermissions(){
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[2]) == PackageManager.PERMISSION_GRANTED){
            dispatchTakePictureIntent();
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    CAMERA_PERM_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera Permission is Required to Use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                File f = new File(currentPhotoPath);
                picView.setImageURI(Uri.fromFile(f));
                Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("PATH",currentPhotoPath);
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileproviderNote",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }


}