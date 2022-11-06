package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

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


public class QuestionSetView extends AppCompatActivity {

    FloatingActionButton quizQ;
    TextView quizTitle;
    ImageButton saveQuiz;
    ImageButton deleteQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_set_view);

        QuizDAO db = new QuizDAO(this);

        saveQuiz = findViewById(R.id.save_quiz_Set);
        quizQ = findViewById(R.id.add_quiz);
        quizTitle = findViewById(R.id.quiz_set_title);
        deleteQuiz = findViewById(R.id.deleteBtn);

        String quizTitleID;
        Intent intent2 = getIntent();
        //quizTitleID = quizTitle
        quizTitleID = intent2.getStringExtra("quizTitleID");
        QuizModel quizModel = db.getSingleQuizID(quizTitleID);

        QuestionModel questionModel = db.getSingleQuizQuestions(quizModel.getQuizId());

        Integer quizId = Integer.valueOf(quizModel.getQuizId());

        quizTitle.setText(quizTitleID);

        LinearLayout layout = (LinearLayout) findViewById(R.id.quizList);
        Button quiz = new Button(this);
        quiz.setPadding(5, 5, 10, 5);
        quiz.setTextSize(20);
        quiz.setLayoutParams(new LinearLayout.LayoutParams(600, 100));
        String questionId = String.valueOf(questionModel.getqId());
        quiz.setText(questionId);
        layout.addView(quiz);
        Integer queId = questionModel.getqId();
        quiz.setOnClickListener((v) -> directQuestions(queId));

        AnswerModel answerModel = db.getAnswerForQuestion(queId);
        Integer ansID = Integer.valueOf(answerModel.getAnsID());

        Integer ansId;

        deleteQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteQuizDB(quizId, queId, ansID);
                Intent intent = new Intent(QuestionSetView.this, QuizNewSet.class);
                startActivity(intent);
            }

        });

    }

    private void directQuestions(Integer queId) {
        Intent intent = new Intent(QuestionSetView.this, WrittenAnsView.class);
        intent.putExtra("queID", queId);
        startActivity(intent);
    }
}