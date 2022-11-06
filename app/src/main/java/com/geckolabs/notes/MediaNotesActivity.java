package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MediaNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_notes);

        if(findViewById(R.id.Container)!=null)
        {
            if(savedInstanceState!=null){
                return;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.Container,new viewPictureNote(),null).commit();
        }
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        if(findViewById(R.id.Container)!=null)
//        {
//            Log.d("InAcc","check");
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().add(R.id.Container,new viewPictureNote(),null).commit();
//        }
//
//    }
}