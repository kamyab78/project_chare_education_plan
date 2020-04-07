package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_.R;
import com.example.app_.entity.lesson;
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
import okhttp3.Response;

public class get_id_last_plan extends AppCompatActivity {
EditText get_id;
Button get;
    StringBuffer stringBuffer;
    public static plan_ get_plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_id_last_plan);
        get_id=findViewById(R.id.get_id);
        get=findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=get_id.getText().toString();
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

                System.out.println(id);
                String token = stringBuffer.toString();
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/plans/"+id+"/")
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
                        get_plan=new Gson().fromJson(res,plan_.class);
                        Intent intent=new Intent(get_id_last_plan.this,plan_history.class );
                        startActivity(intent);

                    }
                });
            }
        });
    }
}
