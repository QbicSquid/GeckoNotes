package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
    //RecyclerView qList;
    ImageButton saveQuiz;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizq);

        QuizDAO db = new QuizDAO(this);

        saveQuiz = findViewById(R.id.save_quiz_Set);
        quizQ = findViewById(R.id.quizQ);
        //qList = findViewById(R.id.qList);
        quizTitle = findViewById(R.id.question_set_title);

        //db.addNewQuiz(quizTitle.getText().toString());

        //addQuiz.setOnClickListener((v)-> startActivity(new Intent(QuizNewSet.this,Quizq.class)));

        quizQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long l = db.addNewQuiz(quizTitle.getText().toString());
                Intent intent = new Intent(Quizq.this, NewQuizQ.class);
                intent.putExtra("quizID",l);
                startActivity(intent);
            }
        });


        quizQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quizq.this, NewQuizQ.class));

            }
        });

        LinearLayout layout = (LinearLayout) findViewById(R.id.quesList);

        String[] quesList = db.getQuesList();
        for(String s : quesList){
            TextView quiz = new TextView(this);
            quiz.setPaddingRelative(5,5,5,5);
            quiz.setLayoutParams(new LinearLayout.LayoutParams(600,100));
            quiz.setText(s);
            layout.addView(quiz);



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




}