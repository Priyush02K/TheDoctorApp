package com.example.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class patnlogin1 extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patnlogin1);

        enternumber = findViewById(R.id.inputnumber);
        getotpbutton = findViewById(R.id.getotp);

        ProgressBar progressBar = findViewById(R.id.progressbarsendotp);

        getotpbutton.setOnClickListener(v -> {
            if (!enternumber.getText().toString().trim().isEmpty()) {
                if ((enternumber.getText().toString().trim()).length()==10) {

                    progressBar.setVisibility(View.VISIBLE);
                    getotpbutton.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + enternumber.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            patnlogin1.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);
                                    Toast.makeText(patnlogin1.this, "e.getMessage", Toast.LENGTH_SHORT).show();


                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    progressBar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(patnlogin1.this,patnlogin2.class);
                                    intent.putExtra("mobile",enternumber.getText().toString());
                                    intent.putExtra("backendotp",backendotp);

                                    startActivity(intent);

                                }
                            }
                    );




                }else {
                    Toast.makeText(patnlogin1.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(patnlogin1.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

