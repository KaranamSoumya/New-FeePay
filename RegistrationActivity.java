package com.example.dell.feepay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText userName,userEmail,userPassword;
    private Button register;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(validate()) {
                   String username = userName.getText().toString().trim();
                   String useremail = userEmail.getText().toString().trim();
                   String userpassword = userPassword.getText().toString().trim();

                   mAuth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(RegistrationActivity.this, "Registartion Successfull", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                           } else {
                               Toast.makeText(RegistrationActivity.this, "Registartion Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
           }
       });

    }

    private void setupUIViews() {
        userName = (EditText)findViewById(R.id.etUserName);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        register = (Button)findViewById(R.id.btnRegister);
        login = (TextView)findViewById(R.id.tvLogin);
    }

    private Boolean validate() {
        Boolean result = false;
        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if((name.isEmpty())|| (email.isEmpty()) || (password.isEmpty())) {
            Toast.makeText(this, "please Enter all the Details",Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
