package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.test.DataBase.DBHandler;

public class ProfileManagement extends AppCompatActivity {
    Button update,add;
    EditText username,dob,password;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        add = (Button)findViewById(R.id.btnpfAdd);
        username =(EditText) findViewById(R.id.pfName);
        dob =(EditText) findViewById(R.id.pfDOB);
        password =(EditText) findViewById(R.id.edPassword);
        male =(RadioButton) findViewById(R.id.pfbtnMale);
        female =(RadioButton) findViewById(R.id.pfbtnFemale);
        update = (Button)findViewById(R.id.btnUpdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileManagement.this,EditProfile.class);
                startActivity(intent);
            }
        });

       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(male.isChecked()){
                   gender = "Male";
               }
               else{
                   gender = "Female";
               }
               DBHandler dbhandler = new DBHandler(getApplicationContext());
              long newID =  dbhandler.addInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
               Toast.makeText(ProfileManagement.this, "User Added.User ID:"+newID, Toast.LENGTH_SHORT).show();
               Intent intent =new Intent(getApplicationContext(), EditProfile.class);
               startActivity(intent);
           }
       });
    }
}
