package com.example.ar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button login_btn, signup_btn;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.signup_screen);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                String userName = username.getText().toString().trim();
                String Password = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(userName, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), products.class));
                    Intent intent = new Intent(login.this, products.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


//                new login();
//                Intent intent = new Intent(login.this, products.class);
//                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, sign_up.class);
                startActivity(intent);
            }
        });

//        public void login(){
//
//            String userName = username.getText().toString().trim();
//            String Password = password.getText().toString().trim();
//
//            if(userName.isEmpty()){
//                username.setError("This field can't be empty");
//                username.requestFocus();
//                return;
//            }
//            if(Password.isEmpty()){
//                password.setError("This field can't be empty");
//                password.requestFocus();
//                return;
//            }
//            if(Password.length()<6){
//                password.setError("Password should be atleast 6 characters");
//                password.requestFocus();
//                return;
//            }
//        mAuth.signInWithEmailAndPassword(e_mail, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), products.class));
//                } else {
//                    Toast.makeText(login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        }

    }
}