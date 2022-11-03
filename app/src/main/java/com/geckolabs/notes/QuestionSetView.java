package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geckolabs.dao.QuizDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class QuestionSetView extends AppCompatActivity {

    FloatingActionButton quizQ;
    TextView quizTitle;
    ImageButton saveQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizq);

        QuizDAO db = new QuizDAO(this);

        saveQuiz = findViewById(R.id.save_quiz_Set);
        quizQ = findViewById(R.id.quizQ);
        quizTitle = findViewById(R.id.question_set_title);

        String quizTitleID;
        Intent intent2 = getIntent();
        quizTitleID = intent2.getStringExtra("quizTitleID");
        Log.d("Titles", quizTitleID);
        QuizModel quizModel = db.getSingleQuizID(quizTitleID);

        QuestionModel questionModel = db.getSingleQuizQuestions(quizModel.getQuizId());
        Log.d("CheckforQuizId", String.valueOf(questionModel.getqId()));
        Log.d("CHECKFORID", String.valueOf(quizModel.getQuizId()));

        quizTitle.setText(quizTitleID);

        LinearLayout layout = (LinearLayout) findViewById(R.id.quesList);
        Button quiz = new Button(this);
        quiz.setPadding(5, 5, 5, 5);
        quiz.setTextSize(20);
        quiz.setLayoutParams(new LinearLayout.LayoutParams(600, 100));
        String questionId = String.valueOf(questionModel.getqId());
        Log.d("checkkkk",questionId);
        quiz.setText(questionId);
        layout.addView(quiz);
        Integer queId = questionModel.getqId();
        quiz.setOnClickListener((v) -> directQuestions(queId));

    }

    private void directQuestions(Integer queId) {
        Intent intent = new Intent(QuestionSetView.this, WrittenAnsView.class);
        intent.putExtra("queID", queId);
        Log.d("PASS que ID", String.valueOf(queId));
        startActivity(intent);
    }
}