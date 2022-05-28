package com.example.ar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity  {
//implements View.OnClickListener
    private FirebaseAuth mAuth;

    TextView banner;
    EditText fullname,username,email, phoneno, password;
    Button reg_btn,reg_login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        fullname = (EditText) findViewById(R.id.full_name);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phoneno = (EditText) findViewById(R.id.phonenoo);
        password = (EditText) findViewById(R.id.password);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        reg_login_btn = (Button) findViewById(R.id.reg_login_btn);

        banner = (TextView) findViewById(R.id.bannerStarted);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }


        });

        // after all the details are filled clicking this button with register the use

        reg_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_up.this, login.class);
                startActivity(intent);
            }


        });




    }
    // register page requirement fields to be filled
        public void registerUser () {

            String full_name = fullname.getText().toString().trim();
            String userName = username.getText().toString().trim();
            String Email = email.getText().toString().trim();
            String phoneNo = phoneno.getText().toString().trim();
            String Password = password.getText().toString().trim();

            if (full_name.isEmpty()) {
                fullname.setError("This field can't be empty");
                fullname.requestFocus();
                return;
            }
            if (userName.isEmpty()) {
                username.setError("This field can't be empty");
                username.requestFocus();
                return;
            }
            if (Email.isEmpty()) {
                email.setError("This field can't be empty");
                email.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                email.setError("Provide a valid email address");
                email.requestFocus();
                return;
            }
            if (phoneNo.isEmpty()) {
                phoneno.setError("This field can't be empty");
                phoneno.requestFocus();
                return;
            }
            if (Password.isEmpty()) {
                password.setError("This field can't be empty");
                password.requestFocus();
                return;
            }
            if (Password.length() < 6) {
                password.setError("Password should be atleast 6 characters");
                password.requestFocus();
                return;
            }


            // storing of information provided by user to firebase and displaying toast messages

            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(sign_up.this, "User Created.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), login.class));

                                } else {
                                    Toast.makeText(sign_up.this, "Error ! Try again" , Toast.LENGTH_SHORT).show();
//                                    + Objects.requireNonNull(task.getException()).getMessage()

                                }
//                            }
//                        });

                    } else {
                        Toast.makeText(sign_up.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }


            });


        }


}

