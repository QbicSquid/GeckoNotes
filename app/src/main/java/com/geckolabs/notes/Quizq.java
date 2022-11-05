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


public class Quizq extends AppCompatActivity {

    FloatingActionButton quizQ;
    TextView quizTitle;
    ImageButton saveQuiz;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizq);

        QuizDAO db = new QuizDAO(this);

        saveQuiz = findViewById(R.id.save_quiz_Set);
        quizQ = findViewById(R.id.quizQ);
        quizTitle = findViewById(R.id.question_set_title);


        quizQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long l = db.addNewQuiz(quizTitle.getText().toString());
                Intent intent = new Intent(Quizq.this, NewQuizQ.class);
                intent.putExtra("quizID", l);
                startActivity(intent);
            }
        });

    }

}
