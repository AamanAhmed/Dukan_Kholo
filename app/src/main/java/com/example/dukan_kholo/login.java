package com.example.dukan_kholo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText txt1, txt2;
    Button login;
    TextView Gotosignup;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt1 = (EditText) findViewById(R.id.loginemail);
        txt2 = (EditText) findViewById(R.id.loginpassword);
        login = (Button) findViewById(R.id.loginbtn);
        Gotosignup = (TextView) findViewById(R.id.signup);
        db = new Db(login.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = txt1.getText().toString();
                String userpswd = txt2.getText().toString();

                if (useremail.isEmpty() == true) {
                    txt1.requestFocus();
                    txt1.setError("Required");
                } else if (userpswd.isEmpty() == true) {
                    txt2.requestFocus();
                    txt2.setError("Required");
                } else{

                    boolean check = db.loginemail(useremail, userpswd);
                if (check == true) {
                    Toast.makeText(login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, add_buisness_idea.class);
                    startActivity(i);
                } else {
                    Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        Gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this , signup.class);
                startActivity(intent);
            }
        });
    }

}