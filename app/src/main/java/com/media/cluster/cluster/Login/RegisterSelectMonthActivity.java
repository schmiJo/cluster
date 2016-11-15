package com.media.cluster.cluster.Login;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.media.cluster.cluster.R;

public class RegisterSelectMonthActivity extends AppCompatActivity {

    RadioButton JanButton , FebButton, MarButton , AprButton , MayButton , JunButton , JulButton , AugButton , SepButton, OktButton,NovButton, DecButton;
    RegisterDateFragment registerDateFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_month);
        JanButton = (RadioButton) findViewById(R.id.register_radio_button_jan);
        FebButton = (RadioButton) findViewById(R.id.register_radio_button_feb);
        MarButton = (RadioButton) findViewById(R.id.register_radio_button_mar);
        AprButton = (RadioButton) findViewById(R.id.register_radio_button_apr);
        MayButton = (RadioButton) findViewById(R.id.register_radio_button_may);
        JunButton = (RadioButton) findViewById(R.id.register_radio_button_jun);
        JulButton = (RadioButton) findViewById(R.id.register_radio_button_jul);
        AugButton = (RadioButton) findViewById(R.id.register_radio_button_aug);
        SepButton = (RadioButton) findViewById(R.id.register_radio_button_sep);
        OktButton = (RadioButton) findViewById(R.id.register_radio_button_okt);
        NovButton = (RadioButton) findViewById(R.id.register_radio_button_nov);
        DecButton = (RadioButton) findViewById(R.id.register_radio_button_dec);

        registerDateFragment = new RegisterDateFragment();
    }

    public void Jan(View view){
        switchState(JanButton);
    }
    public void Feb(View view){
        switchState(FebButton);
    }
    public void Mar(View view){
        switchState(MarButton);
    }
    public void May(View view){
        switchState(MayButton);
    }
    public void Apr(View view){
        switchState(AprButton);
    }
    public void Jun(View view){
        switchState(JunButton);
    }
    public void Jul(View view){
        switchState(JulButton);
    }
    public void Aug(View view){
        switchState(AugButton);
    }
    public void Sep(View view){
        switchState(SepButton);
    }
    public void Okt(View view){
        switchState(OktButton);
    }
    public void Nov(View view){
        switchState(NovButton);
    }
    public void Dec(View view){
        switchState(DecButton);
    }

    private void switchState(RadioButton radioButton){
        Handler handler = new Handler();


            radioButton.setChecked(true);

        if(JanButton != radioButton){
            JanButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.JAN,getApplicationContext());
        }
        if(FebButton != radioButton){
            FebButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.FEB,getApplicationContext());
        }
        if(MarButton != radioButton){
            MarButton.setChecked(false);
        }else {
            registerDateFragment.setMonth(RegisterDateFragment.Month.MAR,getApplicationContext());
        }
        if(AprButton != radioButton){
            AprButton.setChecked(false);
        }else {
            registerDateFragment.setMonth(RegisterDateFragment.Month.APR,getApplicationContext());
        }
        if(MayButton != radioButton){
            MayButton.setChecked(false);
        }else {
            registerDateFragment.setMonth(RegisterDateFragment.Month.MAY,getApplicationContext());
        }
        if(JunButton!= radioButton){
            JunButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.JUN,getApplicationContext());
        }
        if(JulButton != radioButton){
            JulButton.setChecked(false);
        }else {
            registerDateFragment.setMonth(RegisterDateFragment.Month.JUL,getApplicationContext());
        }
        if(AugButton != radioButton){
            AugButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.AUG,getApplicationContext());
        }
        if(SepButton != radioButton){
            SepButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.SEP,getApplicationContext());
        }
        if( OktButton!= radioButton){
            OktButton.setChecked(false);
        }else {
            registerDateFragment.setMonth(RegisterDateFragment.Month.OKT,getApplicationContext());
        }
        if(NovButton != radioButton){
            NovButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.NOV,getApplicationContext());
        }
        if(DecButton != radioButton){
            DecButton.setChecked(false);
        }else{
            registerDateFragment.setMonth(RegisterDateFragment.Month.DEC,getApplicationContext());
        }

        handler.postDelayed(new Runnable() {

        @Override
        public void run() {
            finish();
        }

    }, 300);
}
}
