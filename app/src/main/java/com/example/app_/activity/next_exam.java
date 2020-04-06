package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.app_.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class next_exam extends AppCompatActivity {
    Spinner exam_b;
    List<String> bexam = new ArrayList<>();
  public static   HashMap<String, Integer> name_id_exam=new HashMap<>();
  Button next;
   public static String exam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_exam);
        exam_b = findViewById(R.id.spin_exam_b);
        next=findViewById(R.id.next_step);
        for (int i = 0; i < percent.exam_badis.length; i++) {
            bexam.add(percent.exam_badis[i].name);
            name_id_exam.put(percent.exam_badis[i].name, percent.exam_badis[i].id);
        }
        ArrayAdapter<String> item_exam_ = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bexam);
        item_exam_.setDropDownViewResource(android.R.layout.simple_spinner_item);

        exam_b.setAdapter(item_exam_);
        exam_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exam = adapterView.getItemAtPosition(i).toString();
                System.out.println(name_id_exam.get(exam));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(next_exam.this,taghvim.class);
                startActivity(intent);
            }
        });
    }
}
