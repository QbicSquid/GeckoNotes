package com.geckolabs.notes;

import android.content.Intent;
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
    ImageView imgBtnUpdate;
    ImageView imgBtnDelete;
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
        imgBtnDelete = view.findViewById(R.id.imgBtnDelete);
        imgBtnUpdate = view.findViewById(R.id.imgBtnUpdate);

        picNoteId = 15;

        //Get Details From DB And Display
        PicNoteModel picNoteModel= db.getSinglePicNote(picNoteId);
//        Log.d("des",picNoteModel.getDescription());
//        Log.d("title",picNoteModel.getTitle());
//        Log.d("path",picNoteModel.getMediaPath());
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

        //Delete a pic Note
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log1","in On click in delete");
                int picId = 1;
                db.deletePicNote(picId);

            }
        });

        //Update a pic Note
        imgBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("before",picNoteModel.getDescription());
                String test = "test des";
                picNoteModel.setDescription(test);
                Log.d("after",picNoteModel.getDescription());
                db.updateSinglePicNote(picNoteModel);
                Log.d("Log1","in On click in update");
                Intent intent = new Intent(getActivity(),UpdatePicNoteActivity.class);
                intent.putExtra("id",picNoteModel.getId());
                startActivity(intent);

//                CreateAudioNote nextFrag= new CreateAudioNote();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.Container, nextFrag, "findThisFragment")
//                        .addToBackStack(null)
//                        .commit();
            }
        });



    }
}