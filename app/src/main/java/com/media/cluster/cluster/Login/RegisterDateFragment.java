package com.media.cluster.cluster.Login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.media.cluster.cluster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterDateFragment extends Fragment {

    static EditText registerMonthEditText, registerGenderEditText ,registerYearEditText, registerDayEditText;
    View layout;
    enum Month {JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEP,OKT,NOV,DEC}
    private static String month;
    private static String year;
    private static String day;
    enum Gender{MALE,FEMALE,OTHER, ALIEN}
    static private Gender gender;


    static TextView dateErrorMessage ,genderErrorMessage;

    public RegisterDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_register_date, container, false);
        dateErrorMessage = (TextView)layout.findViewById(R.id.date_error_message);
        genderErrorMessage = (TextView)layout.findViewById(R.id.gender_error_message);

        registerDayEditText = (EditText) layout.findViewById(R.id.register_date_day_edit_text);
        registerDayEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RegisterActivity.DateSetNextButtonOnClickListener();
                checkDate();
            }
        });
        registerYearEditText = (EditText) layout.findViewById(R.id.register_date_year_edit_text);
        registerYearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RegisterActivity.DateSetNextButtonOnClickListener();
                checkDate();
            }
        });

        registerMonthEditText = (EditText)layout.findViewById(R.id.register_date_month_edit_text);
        registerMonthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),RegisterSelectMonthActivity.class));}});




        registerGenderEditText = (EditText)layout.findViewById(R.id.register_date_gender_edit_text);
        registerGenderEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterSelectGenderActivity.class));}});

        return layout;
    }

    public void setMonth(Month monthIntern, Context context){
        if(monthIntern == Month.JAN){registerMonthEditText.setText(context.getResources().getString(R.string.Jan_s));month = "01";}
        else if(monthIntern == Month.FEB){registerMonthEditText.setText(context.getResources().getString(R.string.Feb_s));month = "02";}
        else if(monthIntern == Month.MAR){registerMonthEditText.setText(context.getResources().getString(R.string.Mar_s));month = "03";}
        else if(monthIntern == Month.APR){registerMonthEditText.setText(context.getResources().getString(R.string.Apr_s));month = "04";}
        else if(monthIntern == Month.MAY){registerMonthEditText.setText(context.getResources().getString(R.string.May_s));month = "05";}
        else if(monthIntern == Month.JUN){registerMonthEditText.setText(context.getResources().getString(R.string.Jun_s));month = "06";}
        else if(monthIntern == Month.JUL){registerMonthEditText.setText(context.getResources().getString(R.string.Jul_s));month = "07";}
        else if(monthIntern == Month.AUG){registerMonthEditText.setText(context.getResources().getString(R.string.Aug_s));month = "08";}
        else if(monthIntern == Month.SEP){registerMonthEditText.setText(context.getResources().getString(R.string.Sep_s));month = "09";}
        else if(monthIntern == Month.OKT){registerMonthEditText.setText(context.getResources().getString(R.string.Okt_s));month = "10";}
        else if(monthIntern == Month.NOV){registerMonthEditText.setText(context.getResources().getString(R.string.Nov_s));month = "11";}
        else if(monthIntern == Month.DEC){registerMonthEditText.setText(context.getResources().getString(R.string.Dec_s));month = "12";}
        RegisterActivity.DateSetNextButtonOnClickListener();
        checkDate();
    }

    public  void setGender(Gender genderIntern, Context context){
        if(genderIntern == Gender.MALE){registerGenderEditText.setText(context.getResources().getString(R.string.genderMale)); gender = Gender.MALE;}
        else if(genderIntern == Gender.FEMALE){registerGenderEditText.setText(context.getResources().getString(R.string.genderFemale)); gender = Gender.FEMALE;}
        else if(genderIntern == Gender.OTHER){registerGenderEditText.setText(context.getResources().getString(R.string.genderOther)); gender = Gender.OTHER;}
        else if(genderIntern == Gender.ALIEN){registerGenderEditText.setText(context.getResources().getString(R.string.genderAlien)); gender = Gender.ALIEN;}
        RegisterActivity.DateSetNextButtonOnClickListener();
        checkDate();
    }
   static public void  setDateError(boolean valid) {
        if(valid) {
            dateErrorMessage.setVisibility(View.VISIBLE);
        }else {
            dateErrorMessage.setVisibility(View.INVISIBLE);

        }
       }

    public static void  setGenderError(boolean valid){
        if(valid){
            genderErrorMessage.setVisibility(View.VISIBLE);
            RegisterActivity.setButtonGrey();
        }else{
            genderErrorMessage.setVisibility(View.INVISIBLE);
        }
    }

    public static boolean checkDate(){
        if (!registerMonthEditText.getText().toString().trim().equals("") && !registerDayEditText.getText().toString().trim().equals("") && !registerYearEditText.getText().toString().trim().equals("") ){
            if (Integer.parseInt(registerDayEditText.getText().toString().trim()) < 32 && !(Integer.parseInt(registerYearEditText.getText().toString().trim()) < 1909) && !(Integer.parseInt(registerYearEditText.getText().toString().trim())> 2016 )) {
                if(checkGender()){
                    RegisterActivity.setButtonGrey();
                }else{

                    setDateIntern(registerDayEditText.getText().toString().trim(),registerYearEditText.getText().toString().trim());
                    RegisterActivity.setButtonWhite();
                }
                return true;
            }

        }
        RegisterActivity.setButtonGrey();
        return false;

    }



    public static boolean checkGender(){
        if(registerGenderEditText.getText().toString().trim().equals("")){
        RegisterActivity.setButtonGrey();
        return true;
        }else {
        return false;
        }
    }

    public static Gender getGender() {
        return gender;
    }

    private static void setDateIntern(String dayInput, String yearInput){
        year = yearInput;
        day = dayInput;
    }
    public static String Date(){
        return year + month + day;
    }
}


