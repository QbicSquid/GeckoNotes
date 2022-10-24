package com.geckolabs.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.geckolabs.dao.DBTest;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final int PICK_AUDIO = 1;
    Uri AudioUri;
    TextView select_Audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select_Audio = findViewById(R.id.textView);

        // SETTING ONCLICK LISTENER ------ ON TEXT VIEW CLICK TO TAKE AUDIO INPUT
        select_Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audio = new Intent();
                audio.setType("audio/*");
                audio.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(audio, "Select Audio"), PICK_AUDIO);

            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO && resultCode == RESULT_OK) {
            // Audio is Picked in format of URI
            AudioUri = data.getData();
            select_Audio.setText("Audio Selected");
            Log.d("Loguri", String.valueOf(AudioUri));
            Uri myUri = AudioUri; // initialize Uri here
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(getApplicationContext(), myUri);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // or just mediaPlayer.setDataSource(mFileName);
             // must call prepare first
            mediaPlayer.start(); // then start
        }
    }


//    public void addCourse(View view) {
//        DBTest db = new DBTest(this);
//        EditText input = (EditText) findViewById(R.id.courseInput);
//
//        db.addNewCourse(input.getText().toString());
//
//        String[] courses = db.getCourses();
//        for (String s : courses)
//            Log.d("LOGGG: ", s);
//    }

}