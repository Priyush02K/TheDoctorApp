package com.example.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Doctlogin extends AppCompatActivity {

    private EditText EmailAddress;
    private EditText Password;
    private Button btnLogin;


    private String Username = "Admin";
    private String PPassword = "Admin@123";

    boolean isValid = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctlogin);

        EmailAddress =findViewById(R.id.EmailAddress);
        Password =findViewById(R.id.Password);
        btnLogin =findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = EmailAddress.getText().toString();
                String inputPassword = Password.getText().toString();

                if (input.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(Doctlogin.this, "Enter all credentials", Toast.LENGTH_SHORT).show();
                }
                else {

                    isValid = validate(input,inputPassword);

                    if (!isValid){

                        Toast.makeText(Doctlogin.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Doctlogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Doctlogin.this,doctdashboard.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private boolean validate (String name,String Password){

        if (name.equals(Username) && Password.equals(PPassword)){
            return true;
        }

        return false;
    }
}