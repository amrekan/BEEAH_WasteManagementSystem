package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mehmood_beeah_wastemanagementsystem.CusDeleteAccount;
import com.example.mehmood_beeah_wastemanagementsystem.FindBinLocator;
import com.example.mehmood_beeah_wastemanagementsystem.Home;
import com.example.mehmood_beeah_wastemanagementsystem.R;
import com.example.mehmood_beeah_wastemanagementsystem.SelectUser;
import com.example.mehmood_beeah_wastemanagementsystem.ViewSchedule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHome extends AppCompatActivity {

    Button add_bin, view_bin, del_bin, view_comp, view_feed, delete_account, logout;

    FirebaseAuth firebaseAuth;;
    FirebaseUser user;
    public static final String SHARED_PREFS= "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        add_bin= findViewById(R.id.add_bin);
        view_comp= findViewById(R.id.view_comp);
        view_feed= findViewById(R.id.view_feed);
        delete_account= findViewById(R.id.delete_account1);
        view_bin= findViewById(R.id.view_bin);
        del_bin= findViewById(R.id.del_bin);
        logout= findViewById(R.id.logout1);

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        add_bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AddBinLocator.class));
                finish();
            }
        });

        view_bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminViewBinLocation.class));
                finish();
            }
        });

        del_bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminDeleteBinLocation.class));
                finish();
            }
        });

        view_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, ViewComplaint.class));
                finish();
            }
        });

        view_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, ViewFeedback.class));
                finish();
            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminDeleteAccount.class));
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.commit();
                firebaseAuth.signOut();
                finish();
                Toast.makeText(AdminHome.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminHome.this, SelectUser.class));
            }
        });

    }
    @Override
    public void onBackPressed() {
        backButtonHandler();
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                AdminHome.this);
        alertDialog.setTitle("Leave application?");
        alertDialog.setMessage("Are you sure you want to leave the application?");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setPositiveButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}