package com.media.cluster.cluster.Twitter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.media.cluster.cluster.Client.TwitterClient;
import com.media.cluster.cluster.Login.AddServices.SelectServiceActivity;
import com.media.cluster.cluster.R;

import static com.loopj.android.http.AsyncHttpClient.log;

public class TwitterLoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                log.d("debug","Twitter login Clicked     Client:  " + getClient().toString());
                getClient().connect();
            }
        }, 300);
    }

    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
        finish();
        Toast.makeText(getApplicationContext(), R.string.serviceLoginUnsuccessful, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(getApplicationContext(), SelectServiceActivity.class));
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
