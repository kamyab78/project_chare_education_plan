package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_.MainActivity;
import com.example.app_.R;
import com.example.app_.entity.filed;
import com.example.app_.entity.information;
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

public class reshte extends AppCompatActivity {
     Spinner spin_reshte;
Button accept;
     String reshte;
    HashMap<String,Integer>data=new HashMap<>();
    List<String>list;
    StringBuffer stringBuffer;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reshte);
        spin_reshte=findViewById(R.id.spin_reshte);
        accept=findViewById(R.id.next);
       list=new ArrayList<>();


        for (int i = 0; i <sabt_nam.fileds.length; i++) {
            list.add(sabt_nam.fileds[i].name);
            data.put(sabt_nam.fileds[i].name, sabt_nam.fileds[i].id);
        }
            ArrayAdapter<String> item_reshte = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            item_reshte.setDropDownViewResource(android.R.layout.simple_spinner_item);

            spin_reshte.setAdapter(item_reshte);
            spin_reshte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    reshte = adapterView.getItemAtPosition(i).toString();
                    System.out.println(data.get(reshte));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

accept.setOnClickListener(new View.OnClickListener() {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token=stringBuffer.toString();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,"{\n" +
                "  \"field\": "+data.get(reshte)+"\n" +
                "}" );
        Request request = new Request.Builder()
                .url("http://194.5.207.137:8000/api/v1/users/main/profile/edit/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "token "+token)
                .method("PUT" , body)
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
                   System.out.println(response.body().string());
                System.out.println(response.message());
                Intent intent=new Intent(reshte.this , first_page.class);
                startActivity(intent);

            }
        });
    }
});
    }

}
