package com.example.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class consultation extends AppCompatActivity {


    EditText text;
    Button btn;
    DatabaseReference book;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        text = findViewById(R.id.textlink);
        btn = findViewById(R.id.move);
        book = FirebaseDatabase.getInstance().getReference();
        btn1 = findViewById(R.id.sent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://meet.google.com"));
               startActivity(intent);

            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = text.getText().toString();
                if (str.equals("")) {
                    Toast.makeText(consultation.this, "Plz Enter The Link", Toast.LENGTH_SHORT).show();

                }else {
                    String s = "https://"+ str;
                    meethelper m = new meethelper(s);
                    book.child("videomeet").setValue(m);
                    Toast.makeText(consultation.this, "meet Link Sent", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}