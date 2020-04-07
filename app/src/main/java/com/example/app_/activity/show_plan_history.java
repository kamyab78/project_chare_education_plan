package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app_.R;

public class show_plan_history extends AppCompatActivity {

    TextView tit_history;
    int index = 0;
    RelativeLayout rele_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan_history);
        tit_history = findViewById(R.id.tit_history);
        rele_history = findViewById(R.id.rele_history);
        tit_history.setText("برنامه درسی در  تاریخ " + plan_history.date);
        for (int i = 0; i < get_id_last_plan.get_plan.boxes.length; i++) {
            if (plan_history.date.equals(plan_history.zaman.get(i))) {
                TextView type = new TextView(this);
                type.setText(get_id_last_plan.get_plan.boxes[i].type);
                type.setTranslationX(40);
                type.setTranslationY(100 + 150 * index);
//type.setWidth(100);

                TextView topic = new TextView(this);
//                topic.setText("topic");
                topic.setText(get_id_last_plan.get_plan.boxes[i].topic);
                topic.setTranslationX(200);
                topic.setTranslationY(100 + 150 * index);
//topic.setWidth(100);

                TextView lesson = new TextView(this);
                lesson.setText(get_id_last_plan.get_plan.boxes[i].lesson);
                lesson.setTranslationX(480);
                lesson.setTranslationY(100 + 150 * index);
//lesson.setWidth(100);

                String[] start = get_id_last_plan.get_plan.boxes[i].start_time.split("T");
                String[] start_time = start[1].split(":");
                String[] end = get_id_last_plan.get_plan.boxes[i].end_time.split("T");
                String[] end_time = end[1].split(":");
                TextView time = new TextView(this);
                time.setText(start_time[0] + ":" + start_time[1] + " - " + end_time[0] + ":" + end_time[1]);
                time.setTranslationX(750);
                time.setTranslationY(100 + 150 * index);
//time.setWidth(100);

                rele_history.addView(type);
                rele_history.addView(topic);
                rele_history.addView(lesson);
                rele_history.addView(time);
                index++;
            }
        }
    }
}
