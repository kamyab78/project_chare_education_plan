package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.app_.R;

public class percent extends AppCompatActivity {
RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        layout=findViewById(R.id.layout);
        for (int i = 0; i <last_exam.lessons.length; i++) {

            EditText editText =new EditText(this);
            //editText.setId();
           // editText.setId(i);
            editText.setTag(last_exam.lessons[i].lesson.name);
            editText.setHeight(150);
            editText.setWidth(500);
            editText.setTranslationX(300);
            editText.setTranslationY(300+150*i);
            editText.setHint(last_exam.lessons[i].lesson.name);
            layout.addView(editText);

        }

    }
}
