package com.geckolabs.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.geckolabs.dao.DBTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffe1a8")));
    }

    public void addCourse(View view) {
        DBTest db = new DBTest(this);
        EditText input = (EditText) findViewById(R.id.courseInput);

        db.addNewCourse(input.getText().toString());

        String[] courses = db.getCourses();
        for (String s : courses)
            Log.d("LOGGG: ", s);
        Context c = view.getContext();
    }

    public void showCourses(View view) {
        DBTest db = new DBTest(this);
        String[] courses = db.getCourses();
        for (String s : courses)
            Log.d("LOGGG: ", s);
    }

    public void delDB(View view) {
        this.deleteDatabase("coursedb");
    }

    public void addButton(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.courses);

        layout.removeAllViews();

        DBTest db = new DBTest(this);
        String[] courses = db.getCourses();
        for (String s : courses) {
            Button button = new Button(this);
            button.setPadding(5, 5, 5, 5);
            button.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            button.setText(s);
            layout.addView(button);
        }
    }
}