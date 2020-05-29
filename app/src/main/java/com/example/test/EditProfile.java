package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.test.DataBase.DBHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    Button edit,delete,search;
    EditText username,dob,password;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        search = (Button)findViewById(R.id.btnSearch);
        username =(EditText) findViewById(R.id.edUseraName);
        dob =(EditText) findViewById(R.id.edDOB);
        password =(EditText) findViewById(R.id.edPassword);
        male =(RadioButton) findViewById(R.id.edbtnMale);
        female =(RadioButton) findViewById(R.id.edbtnFemale);
        edit = (Button)findViewById(R.id.btnEdit);
        delete = (Button)findViewById(R.id.btnDelete);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbhandler = new DBHandler(getApplicationContext());
                List User = dbhandler.readAllInfo(username.getText().toString());

                if(User.isEmpty()){
                    Toast.makeText(EditProfile.this, "no user", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }
                else {
                    Toast.makeText(EditProfile.this, "User Found! User:" + User.get(0).toString(), Toast.LENGTH_SHORT).show();
                    username.setText(User.get(0).toString());
                    dob.setText(User.get(1).toString());
                    password.setText(User.get(2).toString());

                    if (User.get(3).toString().equals("Male")) {
                        male.setChecked(true);
                    } else {
                        female.setChecked(true);
                    }
                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender = "Male";
                }
                else{
                    gender = "Female";
                }

                DBHandler dbhandler = new DBHandler(getApplicationContext());

                Boolean status = dbhandler.updateInfor(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);

                if(status){
                    Toast.makeText(EditProfile.this, "User update", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Upadate fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbhandler = new DBHandler(getApplicationContext());

                dbhandler.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "User deleted", Toast.LENGTH_SHORT).show();

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }
}
