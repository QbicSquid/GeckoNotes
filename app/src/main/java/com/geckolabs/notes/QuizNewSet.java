package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geckolabs.dao.QuizDAO;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuizNewSet extends AppCompatActivity {

    FloatingActionButton addQuiz;
    ImageButton saveQuizSet;
    TextView quizSetTitle;
    //RecyclerView quizList;
    //QuizAdapter quizAdapter;
    // RecyclerView.LayoutManager layoutManager;
    //QuizList<Quiz> allQuiz = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_new_set);


        addQuiz = findViewById(R.id.add_quiz);
        saveQuizSet = findViewById(R.id.save_quiz_Set);
        quizSetTitle = findViewById(R.id.quiz_set_title);
        //quizList = findViewById(R.id.quizList);

        addQuiz.setOnClickListener((v) -> startActivity(new Intent(QuizNewSet.this, Quizq.class)));
        //saveQuizSet.setOnClickListener((v) -> saveQuiz());
        setupRecyclerVew();

        LinearLayout layout = (LinearLayout) findViewById(R.id.quizList);

        QuizDAO db = new QuizDAO(this);
        String[] quizList = db.getQuizList();
        for (String s : quizList) {
            Button quiz = new Button(this);
            quiz.setPadding(5, 5, 5, 5);
            quiz.setTextSize(20);
            quiz.setLayoutParams(new LinearLayout.LayoutParams(600, 100));
            quiz.setText(s);
            layout.addView(quiz);
            quiz.setOnClickListener((v) -> directQuestions(s));

        }


    }

    private void directQuestions(String s) {
        Intent intent = new Intent(QuizNewSet.this, QuestionSetView.class);
        intent.putExtra("quizTitleID", s);
        startActivity(intent);

    }

    private void setupRecyclerVew() {

    }

    private void saveQuiz() {
        //save data
    }


}