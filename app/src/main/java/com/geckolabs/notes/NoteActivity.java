package com.geckolabs.notes;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.geckolabs.dao.TextDB;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    //reference to buttons and other controls on the layout
    Button TextBox;
    Button List;
    Button Table;
    Button ViewAll;
    ListView lv_customerList;
    SearchView searchView;

    ArrayList<String> listItem;
    ArrayAdapter noteArrayAdapter;
    static TextDB textDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextBox = findViewById(R.id.btntextbox);
        ViewAll = findViewById(R.id.btnviewall);
        lv_customerList = findViewById(R.id.lv_customerList);
        searchView = findViewById(R.id.search);
        textDB = new TextDB(NoteActivity.this);

        listItem = new ArrayList<>();
        viewData();
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String text=lv_customerList.getItemAtPosition(i).toString();
                Toast.makeText(NoteActivity.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });

//........................................................................................................................................................
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String text = lv_customerList.getAdapter().getItem(position).toString();
                Intent i = new Intent(NoteActivity.this, EditNoteActivity.class);

                i.putExtra("text", text);
                Log.d("text", text);
                startActivity(i);
                lv_customerList.setSelector(R.color.teal_700);

            }
        });
    }

    private void viewData() {
        Cursor cursor = textDB.viewData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"no data to show",Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
//                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
            }
            noteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
            lv_customerList.setAdapter(noteArrayAdapter);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                noteArrayAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String n) {
                noteArrayAdapter.getFilter().filter(n);
                return false;
            }
        });

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