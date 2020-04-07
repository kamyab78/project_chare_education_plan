package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.app_.R;
import com.example.app_.entity.plan_;
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

public class patern extends AppCompatActivity {
    Spinner pattern;
    List<String> patt_name = new ArrayList<>();
    HashMap<String, Integer> name_id = new HashMap<>();
    String patterns;
    StringBuffer stringBuffer;
    Button submit;
public static plan_ plans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patern);
        pattern = findViewById(R.id.spin_patern);
        submit = findViewById(R.id.submit);

        for (int i = 0; i < last_edite.patts.length; i++) {
            patt_name.add(last_edite.patts[i].name);
            name_id.put(last_edite.patts[i].name, last_edite.patts[i].id);
        }

        ArrayAdapter<String> item_exam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, patt_name);
        item_exam.setDropDownViewResource(android.R.layout.simple_spinner_item);

        pattern.setAdapter(item_exam);
        pattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                patterns = adapterView.getItemAtPosition(i).toString();
                System.out.println(name_id.get(patterns));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
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
                RequestBody body = RequestBody.create(mediaType, "{\n" +
                        "  \"pattern\": " + name_id.get(patterns) + ",\n" +
                        "  \"for_exam\": " + next_exam.name_id_exam.get(next_exam.exam) + "\n" +
                        "}");
                Request request = new Request.Builder()
                        .url("http://194.5.207.137:8000/api/v1/plans/")
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
                        plans=new Gson().fromJson(body,plan_.class);
                        Intent intent=new Intent(patern.this,plan.class );
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
