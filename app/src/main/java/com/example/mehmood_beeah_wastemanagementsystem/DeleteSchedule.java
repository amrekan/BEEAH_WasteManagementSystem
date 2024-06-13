package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class DeleteSchedule extends AppCompatActivity implements ImageAdapterDeleteSchedule.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterDeleteSchedule mAdapter;
    private DatabaseReference mDatabaseRef, ref1;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<Model> mUploads;
    private ProgressBar mProgressCircle;
    String selectedUID,selectedKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_schedule);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle1);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterDeleteSchedule(DeleteSchedule.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(DeleteSchedule.this);


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
                        Toast.makeText(DeleteSchedule.this, "No Schedule is set yet", Toast.LENGTH_SHORT).show();
                    }
                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DeleteSchedule.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onItemClick(int position) {
        Model selectedItem = mUploads.get(position);
        selectedKey = selectedItem.getKey();

        deleteScheduleFunction();

    }
    public void deleteScheduleFunction() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                DeleteSchedule.this);
        alertDialog.setTitle("Cancel Schedule");
        alertDialog.setMessage("Are you sure you want to cancel this schedule?");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ref1= FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Tasks").child(selectedKey);
                        ref1.getRef().removeValue();
                        Toast.makeText(DeleteSchedule.this, "Schedule Canceled Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DeleteSchedule.this, DeleteSchedule.class));
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DeleteSchedule.this, Home.class));
        finish();
    }
}