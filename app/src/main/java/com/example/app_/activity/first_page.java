package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_.MainActivity;
import com.example.app_.R;
import com.example.app_.entity.exam;
import com.example.app_.entity.filed;
import com.google.gson.Gson;

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

public class first_page extends AppCompatActivity {
    TextView head;
    Button new_program;
    Button prof;
    Button history;
    StringBuffer stringBuffer;
    long backprestime;
    public static exam[] exams;
    public  static HashMap<String, List<String>> show_time=new HashMap<>();
    public static List<String> time = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        head = findViewById(R.id.head);
        new_program = findViewById(R.id.new_program);
        history = findViewById(R.id.history);
        prof = findViewById(R.id.prof);
      //  head.setText("سلام " + login.user_name);
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first_page.this, reshte.class);
                startActivity(intent);
            }
        });
        new_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream=openFileInput("data.txt");
                    InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    String line;
                    stringBuffer=new StringBuffer();
                    while ((line=bufferedReader.readLine())!=null)
                        stringBuffer.append(line);
                } catch (
                        FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String token=stringBuffer.toString();
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/core/main/exam/")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "token "+token)
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
                        String list_azmon=response.body().string();
                        exams = new Gson().fromJson(list_azmon, exam[].class);
                        System.out.println(exams[0].name);
                        Intent intent=new Intent(first_page.this,last_exam.class);
                        startActivity(intent);
                    }
                });
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
