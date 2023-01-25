package com.example.loginpage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_second extends AppCompatActivity {


    TextView cong, msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        cong = findViewById(R.id.tv_cong);
        msg = findViewById(R.id.tv_msg);


    }
}