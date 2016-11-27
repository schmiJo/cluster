package com.media.cluster.cluster.Login.AddServices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.R;

public class AddServicesActivity extends AppCompatActivity  {

    Toolbar toolbar;
    Button noServiceButton;
    View noServiceLayout;
    SharedPreferences loginPref;
    String facebookName ,twitterName,skypeName, tumblrName;
    Switch  facebookSwitch, twitterSwitch, skypeSwitch, tumblrSwitch;
    TextView facebookText, twitterText, skypeText, tumblrText;
    View facebookRow, twitterRow, skypeRow ,tumblrRow;
    final public static int FACEBOOK =0 ;
    final public static int TWITTER = 1;
    final public static int SKYPE = 2;
    final public static int TUMBLR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);
        implementViews();
        setSupportActionBar(toolbar);

        try {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle(getString(R.string.addServices));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        }catch (NullPointerException e){
            e.printStackTrace();
        }





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(this.getResources().getColor(R.color.splashScreenDark, getTheme()));
            }else{
                window.setStatusBarColor(this.getResources().getColor(R.color.splashScreenDark));
            }
        }

        loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        final String clustername = loginPref.getString("clustername", "");
        if (clustername.equals("")) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            Log.d("debug", "Login started from AddServiceActivity (44)  [Clustername was null]");
        }

        GetUserData.getAddedServices(getApplicationContext(), clustername, false);
        facebookName = loginPref.getString("facebook","");
        twitterName = loginPref.getString("twitter", "");
        skypeName = loginPref.getString("skype", "");
        tumblrName = loginPref.getString("tumblr","");

        if (facebookName.equals("") && twitterName.equals("") && skypeName.equals("") && tumblrName.equals("")){
            noServiceLayout.setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.noServiceText)).setText(getString(R.string.noAddedServicesHeader));
        }
        if( !facebookName.equals("") || !twitterName.equals("") || !skypeName.equals("") || !tumblrName.equals("")){
            noServiceButton.setVisibility(View.GONE);
        }else{
            noServiceButton.setVisibility(View.VISIBLE);
        }
        setDefaultRows();


        facebookRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        twitterRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        tumblrRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        skypeRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });


    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.add_service, menu);
    return true;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            finish();
            break;


        }

        return super.onOptionsItemSelected(item);
    }


    private void implementViews(){
        tumblrSwitch = (Switch) findViewById(R.id.tumblrSwitch);
        twitterSwitch = (Switch) findViewById(R.id.twitterSwitch);
        skypeSwitch = (Switch) findViewById(R.id.skypeSwitch);
        facebookSwitch = (Switch) findViewById(R.id.facebookSwitch);
        
        tumblrText = (TextView) findViewById(R.id.tumblrText);
        facebookText = (TextView) findViewById(R.id.facebookText);
        skypeText = (TextView) findViewById(R.id.skypeText);
        twitterText = (TextView) findViewById(R.id.twitterText);

        facebookRow = findViewById(R.id.facebookRow);
        twitterRow = findViewById(R.id.twitterRow);
        tumblrRow = findViewById(R.id.tumblrRow);
        skypeRow = findViewById(R.id.skypeRow);

        noServiceLayout = findViewById(R.id.addServicesLayout);
        noServiceButton = (Button) findViewById(R.id.noServiceButtonList);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setDefaultRows(){
        if (!facebookName.equals("")){setRow(FACEBOOK);}
        if (!skypeName.equals("")){setRow(SKYPE);}
        if (!tumblrName.equals("")) {setRow(TUMBLR);}
        if (!twitterName.equals("")) {setRow(TWITTER);}
    }

    private void setRow(int service){
        switch (service){
            case FACEBOOK:
                facebookRow.setVisibility(View.VISIBLE);
                facebookText.setText(facebookName);
                break;
            case TWITTER:
                twitterRow.setVisibility(View.VISIBLE);
                twitterText.setText(twitterName);
                break;
            case SKYPE:
                skypeRow.setVisibility(View.VISIBLE);
                skypeText.setText(skypeName);
                break;
            case TUMBLR:
                tumblrRow.setVisibility(View.VISIBLE);
                tumblrText.setText(tumblrName);
                break;
        }
    }

    public void addServices(View view){
        Intent i = new Intent(getApplicationContext(), SelectServiceActivity.class);

        if (!facebookName.equals("")){i.putExtra("facebook",true );}
        if (!skypeName.equals("")){i.putExtra("skype",true);}
        if (!tumblrName.equals("")) {i.putExtra("tumblr",true);}
        if (!twitterName.equals("")) {i.putExtra("twitter",true);}

        startActivity(i);
    }

    //// TODO: 11/27/2016 add muting of services 
    public void switchSkype(View view){
        skypeSwitch.setChecked(!skypeSwitch.isChecked());
        //add muting of service
    }

    public void switchFacebook(View view){
        facebookSwitch.setChecked(!facebookSwitch.isChecked());
        //add muting of service
    }

    public void switchTumblr(View view){
        tumblrSwitch.setChecked(!tumblrSwitch.isChecked());
        //add muting of service
    }

    public void switchTwitter(View view){
        twitterSwitch.setChecked(!twitterSwitch.isChecked());
        //add muting of service
    }
}
