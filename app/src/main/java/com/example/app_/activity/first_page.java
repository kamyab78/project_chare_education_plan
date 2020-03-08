package com.example.app_.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.app_.MainActivity;
import com.example.app_.R;

public class first_page extends AppCompatActivity {
TextView head;
Button new_program;
Button history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        head=findViewById(R.id.head);
        new_program=findViewById(R.id.new_program);
        history=findViewById(R.id.history);
        head.setText( "سلام "+ MainActivity.user_name);

    }
}
