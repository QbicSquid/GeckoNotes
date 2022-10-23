package com.geckolabs.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class WrittenAns extends Fragment {

    EditText editAns;
    ImageButton saveWrtAns;

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

        editAns = view.findViewById(R.id.writtenAns);
        saveWrtAns = view.findViewById(R.id.saveWrittenAns);
        
        saveWrtAns.setOnClickListener((v) -> saveNote());

    }

    void saveNote() {
        String WriteAns = editAns.getText().toString();
        if(WriteAns==null || WriteAns.isEmpty()){
            editAns.setError("Answer is required");
            return;
        }
    }
}