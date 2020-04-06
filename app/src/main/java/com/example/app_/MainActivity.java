package com.example.app_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_.activity.first_page;
import com.example.app_.activity.login;
import com.example.app_.activity.patern;
import com.example.app_.activity.plan;
import com.example.app_.activity.sabt_nam;
import com.example.app_.base.request_base;
import com.example.app_.entity.information;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    StringBuffer stringBuffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
   // String token=stringBuffer.toString();
        if(stringBuffer==null){
            Intent intent=new Intent(MainActivity.this, login.class);
            startActivity(intent);
        }
        else {
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
//            RequestBody body = RequestBody.create(mediaType, "{\n" +
//                    "  \"pattern\": " + name_id.get(patterns) + ",\n" +
//                    "  \"for_exam\": " + 3 + "\n" +
//                    "}");
            Request request = new Request.Builder()
                    .url("http://194.5.207.137:8000/api/v1/users/main/profile/")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "token " + token)
//                    .method("POST", body)
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
//                    System.out.println(body);
                    System.out.println(response.code());
                    if(response.code()==200){
                        Intent intent=new Intent(MainActivity.this, first_page.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                    }

                }
            });

        }

    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
