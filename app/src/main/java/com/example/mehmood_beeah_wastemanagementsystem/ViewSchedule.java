package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class ViewSchedule extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageAdapterViewSchedule mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<Model> mUploads;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;
    String userName, prodName;
    String im_name, im_price, im_time, im_detail, im_pic, key;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterViewSchedule(ViewSchedule.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Tasks");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.exists()){
                        Model model= postSnapshot.getValue(Model.class);
                        model.setKey(postSnapshot.getKey());
                        mUploads.add(model);
                    }if (!postSnapshot.exists()){
                        Toast.makeText(ViewSchedule.this, "No Schedule is set yet", Toast.LENGTH_SHORT).show();
                    }
                    }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewSchedule.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(ViewSchedule.this, Home.class));
        finish();
    }
}