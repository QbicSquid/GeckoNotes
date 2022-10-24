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
import android.widget.EditText;
import android.widget.ImageButton;

import com.geckolabs.dao.QuizDAO;

public class NewQuizQ extends AppCompatActivity {

    CheckboxAns checkboxAnsAction;
    RadiobtnAns radiobtnAnsAction;
    WrittenAns writtenAnsAction;

    EditText question;
    ImageButton btn;

    String[] items = {"Checkbox", "Radio", "Written"};

    AutoCompleteTextView autoCompleteTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_q);

        QuizDAO db = new QuizDAO(this);

        question = findViewById(R.id.question);
        btn = findViewById(R.id.saveWrittenAns);

        btn.setOnClickListener((v) -> saveNote());



        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        checkboxAnsAction = new CheckboxAns();
        radiobtnAnsAction = new RadiobtnAns();
        writtenAnsAction = new WrittenAns();

        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this,R.layout.select_type,items);
        autoCompleteTxt.setAdapter(adapterItems);


        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(adapterItems.getContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
                switch (position){
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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addNewQuestion(autoCompleteTxt.getText().toString(),question.getText().toString());
                Intent intent = getIntent();
                Log.d("", String.valueOf(intent.getLongExtra("quizID",0)));
            }
        });
    }



    private void saveNote() {
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ansType,fragment);
        fragmentTransaction.commit();
    }


}