package com.example.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class bookinclinic extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText number;
    RadioGroup r1;
    RadioButton rb1;

    Button bookappointment;
    int counti ,countv;


    DatabaseReference bookinclinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinclinic);

        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.surname);
        email = findViewById(R.id.emailid);
        number = findViewById(R.id.mobilenumber);
        bookappointment = findViewById(R.id.book1);
        r1 = findViewById(R.id.rg);




        bookinclinic = FirebaseDatabase.getInstance().getReference();
        Query q = bookinclinic.child("counter");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                counti = snapshot.child("inclinic").getValue(Integer.class);
                countv = snapshot.child("video").getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        bookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertdata();
            }


        });

    }
    private void insertdata() {
        String name = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String emailid = email.getText().toString();
        String pnumber = number.getText().toString();
        String cat = "";
        int id= r1.getCheckedRadioButtonId();
        rb1 = findViewById(id);
        if (id == -1) {
            Toast.makeText(bookinclinic.this, "appointment category is not selected", Toast.LENGTH_SHORT).show();

        }else {
            cat = rb1.getText().toString();


            if(cat.equals("inclinic")){
                if(counti<20) {
                    inclinic inclinic = new inclinic(name, lname, emailid, pnumber, cat);

                    bookinclinic.child(pnumber).child(pnumber).setValue(inclinic);
                    bookinclinic.child("Doctor").child(name).setValue(inclinic);
                    Toast.makeText(bookinclinic.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                    counti = counti + 1;
                    bookinclinic.child("counter").child("inclinic").setValue(counti);
                }else{
                    Toast.makeText(bookinclinic.this, "inclinic appointments are fully booked", Toast.LENGTH_SHORT).show();
                }

            }
            if(cat.equals("video")){
                if(countv<20) {
                    inclinic inclinic = new inclinic(name, lname, emailid, pnumber, cat);

                    bookinclinic.child(pnumber).child(pnumber).setValue(inclinic);
                    bookinclinic.child("Doctor").child(name).setValue(inclinic);
                    Toast.makeText(bookinclinic.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                    countv = countv + 1;
                    bookinclinic.child("counter").child("video").setValue(countv);
                }else{
                    Toast.makeText(bookinclinic.this, "video appointments are fully booked", Toast.LENGTH_SHORT).show();
                }
            }

        }



    }
}


