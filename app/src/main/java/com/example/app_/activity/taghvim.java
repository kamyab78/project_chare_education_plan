package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
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
    List<String>cal_date;
CalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taghvim);
        cal=findViewById(R.id.cal);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date=i+"/"+i1+"/"+i2;

                if(cal_date.contains(date)) {
                    cal_date.remove(date);
                    Toast.makeText(getApplicationContext(),"تاریخ مورد نظر حذف شد" ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
                    cal_date.add(date);
                }
                System.out.println(date);
            }
        });
    }



}
