package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geckolabs.dao.QuizDAO;

import java.util.Locale;

public class WrittenAnsView extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 20000;

    TextView queDisplay;
    Button submit;
    EditText userAns;
    EditText correctAns;
    ImageButton editButton;
    TextView textViewCountDown;

    ColorStateList textColorDefaultCd;
    CountDownTimer countDownTimer;
    long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_written_ans_view);

        QuizDAO db = new QuizDAO(this);

        queDisplay = findViewById(R.id.viewQuestion);
        submit = findViewById(R.id.viewAns);
        userAns = findViewById(R.id.writtenAns);
        correctAns = findViewById(R.id.correctAns);
        editButton = findViewById(R.id.editAns);
        textViewCountDown = findViewById(R.id.test_view_countdown);

        textColorDefaultCd = textViewCountDown.getTextColors();

        Integer questionId;
        Intent intent = getIntent();
        questionId = intent.getIntExtra("queID",0);
        QuestionModel questionModel = db.getAQuizQuestion(questionId);

        queDisplay.setText(questionModel.getqText());

        AnswerModel answerModel = db.getAnswerForQuestion(questionId);
        Integer ansID = answerModel.getAnsID();

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                correctAns.setText(answerModel.getqAns());
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WrittenAnsView.this, EditQuizAns.class);
                intent.putExtra("answerID",ansID);
                startActivity(intent);
            }
        });
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();

            }
        }.start();
    }

    private  void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000)/60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d: %02d", minutes,seconds);
        textViewCountDown.setText(timeFormatted);
        if(timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}