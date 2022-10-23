package com.geckolabs.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;


public class RadiobtnQuiz extends Fragment {

    CheckboxAns checkboxAnsAction;
    RadiobtnAns radiobtnAnsAction;
    WrittenAns writtenAnsAction;

    String[] items = {"Checkbox", "Radio", "Written"};

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapterItems;
    public RadiobtnQuiz() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radiobtn_quiz, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        autoCompleteTxt = view.findViewById(R.id.auto_complete_txt);

        checkboxAnsAction = new CheckboxAns();
        radiobtnAnsAction = new RadiobtnAns();
        writtenAnsAction = new WrittenAns();

        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(getActivity(),R.layout.select_type,items);
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
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ansType,fragment);
        fragmentTransaction.commit();
    }

}