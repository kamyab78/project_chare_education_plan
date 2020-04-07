package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.app_.R;

import java.util.ArrayList;
import java.util.List;

public class plan_history extends AppCompatActivity {
    CalendarView calender;
    public static String date;
    public static List<String> zaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_history);
        calender=findViewById(R.id.calender_history);

        zaman=new ArrayList<>();
        for (int i = 0; i <get_id_last_plan.get_plan.boxes.length ; i++) {
            String []dates=get_id_last_plan.get_plan.boxes[i].start_time.split("T");
            zaman.add(dates[0]);
        }
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                if(i1<10){
                    if(i2<10){
                        date=i+"-"+0+""+i1+"-"+0+""+i2;
                    }
                    else {
                        date=i+"-"+0+""+i1+"-"+i2;
                    }
                }
                else {
                    if(i2<10){
                        date=i+"-"+i1+"-"+0+""+i2;
                    }
                    else {
                        date=i+"-"+i1+"-"+i2;
                    }

                }
                System.out.println(date);
                Intent intent=new Intent(plan_history.this,show_plan_history.class);
                startActivity(intent);

            }
        });

    }
}
