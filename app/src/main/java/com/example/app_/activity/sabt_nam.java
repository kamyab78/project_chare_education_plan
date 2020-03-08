package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_.MainActivity;
import com.example.app_.R;
import com.example.app_.base.request_base;
import com.example.app_.entity.info;
import com.example.app_.entity.information;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class sabt_nam extends AppCompatActivity {
EditText name;
EditText familyname;
EditText email;
EditText user;
TextView txt;
EditText pass;
Button accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabt_nam);
        name=findViewById(R.id.name);
        familyname=findViewById(R.id.familyname);
        email=findViewById(R.id.email);
        user=findViewById(R.id.user);
        txt=findViewById(R.id.textView_);
        pass=findViewById(R.id.pass);
        accept=findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=user.getText().toString();
                String firstname=name.getText().toString();
                String lastname=familyname.getText().toString();
                String emails=email.getText().toString();
                String password=pass.getText().toString();

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\n\t\"username\": \""+username+"\",\n\t\"first_name\": \""+firstname+"\",\n\t\"last_name\": \""+lastname+"\",\n\t\"email\": \""+emails+"\",\n\t\"password\": \""+password+"\"\n}");
                Request request = new Request.Builder()
                        .url("http://37.139.22.60:8000/api/v1/users/register/")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
//                        Toast.makeText(getBaseContext(), "وضعیت اینترنت خود را بررسی کنید", Toast.LENGTH_LONG).show();
                        System.out.println("fail");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String json=response.body().string();
                        System.out.println(json);
                        if (response.isSuccessful()){
                            System.out.println("yes");
                            System.out.println(json);
                            String[]key=json.split(": ");
                            String k=key[1].substring(1,key[1].length()-2);
                            System.out.println(k);
                            Intent intent=new Intent(sabt_nam.this , MainActivity.class);
                            intent.putExtra("key" , k);
                            startActivity(intent);
                        }
                        else{
                            System.out.println(json);
                            txt.setText(json);
                            System.out.println("no");
                        }
                    }
                });
            }
        });
    }
}
