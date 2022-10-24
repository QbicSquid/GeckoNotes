package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddListActivity extends AppCompatActivity {
    Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Back = findViewById(R.id.listbkbtn);
    }
    public void onclickButton(View view){
        Intent in=new Intent(this,NoteActivity.class);
        startActivity(in);
    }
}