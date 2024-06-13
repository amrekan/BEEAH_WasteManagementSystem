package com.example.mehmood_beeah_wastemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedback extends AppCompatActivity {

    TextView rateCount, showRating;
    EditText review, feed_ser_name;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String temp;
    String rev, r_count, r_ser_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        rateCount= (TextView)findViewById(R.id.rateCount);
        showRating= (TextView) findViewById(R.id.showRating);
        review= (EditText) findViewById(R.id.review);
        feed_ser_name= (EditText) findViewById(R.id.feedback_ser_name);
        submit= (Button) findViewById(R.id.submitBtn);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue= ratingBar.getRating();
                if (rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad "+rateValue + "/5");
                else if (rateValue<=2 && rateValue>1)
                    rateCount.setText("OK "+rateValue + "/5");
                else if (rateValue<=3 && rateValue>2)
                    rateCount.setText("Good "+rateValue + "/5");
                else if (rateValue<=4 && rateValue>3)
                    rateCount.setText("Very Good "+rateValue + "/5");
                else if (rateValue<=5 && rateValue>4)
                    rateCount.setText("Best "+rateValue + "/5");

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()){
                    temp= rateCount.getText().toString();
                    if (user != null) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Feedbacks");
                        UserFeedback userFeedback= new UserFeedback(r_ser_name, temp, rev);
                        ref.child(ref.push().getKey()).setValue(userFeedback);
                        Toast.makeText(AddFeedback.this, "Feedback Submitted Successfully", Toast.LENGTH_SHORT).show();
                        showRating.setText("Your Rating: \n" +"Username: " +r_ser_name+ "\nApp Rating: " +temp + "\nComment: " +review.getText());
                        review.setText("");
                        ratingBar.setRating(0);
                        rateCount.setText("");
                        feed_ser_name.setText("");
                    }
                }
            }
        });
    }

    private Boolean valid() {
        boolean result = false;
        rev = review.getText().toString();
        r_count= rateCount.getText().toString();
        r_ser_name= feed_ser_name.getText().toString();

        if (rev.isEmpty() || r_count.isEmpty() || r_ser_name.isEmpty()) {
            Toast.makeText(this, "Enter all the required details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddFeedback.this, Home.class));
        finish();
    }
}