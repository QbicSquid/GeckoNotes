package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.geckolabs.dao.QuizDAO;

public class EditQuizAns extends AppCompatActivity {

    EditText edAnswer;
    ImageButton saveEditAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz_ans);

        QuizDAO db = new QuizDAO(this);

        edAnswer = findViewById(R.id.correctAns);
        saveEditAns = findViewById(R.id.saveAns);

        Integer answID;
        Intent intent = getIntent();
        answID = intent.getIntExtra("answerID", 0);


        AnswerModel answerModel = db.getQuestionAnswer(answID);
        edAnswer.setText(answerModel.getqAns());
        String testdbAnswer = answerModel.getqAns();

        saveEditAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerModel.setqAns(edAnswer.getText().toString());
                db.updateAns(answerModel);
                Intent intent = new Intent(EditQuizAns.this, QuizNewSet.class);
                startActivity(intent);
            }

        });
    }
}