package com.media.cluster.cluster.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.media.cluster.cluster.ClusterDBConnect.ImplementUserData;
import com.media.cluster.cluster.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class TwitterLoginActivity extends AppCompatActivity {
    TwitterLoginButton twitterLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo",MODE_PRIVATE);
        final String clustername = loginPref.getString("clustername","");
        final String password = loginPref.getString("password","");
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                String twitterUsername = result.data.getUserName();
                ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.TWITTER_USERNAME,twitterUsername,clustername,password);
                AddServicesActivity.addRow(AddServicesActivity.TWITTER,twitterUsername, getApplicationContext(),true);
                finish();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.twitterLoginError),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode,resultCode,data);
    }

    public void addTwitterDataCancel(View view){

        AddServicesActivity.setIconVisible("twitter",getApplicationContext(),true);

        finish();

    }
    public void addTwitterCardView (View view){

    }
}
