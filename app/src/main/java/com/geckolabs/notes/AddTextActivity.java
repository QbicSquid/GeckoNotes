package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geckolabs.dao.TextDB;

public class AddTextActivity extends AppCompatActivity {
    Button Save;
    EditText editTextTextMultiLine;
    Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        Save = findViewById(R.id.txtsavebtn);
        Back = findViewById(R.id.txtbkbtn);
        editTextTextMultiLine =  findViewById(R.id.editTextTextMultiLine);
        Save.setOnClickListener((v) -> {

                NoteModel noteModel;
                try{
                    noteModel = new NoteModel(-1, editTextTextMultiLine.getText().toString());
                    Toast.makeText(AddTextActivity.this, noteModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(AddTextActivity.this,"Error creating text", Toast.LENGTH_SHORT).show();
                    noteModel = new NoteModel(-1, "error" );
                }

                TextDB TextDB = new TextDB(AddTextActivity.this);
                boolean success = TextDB.addNewNote(noteModel);
                Toast.makeText(AddTextActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();
        });


    }
//    public void onclickButton(View view){
//        Intent in=new Intent(this,NoteActivity.class);
//        startActivity(in);
//    }
    public void onclickBtn(View view){
        Intent in=new Intent(this,NoteActivity.class);
        startActivity(in);
    }


}