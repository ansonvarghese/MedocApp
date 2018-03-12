package com.zibbix.docapp.docapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        Intent intent = getIntent();

        final int counter = intent.getExtras().getInt("counter");
        DatabaseReference databaseRefDoc = FirebaseDatabase.getInstance().getReference().child("Doctors").child(currentFirebaseUser.getUid());
        databaseRefDoc.child("counter").setValue(counter);
    }
}
