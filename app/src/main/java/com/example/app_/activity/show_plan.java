package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app_.R;

public class show_plan extends AppCompatActivity {
    TextView tit;
    int index = 0;
    RelativeLayout rele;
Button taghir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan);
        tit = findViewById(R.id.tit);
        rele = findViewById(R.id.rele);
        taghir=findViewById(R.id.taghir);
        tit.setText("برنامه درسی در  تاریخ " + plan.date);
taghir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(show_plan.this,taghir.class);
        startActivity(intent);
    }
});
        for (int i = 0; i < patern.plans.boxes.length; i++) {
            if (plan.date.equals(plan.zaman.get(i))) {
                TextView type = new TextView(this);
                type.setText(patern.plans.boxes[i].type);
                type.setTranslationX(40);
                type.setTranslationY(100 + 150 * index);
//type.setWidth(100);

                TextView topic = new TextView(this);
//                topic.setText("topic");
                topic.setText(patern.plans.boxes[i].topic);
                topic.setTranslationX(200);
                topic.setTranslationY(100 + 150 * index);
//topic.setWidth(100);

                TextView lesson = new TextView(this);
                lesson.setText(patern.plans.boxes[i].lesson);
                lesson.setTranslationX(460);
                lesson.setTranslationY(100 + 150 * index);
//lesson.setWidth(100);

                String[] start = patern.plans.boxes[i].start_time.split("T");
                String[] start_time = start[1].split(":");
                String[] end = patern.plans.boxes[i].end_time.split("T");
                String[] end_time = end[1].split(":");
                TextView time = new TextView(this);
                time.setText(start_time[0] + ":" + start_time[1] + " - " + end_time[0] + ":" + end_time[1]);
                time.setTranslationX(720);
                time.setTranslationY(100 + 150 * index);
//time.setWidth(100);
                TextView idd = new TextView(this);
                idd.setText(""+patern.plans.boxes[i].id);
                idd.setTranslationX(910);
                idd.setTranslationY(100 + 150 * index);


                rele.addView(type);
                rele.addView(topic);
                rele.addView(lesson);
                rele.addView(time);
                rele.addView(idd);
                index++;
            }
        }
    }
}
