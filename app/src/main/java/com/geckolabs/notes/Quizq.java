package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quizq extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizq);

        btn = findViewById(R.id.quizQ);

        //addQuiz.setOnClickListener((v)-> startActivity(new Intent(QuizNewSet.this,Quizq.class)));
        btn.setOnClickListener((v)-> startActivity(new Intent(Quizq.this, NewQuizQ.class)));

//        btn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//
//                Fragment fragment = new RadiobtnQuiz();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container,fragment).commit();
//            }
//        });
    }
}