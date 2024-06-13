package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mehmood_beeah_wastemanagementsystem.DeleteSchedule;
import com.example.mehmood_beeah_wastemanagementsystem.ImageAdapterDeleteSchedule;
import com.example.mehmood_beeah_wastemanagementsystem.ImageAdapterViewBInLocation;
import com.example.mehmood_beeah_wastemanagementsystem.Model;
import com.example.mehmood_beeah_wastemanagementsystem.R;
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

public class AdminDeleteBinLocation extends AppCompatActivity implements ImageAdapterDeleteBinLocation.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private ImageAdapterDeleteBinLocation mAdapter;
    private DatabaseReference mDatabaseRef, ref1;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserBinLocator> mUploads;
    private ProgressBar mProgressCircle;
    String selectedKey;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_bin_location);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view6);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle6);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterDeleteBinLocation(AdminDeleteBinLocation.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AdminDeleteBinLocation.this);

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
                Toast.makeText(AdminDeleteBinLocation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        UserBinLocator selectedItem = mUploads.get(position);
        selectedKey = selectedItem.getKey();

        deleteBinLOcationFunction();

    }
    public void deleteBinLOcationFunction() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                AdminDeleteBinLocation.this);
        alertDialog.setTitle("Delete Location");
        alertDialog.setMessage("Are you sure you want to delete this location?");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ref1= FirebaseDatabase.getInstance().getReference("BinLocations").child(selectedKey);
                        ref1.getRef().removeValue();
                        Toast.makeText(AdminDeleteBinLocation.this, "Bin location deleted Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminDeleteBinLocation.this, AdminDeleteBinLocation.class));
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
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminDeleteBinLocation.this, AdminHome.class));
        finish();
    }
    }
