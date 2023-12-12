package com.example.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class doctdashboard extends AppCompatActivity {


    Button myprofile,myappointm,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctdashboard);
        String strd="Doctor";
        myprofile = (Button) findViewById(R.id.myprofile);
        myappointm = (Button) findViewById(R.id.myappoint);
        logout = (Button) findViewById(R.id.logout1);

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctdashboard.this,doctprofile.class);
                startActivity(intent);
            }
        });
        myappointm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctdashboard.this,userlist.class);
                intent.putExtra("name",strd);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctdashboard.this,consultation.class);
                startActivity(intent);
            }
        });




    }
}