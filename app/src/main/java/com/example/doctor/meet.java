package com.example.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class meet extends AppCompatActivity {
    EditText text;
    Button btn;
    DatabaseReference book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);

        text = findViewById(R.id.textlink);
        btn = findViewById(R.id.sent);
        String str = text.getText().toString();
        book = FirebaseDatabase.getInstance().getReference();
        meethelper m = new meethelper(str);
        book.child("videomeet").setValue(m);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}