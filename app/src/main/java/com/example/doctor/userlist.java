package com.example.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    String number1;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        back = findViewById(R.id.back);
        String docp=getIntent().getStringExtra("name");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (docp.equals("patient")) {
                    Intent intent = new Intent(userlist.this, patndashboard.class);
                    intent.putExtra("us", number1);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(userlist.this, doctdashboard.class);
                    intent.putExtra("us", number1);
                    startActivity(intent);
                }
            }
        });


        if(docp.equals("patient"))
        {
            number1=getIntent().getStringExtra("number");
        }
        recyclerView = findViewById(R.id.userList);
        if(docp.equals("patient")) {
            database = FirebaseDatabase.getInstance().getReference(number1);
        }
        else
        {
            database = FirebaseDatabase.getInstance().getReference("Doctor");
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}