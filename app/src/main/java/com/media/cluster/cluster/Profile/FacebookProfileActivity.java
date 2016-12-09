package com.media.cluster.cluster.Profile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.media.cluster.cluster.R;
import com.squareup.picasso.Picasso;

public class FacebookProfileActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_profile);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        ImageView profilePic = (ImageView) findViewById(R.id.facebook_profile_profile_pic);


        Picasso.with(getApplicationContext()).load(Profile.getCurrentProfile().getProfilePictureUri(500,500)).into(profilePic);


    }
}
