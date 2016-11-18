package com.media.cluster.cluster.Main.Fab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.Main.DrawerLayoutManager;
import com.media.cluster.cluster.Main.MainActivity;
import com.media.cluster.cluster.Twitter.PostTwitterActivity;
import com.media.cluster.cluster.R;

import java.util.ArrayList;
import java.util.List;

public class FabActivity extends AppCompatActivity {
    FabRowAdapter fabRowAdapter;
    RecyclerView fabRecyclerView;
    static View background;
    ViewGroup backgroundGroup;
    FloatingActionButton fab;
    private int tab;
    public static boolean facebook;
    public static boolean tumblr;
    public static boolean twitter;
    public static boolean skype;
    private boolean firstlayer = true;




    public FabActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_fab);

        SharedPreferences loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        String clustername = loginPref.getString("clustername","");
        final String facebookPref = loginPref.getString("facebook", "");
        final String twitterPref = loginPref.getString("twitter", "");
        final String tumblrPref = loginPref.getString("tumblr", "");
        final String skypePref = loginPref.getString("skype", "");
        boolean mediaSet = loginPref.getBoolean("mediaSet",false);
        fab = (FloatingActionButton) findViewById(R.id.fabClose);
        fab.setRotation(45);
        background = findViewById(R.id.activity_fab_background);
        fabRecyclerView = (RecyclerView) findViewById(R.id.fab_recycler_view);
        Intent i = new Intent(getIntent());
        tab = i.getIntExtra("tab", 0);
        switch (tab) {
            case 0:
                fabRowAdapter = new FabRowAdapter(getApplicationContext(), getFabDataRecommended());
                break;
            case 1:
                fabRowAdapter = new FabRowAdapter(getApplicationContext(), getFabDataFeed());
                break;
            case 2:
                fabRowAdapter = new FabRowAdapter(getApplicationContext(), getFabDataChats());
                break;
            case 3:
                fabRowAdapter = new FabRowAdapter(getApplicationContext(), getFabDataProfile());
                break;

        }

        if(mediaSet){
            try {
                facebook = !facebookPref.equals("");
                twitter = !twitterPref.equals("");
                skype = !skypePref.equals("");
                tumblr = !tumblrPref.equals("");
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }else{
            if(!clustername.equals("")) {
                GetUserData.getAddedServices(getApplicationContext(), clustername, false);
            }else{
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        }
        fabRecyclerView.setAdapter(fabRowAdapter);
        fabRecyclerView.setLayoutManager(new DrawerLayoutManager(getApplicationContext()));

        fabRecyclerView.addOnItemTouchListener(new FabRecyclerTouchListener(getApplicationContext(), new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(firstlayer){
                switch (tab) {
                    case 0:
                        switch (position) {
                            case 0:
                                //Scanner
                                fabRowAdapter.setIcon(R.drawable.fab_ic_scanner_selected,view);
                                break;
                            case 1:
                                //Story
                                fabRowAdapter.setIcon(R.drawable.fab_ic_story_selected,view);
                                break;
                            case 2:
                                //Activity
                                fabRowAdapter.setIcon(R.drawable.fab_ic_activity_selected,view);

                                break;

                        }
                        break;
                    case 1:
                        switch (position) {
                            case 0:
                                //Scanner
                                fabRowAdapter.setIcon(R.drawable.fab_ic_scanner_selected, view);
                                break;
                            case 1:
                                //Filter
                                fabRowAdapter.setIcon(R.drawable.fab_ic_filter_selected, view);
                                break;
                            case 2:
                                fabRowAdapter.setIcon(R.drawable.fab_ic_story_selected, view);
                                //Story
                                break;
                            case 3:
                                //Post
                                firstlayer = false;
                                fabRowAdapter.setIcon(R.drawable.fab_ic_upload_selected, view);
                                postSelect();

                                break;
                        }
                        break;
                    case 2:
                        switch (position) {
                            case 0:
                                //Scanner
                                fabRowAdapter.setIcon(R.drawable.fab_ic_scanner_selected, view);
                                break;
                            case 1:
                                //Contacts
                                fabRowAdapter.setIcon(R.drawable.fab_ic_contacts_selected, view);
                                break;

                        }
                        break;
                    case 3:
                        switch (position) {
                            case 0:
                                //Scanner
                                fabRowAdapter.setIcon(R.drawable.fab_ic_scanner_selected, view);
                                break;
                            case 1:
                                //Contacts
                                fabRowAdapter.setIcon(R.drawable.fab_ic_contacts_selected, view);
                                break;
                            case 2:
                                //Story
                                fabRowAdapter.setIcon(R.drawable.fab_ic_story_selected, view);
                                break;
                            case 3:
                                //Post
                                firstlayer = false;
                                fabRowAdapter.setIcon(R.drawable.fab_ic_upload_selected, view);
                                postSelect();
                        }
                        break;
                }

            }}
        }));
        backgroundGroup = (ViewGroup) background;
        MainActivity.setFabBlur(getResources());
        Animation slideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
        fabRecyclerView.startAnimation(slideIn);


    }

    public List<FabRowModel> getFabDataRecommended() {
        List<FabRowModel> fabData = new ArrayList<>();
        int[] icons = {
                R.drawable.fab_ic_scanner,
                R.drawable.fab_ic_story,
                R.drawable.fab_ic_activity
        };
        String[] titles = { getResources().getString(R.string.fabScanner),getResources().getString(R.string.fabStory), getResources().getString(R.string.fabActivity)};

        for (int i = 0; i < icons.length && i < titles.length; i++) {
            FabRowModel current = new FabRowModel();
            current.iconId = icons[i];
            current.title = titles[i];
            fabData.add(current);
        }

        return fabData;
    }

    public List<FabRowModel> getFabDataFeed() {
        List<FabRowModel> fabData = new ArrayList<>();
        int[] icons = {
                R.drawable.fab_ic_scanner,
                R.drawable.fab_ic_filter,
                R.drawable.fab_ic_story,
                R.drawable.fab_ic_upload
        };
        String[] titles =  {getResources().getString(R.string.fabScanner),getResources().getString(R.string.fabFilter), getResources().getString(R.string.fabStory), getResources().getString(R.string.fabPost)};

        for (int i = 0; i < icons.length && i < titles.length; i++) {
            FabRowModel current = new FabRowModel();
            current.iconId = icons[i];
            current.title = titles[i];
            fabData.add(current);
        }

        return fabData;
    }
    public List<FabRowModel> getFabDataMedia() {
        List<FabRowModel> fabData = new ArrayList<>();

        List<Integer> icons = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        if(facebook){
            icons.add(R.drawable.fab_ic_facebook);
            titles.add(getResources().getString(R.string.facebook));
        }

        if(twitter){
            icons.add(R.drawable.fab_ic_twitter);
            titles.add(getResources().getString(R.string.twitter));
        }

        if(tumblr){
            icons.add(R.drawable.fab_ic_tumblr);
            titles.add(getResources().getString(R.string.tumblr));
        }



        for (int i = 0; i < icons.size() && i < titles.size(); i++) {
            FabRowModel current = new FabRowModel();
            current.iconId = icons.get(i);
            current.title = titles.get(i);
            fabData.add(current);
        }

        return fabData;
    }

    public List<FabRowModel> getFabDataChats() {
        List<FabRowModel> fabData = new ArrayList<>();
        int[] icons = {

                R.drawable.fab_ic_scanner,
                R.drawable.fab_ic_contacts,

        };
        String[] titles = { getResources().getString(R.string.fabScanner), getResources().getString(R.string.fabContacts)};

        for (int i = 0; i < icons.length && i < titles.length; i++) {
            FabRowModel current = new FabRowModel();
            current.iconId = icons[i];
            current.title = titles[i];
            fabData.add(current);
        }

        return fabData;
    }

    public List<FabRowModel> getFabDataProfile() {
        List<FabRowModel> fabData = new ArrayList<>();
        int[] icons = {

                R.drawable.fab_ic_scanner,
                R.drawable.fab_ic_contacts,
                R.drawable.fab_ic_story,
                R.drawable.fab_ic_upload
        };
        String[] titles = { getResources().getString(R.string.fabScanner), getResources().getString(R.string.fabContacts),getResources().getString(R.string.fabStory), getResources().getString(R.string.fabPost)};

        for (int i = 0; i < icons.length && i < titles.length; i++) {
            FabRowModel current = new FabRowModel();
            current.iconId = icons[i];
            current.title = titles[i];
            fabData.add(current);
        }

        return fabData;
    }

    public void onBackClicked(View view) {
        closeFab();
    }

    public void closeFab(){
        new MainActivity().closeFab(getApplicationContext());
        fab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotation_reverse));
        fabRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_bottom));
        finish();
        overridePendingTransition(0, R.anim.fade_out_slow);
    }


    public static void setBackground(BitmapDrawable bitmapDrawable) {
        background.setBackground(bitmapDrawable);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        finish();
        overridePendingTransition(0, R.anim.fade_out_slow);
    }

    class FabRecyclerTouchListener implements RecyclerView.OnItemTouchListener {


        //NavigationDarwer Gesture Detector
        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        //Constructor
        private FabRecyclerTouchListener(Context context, final MainActivity.ClickListener clickListener) {
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
            View child = fabRecyclerView.findChildViewUnder(e.getX(), e.getY());
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

    public void postSelect(){
        fabRecyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_slow));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final FabRowAdapter fabRowAdapter = new FabRowAdapter(getApplicationContext(), getFabDataMedia());
                fabRecyclerView.setAdapter(fabRowAdapter);
                fabRecyclerView.setLayoutManager(new DrawerLayoutManager(getApplicationContext()));
                fabRecyclerView.addOnItemTouchListener(new FabRecyclerTouchListener(getApplicationContext(), new MainActivity.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                            switch (fabRowAdapter.setServiceIcon(view)){
                                case "facebook":
                                    break;
                                case "twitter":
                                    closeFab();
                                    startActivity(new Intent(getApplicationContext(), PostTwitterActivity.class));

                                    break;
                                case "tumblr":
                                    break;
                            }
                    }
                }));
            }
        },300);



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        closeFab();
        return super.onKeyDown(keyCode, event);

    }
}
