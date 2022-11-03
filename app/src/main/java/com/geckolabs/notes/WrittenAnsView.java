package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geckolabs.dao.QuizDAO;

public class WrittenAnsView extends AppCompatActivity {

    TextView queDisplay;
    Button submit;
    EditText ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_written_ans_view);

        QuizDAO db = new QuizDAO(this);

        queDisplay = findViewById(R.id.textView2);
        submit = findViewById(R.id.button);
        //ans = findViewById(R.id.EnterAns);

        Integer questionId;
        Intent intent = getIntent();
        questionId = intent.getIntExtra("queID",0);
        QuestionModel questionModel = db.getAQuizQuestion(questionId);
        Log.d("Question Id", String.valueOf(questionId));
        Log.d("QuestionType", questionModel.getqType());
        Log.d("Question", questionModel.getqText());
        //AnswerModel answerModel = db.getQuestionAnswer(questionId);

        queDisplay.setText(questionModel.getqText());


    }
}