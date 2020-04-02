package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.app_.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class taghvim extends AppCompatActivity {

//    private MaterialCalendarView calendarView;
//    private ArrayList<Date> dates;
//    private Date date = new Date();
//    Calendar cal = Calendar.getInstance();
    Button virayesh;
    List<String>cal_date;
CalendarView cal;
public static String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taghvim);
        cal=findViewById(R.id.cal);
        virayesh=findViewById(R.id.virayesh);
        virayesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(taghvim.this,last_edite.class);
                startActivity(intent);
            }
        });
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
                Intent intent=new Intent(taghvim.this,time_picker.class);
                startActivity(intent);

            }
        });
    }



}
