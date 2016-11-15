package com.media.cluster.cluster.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.media.cluster.cluster.Profile.MainProfileFragment;
import com.media.cluster.cluster.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Intent basicInfo = getIntent();
        setTitle(basicInfo.getStringExtra("Name"));
        String clustername = basicInfo.getStringExtra("Clustername");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        MainProfileFragment mainProfileFragment = new MainProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CN", clustername);
        mainProfileFragment.setArguments(bundle);
        ft.replace(R.id.profile_fragment_placeholder, mainProfileFragment);

        ft.commit();

    }
}
