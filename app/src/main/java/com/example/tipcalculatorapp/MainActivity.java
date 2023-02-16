package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText purchasePrice;
    TextView totalPrice;
    TextView tipCost;
    TextView tipPer;
    Button button;
    SeekBar seekBar;
    TextView tipText;
    RadioGroup radioGroup;
    RadioButton radioEntire;
    RadioButton radioSplit;
    EditText numPeople;

    double tip=.15;
    double total=0.0;
    double value=0.0;
    double people=1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        purchasePrice = findViewById(R.id.purchasePrice);
        totalPrice = findViewById(R.id.totalPrice);
        tipCost = findViewById(R.id.tipCost);
        tipPer = findViewById(R.id.tipPer);
        button = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);
        tipText = findViewById(R.id.tipText);
        radioGroup = findViewById(R.id.radioGroup);
        radioEntire = findViewById(R.id.radioEntire);
        radioSplit = findViewById(R.id.radioSplit);
        numPeople = findViewById(R.id.numPeople);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipText.setText(i+"%");
                tip=i;
                tip=tip/100;
                total = (value + (value * tip)) / people;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Settings.class);
                startActivity(i);
            }
        });

        purchasePrice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    value = Double.parseDouble(purchasePrice.getText().toString());
                    total = (value + (value * tip)) / people;
                    tipPer.setText((tip*100)+"%");
                    totalPrice.setText("$" + String.format("%.2f", total));
                    tipCost.setText("$"+String.format("%.2f", (value*tip)));
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

        numPeople.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    if (radioEntire.isChecked()) {
                        people = 1.0;
                    } else if (radioSplit.isChecked()) {
                        people = Double.parseDouble(numPeople.getText().toString());
                    }
                }
                return false;
            }
        });


    }
}