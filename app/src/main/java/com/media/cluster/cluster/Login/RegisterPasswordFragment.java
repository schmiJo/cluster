package com.media.cluster.cluster.Login;


import android.graphics.Color;
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


public class RegisterPasswordFragment extends Fragment {
    View layout;
    static EditText passwordInput , passwordConfirmInput;
    static TextView passwordError;

    public RegisterPasswordFragment() {
        // Required empty public constructor
    }

    //Todo: replace all strings with resource reference
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_register_password, container, false);

        passwordConfirmInput = (EditText) layout.findViewById(R.id.register_password_confirm_input);
        passwordInput = (EditText)layout.findViewById(R.id.register_password_input);
        passwordError = (TextView)layout.findViewById(R.id.register_password_error);



        passwordConfirmInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPassword();
            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPassword();
            }
        });

    return layout;
    }


    static public void checkPassword(){
         String passwordInputString = passwordInput.getText().toString().trim();
         String passwordConfirmInputString = passwordConfirmInput.getText().toString().trim();
        if(passwordConfirmInputString.equals("") || passwordInputString.equals("")){
                 RegisterActivity.setButtonGrey();
                RegisterActivity.barNextButtonSetOnPasswordListener(RegisterActivity.PasswordErrors.ONE, null);
        }else if(passwordInputString.length() <8){
                RegisterActivity.setButtonGrey();
                RegisterActivity.barNextButtonSetOnPasswordListener(RegisterActivity.PasswordErrors.TWO,null);
        }else if(!passwordInputString.equals(passwordConfirmInputString)){
                RegisterActivity.setButtonGrey();
                RegisterActivity.barNextButtonSetOnPasswordListener(RegisterActivity.PasswordErrors.THREE,null);
        }else{
                RegisterActivity.setButtonWhite();
                RegisterActivity.barNextButtonSetOnPasswordListener(RegisterActivity.PasswordErrors.NONE, passwordInputString);
        }

    }

    //Todo: make translatable
    public static void passwordErrorCaseOne(){
        passwordError.setVisibility(View.VISIBLE);
        passwordError.setText("Please enter a Password");
        passwordError.setTextColor(Color.parseColor("#Dd0000"));
    }
    public static void passwordErrorCaseTwo(){
        passwordError.setVisibility(View.VISIBLE);
        passwordError.setText("Your Password has to be at least 8 characters long");
        passwordError.setTextColor(Color.parseColor("#Dd0000"));

    }
    public static void passwordErrorCaseTree(){
        passwordError.setVisibility(View.VISIBLE);
        passwordError.setText("Your password does not match");
        passwordError.setTextColor(Color.parseColor("#Dd0000"));

    }
    public static void passwordErrorCaseNone(){
        passwordError.setVisibility(View.INVISIBLE);
    }

}
