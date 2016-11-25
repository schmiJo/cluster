package com.media.cluster.cluster.Main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.R;

public class MainSearchActivity extends AppCompatActivity {

    boolean filterOn ;
    boolean allowedStateChanged = false;
    LinearLayout bottomSheet;
    TextWatcher textWatcher;
    EditText searchText;
    Menu menu;
    ViewGroup rootLayoutGroup;
    Switch filterSwitch,facebookSwitch, twitterSwitch, skypeSwitch, tumblrSwitch;
    ImageButton openButton;
    BottomSheetBehavior bottomSheetBehavior;
    CompoundButton.OnCheckedChangeListener filterPowerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        final String clustername = loginPref.getString("clustername", "");
        if(clustername.equals("")){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
            Log.d("debug","Login started from MainSearchActivity (57)  [Clustername was null]");
        }
        GetUserData.getAddedServices(getApplicationContext(),clustername,false);


        if(!loginPref.getString("facebook","").equals("")){
            findViewById(R.id.facebookRow).setVisibility(View.VISIBLE);
        }

        if(!loginPref.getString("skype","").equals("")){
            findViewById(R.id.skypeRow).setVisibility(View.VISIBLE);
        }

        if(!loginPref.getString("twitter","").equals("")){
            findViewById(R.id.twitterRow).setVisibility(View.VISIBLE);
        }

        if(!loginPref.getString("tumblr","").equals("")){
            findViewById(R.id.tumblrRow).setVisibility(View.VISIBLE);
        }



        tumblrSwitch = (Switch) findViewById(R.id.tumblrSwitch);
        twitterSwitch = (Switch) findViewById(R.id.twitterSwitch);
        skypeSwitch = (Switch) findViewById(R.id.skypeSwitch);
        facebookSwitch = (Switch) findViewById(R.id.facebookSwitch);
        rootLayoutGroup = (ViewGroup)findViewById(R.id.main_search_root);
        filterSwitch = (Switch) findViewById(R.id.filter_switch);
        openButton = (ImageButton) findViewById(R.id.open_button);
        bottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        searchText = (EditText) findViewById(R.id.search_text);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filterOn = filterSwitch.isChecked();
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);


        checkIfEnabled();
        filterPowerListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filterOn = b;
                checkIfEnabled();
            }
        };
        filterSwitch.setOnCheckedChangeListener(filterPowerListener);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {


                    if (newState != BottomSheetBehavior.STATE_HIDDEN) {
                        menu.findItem(R.id.action_filter).setIcon(R.drawable.action_filter_light_ic);


                    } else {
                        menu.findItem(R.id.action_filter).setIcon(R.drawable.action_filter_ic);


                    }
                if (filterOn) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation_180);
                        openButton.startAnimation(rotate);
                        openButton.setRotation(180);
                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        openButton.setRotation(0);
                    }

                }else {

                    if(!allowedStateChanged){
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }}

                    allowedStateChanged = false;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }


        };

        bottomSheetBehavior.setBottomSheetCallback(callback);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!searchText.getText().toString().equals("")) {
                    menu.findItem(R.id.action_clear).setVisible(true);

                } else {
                    if(menu != null){
                    menu.findItem(R.id.action_clear).setVisible(false);
                }}
            }
        };
        ActionBar supportActionBar = getSupportActionBar();

        searchText.addTextChangedListener(textWatcher);
        try {
            assert supportActionBar != null;
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_dark);
            supportActionBar.setTitle("");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_search, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                searchText.setText("");
                break;
            case R.id.action_filter:


                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                if(!filterOn){
                    allowedStateChanged = true;
                    bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics()));
                }else{
                    bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 340, getResources().getDisplayMetrics()));
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                            //expand
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        } else {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                    }
                }, 200);

                break;
            case android.R.id.home:
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }


    public void OpenSlider(View view) {

        if (!filterOn) {
            allowedStateChanged = true;
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        }else {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchText.removeTextChangedListener(textWatcher);
    }


    private void addX(boolean b){

        if(b) {
            openButton.setImageResource(R.drawable.ic_clear_white);
        }else{
            openButton.setImageResource(R.drawable.arrow_up_white);
        }
    }

    private void checkIfEnabled(){
        if (filterOn) {
            bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 340, getResources().getDisplayMetrics()));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            openButton.setRotation(0);
        } else {
            bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics()));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        TransitionManager.beginDelayedTransition(rootLayoutGroup);
        addX(!filterOn);
    }


    public void switchFacebook(View view){
        facebookSwitch.setChecked(!facebookSwitch.isChecked());

    }

    public void switchTwitter(View view){
        twitterSwitch.setChecked(!twitterSwitch.isChecked());
    }

    public void switchTumblr(View view){
        tumblrSwitch.setChecked(!tumblrSwitch.isChecked());
    }

    public void switchSkype(View view){
        skypeSwitch.setChecked(!skypeSwitch.isChecked());
    }
}
