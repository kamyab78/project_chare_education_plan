package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.app_.R;

import java.util.ArrayList;
import java.util.List;

public class plan extends AppCompatActivity {
CalendarView calender;
public static String date;
public static List<String>zaman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
calender=findViewById(R.id.calender);
zaman=new ArrayList<>();
        for (int i = 0; i <patern.plans.boxes.length ; i++) {
            String []dates=patern.plans.boxes[i].start_time.split("T");
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
                Intent intent=new Intent(plan.this,show_plan.class);
                startActivity(intent);

            }
        });

    }
    int backButtonCount=0;
    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
