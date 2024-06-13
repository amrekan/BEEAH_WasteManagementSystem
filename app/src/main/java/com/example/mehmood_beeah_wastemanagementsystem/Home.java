package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    Button add, delete, view, places, waste, add_comp, add_feed, delete_account, logout;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    public static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add= findViewById(R.id.add);
        delete= findViewById(R.id.delete);
        view= findViewById(R.id.view);
        places= findViewById(R.id.places);
        waste= findViewById(R.id.waste);
        add_comp= findViewById(R.id.add_comp);
        add_feed= findViewById(R.id.add_feed);
        delete_account= findViewById(R.id.delete_account);
        logout= findViewById(R.id.logout);

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddSchedule.class));
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, DeleteSchedule.class));
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ViewSchedule.class));
                finish();
            }
        });

        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, FindBinLocator.class));
                finish();
            }
        });

        waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ViewWasteGuidance.class));
                finish();
            }
        });

        add_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddComplain.class));
                finish();
            }
        });

        add_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddFeedback.class));
                finish();
            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, CusDeleteAccount.class));
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
                Toast.makeText(Home.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, SelectUser.class));
            }
        });

    }
    @Override
    public void onBackPressed() {
        backButtonHandler();
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Home.this);
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