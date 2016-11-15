package com.media.cluster.cluster.Login;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.media.cluster.cluster.ClusterDBConnect.ImplementUserData;
import com.media.cluster.cluster.R;

public class AddServiceDataActivity extends AppCompatActivity {

    private ImageView serviceIcon;
    private TextView serviceText;
    private TextView errorMessage;
    private EditText serviceUsername, servicePassword;
    private String currentService;
    private Button Submit;
    AddServicesActivity.Service currentServiceEnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_data);

        Submit = (Button) findViewById(R.id.add_service_data_login_button);
        errorMessage = (TextView)findViewById(R.id.add_service_data_error_message);
        serviceIcon = (ImageView)findViewById(R.id.add_service_data_service_ic);
        serviceText = (TextView)findViewById(R.id.add_service_data_service_text);
        serviceUsername = (EditText)findViewById(R.id.add_service_data_login_username);
        servicePassword = (EditText)findViewById(R.id.add_service_data_login_password);
        Intent i = new Intent(getIntent());
        currentService = i.getStringExtra("service");

        switch (currentService){
            case "facebook":
                serviceIcon.setImageResource(R.drawable.speech_service_ic_facebook);
                serviceText.setText(getResources().getString(R.string.facebook));

                break;
            case "twitter":
                serviceIcon.setImageResource(R.drawable.speech_service_ic_twiter);
                serviceText.setText(getResources().getString(R.string.twitter));
                Submit.setVisibility(View.GONE);

                break;
            case "tumblr":
                serviceIcon.setImageResource(R.drawable.speech_service_ic_tumblr);
                serviceText.setText(getResources().getString(R.string.tumblr));

                break;
            case "skype":
                serviceIcon.setImageResource(R.drawable.speech_service_ic_skype);
                serviceText.setText(getResources().getString(R.string.skype));
                break;
        }


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(servicePassword.getText().toString().trim().equals("") || serviceUsername.getText().toString().trim().equals("")){
                    //Todo: check from api
                    Animation fadeErrorMessage = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                    errorMessage.setAnimation(fadeErrorMessage);
                    errorMessage.setVisibility(View.VISIBLE);
                }else{
                    SharedPreferences loginPref = getSharedPreferences("userLoginInfo",MODE_PRIVATE);
                    final String clustername = loginPref.getString("clustername","");
                    final String password = loginPref.getString("password","");

                    errorMessage.setVisibility(View.GONE);
                    switch (currentService){
                        case "facebook":
                            currentServiceEnum = AddServicesActivity.Service.FACEBOOK;
                            ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.FACEBOOK_EMAIL,serviceUsername.getText().toString().trim(),clustername,password);
                            finish();
                        case "tumblr":
                            currentServiceEnum = AddServicesActivity.Service.TUMBLR;
                            ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.TUMBLR_USERNAME,serviceUsername.getText().toString().trim(),clustername,password);
                            finish();
                            break;
                        case "skype":
                            currentServiceEnum = AddServicesActivity.Service.SKYPE;
                            ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.SKYPE_USERNAME,serviceUsername.getText().toString().trim(),clustername,password);
                            finish();
                            break;

                    }

                    AddServicesActivity.addRow(currentServiceEnum,serviceUsername.getText().toString().trim(), getApplicationContext(),true);


                }
            }
        });
    }

    public void addServiceDataCancel(View view){

         AddServicesActivity.setIconVisible(currentService,getApplicationContext(),true);

                finish();

    }
    public void addServiceCardviewClick(View view){

    }
}
