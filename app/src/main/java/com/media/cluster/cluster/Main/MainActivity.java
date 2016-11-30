package com.media.cluster.cluster.Main;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.media.cluster.cluster.BuildConfig;
import com.media.cluster.cluster.ClusterCode.ClusterCodeActivity;
import com.media.cluster.cluster.General.FloatingActionWheel;
import com.media.cluster.cluster.Login.AddServices.AddServicesActivity;
import com.media.cluster.cluster.Login.AddServices.ClusterInfoActivity;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FloatingActionWheel.OnFAWItemClickListner {
    //DrawerRecyclerView
    RecyclerView drawerOptionRecyclerView;
    RecyclerView drawerServiceRecyclerView;
    DrawerAdapter drawerOptionAdapter;
    DrawerAdapter drawerSocialAdapter;
    //
    //View Pager && Toolbar
    ViewPager mainViewPager;
    private TabLayout tabLayout;
    //
    Menu menu;
    Context dialogContext;
    private Toolbar toolbar;
    private ViewGroup rootLayoutGroup;
    public static String CurrentClustername;
    private FloatingActionButton fab;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private TabLayout.OnTabSelectedListener tabListener;
    private TabLayout.TabLayoutOnPageChangeListener onTabChangeListener;
    private DrawerRecyclerTouchListener drawerRecyclerTouchListener;
    private DrawerRecyclerTouchListener serviceListener;
    private FloatingActionWheel faw;
    private BottomSheetBehavior bottomSheetBehavior;
    private SharedPreferences loginPref;
    private ImageButton openButton;
    private Switch facebookSwitch, twitterSwitch, skypeSwitch, tumblrSwitch;
    private boolean filterOn, accessTwitter, accessFacebook, accessTumblr, accessSkype;
    CompoundButton.OnCheckedChangeListener filterPowerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filterPowerListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filterOn = b;
                checkIfEnabled();
            }
        };
        initializeViews();
        setSupportActionBar(toolbar);
        dialogContext = this;

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState != BottomSheetBehavior.STATE_HIDDEN) {
                    menu.findItem(R.id.action_filter).setIcon(R.drawable.action_filter_light_ic);

                    if(fab.getVisibility() == View.VISIBLE) {
                        fab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_scale_out));
                        fab.setVisibility(View.GONE);
                        faw.setExpantionState(false);
                    }
                } else {
                    menu.findItem(R.id.action_filter).setIcon(R.drawable.action_ic_filter);
                    if(fab.getVisibility() == View.GONE) {
                        fab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_scale_in));
                        fab.setVisibility(View.VISIBLE);
                    }
                }

                if (filterOn) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation_180);
                        openButton.startAnimation(rotate);
                        openButton.setRotation(180);
                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        openButton.setRotation(0);
                    }
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
//----------------------------------------------------------------------------------------------------Login Shared Preferences Start----------------------------------------------------------------
        loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        CurrentClustername = loginPref.getString("clustername", "");


        View layout = findViewById(R.id.drawer_layout);
        final String password = loginPref.getString("password", "");
        if (CurrentClustername.equals("") || password.equals("")) {
            final Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
            Log.d("debug", "login activity started MainActivity (83) [Shared pref = null]");
        }

//----------------------------------------------------------------------------------------------------Login Shared Preferences End------------------------------------------------------------------
        checkIfEnabled();
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSlider();
            }
        });
