package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.app_.R;
import com.example.app_.entity.user_budget;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class time_picker extends AppCompatActivity {
    Button new_saat;
    Button new_cal;
    TextView time_txt;
    TextView tarikh;
    StringBuffer stringBuffer;
    String from;
    String to;
    String saat;
    String deighe;
    String ta;
    EditText id_;
    public static String[] id_budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        new_saat = findViewById(R.id.new_saat);
        time_txt = findViewById(R.id.time_txt);

        new_cal = findViewById(R.id.new_cal);
        tarikh = findViewById(R.id.tarikh);
        id_ = findViewById(R.id.id);

        tarikh.setText("زمان های مورد نظر در تاریخ " + taghvim.date);
        first_page.time.add(taghvim.date);

        time_txt.setText("");
        new_saat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker();
            }
        });
        new_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(time_picker.this, taghvim.class);
                startActivity(intent);
            }
        });


    }

    private void picker() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);

                    int ta_time = hourOfDay + 1;

                    if (hourOfDay < 10) {
                        if (minute < 10) {
                            if(ta_time==24) {
                                saat = "0" + hourOfDay;
                                ta = "" + "00";
                                deighe = 0 + "" + minute;
                            }
                            else {
                                if(ta_time==10) {
                                    saat = "0" + hourOfDay;
                                    ta = "" + ta_time;
                                    deighe = 0 + "" + minute;
                                }
                                else {
                                    saat = "0" + hourOfDay;
                                    ta = "0" + ta_time;
                                    deighe = 0 + "" + minute;
                                }
                            }
                        } else {
                            if(ta_time==24) {
                                saat = "0" + hourOfDay;
                                ta = "" + "00";
                                deighe = "" + minute;
                            }
                            else {
                                saat = "0" + hourOfDay;
                                ta = "0" + ta_time;
                                deighe = "" + minute;
                            }
                        }
                    } else {
                        if (minute < 10) {
                            if(ta_time==24) {
                                saat = "" + hourOfDay;
                                ta = "" + "00";
                                deighe = 0 + "" + minute;
                            }
                            else {
                                saat = "" + hourOfDay;
                                ta = "" + ta_time;
                                deighe = 0 + "" + minute;
                            }
                        } else {
                            if(ta_time==24) {
                                saat = "" + hourOfDay;
                                ta = "" + "00";
                                deighe = "" + minute;
                            }
                            else {
                                saat = "" + hourOfDay;
                                ta = "" + ta_time;
                                deighe = "" + minute;
                            }
                        }
                    }
                    from = saat + ":" + deighe + ":" + "00";
                    to = ta + ":" + deighe + ":" + "00";

                    System.out.println(hourOfDay + " " + minute);
                    try {
                        FileInputStream fileInputStream = openFileInput("data.txt");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String line;
                        stringBuffer = new StringBuffer();
                        while ((line = bufferedReader.readLine()) != null)
                            stringBuffer.append(line);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String token = stringBuffer.toString();
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "{\n" +
                            "  \"from_date\": \"" + taghvim.date + "T" + from + ".707Z\",\n" +
                            "  \"to_date\": \"" + taghvim.date + "T" + to + ".707Z\",\n" +
                            "  \"for_exam\": " + next_exam.name_id_exam.get(next_exam.exam) + "\n" +
                            "}");
                    Request request = new Request.Builder()
                            .url("http://194.5.207.137:8000/api/v1/users/main/user_budget/")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "token " + token)
                            .method("POST", body)
                            .build();

                    System.out.println("{\n" +
                            "  \"from_date\": \"" + taghvim.date + "T" + from + ".707Z\",\n" +
                            "  \"to_date\": \"" + taghvim.date + "T" + to + ".707Z\",\n" +
                            "  \"for_exam\": " + next_exam.name_id_exam.get(next_exam.exam) + "\n" +
                            "}");
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("fail");
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String body = response.body().string();
                            System.out.println(body);
                            System.out.println(response.message());
                            id_budget = body.split(",");
                            String[] id = id_budget[0].split(":");
                            first_page.time.add(id[1] + " - " + saat + ":" + deighe + "-" + ta + ":" + deighe + "\n");
//                            first_page.show_time.put(taghvim.date, time);
                            time_txt.append(id[1] + " - " + saat + ":" + deighe + "-" + ta + ":" + deighe + "\n");
                        }
                    });
                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();

    }

}
