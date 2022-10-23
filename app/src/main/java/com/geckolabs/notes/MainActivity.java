package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.geckolabs.dao.DBTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addCourse(View view) {
        DBTest db = new DBTest(this);
        EditText input = (EditText) findViewById(R.id.courseInput);

        db.addNewCourse(input.getText().toString());

        String[] courses = db.getCourses();
        for (String s : courses)
            Log.d("LOGGG: ", s);
    }

}