//----------------------------------------------------------------------------------------------------View Pager Start------------------------------------------------------------------------------
        mainViewPager.setAdapter(new PagerAdapterMain(getSupportFragmentManager()));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //Tab layout start
        final TabLayout.Tab feed = tabLayout.newTab();
        final TabLayout.Tab hot = tabLayout.newTab();
        final TabLayout.Tab chat = tabLayout.newTab();
        final TabLayout.Tab profile = tabLayout.newTab();
        tabListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.equals(feed)) {
                    mainViewPager.setCurrentItem(1);
                    toolbar.setTitle(getResources().getString(R.string.feed));
                } else if (tab.equals(hot)) {
                    mainViewPager.setCurrentItem(0);
                    toolbar.setTitle(getResources().getString(R.string.trending));
                } else if (tab.equals(chat)) {
                    mainViewPager.setCurrentItem(2);
                    toolbar.setTitle(getResources().getString(R.string.chats));
                } else if (tab.equals(profile)) {
                    mainViewPager.setCurrentItem(3);
                    toolbar.setTitle(getResources().getString(R.string.profile));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
        tabLayout.addOnTabSelectedListener(tabListener);

        feed.setIcon(R.drawable.main_tab_layout_ic_feed_selected);
        hot.setIcon(R.drawable.main_tab_layout_ic_whatshot);
        chat.setIcon(R.drawable.main_tab_layout_ic_chat);
        profile.setIcon(R.drawable.main_tab_layout_ic_profile);


        tabLayout.addTab(hot, 0);
        tabLayout.addTab(feed, 1);
        tabLayout.addTab(chat, 2);
        tabLayout.addTab(profile, 3);


        //Tab layout end

        setFilterRows();
        //Onchange Listener checks for a change with the ViewPager
        onTabChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        mainViewPager.addOnPageChangeListener(onTabChangeListener);
        pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                faw.setExpantionState(false);
                if (mainViewPager.getCurrentItem() == 1) {
                    feed.setIcon(R.drawable.main_tab_layout_ic_feed_selected);
                    hot.setIcon(R.drawable.main_tab_layout_ic_whatshot);
                    chat.setIcon(R.drawable.main_tab_layout_ic_chat);
                    profile.setIcon(R.drawable.main_tab_layout_ic_profile);
                } else if (mainViewPager.getCurrentItem() == 0) {

                    feed.setIcon(R.drawable.main_tab_layout_ic_feed);
                    hot.setIcon(R.drawable.main_tab_layout_ic_whatshot_selected);
                    chat.setIcon(R.drawable.main_tab_layout_ic_chat);
                    profile.setIcon(R.drawable.main_tab_layout_ic_profile);
                } else if (mainViewPager.getCurrentItem() == 2) {

                    feed.setIcon(R.drawable.main_tab_layout_ic_feed);
                    hot.setIcon(R.drawable.main_tab_layout_ic_whatshot);
                    chat.setIcon(R.drawable.main_tab_layout_ic_chat_selected);
                    profile.setIcon(R.drawable.main_tab_layout_ic_profile);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else if (mainViewPager.getCurrentItem() == 3) {

                    feed.setIcon(R.drawable.main_tab_layout_ic_feed);
                    hot.setIcon(R.drawable.main_tab_layout_ic_whatshot);
                    chat.setIcon(R.drawable.main_tab_layout_ic_chat);
                    profile.setIcon(R.drawable.main_tab_layout_ic_profile_selected);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

                try {
                    if (position == 0 || position == 1) {
                        menu.findItem(R.id.action_filter).setVisible(true);
                    } else {
                        menu.findItem(R.id.action_filter).setVisible(false);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        mainViewPager.addOnPageChangeListener(pageChangeListener);

        mainViewPager.setCurrentItem(1);


//----------------------------------------------------------------------------------------------------View Pager End------------------------------------------------------------------------------


        ArrayList<Integer> fawItems = new ArrayList<>();
        fawItems.add(R.drawable.faw_ic_post);
        fawItems.add(R.drawable.faw_ic_activity);
        fawItems.add(R.drawable.faw_ic_contacts);
        fawItems.add(R.drawable.faw_ic_story);
        fawItems.add(R.drawable.faw_ic_scanner);
        faw.setItems(fawItems);

//----------------------------------------------------------------------------------------------------Fab Start-----------------------------------------------------------------------------------
        View.OnClickListener fabClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //---fab clicked--

                if (!faw.getExpantionState()) {
                    //extended

                    faw.setExpantionState(true);
                    android.view.animation.Animation animOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                    fab.startAnimation(animOpen);
                    fab.setRotation(45);
                    Log.d("debug", "EXTENDING");
                } else {
                    //
                    Log.d("debug", "COLLAPSING");
                    faw.setExpantionState(false);
                    android.view.animation.Animation animClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
                    fab.startAnimation(animClose);
                    fab.setRotation(0);


                }
            }

        };
        fab.setOnClickListener(fabClickListener);


//----------------------------------------------------------------------------------------------------Fab End-------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------Navigation drawer Start-----------------------------------------------------------------------
        final DrawerLayout drawer = (DrawerLayout) layout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //-----Option Drawer

        drawerOptionAdapter = new DrawerAdapter(getApplicationContext(), getDrawerOptionData());
        drawerOptionRecyclerView.setAdapter(drawerOptionAdapter);
        drawerOptionRecyclerView.setLayoutManager(new DrawerLayoutManager(getApplicationContext()));


        //Option Drawer OnClick & OnLongClick Listeners
        drawerRecyclerTouchListener = new DrawerRecyclerTouchListener(getApplication(), new ClickListener() {
            //Option onClickListener
            @Override
            public void onClick(View view, int position) {

                drawer.closeDrawers();
                switch (position) {
                    case 0:
                        //add Activity
                        break;
                    case 1:
                        //Add Cluster Code Activity
                        startActivity(new Intent(getApplicationContext(), ClusterCodeActivity.class));

                        break;
                    case 2:
                        //Add Contact Activity
                        Toast.makeText(getApplication(), "Contacts", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        //Add Add Services Activity
                        startActivity(new Intent(getApplicationContext(), AddServicesActivity.class));
                        break;
                    case 4:
                        // Add Notifications Activity
                        Toast.makeText(getApplication(), "Notification", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        //Add Settings Dialog
                        Toast.makeText(getApplication(), "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        //Add Privacy Activity
                        Toast.makeText(getApplication(), "Privacy", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        //Add Store Activity
                        Toast.makeText(getApplication(), "Sticker Store", Toast.LENGTH_SHORT).show();
                    case 8:
                        //Add Feedback
                        Toast.makeText(getApplication(), "Feedback", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        //Link to InfoActivity
                        startActivity(new Intent(getApplicationContext(), ClusterInfoActivity.class));
                        break;
                    case 10:
                        //Logout

                        AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
                        builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Delete the login user information
                                SharedPreferences login = getApplicationContext().getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor loginEdit = login.edit();
                                loginEdit.putString("clustername", "");
                                loginEdit.putString("password", "");
                                loginEdit.putString("id", "");
                                loginEdit.apply();

                                //Delete the saved ClusterCode
                                boolean deleteClusterCode;
                                File dirCC = new File("sdcard/.cluster");
                                File fileCC = new File(dirCC, "cluster_code.png");
                                deleteClusterCode = fileCC.delete();
                                if (BuildConfig.DEBUG) {
                                    Log.d("debug", "deleted .cluster/cluster_code:   " + deleteClusterCode);
                                }
                                //Delete the saved ProfilePic
                                boolean deleteProfilePic;
                                File dirPP = new File("sdcard/.cluster");
                                File filePP = new File(dirPP, "profile_pic.png");
                                deleteProfilePic = filePP.delete();
                                if (BuildConfig.DEBUG) {
                                    Log.d("debug", "deleted Profile Pic:   " + deleteProfilePic);
                                }
                                //Start Login Activity
                                Log.d("debug", "login activity started MainActivity (324) [Log Out confirmed]");
                                Intent loginAction = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(loginAction);
                            }
                        });

                        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.setTitle(getResources().getString(R.string.logoutTitle));
                        builder.setMessage(getResources().getString(R.string.logoutMessage));
                        AlertDialog dialog = builder.create();
                        dialog.show();

                        break;
                }
            }


        });
        drawerOptionRecyclerView.addOnItemTouchListener(drawerRecyclerTouchListener);
        //
        //

        //-----Social Drawer
        drawerSocialAdapter = new DrawerAdapter(getApplicationContext(), getDrawerSocialData());
        drawerServiceRecyclerView.setAdapter(drawerSocialAdapter);
        drawerServiceRecyclerView.setLayoutManager(new DrawerLayoutManager(getApplicationContext()));

        //Service Drawer onCLick
        serviceListener = new DrawerRecyclerTouchListener(getApplication(), new ClickListener() {
            //Service onClickListeners
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        //Add Facebook description
                        Toast.makeText(getApplication(), "Facebook", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //Add Instagram description
                        Toast.makeText(getApplication(), "Instagram", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        //Add Twitter description
                        Toast.makeText(getApplication(), "Twitter", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        //Add Whatsapp description
                        Toast.makeText(getApplication(), "Whatsapp", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        //Add Snapchat description
                        Toast.makeText(getApplication(), "Snapchat", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        //Add Duo description
                        Toast.makeText(getApplication(), "Google Duo", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        //Add Tumblr description
                        Toast.makeText(getApplication(), "Tumblr", Toast.LENGTH_SHORT).show();
                        break;


                }

            }
            //

        });
        drawerServiceRecyclerView.addOnItemTouchListener(serviceListener);
        //

//--------------------------------------------------------------------------------------------------Navigation drawer End ------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    //Navigation drawer Recycler getData() Methods
    public List<DrawerRowDataModel> getDrawerOptionData() {
        List<DrawerRowDataModel> DrawerData = new ArrayList<>();
        int[] icons = {
                R.drawable.nav_ic_activity,
                R.drawable.nav_ic_clustercode,
                R.drawable.nav_ic_contacts,
                R.drawable.nav_ic_add_services,
                R.drawable.nav_ic_notifications,
                R.drawable.nav_ic_settings,
                R.drawable.nav_ic_privacy,
                R.drawable.nav_ic_sticker_store,
                R.drawable.nav_ic_feedback,
                R.drawable.nav_ic_info_inverted,
                R.drawable.nav_ic_logout,
        };
        String[] titles = {getResources().getString(R.string.navActivity), getResources().getString(R.string.navClusterCode), getResources().getString(R.string.navContacts), getResources().getString(R.string.navAddServices), getResources().getString(R.string.navNotification), getResources().getString(R.string.navSettings), getResources().getString(R.string.navPrivacy), getResources().getString(R.string.navStickerStore), getResources().getString(R.string.navSendFeedback), getResources().getString(R.string.info), getResources().getString(R.string.navLogout)};
        for (int i = 0; i < titles.length && i < icons.length; i++) {
            DrawerRowDataModel current = new DrawerRowDataModel();
            current.iconId = icons[i];
            current.title = titles[i];
            DrawerData.add(current);
        }
        return DrawerData;
    }

    public List<DrawerRowDataModel> getDrawerSocialData() {
        List<DrawerRowDataModel> DrawerData = new ArrayList<>();
        int[] socialIcons = {R.drawable.round_service_ic_facebook,
                R.drawable.ic_instagram_round,
                R.drawable.ic_twitter_round,
                R.drawable.ic_whatsapp_round,
                R.drawable.ic_snapchat_round,
                R.drawable.ic_duo_round,
                R.drawable.ic_tumblr_round};
        String[] socialTitles = {getResources().getString(R.string.facebook), getResources().getString(R.string.instagram), getResources().getString(R.string.twitter), getResources().getString(R.string.whatsapp), getResources().getString(R.string.snapchat), getResources().getString(R.string.googleDuo), getResources().getString(R.string.tumblr)};
        for (int i = 0; i < socialIcons.length && i < socialTitles.length; i++) {
            DrawerRowDataModel current = new DrawerRowDataModel();
            current.iconId = socialIcons[i];
            current.title = socialTitles[i];
            DrawerData.add(current);
        }
        return DrawerData;
    }


//

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
            builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    System.exit(0);
                }
            });
            DialogInterface.OnClickListener negativeDialogButton = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            };
            builder.setNegativeButton(getResources().getString(R.string.cancel), negativeDialogButton);

            builder.setTitle(getResources().getString(R.string.mainCloseDialogTitle));
            builder.setMessage(getResources().getString(R.string.mainCloseDialogMessage));
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
    //-----------------------------------------------------------Design Icons in Toolbar----------------------------------------------------


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_info:
                startActivity(new Intent(getApplicationContext(), ClusterInfoActivity.class));
                break;
            case R.id.action_my_profile:
                startActivity(new Intent(getApplicationContext(), ClusterInfoActivity.class));
                break;
            case R.id.action_search:
                startActivity(new Intent(getApplicationContext(), MainSearchActivity.class));
                break;
            case R.id.action_filter:
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFAWItemClick(int itemId) {

    }

    //-----------------------------------------------------------Design Icons in Toolbar----------------------------------------------------


    //Drawer OnItemTouchListenerClass
    class DrawerRecyclerTouchListener implements RecyclerView.OnItemTouchListener {


        //NavigationDrawer Gesture Detector
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        //Constructor
        private DrawerRecyclerTouchListener(Context context, final ClickListener clickListener) {
            this.clickListener = clickListener;
            //Gesture Detector
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            //

        }
        //


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = drawerOptionRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
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

    //ClickListener interface
    public interface ClickListener {
        void onClick(View view, int position);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (android.os.Build.VERSION.SDK_INT > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        tabLayout.removeOnTabSelectedListener(tabListener);
        mainViewPager.removeOnPageChangeListener(pageChangeListener);
        mainViewPager.removeOnPageChangeListener(onTabChangeListener);
        drawerOptionRecyclerView.removeOnItemTouchListener(drawerRecyclerTouchListener);
        fab.setOnClickListener(null);
        drawerServiceRecyclerView.removeOnItemTouchListener(serviceListener);

    }


    private void openSlider() {
        if (!filterOn) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        } else {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }

    }


    private void setFilterRows() {
        if (!loginPref.getString("facebook", "").equals("")) {
            findViewById(R.id.facebookRow).setVisibility(View.VISIBLE);
            accessFacebook = true;
        }

        if (!loginPref.getString("skype", "").equals("")) {
            findViewById(R.id.skypeRow).setVisibility(View.VISIBLE);
            accessSkype = true;
        }

        if (!loginPref.getString("twitter", "").equals("")) {
            findViewById(R.id.twitterRow).setVisibility(View.VISIBLE);
            accessTwitter = true;
        }

        if (!loginPref.getString("tumblr", "").equals("")) {
            findViewById(R.id.tumblrRow).setVisibility(View.VISIBLE);
            accessTumblr = true;
        }
    }

    private void addX(boolean b) {

        if (b) {
            openButton.setImageResource(R.drawable.ic_clear_white);
        } else {
            openButton.setImageResource(R.drawable.arrow_up_white);
        }
    }

    private void initializeViews(){
        openButton = (ImageButton) findViewById(R.id.open_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mainViewPager = (ViewPager) findViewById(R.id.mainPager);
        tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        bottomSheetBehavior = BottomSheetBehavior.from((LinearLayout) findViewById(R.id.bottom_sheet));
        faw = (FloatingActionWheel) findViewById(R.id.faw);
        drawerOptionRecyclerView = (RecyclerView) findViewById(R.id.drawer_options_recycler_view);
        drawerServiceRecyclerView = (RecyclerView) findViewById(R.id.drawer_service_recycler_view);
        tumblrSwitch = (Switch) findViewById(R.id.tumblrSwitch);
        twitterSwitch = (Switch) findViewById(R.id.twitterSwitch);
        skypeSwitch = (Switch) findViewById(R.id.skypeSwitch);
        facebookSwitch = (Switch) findViewById(R.id.facebookSwitch);
        Switch filterSwitch = (Switch) findViewById(R.id.filter_switch);
        rootLayoutGroup = (ViewGroup) findViewById(R.id.main_view_root);
        filterOn = filterSwitch.isChecked();
        filterSwitch.setOnCheckedChangeListener(filterPowerListener);
    }

    private void checkIfEnabled() {
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

}


