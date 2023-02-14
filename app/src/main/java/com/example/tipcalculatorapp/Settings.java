package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings extends AppCompatActivity {


    EditText numPeople;
    RadioGroup radioGroup;
    RadioButton radioEntire;
    RadioButton radioSplit;

    double tip=.15;
    double total;
    double value=0.0;
    double people=1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        numPeople = findViewById(R.id.numPeople);
        radioGroup = findViewById(R.id.radioGroup);
        radioEntire = findViewById(R.id.radioEntire);
        radioSplit = findViewById(R.id.radioSplit);



        numPeople.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    value = Double.parseDouble(purchasePrice.getText().toString());
                    if (radioEntire.isChecked()) {
                        total = value + (value * tip);

                    } else if (radioSplit.isChecked()) {
                        people = Double.parseDouble(numPeople.getText().toString());
                        total = (v
                    totalPrice.setText("$" + String.format("%.2f", total));
                    tipCost.setText("$"+String.format("%.2f", (value*tip)));alue + (value * tip)) / people;

                    }
                }
                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                value = Double.parseDouble(purchasePrice.getText().toString());
                if(i==R.id.radioEntire) {
                    people = 1.0;
                    total = (value + (value * tip)) / people;
                    totalPrice.setText("$" + String.format("%.2f", total));
                    tipCost.setText("$"+String.format("%.2f", (value*tip)));
                } else if(i==R.id.radioSplit){
                    numPeople.setText("");
                }
            }
        });
    }
}