package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geckolabs.dao.TextDB;

public class NoteActivity extends AppCompatActivity {


    //reference to buttons and other controls on the layout
    Button TextBox;
    Button List;
    Button Table;
//    Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextBox = findViewById(R.id.btntextbox);
        List = findViewById(R.id.btnlist);
        Table = findViewById(R.id.btntable);
//        Back = findViewById(R.id.h_btn1);

//        TextBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(NoteActivity.this, AddTextFragment.class));
//
//                AddTextFragment fragment = new AddTextFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.ConstraintLayout,fragment).commit();
//
//            }
//
//        });

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
//    public void onclickBt(View view){
//        Intent in=new Intent(this,AddTableActivity.class);
//        startActivity(in);
//    }
}