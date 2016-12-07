package com.media.cluster.cluster.Tumblr;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.media.cluster.cluster.Client.TumblrClient;
import com.media.cluster.cluster.R;

import static com.loopj.android.http.AsyncHttpClient.log;

public class TumblrLoginActivity extends OAuthLoginActionBarActivity<TumblrClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumblr_login);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                log.d("debug","Tumblr login Clicked     Client:  " + getClient().toString());
                getClient().connect();
            }
        }, 300);
    }

    @Override
    public void onLoginFailure(Exception e) {

    }

    @Override
    public void onLoginSuccess() {

    }
}
