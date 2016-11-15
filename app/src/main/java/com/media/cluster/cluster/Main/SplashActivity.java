package com.media.cluster.cluster.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.media.cluster.cluster.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.TwitterApi;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final Class<TwitterApi> TWITTER_REST_API_CLASS = TwitterApi.class;
    public static final String TWITTER_REST_URL = "https://api.twitter.com/1.1";
    public static final String TWITTER_KEY = "pq6Gp4dWWh1KSbnbVvcMT6vrW ";
    public static final String TWITTER_SECRET = " SBvlz259LdPLF5FTOkeA6ReXVu1NPdnBNExAyTZVjYBIQxZ89e ";
    public static final String TIWTTER_REST_CALLBACK_URL = "oauth://codepathtweets";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        Fabric.with(this, new TwitterCore(authConfig), new Crashlytics());
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        setContentView(R.layout.activity_splash);
        Thread thread=  new Thread(){
            @Override
            public void run() {
                try{
                    sleep(1800);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };




        thread.start();
    }
}
