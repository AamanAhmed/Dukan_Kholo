package com.example.dukan_kholo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_buisness_idea extends AppCompatActivity {

    EditText Startupname,Amount,Person,Idea;
    Button Start;
    Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buisness_idea);

        Startupname = (EditText) findViewById(R.id.Startupname);
        Amount = (EditText) findViewById(R.id.Amount);
        Person = (EditText) findViewById(R.id.person);
        Idea = (EditText) findViewById(R.id.Idea);
        Start = (Button) findViewById(R.id.Start);
        db = new Db(add_buisness_idea.this);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String startupname = Startupname.getText().toString();
                int amount = Integer.parseInt(Amount.getText().toString());
                int person = Integer.parseInt(Person.getText().toString());
                String idea = Idea.getText().toString();
                if (startupname.isEmpty() == true){
                    Startupname.requestFocus();
                    Startupname.setError("Required");
                }
                else  if (amount == 0){
                    Amount.requestFocus();
                    Amount.setError("Amount must be 25000 to 100000");
                }
                else  if (person == 0){
                    Person.requestFocus();
                    Person.setError("Required");
                }
                else  if (idea.isEmpty() == true){
                    Idea.requestFocus();
                    Idea.setError("Required");
                }
                else {
                    boolean startupcheck = db.Startupname(startupname);
                    if (startupcheck == true) {
                        Startupname.requestFocus();
                        Startupname.setError("Starup name Already Exists");
                    } else {
                        boolean successfullyideainserted = db.startupdetailinsert(startupname, amount, person, idea);
                        if (successfullyideainserted == true) {
                            Toast.makeText(add_buisness_idea.this, "Buisness idea Inserted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(add_buisness_idea.this, MainActivity.class);
                            startActivity(i);
                        }
                    }
                }
            }

        });
    }
}