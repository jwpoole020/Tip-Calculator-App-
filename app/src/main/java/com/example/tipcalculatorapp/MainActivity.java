package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private double tip=.15;
    private int tipI=15;
    private double total=0.0;
    private double value=0.0;
    private double people=1.0;
    private boolean split = false;

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
                tipI=i;
                tipText.setText(tipI+"%");
                tip=tipI;
                tip=tip/100.0;
                total = (value + (value * tip)) / people;
                tipPer.setText(tipI+"%");
                total = (value + (value * tip)) / people;
                totalPrice.setText("$" + String.format("%.0f", total));
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
                    tipPer.setText(tipI+"%");
                    totalPrice.setText("$" + String.format("%.0f", total));
                    tipCost.setText("$"+String.format("%.2f", (value*tip)));}
                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                try {
                    value = Double.parseDouble(purchasePrice.getText().toString());
                    if (i == R.id.radioEntire) {
                        people = 1.0;
                        total = (value + (value * tip)) / people;
                        totalPrice.setText("$" + String.format("%.2f", total));
                        tipCost.setText("$" + String.format("%.2f", (value * tip)));
                    } else if (i == R.id.radioSplit) {
                        people = Double.parseDouble(numPeople.getText().toString());
                        total = (value + (value * tip)) / people;
                        totalPrice.setText("$" + String.format("%.2f", total));
                        tipCost.setText("$" + String.format("%.2f", (value * tip)));
                    }
                }catch(NumberFormatException error){}
            }
        });

        numPeople.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    if (radioSplit.isChecked()) {
                        people = Integer.parseInt(numPeople.getText().toString());
                        total = (value + (value * tip)) / people;
                        totalPrice.setText("$" + String.format("%.2f", total));
                        tipCost.setText("$"+String.format("%.2f", (value*tip)));
                    }
                }
                return false;
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();
        updateSettings();
    }

    public void updateSettings(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        tipI = sp.getInt("tip",15);
        people = sp.getInt("people", 1);
        split = sp.getBoolean("split", false);
        seekBar.setProgress(tipI);
        numPeople.setText(people+"");
        if (split==false){
            radioEntire.setChecked(true);
        } else{
            radioSplit.setChecked(true);
        }
    }
}