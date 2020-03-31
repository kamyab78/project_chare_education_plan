package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app_.R;
import com.example.app_.entity.percent_id;
import com.example.app_.entity.percent_list;
import com.google.gson.Gson;

import java.util.HashMap;

public class percent extends AppCompatActivity {
    RelativeLayout layout;
    EditText editText;
    Button darsad;
    HashMap<String, EditText> percent = new HashMap<>();
    HashMap<Integer,String>list_darsad=new HashMap<>();
    public percent_list []percent_lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        layout = findViewById(R.id.layout);
        darsad = findViewById(R.id.darsad);
        for (int i = 0; i < last_exam.lessons.length; i++) {
            editText = new EditText(this);
            editText.setTag(last_exam.lessons[i].lesson.name);
            editText.setHeight(150);
            editText.setWidth(500);
            editText.setTranslationX(300);
            editText.setTranslationY(300 + 150 * i);
            editText.setHint(last_exam.lessons[i].lesson.name);
            layout.addView(editText);
            percent.put(last_exam.lessons[i].lesson.name, editText);
        }
        darsad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < last_exam.lessons.length; i++) {
                    EditText edit = percent.get(last_exam.lessons[i].lesson.name);
                    list_darsad.put(last_exam.lessons[i].lesson.id , edit.getText().toString());
                    System.out.println(edit.getText().toString());
                }
                percent_lists=new percent_list[1];

                percent_lists[0].exam=last_exam.id;

                for (int i=0;i<last_exam.lessons.length;i++){
                    percent_lists[i].lesson=new percent_id(list_darsad.get(last_exam.lessons[i].lesson.id ),last_exam.lessons[i].lesson.id );
                }
              String json=new Gson().toJson(percent_lists);
                System.out.println(json);
                Intent intent = new Intent(percent.this, taghvim.class);
                startActivity(intent);
            }
        });
    }
}
