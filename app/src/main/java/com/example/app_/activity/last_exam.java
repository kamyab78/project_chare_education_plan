package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_.R;
import com.example.app_.entity.exam;
import com.example.app_.entity.lesson;
import com.example.app_.entity.lesson_item;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class last_exam extends AppCompatActivity {
    Button sub;
    Spinner azmon;
    String exam;
    public static lesson[] lessons;
    public static lesson_item[] lesson_items;
    StringBuffer stringBuffer;
public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_exam);
        sub = findViewById(R.id.sub);
        azmon = findViewById(R.id.spin_azmon);
        List<String> list_exam = new ArrayList<>();
        final HashMap<String, Integer> name_id = new HashMap<>();
        for (int i = 0; i < first_page.exams.length; i++) {
            list_exam.add(first_page.exams[i].name);
            name_id.put(first_page.exams[i].name, first_page.exams[i].id);
        }
        ArrayAdapter<String> item_exam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_exam);
        item_exam.setDropDownViewResource(android.R.layout.simple_spinner_item);

        azmon.setAdapter(item_exam);
        azmon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exam = adapterView.getItemAtPosition(i).toString();
                System.out.println(name_id.get(exam));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream = openFileInput("data.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    stringBuffer = new StringBuffer();
                    while ((line = bufferedReader.readLine()) != null)
                        stringBuffer.append(line);
                } catch (
                        FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                id = name_id.get(exam).toString();
                System.out.println(id);
                String token = stringBuffer.toString();
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/core/main/exam_lesson/?exam=" + id)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "token " + token)
                        .build();
                System.out.println(request.headers());
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("fail");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        System.out.println(response.message());
                        lessons = new Gson().fromJson(res, lesson[].class);
//                        System.out.println(lessons[0].lesson);
//                        System.out.println(lessons[0].lesson.toString());
//                        System.out.println(lessons[0].lesson.name);
                        Intent intent = new Intent(last_exam.this, percent.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
