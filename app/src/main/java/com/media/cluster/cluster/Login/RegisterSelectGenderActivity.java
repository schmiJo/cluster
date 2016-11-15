package com.media.cluster.cluster.Login;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.media.cluster.cluster.R;

public class RegisterSelectGenderActivity extends AppCompatActivity {

    RadioButton ButtonMale, ButtonFemale, ButtonOther, ButtonAlien;
    RegisterDateFragment registerDateFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_gender);
        registerDateFragment = new RegisterDateFragment();
        ButtonMale = (RadioButton)findViewById(R.id.register_radio_button_male);
        ButtonFemale = (RadioButton)findViewById(R.id.register_radio_button_female);
        ButtonOther = (RadioButton)findViewById(R.id.register_radio_button_other);
        ButtonAlien = (RadioButton)findViewById(R.id.register_radio_button_alien);
    }

    public void Male(View view){

        switchState(ButtonMale);
    }
    public void Female(View view){
        switchState(ButtonFemale);
    }
    public void Other(View view){
        switchState(ButtonOther);
    }
    public void Alien(View view){switchState(ButtonAlien);}

    private void switchState(RadioButton radioButton){
        radioButton.setChecked(true);
        Handler handler = new Handler();
        if(radioButton != ButtonMale){
            ButtonMale.setChecked(false);
        }else{
            registerDateFragment.setGender(RegisterDateFragment.Gender.MALE, getApplicationContext());
        }
        if(radioButton != ButtonFemale){
            ButtonFemale.setChecked(false);
        }else{
            registerDateFragment.setGender(RegisterDateFragment.Gender.FEMALE, getApplicationContext());
        }
        if(radioButton != ButtonOther){
            ButtonOther.setChecked(false);
        }else{
            registerDateFragment.setGender(RegisterDateFragment.Gender.OTHER, getApplicationContext());
        }
        if(radioButton != ButtonAlien){
            ButtonAlien.setChecked(false);
        }else{
            registerDateFragment.setGender(RegisterDateFragment.Gender.ALIEN, getApplicationContext());
        }

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }

        }, 300);
    }
    }

