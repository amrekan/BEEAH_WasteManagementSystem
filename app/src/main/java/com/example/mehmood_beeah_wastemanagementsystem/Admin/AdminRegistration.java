package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehmood_beeah_wastemanagementsystem.Login;
import com.example.mehmood_beeah_wastemanagementsystem.R;
import com.example.mehmood_beeah_wastemanagementsystem.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminRegistration extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    NetworkInfo nInfo;
    private EditText email, password, c_password;
    private Button signup;
    String user_email, user_password, user_c_password;
    private TextView log;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        firebaseAuth= FirebaseAuth.getInstance();
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        nInfo = cManager.getActiveNetworkInfo();


        email= (EditText) findViewById(R.id.email1);
        password= (EditText) findViewById(R.id.password1);
        c_password= (EditText) findViewById(R.id.c_password1);
        signup= (Button) findViewById(R.id.signup1);
        log= (TextView) findViewById(R.id.log1);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    if (paswordlength()) {
                        if (paswordmatch()){
                            if(nInfo!=null && nInfo.isConnected()) {

                                String email_user = email.getText().toString().trim();
                                String pass_user = password.getText().toString().trim();

                                firebaseAuth.createUserWithEmailAndPassword(email_user, pass_user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(AdminRegistration.this, AdminLogin.class));
                                            Toast.makeText(AdminRegistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                            finish();

                                        } else {
                                            Toast.makeText(AdminRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(AdminRegistration.this, "Network is not available", Toast.LENGTH_LONG).show();}

                        }
                    }
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRegistration.this, AdminLogin.class));
                finish();
            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_email = email.getText().toString();
        user_password = password.getText().toString();
        user_c_password = c_password.getText().toString();
        if(user_c_password.isEmpty() || user_password.isEmpty() || user_email.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean paswordlength(){
        boolean result1= false;
        if (user_password.length() < 6 ){
            Toast.makeText(this, "Password lenght should be atleast 6", Toast.LENGTH_SHORT).show();
        }else{
            result1= true;
        }
        return result1;
    }

    private Boolean paswordmatch(){
        boolean result2= false;
        if (!user_password.equals(user_c_password)){
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        }else{
            result2= true;
        }
        return result2;
    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        startActivity(new Intent(AdminRegistration.this, AdminLogin.class));
        finish();
    }
}