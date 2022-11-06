package com.geckolabs.notes;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.geckolabs.dao.TextDB;

import org.w3c.dom.Text;

public class EditNoteActivity extends AppCompatActivity {

     Button Update,Delete;
     EditText text;
     TextDB textDB;
     NoteModel model;

    AlertDialog.Builder builder;
//     Integer id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Update = findViewById(R.id.editsavebtn);
        Delete = findViewById(R.id.editdelbtn);
        textDB = new TextDB(this);
        text = findViewById(R.id.editTextTextMultiLine4);


//        id= Integer.valueOf((getIntent().getExtras().getString("id")));

        text.setText(getIntent().getStringExtra("text"));
        model = new NoteModel(5,"sample");

        Update.setOnClickListener((v) -> {
//            model.setId(id);
            model.setText(text.getText().toString());

            Log.d("text",model.getText());
            TextDB TextDB = new TextDB(EditNoteActivity.this);
            boolean success = TextDB.updateOne(model);
            Toast.makeText(EditNoteActivity.this, "Successfully updated" + success, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(EditNoteActivity.this, NoteActivity.class);
            startActivity(i);

        });


        Delete.setOnClickListener((v) -> {
            TextDB TextDB = new TextDB(EditNoteActivity.this);
            model.setText(model.getText());
            TextDB.DeleteOne(model);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditNoteActivity.this);
            builder1.setMessage("Do you want to delete this entry.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent i = new Intent(EditNoteActivity.this, NoteActivity.class);
                            startActivity(i);
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent i = new Intent(EditNoteActivity.this, NoteActivity.class);
                            startActivity(i);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();



        });


    }
}
