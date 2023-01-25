package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name_id;
    EditText password_id;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_id = findViewById(R.id.et_Name);
        password_id = findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.button);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String value_name = name_id.getText().toString();

                String value_password = password_id.getText().toString();

                String match_name = "Hisham";

                //Arangement of else if is all that matters.

                if (value_name.equalsIgnoreCase(match_name) && value_password.equals("123456")) {

                    Intent intent = new Intent(MainActivity.this, activity_second.class);
                    startActivity(intent);

                } else if (value_name.isEmpty()  && value_password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Both The Credentials.", Toast.LENGTH_SHORT).show();
                } else if (value_name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Your Name.", Toast.LENGTH_SHORT).show();
                } else if (value_password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Your Password.", Toast.LENGTH_SHORT).show();
                } else if (!value_name.equals(match_name)) {
                    Toast.makeText(MainActivity.this, "Please Correct Your Name.", Toast.LENGTH_SHORT).show();
                } else if (!value_password.equals("123456")) {
                    Toast.makeText(MainActivity.this, "Please Correct Your Password.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Corret Credentials.", Toast.LENGTH_SHORT).show();
                }
            }
/*
            else if ((value_name.isEmpty() || value_password.isEmpty()) || (value_name.isEmpty() && value_password.isEmpty())) {
            Toast.makeText(MainActivity.this, "Please Enter the both Credentials.", Toast.LENGTH_SHORT).show();
*/

        });
    }
}