package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ViewWasteGuidance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_waste_guidance);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewWasteGuidance.this, Home.class));
        finish();
    }
}