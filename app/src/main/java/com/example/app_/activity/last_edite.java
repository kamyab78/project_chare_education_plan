package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_.R;
import com.example.app_.entity.exam;
import com.example.app_.entity.patt;
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

public class last_edite extends AppCompatActivity {

    Button plan;
    Button delet;
    EditText id_;
    StringBuffer stringBuffer;
    TextView show ;
    TextView toast;
    public static patt[]patts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_edite);
        plan = findViewById(R.id.plan);
        delet = findViewById(R.id.delet);
        show=findViewById(R.id.show_times);
        toast=findViewById(R.id.toast);
        for (int i = 0; i <first_page.time.size() ; i++) {
            show.append(first_page.time.get(i)+"\n");
        }
plan.setOnClickListener(new View.OnClickListener() {
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
                .url("http://194.5.207.137:8000/api/v1/plans/patterns/")
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
                patts=new Gson().fromJson(body,patt[].class);
                Intent intent=new Intent(last_edite.this,patern.class);
                startActivity(intent);
                //System.out.println(patts[0].name);
            }
        });
    }
});
        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = id_.getText().toString();
                System.out.println(id);
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
                System.out.println("http://194.5.207.137:8000/api/v1/users/main/user_budget/" + id + "/");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/users/main/user_budget/" + id + "/")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "token " + token)

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
                        String body = response.body().string();
                        System.out.println(body);
                        System.out.println(response.message());
                        toast.setText("حذف با موفقیت انجام شد");
//                        Toast.makeText(getApplicationContext(),,Toast.LENGTH_SHORT).show();
//                first_page.show_time.get(taghvim.date).remove()

                    }
                });

            }
        });
    }
}
