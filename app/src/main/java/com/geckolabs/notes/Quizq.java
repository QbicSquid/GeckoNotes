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
    //RecyclerView qList;
    ImageButton deleteQuiz;
    ImageButton saveQuiz;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizq);

        QuizDAO db = new QuizDAO(this);

        saveQuiz = findViewById(R.id.save_quiz_Set);
        quizQ = findViewById(R.id.quizQ);
        deleteQuiz = findViewById(R.id.deleteQuiz);
        //qList = findViewById(R.id.qList);
        quizTitle = findViewById(R.id.question_set_title);

//        String quizTitleID;
//        Intent intent2 = getIntent();
//        quizTitleID = intent2.getStringExtra("quizTitleID");
//       // Log.d("Titles", quizTitleID);
//        QuizModel quizModel = db.getSingleQuizID(quizTitleID);
//
//        QuestionModel questionModel = db.getSingleQuizQuestions(quizModel.getQuizId());
//        Log.d("CheckforQuizId", String.valueOf(questionModel.getqId()));
//        Log.d("CHECKFORID", String.valueOf(quizModel.getQuizId()));
//        if (quizTitle == null){
//            quizTitle.setText(quizTitleID);
//        }


        //db.addNewQuiz(quizTitle.getText().toString());
        //addQuiz.setOnClickListener((v)-> startActivity(new Intent(QuizNewSet.this,Quizq.class)));

        quizQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long l = db.addNewQuiz(quizTitle.getText().toString());
                Intent intent = new Intent(Quizq.this, NewQuizQ.class);
                intent.putExtra("quizID", l);
                startActivity(intent);
            }
        });

//        LinearLayout layout = (LinearLayout) findViewById(R.id.quesList);
//        Button quiz = new Button(this);
//        quiz.setPadding(5, 5, 5, 5);
//        quiz.setTextSize(20);
//        quiz.setLayoutParams(new LinearLayout.LayoutParams(600, 100));
//        String questionId = String.valueOf(questionModel.getqId());
//        Log.d("checkkkk",questionId);
//        quiz.setText(questionId);
//        layout.addView(quiz);

        /*
        quizQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quizq.this, NewQuizQ.class));

            }
        });

         */

       // LinearLayout layout = (LinearLayout) findViewById(R.id.quesList);


//        String[] quesList = db.getQuesList();
//        for (String s : quesList) {
//            Button quiz = new Button(this);
//            quiz.setPadding(5, 5, 5, 5);
//            quiz.setTextSize(20);
//            quiz.setLayoutParams(new LinearLayout.LayoutParams(600, 100));
//            quiz.setText(s);
//            layout.addView(quiz);
//        }


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
