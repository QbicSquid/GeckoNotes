package com.geckolabs.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.geckolabs.dao.DBTest;
import com.geckolabs.dao.MediaNotesDB;

public class CreateAudioNote extends Fragment {

    EditText txtAddAudioTitle;
    EditText txtAddAudioDescription;
    Button btnAudioNoteDone;

    public CreateAudioNote() {
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
        return inflater.inflate(R.layout.fragment_create_audio_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MediaNotesDB db = new MediaNotesDB(getActivity());
//        DBTest db = new DBTest(getActivity());
        txtAddAudioTitle = view.findViewById(R.id.txtAddAudioTitle);
        txtAddAudioDescription = view.findViewById(R.id.txtAddAudioDescription);
        btnAudioNoteDone = view.findViewById(R.id.btnAudioNoteDone);
//        db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString());
        btnAudioNoteDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log1","in On click");
                db.addNewAudioNote(txtAddAudioTitle.getText().toString(),txtAddAudioDescription.getText().toString());
            }
        });



    }
}