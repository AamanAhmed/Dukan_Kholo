package com.example.dukan_kholo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    TextView  Gotologin;
    EditText Rusername,Remail,Rpass,Rconfirmpass,Rexpsalary,Rhandle,Rspeciality;
    Button Rbtn;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Rusername = (EditText) findViewById(R.id.username);
        Remail = (EditText) findViewById(R.id.email);
        Rpass = (EditText) findViewById(R.id.password);
        Rconfirmpass = (EditText) findViewById(R.id.confirmpassword);
        Rbtn = (Button) findViewById(R.id.signupbtn);
        Gotologin = (TextView) findViewById(R.id.login);
        db = new Db(signup.this);

        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rusername = Rusername.getText().toString();
                String remail = Remail.getText().toString();
                String rpassword = Rpass.getText().toString();
                String rconfirmpass = Rconfirmpass.getText().toString();

                if (rusername.isEmpty() == true) {
                    Rusername.requestFocus();
                    Rusername.setError("Required");
                } else if (!rusername.matches("[a-zA-Z]+")){
                    Rusername.requestFocus();
                    Rusername.setError("Alphabets allow only");
                } else if (rusername.length() <5 ) {
                    Rusername.requestFocus();
                    Rusername.setError("Invalid Length");
                } else if (remail.isEmpty() == true) {
                    Remail.requestFocus();
                    Remail.setError("Required");
                } else if (!remail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    Remail.requestFocus();
                    Remail.setError("Invalid Pattern");
                } else if (rpassword.isEmpty() == true) {
                    Rpass.requestFocus();
                    Rpass.setError("Required");
                } else if (rpassword.length() <5 ) {
                    Rpass.requestFocus();
                    Rpass.setError("Invalid Length");
                } else if (rconfirmpass.isEmpty() == true){
                    Rconfirmpass.requestFocus();
                    Rconfirmpass.setError("Required");
                }
                else {

                    if (rpassword.equals(rconfirmpass)) {

                        boolean email = db.checkemail(remail);
                        if (email == true) {
                            Toast.makeText(signup.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean successfullyinserted = db.Insertrecord(rusername, remail, rpassword);
                            if (successfullyinserted == true) {
                                Toast.makeText(signup.this, "Data Insert Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(signup.this, login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(signup.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

                            }
                        }

                    } else {
                        Toast.makeText(signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this , login.class);
                startActivity(intent);
            }
        });
    }
}