package com.media.cluster.cluster.Login.AddServices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.ClusterDBConnect.ImplementUserData;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.R;

public class AddServicesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button noServiceButton;
    private View noServiceLayout, serviceItemLayout;
    private ViewGroup addServiceGroup;
    private SharedPreferences loginPref;
    private String facebookName, twitterName, skypeName, tumblrName;
    private Switch facebookSwitch, twitterSwitch, skypeSwitch, tumblrSwitch;
    private TextView facebookText, twitterText, skypeText, tumblrText;
    private View facebookRow, twitterRow, skypeRow, tumblrRow;
    final public static int FACEBOOK = 0;
    final public static int TWITTER = 1;
    final public static int SKYPE = 2;
    final public static int TUMBLR = 3;
    public static int identifyMenuRow = FACEBOOK;
    private String clustername, password;

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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(this.getResources().getColor(R.color.splashScreenDark, getTheme()));
            } else {
                window.setStatusBarColor(this.getResources().getColor(R.color.splashScreenDark));
            }
        }

        loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        clustername = loginPref.getString("clustername", "");
        password = loginPref.getString("password","");
        if (clustername.equals("")) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            Log.d("debug", "Login started from AddServiceActivity (44)  [Clustername was null]");
        }

        GetUserData.getAddedServices(getApplicationContext(), clustername, false);
        facebookName = loginPref.getString("facebook", "");
        twitterName = loginPref.getString("twitter", "");
        skypeName = loginPref.getString("skype", "");
        tumblrName = loginPref.getString("tumblr", "");

        setDefaultRows();
        registerForContextMenu(facebookRow);
        registerForContextMenu(twitterRow);
        registerForContextMenu(tumblrRow);
        registerForContextMenu(skypeRow);
        checkRowCount();

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


    private void implementViews() {
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
        addServiceGroup = (ViewGroup) findViewById(R.id.addServiceLayout);
        serviceItemLayout = findViewById(R.id.serviceItemLayout);
    }

    private void setDefaultRows() {
        if (!facebookName.equals("")) {
            setRow(FACEBOOK);
        }
        if (!skypeName.equals("")) {
            setRow(SKYPE);
        }
        if (!tumblrName.equals("")) {
            setRow(TUMBLR);
        }
        if (!twitterName.equals("")) {
            setRow(TWITTER);
        }
    }

    private void setRow(int service) {
        switch (service) {
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

    public void addServices(View view) {
        Intent i = new Intent(getApplicationContext(), SelectServiceActivity.class);

        if (!facebookName.equals("")) {
            i.putExtra("facebook", true);
        }
        if (!skypeName.equals("")) {
            i.putExtra("skype", true);
        }
        if (!tumblrName.equals("")) {
            i.putExtra("tumblr", true);
        }
        if (!twitterName.equals("")) {
            i.putExtra("twitter", true);
        }

        startActivity(i);
    }

    //// TODO: 11/27/2016 add muting of services 
    public void switchSkype(View view) {
        skypeSwitch.setChecked(!skypeSwitch.isChecked());
        //add muting of service
    }

    public void switchFacebook(View view) {
        facebookSwitch.setChecked(!facebookSwitch.isChecked());
        //add muting of service
    }

    public void switchTumblr(View view) {
        tumblrSwitch.setChecked(!tumblrSwitch.isChecked());
        //add muting of service
    }

    public void switchTwitter(View view) {
        twitterSwitch.setChecked(!twitterSwitch.isChecked());
        //add muting of service
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_service_context, menu);
        switch (v.getId()){
            case R.id.facebookRow:
                identifyMenuRow =  FACEBOOK;
                break;
            case R.id.twitterRow:
                identifyMenuRow = TWITTER;
                break;
            case R.id.skypeRow :
                identifyMenuRow = SKYPE;
                break;
            case R.id.tumblrRow:
                identifyMenuRow = TUMBLR;
                break;

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteRow();
                return true;
            case R.id.action_info:
                startInfo();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteRow() {
        SharedPreferences.Editor loginEdit = loginPref.edit();
        if(identifyMenuRow == FACEBOOK && facebookRow.getVisibility() == View.VISIBLE){
            ImplementUserData.implementUser(getApplicationContext(),ImplementUserData.FACEBOOK_EMAIL,"",clustername,password);
            loginEdit.putString("facebook","");
            facebookRow.setVisibility(View.GONE);
        }else if(identifyMenuRow == TWITTER && twitterRow.getVisibility() == View.VISIBLE) {
            ImplementUserData.implementUser(getApplicationContext(),ImplementUserData.TWITTER_USERNAME,"",clustername,password);
            loginEdit.putString("twitter","");
            twitterRow.setVisibility(View.GONE);
        }else if(identifyMenuRow == SKYPE && skypeRow.getVisibility() == View.VISIBLE){
            ImplementUserData.implementUser(getApplicationContext(),ImplementUserData.SKYPE_USERNAME,"",clustername,password);
            loginEdit.putString("skype","");
            skypeRow.setVisibility(View.GONE);
        }else if(identifyMenuRow == TUMBLR && tumblrRow.getVisibility() == View.VISIBLE){
            ImplementUserData.implementUser(getApplicationContext(),ImplementUserData.TUMBLR_USERNAME,"",clustername,password);
            loginEdit.putString("tumblr","");
            tumblrRow.setVisibility(View.GONE);
        }
        TransitionManager.beginDelayedTransition(addServiceGroup);
        loginEdit.apply();

        checkRowCount();
    }

    private void checkRowCount(){
        if (facebookRow.getVisibility() == View.GONE && twitterRow.getVisibility() == View.GONE && skypeRow.getVisibility() == View.GONE && tumblrRow.getVisibility() == View.GONE) {
            noServiceLayout.setVisibility(View.VISIBLE);
            serviceItemLayout.setVisibility(View.GONE);
            ((TextView) findViewById(R.id.noServiceText)).setText(getString(R.string.noAddedServicesHeader));
        }else if (facebookRow.getVisibility() == View.VISIBLE && twitterRow.getVisibility() == View.VISIBLE && skypeRow.getVisibility() == View.VISIBLE && tumblrRow.getVisibility() == View.VISIBLE) {
            noServiceButton.setVisibility(View.GONE);
            noServiceLayout.setVisibility(View.GONE);
            serviceItemLayout.setVisibility(View.VISIBLE);
        } else {
            noServiceButton.setVisibility(View.VISIBLE);
            noServiceLayout.setVisibility(View.GONE);
            serviceItemLayout.setVisibility(View.VISIBLE);
        }
    }

    private void startInfo(){
        //Todo add service Info Activities
    }
}
