package com.zibbix.docapp.docapp;

import android.app.Activity;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Patient_Activity extends Activity {
    TextView tv;
    EditText et;
    CheckBox cb;
    Spinner sp,sp1;
    Button bt;
    MultiAutoCompleteTextView MultipleValuesholdt,mv2;
    String[] multi={"nachu","jmk","google","android"};
    String[] MultipleTextStringValue = { "Android","Android-MultiAutoCompleteTextView","Android Top Tutorials" };
   //spinners
    String Dept[]={"Gyno","Nuero","Mri"};
    String m[]={"Dr.Arun","Dr.Binu","Dr.Sindhu"};
    String b[]={"Dr.zim","Dr.Zam","Dr.zimba"};
    String u[]={"Dr.Jimbru","Dr.fai","Dr.Fadhiya"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_);
        bt=(Button)findViewById(R.id.button3);
        tv=(TextView)findViewById(R.id.tv3);
        et=(EditText)findViewById(R.id.editText);
        cb=(CheckBox)findViewById(R.id.checkBox);
        sp=(Spinner)findViewById(R.id.spinner);
        sp1=(Spinner)findViewById(R.id.spinner2);
        sp.setVisibility(View.GONE);
        sp1.setVisibility(View.GONE);


//
        final ArrayAdapter<String> ia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Dept);

        final ArrayAdapter<String> ma = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);

        final ArrayAdapter<String> ba = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, b);

        final ArrayAdapter<String> ua = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, u);

        ia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ia);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (parent.getId()) {
                    case R.id.spinner: {
                        if (Dept[position].equals("Gyno")) {

                            ma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp1.setAdapter(ma);

                        }
                        if (Dept[position].equals("Nuero")) {

                            ba.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp1.setAdapter(ba);

                        }
                        if (Dept[position].equals("Mri")) {

                            ua.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp1.setAdapter(ua);

                        }

                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

mv2=(MultiAutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> TopicName1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, multi);
        mv2.setAdapter(TopicName1);
        mv2.setThreshold(2);
        mv2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            MultipleValuesholdt = (MultiAutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
            ArrayAdapter<String> TopicName = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MultipleTextStringValue);
            MultipleValuesholdt.setAdapter(TopicName);
            MultipleValuesholdt.setThreshold(2);
            MultipleValuesholdt.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    sp.setVisibility(View.VISIBLE);
                    sp1.setVisibility(View.VISIBLE);
                    // perform logic
                }
                else
                {
                    sp.setVisibility(View.INVISIBLE);
                    sp1.setVisibility(View.INVISIBLE);
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}

