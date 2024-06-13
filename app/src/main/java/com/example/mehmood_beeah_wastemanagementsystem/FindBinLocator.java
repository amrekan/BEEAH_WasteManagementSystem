package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mehmood_beeah_wastemanagementsystem.Admin.AdminHome;
import com.example.mehmood_beeah_wastemanagementsystem.Admin.AdminViewBinLocation;
import com.example.mehmood_beeah_wastemanagementsystem.Admin.UserBinLocator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class FindBinLocator extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageAdapterViewBInLocation mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserBinLocator> mUploads;
    private ProgressBar mProgressCircle;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bin_locator);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view4);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle4);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterViewBInLocation(FindBinLocator.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("BinLocations");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.exists()){
                        UserBinLocator userBinLocator= postSnapshot.getValue(UserBinLocator.class);
                        userBinLocator.setKey(postSnapshot.getKey());
                        mUploads.add(userBinLocator);
                    }
                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FindBinLocator.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FindBinLocator.this, Home.class));
        finish();
    }
}