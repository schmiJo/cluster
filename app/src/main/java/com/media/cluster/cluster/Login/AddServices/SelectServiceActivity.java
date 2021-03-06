package com.media.cluster.cluster.Login.AddServices;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.media.cluster.cluster.ClusterDBConnect.ImplementUserData;
import com.media.cluster.cluster.R;
import com.media.cluster.cluster.Tumblr.TumblrLoginActivity;
import com.media.cluster.cluster.Twitter.TwitterLoginActivity;

import java.util.Arrays;

public class SelectServiceActivity extends AppCompatActivity implements SelectServicesView.OnItemClickListener {

    SelectServicesView selectServicesView;
    private CallbackManager mCallbackManager;
    private String clustername = null, password = null;
    private SharedPreferences loginPref = null;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);

        loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        clustername = loginPref.getString("clustername", "");
        password = loginPref.getString("password", "");

        Intent services = getIntent();
        selectServicesView = new SelectServicesView(this, getApplicationContext(),
                !services.getBooleanExtra("facebook", false),
                !services.getBooleanExtra("skype", false),
                !services.getBooleanExtra("twitter", false),
                !services.getBooleanExtra("tumblr", false));
        setContentView(selectServicesView);


        try {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle(getString(R.string.addServices));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        //Handle Profile or AccessToken Changes
        mCallbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("debug", "Login");
                        Log.d("debug", "loginResult" + loginResult);

                        Profile profile = Profile.getCurrentProfile();
                        if (profile != null) {
                            Log.d("debug", "Username: " + profile.getName());
                            Toast.makeText(SelectServiceActivity.this, getString(R.string.facebook_welcome_message) + profile.getName(), Toast.LENGTH_SHORT).show();
                            ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.FACEBOOK_EMAIL, profile.getId(), clustername, password);
                        }
                        finish();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("debug", "ERROR: " + exception.getMessage());
                    }
                });


    }


    @Override
    protected void onPause() {
        super.onPause();
        selectServicesView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectServicesView.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                //overridePendingTransition(R.anim.activity_scale_out, R.anim.fade_out_slow);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onItemClick(int itemId) {
        switch (itemId) {
            case 1:
                //Facebook clicked
                Log.d("debug", "Facebook");
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));
                break;
            case 2:
                //Skype clicked
                Log.d("debug", "Skype");
                break;
            case 3:
                //Twitter clicked
                Log.d("debug", "Twitter");
                startActivity(new Intent(getApplicationContext(), TwitterLoginActivity.class));

                break;
            case 4:
                //Tumblr clicked
                Log.d("debug", "Tumblr");
                startActivity(new Intent(getApplicationContext(), TumblrLoginActivity.class));
                break;
        }
    }
}
