package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_.MainActivity;
import com.example.app_.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class login extends AppCompatActivity {
    EditText username;
    EditText pass;
    Button accept;
    TextView sabtnam;
    TextView text;
    public static String token_;
    public static String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        text = findViewById(R.id.text_);
        accept = findViewById(R.id.accept);
        sabtnam = findViewById(R.id.sabt);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub();
            }
        });
        sabtnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, sabt_nam.class);
                startActivity(intent);
            }
        });
    }

    public void sub() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        user_name = username.getText().toString();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"username\": \"" + username.getText().toString() + "\",\n\t\"password\": \"" + pass.getText().toString() + "\"\n}");
        Request request = new Request.Builder()
                .url("http://194.5.207.137:8000/api/v1/users/login/")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")

                .build();
        System.out.println(request.headers());
//        System.out.println();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("fail");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                System.out.println(json);
                if (response.isSuccessful()) {
                    System.out.println("yes");
                    String[] token = json.split(":");
                    token_ = token[1].substring(2, token[1].length() - 2);
                    System.out.println(token_);
                    Intent intent = new Intent(login.this, first_page.class);
                    startActivity(intent);
                } else {
                    text.setText("حساب کاربری خود را بسازید");

                }
            }
        });
    }

}
