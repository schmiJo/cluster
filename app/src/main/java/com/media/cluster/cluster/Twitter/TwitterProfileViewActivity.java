package com.media.cluster.cluster.Twitter;



import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;

public class TwitterProfileViewActivity extends AppCompatActivity {

    ImageView titlePic;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    TabLayout tabLayout;
    ImageView profilePic;
    View toolbarLayout, toolbarMessageLayout;
    TextView toolbarUserName, toolbarUserMessage;
    Toolbar toolbar;
    ViewPager viewPager;
    int amountTweets, amountPhotos, amountLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_profile_view);

        initializeViews();
        amountLikes = 123;
        amountPhotos = 76;
        amountTweets = 43;

        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setPalette();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(" ");
                    toolbarMessageLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slow));
                    toolbarMessageLayout.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    toolbarMessageLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_slow));
                    toolbarMessageLayout.setVisibility(View.INVISIBLE);
                    isShow = false;
                }
            }
        });


        //Tab Layout

        viewPager.setAdapter(new TwitterProfileViewPagerAdapter(getSupportFragmentManager()));

        final TabLayout.Tab tweets = tabLayout.newTab().setText(getResources().getString(R.string.Tweets));
        final TabLayout.Tab photos = tabLayout.newTab().setText(getResources().getString(R.string.Media));
        final TabLayout.Tab likes = tabLayout.newTab().setText(getResources().getString(R.string.Likes));

        tabLayout.addTab(tweets);
        tabLayout.addTab(photos);
        tabLayout.addTab(likes);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        toolbarUserMessage.setText(amountTweets + " " + getResources().getString(R.string.twitterTweet));
                        break;
                    case 1:
                        toolbarUserMessage.setText(amountPhotos + " " + getResources().getString(R.string.Media));
                        break;
                    case 2:
                        toolbarUserMessage.setText(amountLikes + " " + getResources().getString(R.string.Likes));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.equals(tweets)) {
                    viewPager.setCurrentItem(0);

                } else if (tab.equals(photos)) {
                    viewPager.setCurrentItem(1);

                } else if (tab.equals(likes)) {
                    viewPager.setCurrentItem(2);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.twitter_profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.twitter_profile_menu_share:
                break;
            case R.id.twitter_profile_menu_mute:
                break;
            case R.id.private_chat_block:
                break;
            case R.id.twitter_profile_menu_report:
                break;
            case android.R.id.home:
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setPalette() {
        int primary = Color.parseColor("#1DA1F2");
        int primaryDark = Color.parseColor("#657686");
        collapsingToolbarLayout.setContentScrimColor(primary);
        collapsingToolbarLayout.setStatusBarScrimColor(Color.parseColor("#FF000000"));
        toolbarLayout.setBackgroundColor(primary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(primaryDark);
        }
        tabLayout.setBackgroundColor(primary);
    }


    private void initializeViews() {
        toolbarLayout = findViewById(R.id.twitter_profile_actionbar_layout);
        profilePic = (ImageView) findViewById(R.id.twitter_profile_profile_pic);
        appBarLayout = (AppBarLayout) findViewById(R.id.twitter_profile_appBarLayout);
        titlePic = (ImageView) findViewById(R.id.twitter_profile_title_pic);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.twitter_profile_collapsing);
        toolbar = (Toolbar) findViewById(R.id.twitter_profile_actionbar);
        tabLayout = (TabLayout) findViewById(R.id.twitter_profile_tab_layout);
        toolbarMessageLayout = findViewById(R.id.twitter_profile_toolbar_layout);
        toolbarUserName = (TextView) findViewById(R.id.twitter_profile_toolbar_name);
        toolbarUserMessage = (TextView) findViewById(R.id.twitter_profile_toolbar_message);
        viewPager = (ViewPager) findViewById(R.id.twitter_profile_view_pager);
    }
}
