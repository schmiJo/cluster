package com.media.cluster.cluster.Login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.Main.MainActivity;
import com.media.cluster.cluster.R;


import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText loginNameEditText , loginPassEditText;
    private static final String URL = "http://social-cluster.com/user_login.php";

    Button loginSubmit;
    private Button fab;
    private ViewGroup layout;
    private TextView errorMessage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        layout = (ViewGroup) findViewById(R.id.login_layout);
        loginNameEditText = (EditText) findViewById(R.id.login_username_input);
        loginPassEditText = (EditText) findViewById(R.id.login_password_input);
        loginSubmit = (Button) findViewById(R.id.loginSubmitButton);
        fab = (Button) findViewById(R.id.loginRegisterButton);
        errorMessage = (TextView) findViewById(R.id.loginErrorMessage);

        loginPassEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_DONE){
                    loginIn();


                }
                return false;
            }
        });

    }

    public void passwordForgotClick(View view){
        Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
    }

    public void sumbitLoginInfo(View view){
        loginIn();

    }

    public void loginFabClicked(View view){
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
       closeActivity();
    }

    public void makeSucessToast(String firstname){
        Toast.makeText(getApplicationContext(),firstname, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {

        if(getCurrentFocus()!=null) {
            Snackbar.make(getCurrentFocus(), getResources().getString(R.string.loginAccessDenied), Snackbar.LENGTH_LONG).show();

        }
    }

    public void loginIn(){
        RequestQueue requestQueue;
        StringRequest stringRequest;

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                if(loginNameEditText.getText().toString().trim().equals("")|| loginPassEditText.getText().toString().trim().equals("")) {
                    errorMessage.setText(getResources().getString(R.string.loginErrorMessage1));
                    errorMessage.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(layout);
                }else if(response.equals("successful")) {
                    GetUserData.getFirstname(getApplicationContext(), loginNameEditText.getText().toString().trim());
                    errorMessage.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(layout);


                    SharedPreferences login = getApplicationContext().getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor loginEdit = login.edit();
                    loginEdit.putString("clustername",loginNameEditText.getText().toString().trim());
                    loginEdit.putString("password",loginPassEditText.getText().toString().trim());
                    loginEdit.apply();


                    GetUserData.getProfileID(getApplicationContext(),loginNameEditText.getText().toString().trim(), GetUserData.Usage.Login);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            closeActivity();
                        }
                    }, 200);
                }else if(response.equals("unsuccessful")){
                    errorMessage.setText(getResources().getString(R.string.loginErrorMessage3));
                    errorMessage.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(layout);
                }else if(response.equals("not exist")){
                    errorMessage.setText(getResources().getString(R.string.loginErrorMessage2));
                    errorMessage.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(layout);
                }else{
                    errorMessage.setText(getResources().getString(R.string.loginErrorMessage4));
                    errorMessage.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(layout);
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("clustername",loginNameEditText.getText().toString());
                hashMap.put("password",loginPassEditText.getText().toString());

                return hashMap;
            }

        };


        requestQueue.add(stringRequest);

    }


    @Override
    protected void onRestart() {
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        final String clustername = loginPref.getString("clustername", "");
        final String password = loginPref.getString("password", "");

        if (!clustername.equals("") && !password.equals("")) {
           closeActivity();
        }
    }

    private void closeActivity(){
        finish();
    }
}
