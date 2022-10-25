package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.geckolabs.dao.TextDB;

import java.util.List;

public class NoteActivity extends AppCompatActivity {


    //reference to buttons and other controls on the layout
    Button TextBox;
    Button List;
    Button Table;
    Button ViewAll;
    ListView lv_customerList;

    ArrayAdapter noteArrayAdapter;
    TextDB textDB;



//    Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextBox = findViewById(R.id.btntextbox);
        List = findViewById(R.id.btnlist);
        Table = findViewById(R.id.btntable);
        ViewAll = findViewById(R.id.btnviewall);
        lv_customerList = findViewById(R.id.lv_customerList);

        textDB = new TextDB(NoteActivity.this);

        noteArrayAdapter = new ArrayAdapter<NoteModel>(NoteActivity.this, android.R.layout.simple_list_item_1, textDB.getEveryone());
        lv_customerList.setAdapter(noteArrayAdapter);

        ViewAll.setOnClickListener((v) -> {

            TextDB textDB = new TextDB(NoteActivity.this);


            noteArrayAdapter = new ArrayAdapter<NoteModel>(NoteActivity.this, android.R.layout.simple_list_item_1, textDB.getEveryone());
            lv_customerList.setAdapter(noteArrayAdapter);
//            Toast.makeText(NoteActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoteModel clickedNote = (NoteModel) parent.getItemAtPosition(position);
                textDB.deleteOne(clickedNote);
                ShowNoteOnListView(textDB);
                Toast.makeText(NoteActivity.this, "Deleted" + clickedNote.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void ShowNoteOnListView(TextDB textDB) {
    }

    public void onclickButton(View view){
        Intent in=new Intent(this,AddTextActivity.class);
        startActivity(in);
    }
    public void onclickB(View view){
        Intent in=new Intent(this,AddListActivity.class);
        startActivity(in);
    }
    public void onclickBtn(View view){
        Intent in=new Intent(this,AddTableActivity.class);
        startActivity(in);
    }


}