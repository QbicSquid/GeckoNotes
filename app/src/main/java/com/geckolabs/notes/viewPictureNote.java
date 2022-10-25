package com.geckolabs.notes;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geckolabs.dao.MediaNotesDB;

import java.io.IOException;

public class viewPictureNote extends Fragment {
    TextView txtTitle;
    TextView txtDescription;
    ImageView imgView;
    Integer picNoteId;

    Bitmap selectedImageBitmap;
    Uri selectedImageUri;

    public viewPictureNote() {
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
        return inflater.inflate(R.layout.fragment_view_picture_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("InFrag","check");
        MediaNotesDB db = new MediaNotesDB(getActivity());
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);
        imgView = view.findViewById(R.id.imgView);
        picNoteId = 15;

        PicNoteModel picNoteModel= db.getSinglePicNote(picNoteId);
        Log.d("des",picNoteModel.getDescription());
        Log.d("title",picNoteModel.getTitle());
        Log.d("path",picNoteModel.getMediaPath());

        String stringImageUri = "file://"+picNoteModel.getMediaPath();
        selectedImageUri= Uri.parse(stringImageUri);

//        IVPreviewImage.setImageURI(selectedImageUri);


        try {
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getApplicationContext().getContentResolver(), selectedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imgView.setImageBitmap(selectedImageBitmap);


        txtTitle.setText(picNoteModel.getTitle());
        txtDescription.setText(picNoteModel.getDescription());


    }
}