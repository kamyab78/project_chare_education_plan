package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_.R;
import com.example.app_.entity.plan_;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class taghir extends AppCompatActivity {
Button finish;
EditText box1;
    StringBuffer stringBuffer;
        EditText box2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taghir);
        finish=findViewById(R.id.finish);
        box1=findViewById(R.id.box1);
        box2=findViewById(R.id.box2);
        finish.setOnClickListener(new View.OnClickListener() {
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String token = stringBuffer.toString();
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "\"box1\": "+Integer.parseInt(box1.getText().toString())+", \"box2\": "+Integer.parseInt(box2.getText().toString())+"");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/plans/swap/")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "token " + token)
                        .method("POST", body)
                        .build();


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
                        System.out.println(body);
                        System.out.println(response.message());
                Intent intent=new Intent(taghir.this,patern.class);
                startActivity(intent);
                    }
                });
            }
        });
    }
}
