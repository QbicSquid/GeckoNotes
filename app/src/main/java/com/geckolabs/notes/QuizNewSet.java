package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuizNewSet extends AppCompatActivity {

    FloatingActionButton addQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_new_set);

        addQuiz = findViewById(R.id.add_quiz);
        addQuiz.setOnClickListener((v)-> startActivity(new Intent(QuizNewSet.this,Quizq.class)));
    }
}