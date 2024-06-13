package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mehmood_beeah_wastemanagementsystem.Admin.AdminHome;
import com.example.mehmood_beeah_wastemanagementsystem.Admin.AdminLogin;

public class SelectUser extends AppCompatActivity {
    Button customer, admin;
    public static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        customer= (Button) findViewById(R.id.customer);
        admin= (Button) findViewById(R.id.admin);

        checkBox();

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectUser.this, Login.class));
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectUser.this, AdminLogin.class));
                finish();
            }
        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check= sharedPreferences.getString("name", "");

        if (check.equals("customer")){
            startActivity(new Intent(SelectUser.this, Home.class));
            finish();
        }

        if (check.equals("admin")){
            startActivity(new Intent(SelectUser.this, AdminHome.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}