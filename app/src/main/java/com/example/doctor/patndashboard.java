package com.example.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class patndashboard extends AppCompatActivity {

    Button offfline,checklist,patnappointments,startcall;
    String cat1,cat2;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patndashboard);

        String num = getIntent().getStringExtra("us");
        cat1 = "inclinic";
        cat2 = "video";
        String pat="patient";

        offfline = (Button) findViewById(R.id.offfline);

        checklist = (Button) findViewById(R.id.checklist);
        patnappointments =(Button)findViewById(R.id.patnappointments);
        startcall = findViewById(R.id.startcall);
        db = FirebaseDatabase.getInstance().getReference();

        offfline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patndashboard.this,bookinclinic.class);
                startActivity(intent);
            }
        });

        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patndashboard.this,checklist.class);
                startActivity(intent);
            }
        });
        patnappointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(patndashboard.this,userlist.class);
                i.putExtra("number",num);
                i.putExtra("name",pat);
                startActivity(i);
                finish();



            }
        });
        startcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query q = db.child(num).child(num);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String s = snapshot.child("cat").getValue(String.class);
                        if (s.equals("video")) {

                            Query qu = db.child("videomeet");
                            qu.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String l = snapshot.child("link").getValue(String.class);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(l));
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }else{
                            Toast.makeText(patndashboard.this, "you haven't registered for video appointment", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });






    }
}