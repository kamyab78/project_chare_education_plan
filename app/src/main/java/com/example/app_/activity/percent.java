package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app_.R;
import com.example.app_.entity.exam_badi;
import com.example.app_.entity.percent_id;
import com.example.app_.entity.percent_list;
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
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.graphics.Color.rgb;

public class percent extends AppCompatActivity {
    RelativeLayout layout;
    EditText editText;
    Button darsad;
    HashMap<String, EditText> percent = new HashMap<>();
    HashMap<Integer, String> list_darsad = new HashMap<>();
    StringBuffer stringBuffer;
    public List<percent_list> percent_lists;
    public static exam_badi[]exam_badis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        layout = findViewById(R.id.layout);
        darsad = findViewById(R.id.darsad);
        for (int i = 0; i < last_exam.lessons.length; i++) {
            editText = new EditText(this);
            editText.setTag(last_exam.lessons[i].lesson.name);
            editText.setHeight(150);
            editText.setWidth(500);
            editText.setBackgroundColor(rgb(255,250,250));
            editText.setTranslationX(300);
            editText.setTranslationY(100 + 150 * i);
            editText.setHint(last_exam.lessons[i].lesson.name);
            layout.addView(editText);
            percent.put(last_exam.lessons[i].lesson.name, editText);
        }
        darsad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < last_exam.lessons.length; i++) {
                    EditText edit = percent.get(last_exam.lessons[i].lesson.name);
                    list_darsad.put(last_exam.lessons[i].lesson.id, edit.getText().toString());
                    System.out.println(edit.getText().toString());
                }
                percent_list p = new percent_list();
                p.exam = last_exam.id;
                List<percent_id> lessons = new ArrayList<>();
                for (int i = 0; i < last_exam.lessons.length; i++) {
                    lessons.add(new percent_id(Integer.parseInt(list_darsad.get(last_exam.lessons[i].lesson.id)), last_exam.lessons[i].lesson.id));
                }
                p.setLesson(lessons);
                String les = new Gson().toJson(lessons);
                System.out.println(les);

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
                RequestBody body = RequestBody.create(mediaType, "{\"lessons\":" + les + ",\"exam\":" + last_exam.id + "}");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/users/main/last_exam/")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "token " + token)
                        .method("POST", body)
                        .build();

                System.out.println("{\"lessons\":" + les + ",\"exam\":" + last_exam.id + "}");
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("fail");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println(response.body().string());
                        System.out.println(response.message());
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
                                String body=response.body().string();
                                System.out.println(response.message());
                                System.out.println(body);
                                exam_badis=new Gson().fromJson(body, exam_badi[].class);
                                Intent intent = new Intent(percent.this, next_exam.class);
                                startActivity(intent);
                                //System.out.println(patts[0].name);
                            }
                        });

                    }
                });
            }
        });
    }
}
