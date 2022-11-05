package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.geckolabs.dao.QuizDAO;

public class NewQuizQ extends AppCompatActivity {

    CheckboxAns checkboxAnsAction;
    RadiobtnAns radiobtnAnsAction;
    WrittenAns writtenAnsAction;

    EditText question;
    ImageButton btn;
    Button addQue;

    String[] items = {"Checkbox", "Radio", "Written"};

    AutoCompleteTextView autoCompleteTxt;

    Bundle bundle = new Bundle();

    //final long[] questionID = {0};
    long questions = 0;
    long queId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_q);

        QuizDAO db = new QuizDAO(this);

        question = findViewById(R.id.question);
        btn = findViewById(R.id.saveWrittenAns);
        addQue = findViewById(R.id.savequestion);


        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        checkboxAnsAction = new CheckboxAns();
        radiobtnAnsAction = new RadiobtnAns();
        writtenAnsAction = new WrittenAns();

        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, R.layout.select_type, items);
        autoCompleteTxt.setAdapter(adapterItems);


        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(adapterItems.getContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        selectFragment(checkboxAnsAction);
                        break;
                    case 1:
                        selectFragment(radiobtnAnsAction);
                        break;
                    case 2:
                        selectFragment(writtenAnsAction);
                        break;
                }
            }
        });


        addQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer quizId = 999;
                Intent intent = getIntent();
                quizId = Integer.valueOf((int) intent.getLongExtra("quizID", 0));
                queId = db.addNewQuestion(autoCompleteTxt.getText().toString(), question.getText().toString(), quizId);
                //questionID[0] = queId;
                questions = queId;
                Log.d("quizzz", String.valueOf(quizId));
                Log.d("queeee", String.valueOf(queId));

            }

        });
    }


    private void selectFragment(Fragment fragment) {

        bundle.putLong("questionID", questions);

        fragment.setArguments(bundle);
        Log.d("NewQuizQ_questionID", String.valueOf(questions));
        Log.d("NewQuizQ_queIDCheck", String.valueOf(queId));
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ansType, fragment);
        fragmentTransaction.commit();
    }

}