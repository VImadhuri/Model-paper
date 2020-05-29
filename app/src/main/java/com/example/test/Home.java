package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    EditText username,password;
    Button register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (EditText)findViewById(R.id.pfName);
        password = (EditText)findViewById(R.id.edPassword) ;
        login = (Button)findViewById(R.id.btnhLogin);
        register= (Button)findViewById(R.id.btnhRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,ProfileManagement.class);
                startActivity(intent);

            }
        });

    }
}
