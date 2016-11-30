package com.media.cluster.cluster.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.TwitterApi;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final Class<TwitterApi> TWITTER_REST_API_CLASS = TwitterApi.class;
    public static final String TWITTER_REST_URL = "https://api.twitter.com/1.1";
    public static final String TWITTER_KEY = "pq6Gp4dWWh1KSbnbVvcMT6vrW ";
    public static final String TWITTER_SECRET = " SBvlz259LdPLF5FTOkeA6ReXVu1NPdnBNExAyTZVjYBIQxZ89e ";
    public static final String TIWTTER_REST_CALLBACK_URL = "oauth://cluster";


    @Override
    protected void onCreate(Bundle savedInstanceState){
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
        final Thread thread=  new Thread(){
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
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        final String clustername = loginPref.getString("clustername", "");
        final String password = loginPref.getString("password", "");



        final Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        final String loginURL = "http://social-cluster.com/user_login.php";


                if (clustername.equals("") || password.equals("")) {
                    startActivity(login);
                    Log.d("debug","login activity started SplashActivity (85) [Shared pref = null]");
                } else if (isNetworkAvailable()) {

                    RequestQueue loginRequestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest loginStringRequest = new StringRequest(Request.Method.POST, loginURL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (!response.equals("successful")) {
                                startActivity(login);
                                Log.d("debug","login activity started SplashActivity (94) [Response not successful]");
                                finish();
                            }else{
                                thread.start();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            startActivity(login);
                            Log.d("debug","login activity started SplashActivity (104) [Volley Error Response]");
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("clustername", clustername);
                            hashMap.put("password", password);

                            return hashMap;
                        }

                    };

                    loginRequestQueue.add(loginStringRequest);
                }



    }




    //-----------------------------------------------------------Check for connection----------------------------------------------------
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    //-----------------------------------------------------------check for connection----------------------------------------------------
}
