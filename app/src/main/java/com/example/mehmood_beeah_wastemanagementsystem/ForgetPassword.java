package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText for_email;
    Button update_pass;
    NetworkInfo nInfo;
    String email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        for_email= (EditText) findViewById(R.id.for_email);
        update_pass= (Button) findViewById(R.id.update_pass);

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();
        firebaseAuth= FirebaseAuth.getInstance();

        update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nInfo!=null && nInfo.isConnected()) {
                    email = for_email.getText().toString();

                    if (email.isEmpty()){
                        Toast.makeText(ForgetPassword.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
                    }else {
                        forgetPassword();
                    }
                } else {
                    Toast.makeText(ForgetPassword.this, "Network is not available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void forgetPassword(){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this,"Check your Email!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPassword.this, Login.class));
                    finish();

                }else {
                    Toast.makeText(ForgetPassword.this,"Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgetPassword.this, Login.class));
        finish();
    }
}