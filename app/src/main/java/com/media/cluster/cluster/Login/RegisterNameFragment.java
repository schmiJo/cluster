package com.media.cluster.cluster.Login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.media.cluster.cluster.R;

public class RegisterNameFragment extends Fragment {

    EditText FirstNameInput , LastNameInput;
    TextView FirstNameError, LastNameError;
    View Layout;
    RegisterActivity registerActivity;
    public RegisterNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       Layout =  inflater.inflate(R.layout.fragment_register_name, container, false);
        registerActivity = new RegisterActivity();
        FirstNameError = (TextView)Layout.findViewById(R.id.first_name_error);
        LastNameError = (TextView)Layout.findViewById(R.id.last_name_error);

        FirstNameInput = (EditText)Layout.findViewById(R.id.register_first_name_input);
        LastNameInput = (EditText)Layout.findViewById(R.id.register_last_name_input);


        FirstNameInput.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(FirstNameInput.getText().toString().trim().equals("")){
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        FirstNameError.setVisibility(View.VISIBLE);
                        return true;
                    }
                }else {
                    FirstNameError.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        LastNameInput.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(LastNameInput.getText().toString().trim().equals("")){
                    if (actionId == EditorInfo.IME_ACTION_GO) {
                        LastNameError.setVisibility(View.VISIBLE);
                        return true;
                    }
                }else {
                    LastNameError.setVisibility(View.INVISIBLE);
                    if(!FirstNameInput.getText().toString().trim().equals("")){
                        registerActivity.goToNext();
                    }

                }
                return false;
            }
        });





        LastNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                goToNext();
                if(LastNameInput.getText().toString().trim().equals("")){
                    LastNameError.setVisibility(View.VISIBLE);

                }else {
                    LastNameError.setVisibility(View.INVISIBLE);

                }
            }
        });

        FirstNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                goToNext();
                if(FirstNameInput.getText().toString().trim().equals("")){
                        FirstNameError.setVisibility(View.VISIBLE);
                }else {
                    FirstNameError.setVisibility(View.INVISIBLE);
                }

            }
        });


        return Layout;
    }

    public void goToNext(){
        if (!LastNameInput.getText().toString().trim().equals("") && !FirstNameInput.getText().toString().trim().equals("")) {
            registerActivity.setName(FirstNameInput.getText().toString().trim(), LastNameInput.getText().toString().trim());
        } else {
            registerActivity.notSetName();
        }
    }
}
