package com.zibbix.docapp.docapp;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Patient_Activity extends Activity {
    TextView tv;
    EditText et;

    Button bt;
    final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


    //spinners

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_);

        Intent intent = getIntent();
        final HashMap<String, String> appointhash = (HashMap<String, String>)intent.getSerializableExtra("appointhash");
        final int counter = intent.getExtras().getInt("counter");
        DatabaseReference databaseRefDoc = FirebaseDatabase.getInstance().getReference().child("Doctors").child(currentFirebaseUser.getUid());
        databaseRefDoc.child("counter").setValue(counter);

        bt = (Button) findViewById(R.id.button3);
        tv = (TextView) findViewById(R.id.tv3);
        et = (EditText) findViewById(R.id.editText);
        String[] words=new String[] {
                "word1", "word2", "word3", "word4", "word5"
        };

        final MultiAutoCompleteTextView symp = (MultiAutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> aaStr = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,words);
        symp.setAdapter(aaStr);
        symp.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer() );


        String[] words1=new String[] {
                "word1", "word2", "word3", "word4", "word5"
        };

        final MultiAutoCompleteTextView pres = this.findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> aaStr1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,words1);
        pres.setAdapter(aaStr1);
        pres.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer() );


        final String appoint = appointhash.get(Integer.toString(counter));
        DatabaseReference databaseRefappoint = FirebaseDatabase.getInstance().getReference().child("Appointments").child(appoint).child("Patient Name");
        databaseRefappoint.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseRefConsult = FirebaseDatabase.getInstance().getReference().child("Consultation").child(appoint);
                databaseRefConsult.child("Prescriptiton").setValue(pres.getText().toString());
                databaseRefConsult.child("Symptoms").setValue(symp.getText().toString());
                databaseRefConsult.child("Patient Name").setValue(tv.getText());
                DatabaseReference databaseRefDoc = FirebaseDatabase.getInstance().getReference().child("Doctors").child(currentFirebaseUser.getUid());
                databaseRefDoc.child("counter").child(Integer.toString(counter));


                if(counter == appointhash.size()){
                    Intent intent = new Intent(Patient_Activity.this,OverActivity.class);
                    intent.putExtra("counter", counter+1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Patient_Activity.this,Patient_Activity.class);
                    intent.putExtra("appointhash", appointhash);
                    intent.putExtra("counter", counter+1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

}

