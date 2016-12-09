package com.media.cluster.cluster.Main.Search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.ClusterDBConnect.AddSearchHistory;
import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.Login.AddServices.AddServicesActivity;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



    public  class MainSearchActivity extends AppCompatActivity {

        private boolean filterOn, accessTwitter, accessFacebook, accessTumblr, accessSkype;
        private boolean allowedStateChanged = false;
        private TextWatcher textWatcher;
        private EditText searchText;
        private Menu menu;
        private ViewGroup rootLayoutGroup;
        private Switch facebookSwitch, twitterSwitch, skypeSwitch, tumblrSwitch;
        private ImageButton openButton;
        private BottomSheetBehavior bottomSheetBehavior;
        private SharedPreferences loginPref;
        private View noServiceLayout ;
        private Button noServiceButton;
        private String clustername, password;
        private RecyclerView suggestionRecyclerView;
        private RecyclerTouchListener searchSuggestionRecyclerListener;
        private Context dialogContext;
        private boolean repressUpdate = false;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_search);
            loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
            clustername = loginPref.getString("clustername", "");
            password = loginPref.getString("password","");
            dialogContext = this;
            if (clustername.equals("")) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                Log.d("debug", "Login started from MainSearchActivity (101)  [Clustername was null]");
            }

            searchText = (EditText) findViewById(R.id.search_text);

            Intent i = getIntent();
            String defaultSearch = i.getStringExtra("search");
            if(!defaultSearch.equals("")){
                searchText.setText(defaultSearch);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark, getTheme()));
                }else{
                    window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
                }
            }
            GetUserData.getAddedServices(getApplicationContext(), clustername, false);
            noServiceLayout = findViewById(R.id.addServicesLayout);
            noServiceButton = (Button) findViewById(R.id.noServiceButton);

            suggestionRecyclerView = (RecyclerView) findViewById(R.id.searchSuggestionRecyclerView);
            getSuggestions(searchText.getText().toString().trim());

            searchSuggestionRecyclerListener = new RecyclerTouchListener(getApplication(),suggestionRecyclerView,new ClickListener(){
                @Override
                public void onClick(View view, int position) {
                    searchText((new SearchListViewHolder(view)).getSuggestionText().getText().toString().trim());
                }

                @Override
                public void onLongClick(final View view, int position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
                    builder.setPositiveButton(getResources().getString(R.string.remove), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AddSearchHistory.removeSearchHistory(clustername, password, (new SearchListViewHolder(view)).getSuggestionText().getText().toString().trim(), getApplicationContext());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getSuggestions(searchText.getText().toString().trim());
                                }
                            },100);

                        }
                    });
                    DialogInterface.OnClickListener negativeDialogButton = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    };
                    builder.setNegativeButton(getResources().getString(R.string.cancel), negativeDialogButton);

                    builder.setTitle((new SearchListViewHolder(view)).getSuggestionText().getText().toString().trim());
                    builder.setMessage(getResources().getString(R.string.removeConfirm));
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            suggestionRecyclerView.addOnItemTouchListener(searchSuggestionRecyclerListener);



            searchText.setOnEditorActionListener( new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if(i == EditorInfo.IME_ACTION_SEARCH){

                        View view = getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        suggestionRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out));
                        suggestionRecyclerView.setVisibility(View.GONE);
                        if(!searchText.getText().toString().trim().equals("")){
                            AddSearchHistory.addSearchHistory(clustername,password,searchText.getText().toString().trim(),getApplicationContext());
                            return true;
                        }else{

                            return false;
                        }


                    }else{
                        return false;
                    }
                }
            });

            View.OnClickListener rowClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()){
                        case R.id.switchFacebookLayout:
                            facebookSwitch.setChecked(!facebookSwitch.isChecked());
                            break;
                        case R.id.switchSkypeLayout:
                            skypeSwitch.setChecked(!skypeSwitch.isChecked());
                            break;
                        case R.id.switchTumblrLayout:
                            tumblrSwitch.setChecked(!tumblrSwitch.isChecked());
                            break;
                        case R.id.switchTwitterLayout:
                            twitterSwitch.setChecked(!twitterSwitch.isChecked());
                            break;
                    }
                    checkSwitches();
                }
            };

            if (!loginPref.getString("facebook", "").equals("")) {
                findViewById(R.id.facebookRow).setVisibility(View.VISIBLE);
                View facebookSwitchLayout = findViewById(R.id.switchFacebookLayout);
                facebookSwitchLayout.setOnClickListener(rowClickListener);
                accessFacebook = true;
            } else {
                if (loginPref.getString("skype", "").equals("") && loginPref.getString("twitter", "").equals("") && loginPref.getString("tumblr", "").equals("")) {
                    noServiceButton.setVisibility(View.VISIBLE);
                    noServiceLayout.setVisibility(View.VISIBLE);
                    suggestionRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out));
                    suggestionRecyclerView.setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.noServiceText)).setText(getString(R.string.noAddedServicesHeader));
                }
            }

            if (!loginPref.getString("skype", "").equals("")) {
                findViewById(R.id.skypeRow).setVisibility(View.VISIBLE);
                View skypeSwitchLayout = findViewById(R.id.switchSkypeLayout);
                skypeSwitchLayout.setOnClickListener(rowClickListener);
                accessSkype = true;
            }

            if (!loginPref.getString("twitter", "").equals("")) {
                findViewById(R.id.twitterRow).setVisibility(View.VISIBLE);
                View twitterSwitchLayout = findViewById(R.id.switchTwitterLayout);
                twitterSwitchLayout.setOnClickListener(rowClickListener);
                accessTwitter = true;
            }

            if (!loginPref.getString("tumblr", "").equals("")) {
                findViewById(R.id.tumblrRow).setVisibility(View.VISIBLE);
                View tumblrSwitchLayout = findViewById(R.id.switchTumblrLayout);
                tumblrSwitchLayout.setOnClickListener(rowClickListener);
                accessTumblr = true;
            }


            tumblrSwitch = (Switch) findViewById(R.id.tumblrSwitch);
            twitterSwitch = (Switch) findViewById(R.id.twitterSwitch);
            skypeSwitch = (Switch) findViewById(R.id.skypeSwitch);
            facebookSwitch = (Switch) findViewById(R.id.facebookSwitch);
            rootLayoutGroup = (ViewGroup) findViewById(R.id.main_search_root);
            Switch filterSwitch = (Switch) findViewById(R.id.filter_switch);
            openButton = (ImageButton) findViewById(R.id.open_button);
            LinearLayout bottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            filterOn = filterSwitch.isChecked();
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

            openButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openSlider();
                }
            });

            checkIfEnabled();
            CompoundButton.OnCheckedChangeListener filterPowerListener = new CompoundButton.OnCheckedChangeListener() {
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

                    } else {

                        if (!allowedStateChanged) {
                            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            }
                        }

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

                    if(!repressUpdate) {
                        getSuggestions(searchText.getText().toString().trim());
                    }else{
                        repressUpdate = false;
                    }
                    if (!searchText.getText().toString().trim().equals("")) {
                        menu.findItem(R.id.action_clear).setVisible(true);

                    } else {
                        if (menu != null) {
                            menu.findItem(R.id.action_clear).setVisible(false);
                        }
                    }
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
                    getSuggestions(searchText.getText().toString().trim());
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

                    if (loginPref.getString("skype", "").equals("") && loginPref.getString("twitter", "").equals("") && loginPref.getString("tumblr", "").equals("") && loginPref.getString("facebook", "").equals("")) {

                        Toast.makeText(getApplicationContext(), getString(R.string.noAddedServicesHeader), Toast.LENGTH_SHORT).show();
                    } else {
                        View view = this.getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }

                        if (!filterOn) {
                            allowedStateChanged = true;
                            bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics()));
                        } else {
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
                    }
                    break;
                case android.R.id.home:
                    finish();
                    break;


            }

            return super.onOptionsItemSelected(item);
        }


        public void openSlider() {

            if (!filterOn) {
                allowedStateChanged = true;
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            } else {
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
            suggestionRecyclerView.removeOnItemTouchListener(searchSuggestionRecyclerListener);
        }


        private void addX(boolean b) {

            if (b) {
                openButton.setImageResource(R.drawable.ic_clear_white);
            } else {
                openButton.setImageResource(R.drawable.arrow_up_white);
            }
        }

        private void checkIfEnabled() {
            if (filterOn) {
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 340, getResources().getDisplayMetrics()));
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                openButton.setRotation(0);
                if (!((accessFacebook && facebookSwitch.isChecked()) || (accessTwitter && twitterSwitch.isChecked()) || (accessTumblr && tumblrSwitch.isChecked()) || (accessSkype && skypeSwitch.isChecked()))) {
                    setNoServiceVisible();
                } else {
                    if (noServiceLayout.getVisibility() != View.GONE) {
                        setNoServiceGone();
                    }
                }

            } else {
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics()));
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if (noServiceLayout.getVisibility() == View.VISIBLE) {
                    setNoServiceGone();
                }
            }

            TransitionManager.beginDelayedTransition(rootLayoutGroup);
            addX(!filterOn);
        }


        public void addServices(View view) {
            startActivity(new Intent(getApplicationContext(), AddServicesActivity.class));
        }

        private void checkSwitches() {

            if (!((accessFacebook && facebookSwitch.isChecked()) || (accessTwitter && twitterSwitch.isChecked()) || (accessTumblr && tumblrSwitch.isChecked()) || (accessSkype && skypeSwitch.isChecked()))) {
                setNoServiceVisible();
                Log.d("debug", "SetToVisible");
            } else if(noServiceLayout.getVisibility() != View.GONE){
                setNoServiceGone();
                Log.d("debug", "SetToGone");
            }

        }

        private void setNoServiceGone() {
            Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_slow);
            noServiceLayout.startAnimation(fade);
            noServiceLayout.setVisibility(View.GONE);
        }

        private void setNoServiceVisible() {
            ((TextView) findViewById(R.id.noServiceText)).setText(getString(R.string.noSelectedServicesHeader));
            Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slow);
            noServiceLayout.startAnimation(fade);
            noServiceLayout.setVisibility(View.VISIBLE);
            suggestionRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out));
            suggestionRecyclerView.setVisibility(View.GONE);
            noServiceButton.setVisibility(View.GONE);
        }


    private  void getSuggestions(final String search){


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "http://social-cluster.com/get_search.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            if(response.trim().equals("null") ) {
                suggestionRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out));
                suggestionRecyclerView.setVisibility(View.GONE);
            }else {

                try {

                    List<String> suggestions = new ArrayList<>();
                    JSONArray jr = new JSONArray(response);


                    for (int i = 0; i < jr.length(); i++) {

                        String row = jr.getString(i);
                        suggestions.add(row);
                    }
                    if(suggestionRecyclerView.getVisibility() == View.GONE){
                        suggestionRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
                        suggestionRecyclerView.setVisibility(View.VISIBLE);
                    }
                    suggestionRecyclerView.setAdapter(new SearchListAdapter(suggestions));
                    suggestionRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",clustername);
                hashMap.put("password", password);
                hashMap.put("searchRequest", search);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }

        public interface ClickListener {
            void onClick(View view, int position);

            void onLongClick(View view, int position);
        }

        public  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

            private GestureDetector gestureDetector;
            private ClickListener clickListener;

            RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

                this.clickListener=clickListener;
                gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if(child!=null && clickListener!=null)
                        {
                            clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                        }

                    }
                });


            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());

                if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){

                    clickListener.onClick(child, rv.getChildAdapterPosition(child));
                }

                return false;


            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        }



        private void searchText(String text){
            repressUpdate = true;
            searchText.setText(text);
            suggestionRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out));
            suggestionRecyclerView.setVisibility(View.GONE);

            //Todo: add Search
        }



    }

