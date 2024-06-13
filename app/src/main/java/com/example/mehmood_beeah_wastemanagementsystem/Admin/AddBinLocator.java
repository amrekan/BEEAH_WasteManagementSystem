package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mehmood_beeah_wastemanagementsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBinLocator extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText bin_name, bin_address;

    String b_name, b_address;

    Button b_complete;

    private FirebaseAuth firebaseAuth;
    NetworkInfo nInfo;
    Spinner spinner_bin;
    String selectedBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bin_locator);

        bin_name= findViewById(R.id.bin_name);
        bin_address = findViewById(R.id.bin_add);
        b_complete= (Button) findViewById(R.id.b_complete);

        firebaseAuth= FirebaseAuth.getInstance();
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        nInfo = cManager.getActiveNetworkInfo();

        spinner_bin = (Spinner) findViewById(R.id.spinner_bin);
        String[] comps = getResources().getStringArray(R.array.waste_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, comps);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bin.setAdapter(arrayAdapter);
        spinner_bin.setOnItemSelectedListener(this);

        b_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (valSpin()){
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("BinLocations");
                            UserBinLocator userBinLocator = new UserBinLocator(selectedBin, b_name, b_address);
                            ref.child(ref.push().getKey()).setValue(userBinLocator);
                            Toast.makeText(AddBinLocator.this, "Bin Location Successfully Added", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddBinLocator.this, AdminHome.class));
                            finish();
                        }
                    }
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedBin = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), selectedBin, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private Boolean validate(){
        boolean result= false;

        b_name = bin_name.getText().toString();
        b_address= bin_address.getText().toString();

        if(b_name.isEmpty() || (b_address.isEmpty())){
            Toast.makeText(this, "Enter all required details", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean valSpin(){
        boolean result= false;

        if (selectedBin.equals("Select waste type")) {
            Toast.makeText(this, "First select waste type", Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;

    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        startActivity(new Intent(AddBinLocator.this, AdminHome.class));
        finish();

    }
}