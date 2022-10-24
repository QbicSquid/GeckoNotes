package com.geckolabs.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.geckolabs.dao.QuizDAO;


public class WrittenAns extends Fragment {

    EditText editAns;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_written_ans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        QuizDAO db = new QuizDAO(getActivity());
        editAns = view.findViewById(R.id.writtenAns);
        btn = view.findViewById(R.id.ansCorrect);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addAnswer(editAns.getText().toString(), true);
            }
        });


    }

//    void saveNote() {
//        String WriteAns = editAns.getText().toString();
//        if(WriteAns==null || WriteAns.isEmpty()){
//            editAns.setError("Answer is required");
//            return;
//        }
//    }
}