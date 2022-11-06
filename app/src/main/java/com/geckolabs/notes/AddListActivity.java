package com.geckolabs.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geckolabs.dao.ListDB;
import com.geckolabs.dao.TextDB;

public class AddListActivity extends AppCompatActivity {
    Button Back, Delete, Update, Save, view;

    EditText list;
    ListDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Back = findViewById(R.id.listbkbtn);
        Save = findViewById(R.id.listsavebtn);
        Update = findViewById(R.id.listupbtn);
        Delete = findViewById(R.id.listdelbtn);
        view = findViewById(R.id.listviewbtn);
        list =  findViewById(R.id.editTextTextMultiLine2);

        DB = new ListDB(this);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String LIST = list.getText().toString();

                Boolean checkinsertdata = DB.insertlist(LIST);

                if(checkinsertdata==true)
                    Toast.makeText(AddListActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddListActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String LIST = list.getText().toString();


                Boolean checkupdatedata = DB.updatelist(LIST);
                Log.d("update list", LIST);

                if(checkupdatedata==true)
                    Toast.makeText(AddListActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddListActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String LIST = list.getText().toString();

                Boolean checkudeletedata = DB.deletedata(LIST);
                if(checkudeletedata==true)
                    Toast.makeText(AddListActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddListActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AddListActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){

                    buffer.append("List :"+res.getString(1)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AddListActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }


    public void onclickButton(View view){
        Intent in=new Intent(this,NoteActivity.class);
        startActivity(in);
    }
}