package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class NewQuizQ extends AppCompatActivity {

    CheckboxAns checkboxAnsAction;
    RadiobtnAns radiobtnAnsAction;
    WrittenAns writtenAnsAction;

    String[] items = {"Checkbox", "Radio", "Written"};

    AutoCompleteTextView autoCompleteTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_q);


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
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ansType,fragment);
        fragmentTransaction.commit();
    }
}