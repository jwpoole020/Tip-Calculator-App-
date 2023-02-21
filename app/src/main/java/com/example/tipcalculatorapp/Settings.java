package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    EditText editTip;
    EditText editParty;
    CheckBox checkBox;

    private int tip=15;
    private int people=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTip = findViewById(R.id.editTip);
        editParty = findViewById(R.id.editParty);
        checkBox = findViewById(R.id.checkBox);



        editTip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    tip = Integer.parseInt(editTip.getText().toString());

                }
                return false;
            }
        });

        editParty.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if( i==EditorInfo.IME_ACTION_DONE) {
                    people = Integer.parseInt(editParty.getText().toString());

                }
                return false;
            }
        });


    }
    @Override
    public void onPause(){
        super.onPause();
        updateSharedPreferences();
    }

    private void updateSharedPreferences(){

        SharedPreferences sp = getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("tip", tip);
        editor.putInt("people", people);
        editor.putBoolean("split", checkBox.isChecked());
        editor.commit();
    }
}
